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
  { path: '/', name: 'home', component: HomePage },
  { path: '/about-franchising', name: 'about-franchising', component: AboutFranchising },
  { path: '/risk-assessment', name: 'risk-assessment', component: RiskAssessment },
  { path: '/check-franchisor', name: 'check-franchisor', component: CheckFranchisor },
  { path: '/results', name: 'results', component: ResultsPage, props: true },
  { path: '/non-contractual-risks', name: 'non-contractual-risks', component: NonContractualRisks },
  { path: '/critical-risks', name: 'critical-risks', component: CriticalRisks },
  {
    path: '/memo/:file',
    name: 'memo',
    component: MemoPage,
    props: (route) => ({ memoFile: route.params.file })
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;