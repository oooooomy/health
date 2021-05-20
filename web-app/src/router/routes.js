import Layout from '@/layout/Index'

const routes = [

    //公共布局下的路由
    {
        path: '/',
        component: Layout,
        children: [
            //user
            {
                path: "",
                redirect: "/user",
                meta: {auth: true}
            },
            //user
            {
                path: 'user',
                component: () => import('../views/User'),
                meta: {auth: true}
            },
            //admin
            {
                path: 'admin',
                component: () => import('../views/Admin'),
                meta: {auth: true}
            },
            //403
            {
                path: '403',
                component: () => import('@/views/error/403'),
                meta: {auth: true}
            },
            //404
            {
                path: '404',
                component: () => import('@/views/error/404'),
                meta: {auth: true}
            },
            //500
            {
                path: '500',
                component: () => import('@/views/error/500'),
                meta: {auth: true}
            },

        ]
    },

    //登录页
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login'),
        meta: {auth: false}
    },

    //404
    {
        path: "*",
        redirect: '/404'
    }

]

export default routes