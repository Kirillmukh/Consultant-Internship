import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import AboutFranchising from '../components/AboutFranchising.vue';
import RiskAssessment from '../components/RiskAssessment.vue';
import CheckFranchisor from '../components/CheckFranchisor.vue';
import ResultsPage from "../components/ResultsPage.vue";

const routes = [
  { path: '/', name: 'home', component: HomePage },
  { path: '/about-franchising', name: 'about-franchising', component: AboutFranchising },
  { path: '/risk-assessment', name: 'risk-assessment', component: RiskAssessment },
  { path: '/check-franchisor', name: 'check-franchisor', component: CheckFranchisor },
  { path: '/results', name: 'results', component: ResultsPage, props: true },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;