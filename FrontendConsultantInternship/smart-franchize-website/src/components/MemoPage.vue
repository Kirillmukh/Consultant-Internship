<template>
    <div class="memo-page" v-html="content"></div>
</template>

<script>
export default {
    props: {
        memoFile: {
            type: String,
            required: true, // Имя HTML-файла, который нужно загрузить
        },
    },
    data() {
        return {
            title: '', // Заголовок заметки
            content: '', // Содержимое заметки
        };
    },
    mounted() {
        this.loadMemo();
    },
    methods: {
        async loadMemo() {
            try {
                const response = await fetch(`/memos/${this.memoFile}`);
                if (!response.ok) {
                    throw new Error(`Ошибка загрузки файла: ${response.statusText}`);
                }
                const html = await response.text();
                this.content = html;

                // Если в HTML-файле есть заголовок, можно извлечь его
                const tempDiv = document.createElement('div');
                tempDiv.innerHTML = html;
                const h1 = tempDiv.querySelector('h1');
                if (h1) {
                    this.title = h1.textContent;
                } else {
                    this.title = 'Памятка';
                }
                document.title = this.title; // Устанавливаем заголовок страницы
            } catch (error) {
                console.error(error);
                this.content = '<p>Не удалось загрузить заметку.</p>';
            }
        },
    },
};
</script>

<style>
.memo-page {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
}

.memo-page h1 {
    text-align: center;
    font-family: 'Tektur';
    font-weight: 500;
    font-size: 30px;
}

.memo-page p,
.memo-page ul,
.memo-page ol {
    text-align: justify;
}

.memo-page ul,
.memo-page ol {
    margin-left: 20px;
}
</style>