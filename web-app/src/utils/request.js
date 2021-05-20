import request from "axios"
import {message} from 'ant-design-vue'
import router from '../router/index'

export const BASE_URL = 'http://localhost:8080/api'

const service = request.create({
    baseURL: BASE_URL,
    timeout: 50000
});

service.interceptors.response.use(
    response => {

        const res = response.data;

        //判断response状态
        if (!res.status) message.error('请求错误: ' + res.msg)

        if (res.code === 403) router.push("/403")

        return res
    },

    error => {
        message.error(error)
        router.push('/500')
        return Promise.reject(error)
    }
);

export default service