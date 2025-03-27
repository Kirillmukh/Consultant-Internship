import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import AboutFranchising from '../components/AboutFranchising.vue';
import RiskAssessment from '../components/RiskAssessment.vue';
import CheckFranchisor from '../components/CheckFranchisor.vue';

const routes = [
  { path: '/', name: 'home', component: HomePage },
  { path: '/about-franchising', name: 'about-franchising', component: AboutFranchising },
  { path: '/risk-assessment', name: 'risk-assessment', component: RiskAssessment },
  { path: '/check-franchisor', name: 'check-franchisor', component: CheckFranchisor },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;