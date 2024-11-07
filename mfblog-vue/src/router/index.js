import { createRouter, createWebHistory } from 'vue-router'



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'mfBlog',
      component: () => import('../views/index.vue'),
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('../views/backstage/index.vue'),
    }
  ]
})


router.beforeEach((to, from, next) => {
  if (isUserLoggedIn()) {
    next(); // 如果用户已登录，允许访问任何页面
  } else {
    next(); // 即使未登录，也允许访问，将在页面中检查登录状态
  }
});

function isUserLoggedIn() {
  // 检查cookie或localStorage中的token
  return !!getCookie('JSESSIONID'); // 假设JSESSIONID是存储session ID的cookie
}

function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}


export default router;
