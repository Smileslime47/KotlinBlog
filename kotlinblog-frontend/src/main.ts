import { createApp } from "vue";
import App from "./App.vue";
import 'element-plus/theme-chalk/dark/css-vars.css';
import "~/styles/index.scss";
import "uno.css";
import "element-plus/theme-chalk/src/message.scss";
import router from "~/router/router";


const app = createApp(App);
app.use(router)
app.mount("#app");
