import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import MainBoard from '../views/MainBoard.vue'

// 配置路由规则
const routes = [
  // 重定向
  {
    path: '',
    redirect: "/login"
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/mainboard',
    name: 'mainboard',
    component: MainBoard,
    redirect: '/mainboard/messager',  // 设置默认重定向到 message 组件
    children: [
      {
        path: 'message',  
        name: 'message',
        components: {
          default: () => import('@/views/mainboard/Message.vue'),
          right: () => import('@/views/tabs/DefaultIndex.vue')
        },
        children: [
          {
            path: 'messageDetails',
            name: 'messageDetails',
            components: {
              right: () => import('@/views/tabs/MessageDetails.vue')
            }
          },
        ]
      },
      {
        path: 'messager',  // 保留 messager 路径
        name: 'messager',
        components: {
          default: () => import('@/views/mainboard/Messager.vue'),
          right: () => import('@/views/tabs/DefaultIndex.vue')
        },
        children: [
          {
            path: 'userinfo',
            name: 'messager-userinfo',
            components: {
              right: () => import('@/views/tabs/UserInfoDetail.vue')
            }
          },
        ]
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes
})

export default router
