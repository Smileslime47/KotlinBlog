import axios from "axios";
import Constant from "~/Constant";

const httpService = axios.create({
    baseURL: Constant.BASE_URL, //基础公共URL
    timeout: 1000,              //超时配置
    withCredentials: false,      //跨域携带cookie
})

httpService.interceptors.request.use(
    //发生请求拦截
    (config) => {
        // 如果开启 token 认证
        // if (serverConfig.useTokenAuthorization) {
        //     config.headers["Authorization"] = localStorage.getItem("token"); // 请求头携带 token
        // }
        // 设置请求头
        if (!config.headers["content-type"]) { // 如果没有设置请求头
            if (config.method === 'post') {
                config.headers["content-type"] = "application/x-www-form-urlencoded"; // post 请求
                // config.data = qs.stringify(config.data); // 序列化,比如表单数据
            } else {
                config.headers["content-type"] = "application/json"; // 默认类型
            }
        }
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
        // 2xx 范围内的状态码都会触发该函数。
        // 对响应数据做点什么
        return response;
    },
    (error) => {
        // 超出 2xx 范围的状态码都会触发该函数。
        // 对响应错误做点什么
        return Promise.reject(error);
    }
);

export default httpService