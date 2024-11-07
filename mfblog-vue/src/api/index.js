// src/api/index.js
import axios from 'axios';

// 创建axios实例
const service = axios.create({
    baseURL: 'http://localhost:8881/', // 后端服务的URL和端口
    timeout: 5000, // 请求超时时间
    withCredentials: true // 确保跨域请求时携带cookie
});

// 请求拦截器
service.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么，例如设置token
        return config;
    },
    error => {
        // 处理请求错误
        return Promise.reject(error);
    }
);

// 响应拦截器
service.interceptors.response.use(
    response => {
        // 任何HTTP状态码为2xx的响应数据都会触发此函数
        return response.data;
    },
    error => {
        // 任何超出2xx的HTTP状态码都会触发此函数
        console.error('err' + error); // 错误处理
        return Promise.reject(error);
    }
);

export default service;