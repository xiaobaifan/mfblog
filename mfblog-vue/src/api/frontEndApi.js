import axios from 'axios';
import service from './index.js';

// const baseURLs = 'http://127.0.0.1:8881';




const userApi = {
    login: async ({ username, password }) => {
        try {
            // 使用service实例发送POST请求
            const response = await service.post('/login', { username, password });
            return response;
        } catch (error) {
            // 抛出错误，可以在调用API的地方进行错误处理
            throw error;
        }
    },
    register: async (userData) => {
        try {
            // 使用service实例发送POST请求
            // 确保baseURL已经在service实例中设置
            const response = await service.post('/register', userData);
            return response.data;
        } catch (error) {
            // 抛出错误，可以在调用API的地方进行错误处理
            throw error;
        }
    },
    saveUserInfo: async (userData) => {
        try {
            // 使用service实例发送POST请求
            // 确保baseURL已经在service实例中设置
            const response = await service.post('/saveUserInfo', userData);
            return response.data;
        } catch (error) {
            // 抛出错误，可以在调用API的地方进行错误处理
            throw error;
        }
    }
};

export default userApi;
