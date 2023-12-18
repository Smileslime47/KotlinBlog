import {RouteRecordRaw} from "vue-router";

export const routes: RouteRecordRaw[] = [
    {path: '/', redirect: 'Home'},
    {path: '/home', name: 'Home', component: () => import('~/page/Homepage.vue')},
    {path: '/about', name: 'About', component: () => import('~/page/Aboutpage.vue')},
    {path: '/login', name: 'Login', component: () => import('~/page/Loginpage.vue')},
    {path: "/category/:cid/", name: "category", component: () => import('~/page/Archive.vue')},
    {path: "/article/:cid/:aid", name: "article", component: () => import('~/page/Article.vue')},
    {path: '/loading', name: 'Loading', component: () => import('~/page/EmptyPage.vue')},
]

// @ts-ignore
export const getPathParam = (param) => useRoute().params[param]