// Глобальные переменные
    let currentFileType = null; // "pdf" или "doc"
    let pagesData = {};          // для PDF: хранит данные страниц
    let riskResults = [];        // данные, полученные от backend (/upload)
    let docParagraphs = [];      // для doc: массив абзацев (HTML elements)

    // Загружаем список рисковых предложений из файла risks.txt
    let riskList = [];
    fetch('risks.txt')
      .then(response => response.text())
      .then(text => {
        riskList = text.split('\n').map(line => line.trim()).filter(line => line);
        renderRiskList();
      })
      .catch(err => console.error("Ошибка загрузки risks.txt:", err));

    // Формируем список рисков в левой колонке
    function renderRiskList() {
      const container = document.getElementById('risk-container');
      container.innerHTML = '';
      riskList.forEach((risk, index) => {
        const div = document.createElement('div');
        div.classList.add('risk-item');
        div.innerHTML = `<strong>${risk}</strong><br>
          <button data-risk-index="${index}">Перейти к абзацу</button>
          <div class="confidence"></div>`;
        container.appendChild(div);
      });
    }

    // Обработчик клика по кнопке риска
    document.getElementById('risk-container').addEventListener('click', (e) => {
      if(e.target.tagName.toLowerCase() === 'button'){
        const riskIndex = e.target.getAttribute('data-risk-index');
        jumpToRisk(riskIndex);
      }
    });

    // Обработчик загрузки файла
    document.getElementById('file-input').addEventListener('change', (e) => {
      const file = e.target.files[0];
      if (!file) return;

      // Определяем тип файла
      if(file.type === "application/pdf" || file.name.toLowerCase().endsWith('.pdf')) {
        currentFileType = "pdf";
        document.getElementById('pdf-container').style.display = "block";
        document.getElementById('doc-content').style.display = "none";
        loadPDF(file);
      } else if(file.name.toLowerCase().endsWith('.doc') || file.name.toLowerCase().endsWith('.docx')) {
        currentFileType = "doc";
        document.getElementById('doc-content').style.display = "block";
        document.getElementById('pdf-container').style.display = "none";
        loadDoc(file);
      } else {
        alert("Неподдерживаемый формат файла.");
      }

      // Отправляем файл на backend для получения risk результатов
      uploadFile(file);
    });

    // Функция отправки файла на backend (/upload)
    function uploadFile(file) {
      const formData = new FormData();
      formData.append('file', file);
      fetch('/upload', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(data => {
        riskResults = data.results || [];
        // Обновляем UI для каждого риска (например, добавляем уровень уверенности)
        updateRiskConfidence();
      })
      .catch(err => console.error("Ошибка отправки файла:", err));
    }

    function updateRiskConfidence() {
      const riskItems = document.querySelectorAll('.risk-item');
      riskItems.forEach((item, index) => {
        if(riskResults[index]) {
          const confDiv = item.querySelector('.confidence');
          confDiv.textContent = "Уверенность: " + riskResults[index].confidence.toFixed(2);
        }
      });
    }

    /* ===== PDF обработка (на основе предоставленного кода) ===== */
    function loadPDF(file) {
      // Очищаем контейнер и данные
      const pdfContainer = document.getElementById('pdf-container');
      pdfContainer.innerHTML = '';
      pagesData = {};

      const fileReader = new FileReader();
      fileReader.onload = function () {
        const typedarray = new Uint8Array(this.result);
        pdfjsLib.getDocument(typedarray).promise
          .then(pdf => {
            renderAllPages(pdf);
          })
          .catch(error => {
            console.error("Ошибка загрузки PDF:", error);
          });
      };
      fileReader.readAsArrayBuffer(file);
    }

    async function renderAllPages(pdf) {
      const pdfContainer = document.getElementById('pdf-container');
      pdfContainer.innerHTML = '';
      pagesData = {};

      for (let pageNum = 1; pageNum <= pdf.numPages; pageNum++) {
        const page = await pdf.getPage(pageNum);
        const scale = 1.5;
        const viewport = page.getViewport({ scale });
        const canvas = document.createElement('canvas');
        canvas.classList.add('pdf-page');
        canvas.width = viewport.width;
        canvas.height = viewport.height;
        canvas.setAttribute('data-page-number', pageNum);
        pdfContainer.appendChild(canvas);
        const ctx = canvas.getContext('2d');

        const renderContext = { canvasContext: ctx, viewport: viewport };
        await page.render(renderContext).promise;

        // Получаем текст страницы для поиска
        const textContent = await page.getTextContent();
        const pageText = textContent.items.map(item => item.str).join(' ');

        pagesData[pageNum] = {
          text: pageText,
          canvas: canvas,
          items: textContent.items,
          viewport: viewport
        };
      }
      console.log("Все страницы PDF загружены.");
    }

    /* ===== DOC/DOCX обработка с использованием Mammoth ===== */
    function loadDoc(file) {
      const reader = new FileReader();
      reader.onload = function(event) {
        const arrayBuffer = event.target.result;
        mammoth.convertToHtml({arrayBuffer: arrayBuffer})
          .then(function(resultObject) {
            // Выводим HTML в контейнер doc-content
            const docContent = document.getElementById('doc-content');
            docContent.innerHTML = resultObject.value;
            // Разбиваем на абзацы и добавляем data-атрибуты для навигации
            docParagraphs = Array.from(docContent.getElementsByTagName('p'));
            docParagraphs.forEach((p, idx) => {
              p.setAttribute('data-index', idx);
            });
          })
          .catch(function(err) {
            console.error("Ошибка преобразования doc/docx:", err);
          });
      };
      reader.readAsArrayBuffer(file);
    }

    /* ===== Функция перехода к рисковому фрагменту ===== */
    function jumpToRisk(riskIndex) {
      if(!riskResults[riskIndex]){
        alert("Для этого риска не найден подходящий абзац.");
        return;
      }
      const result = riskResults[riskIndex];
      const matchingParagraph = result.matching_paragraph;

      if(currentFileType === "pdf") {
        // Для PDF пробегаем по страницам и ищем фрагмент текста (по части текста, например первые 30 символов)
        const snippet = matchingParagraph.substring(0, 30);
        let found = false;
        for (const [pageNum, pageData] of Object.entries(pagesData)) {
          if (pageData.text.includes(snippet)) {
            // Скроллим к найденной странице
            pageData.canvas.scrollIntoView({ behavior: 'smooth', block: 'center' });
            // Дополнительно можно вызвать функцию выделения найденного фрагмента (например, highlightWordOnPage)
            found = true;
            break;
          }
        }
        if(!found) {
          alert("Не удалось найти абзац в PDF.");
        }
      } else if(currentFileType === "doc") {
        // Для doc/docx ищем абзац в отрендеренном HTML
        let foundIndex = null;
        for (let i = 0; i < docParagraphs.length; i++) {
          if(docParagraphs[i].textContent.includes(matchingParagraph.substring(0, 30))) {
            foundIndex = i;
            break;
          }
        }
        if(foundIndex !== null) {
          const pElem = docParagraphs[foundIndex];
          pElem.scrollIntoView({ behavior: 'smooth', block: 'center' });
          // Выделяем абзац временно
          pElem.classList.add('highlight');
          setTimeout(() => { pElem.classList.remove('highlight'); }, 2000);
        } else {
          alert("Не удалось найти абзац в документе.");
        }
      }
    }