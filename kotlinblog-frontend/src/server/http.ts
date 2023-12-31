import axios from "axios";
import Constant from "~/constant/Constant";
import routeTo from "~/router/routeTo";
import token from "~/composables/token";

const httpService = axios.create({
    baseURL: Constant.BASE_URL, //基础公共URL
    timeout: 10000,              //超时配置
    withCredentials: false,      //跨域携带cookie
})

httpService.interceptors.request.use(
    //发生请求拦截
    (config) => {
        // 如果开启 token 认证
        config.headers["Authorization"] = localStorage.getItem("jwtToken"); // 请求头携带 token

        // // 设置请求头
        // if (!config.headers["content-type"]) { // 如果没有设置请求头
        //     if (config.method === 'post') {
        //         config.headers["content-type"] = "application/json"; // 默认类型
        //         // config.data = qs.stringify(config.data); // 序列化,比如表单数据
        //     } else {
        //         config.headers["content-type"] = "application/x-www-form-urlencoded"; // post 请求
        //     }
        // }
        return config;
    },
    //请求错误拦截
    (error) => {
        console.log("Test")
        Promise.reject(error)
    }
);

httpService.interceptors.response.use(
    (response) => {
        if(response.data.code!=200){
            console.log(response)
            ElMessage.error("エロ発生："+response.data.code+(response.data.msg!=null?","+response.data.msg:""))
            if(response.data.code==401){
                routeTo.login();
            }
            if(response.data.code==505){
                token.removeToken();
            }
        }
        return response.data;
    },
    (error) => {
        // 超出 2xx 范围的状态码都会触发该函数。
        if (error.response.status === 401) {
            ElMessage.error("Http异常：" + error.response.status + ",请登录")
            routeTo.login();
        } else {
            ElMessage.error("Http异常：" + error.response.status + "," + error.response.statusText)
        }
        return Promise.reject(error);
    }
);

export default httpService