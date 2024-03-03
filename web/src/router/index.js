import { createRouter, createWebHistory } from 'vue-router'


// 页面路由配置
const routes = [{
  path: '/login',
  component: () => import('../views/LoginView.vue')
}, {
  path: '/',
  component: () => import('../views/MainView.vue'),
  meta: {
    loginRequire: true
  },
  children: [{
    path: 'welcome',
    component: () => import('../views/main/the-welcome.vue'),
  }
  // , {
  //   path: 'passenger',
  //   component: () => import('../views/main/passenger.vue'),
  // }, {
  //   path: 'ticket',
  //   component: () => import('../views/main/ticket.vue'),
  // }, {
  //   path: 'order',
  //   component: () => import('../views/main/order.vue'),
  // }
  ]
}, {
  path: '',
  redirect: '/welcome'
}];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
