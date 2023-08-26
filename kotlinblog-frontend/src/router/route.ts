import {RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    { path: '/',  redirect:'Home'},
    { path: '/Home', name: 'Home', component: () => import('~/page/Homepage.vue') },
]


export default routes