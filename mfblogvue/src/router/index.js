import { createRouter, createWebHashHistory } from "vue-router";

// 制定路由规则
const routes = [
    {
        path: '/',
        name: 'main',
        component: () => import("@/views/Main.vue"),
    },
    {
        path: '/home',
        name: 'home',
        component: () => import("@/views/home/Home.vue"),
    }
];

/**创建路由 */
const router = createRouter({
    // 设置路由模式 
    history: createWebHashHistory(),
    routes,
});

export default router;