import {RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    {path: '/', redirect: 'Home'},
    {path: '/home', name: 'Home', component: () => import('~/page/Homepage.vue')},
    {path: '/about', name: 'About', component: () => import('~/page/Aboutpage.vue')},
    {path: "/category/:cid/", name: "category", component: () => import('~/page/Archive.vue')},
    {path: "/article/:cid/:aid", name: "article", component: () => import('~/page/Article.vue')}
]

export default routes