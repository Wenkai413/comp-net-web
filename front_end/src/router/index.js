import Vue from 'vue'
import VueRouter from 'vue-router'

const Home = () => import('../views/home/Home')
const Category = () => import('../views/category/Category')
const Profile = () => import('../views/profile/Profile')
const Login = () => import('../views/login/Login')
const SignUp = () => import('../views/signUp/SignUp')
const Blog = () => import('../views/blog/Blog')

// 1.安装组件
Vue.use(VueRouter)

// 2.创建路由对象
const routes = [
  {
    path: '',
    redirect: '/login'
  },
  {
    path: '/home',
    component: Home
  },
  {
    path: '/category',
    component: Category
  },
  {
    path: '/profile',
    component: Profile
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/signUp',
    component: SignUp
  },
  {
    path: '/blog/:title',
    component: Blog
  }
]
const router = new VueRouter({
  routes,
  mode: 'history'
})

// 3.导出router
export default router
