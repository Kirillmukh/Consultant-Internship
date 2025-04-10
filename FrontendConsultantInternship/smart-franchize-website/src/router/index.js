import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import AboutFranchising from '../components/AboutFranchising.vue';
import RiskAssessment from '../components/RiskAssessment.vue';
import CheckFranchisor from '../components/CheckFranchisor.vue';
import ResultsPage from "../components/ResultsPage.vue";
import NonContractualRisks from '@/components/NonContractualRisks.vue';
import CriticalRisks from '@/components/CriticalRisks.vue';
import MemoPage from '@/components/MemoPage.vue';

const routes = [
  { path: '/', name: 'home', component: HomePage, meta: { title: 'Главная - СмартФранчайз' } },
  { path: '/about-franchising', name: 'about-franchising', component: AboutFranchising, meta: { title: 'О франчайзинге' } },
  { path: '/risk-assessment', name: 'risk-assessment', component: RiskAssessment, meta: { title: 'Оценка рисков' } },
  { path: '/check-franchisor', name: 'check-franchisor', component: CheckFranchisor, meta: { title: 'Проверка франчайзера' } },
  { path: '/results', name: 'results', component: ResultsPage, props: true, meta: { title: 'Результаты' } },
  { path: '/memos', name: 'non-contractual-risks', component: NonContractualRisks, meta: { title: 'Памятки' } },
  { path: '/critical-risks', name: 'critical-risks', component: CriticalRisks, meta: { title: 'Критические риски' } },
  {
    path: '/memo/:file',
    name: 'memo',
    component: MemoPage,
    props: (route) => ({ memoFile: route.params.file }),
    meta: { title: 'Памятка' },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

export default router;