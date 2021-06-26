import Vue from 'vue'
import Vuex from 'vuex'

import mutations from "./mutations";
import actions from "./actions";
import getters from "./getters";
import moduleA from "./modules/moduleA";

// 1.安装插件
Vue.use(Vuex)

// 2.创建对象
const state = {
  writeShow: false,
  blogDropdownShow: false,
  blogDropdownIndex: 0,
  followBlogs: [
    {blogId: 1, user: '用户01', createDate:'2021-06-14' ,content: '我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈', collect:8, comments: 10, likes: 38},
    {blogId: 2, user: '用户02', createDate:'2021-06-13' ,content: '我是博客2的内容哈哈哈哈哈', collect:9, comments: 12, likes: 8},
    {blogId: 3, user: '用户03', createDate:'2021-05-14' ,content: '我是博客3的内容哈哈哈哈哈', collect:10, comments: 22, likes: 7},
    {blogId: 4, user: '用户04', createDate:'2021-06-16' ,content: '我是博客4的内容哈哈哈哈哈', collect:11, comments: 13, likes: 228},
    {blogId: 5, user: '用户05', createDate:'2021-06-17' ,content: '我是博客5的内容哈哈哈哈哈', collect:12, comments: 16, likes: 6},
    {blogId: 6, user: '用户06', createDate:'2021-06-14' ,content: '我是博客6的内容哈哈哈哈哈', collect:13, comments: 17, likes: 53},
    {blogId: 7, user: '用户07', createDate:'2021-06-18' ,content: '我是博客7的内容哈哈哈哈哈', collect:14, comments: 10, likes: 8},
    {blogId: 8, user: '用户08', createDate:'2021-06-21' ,content: '我是博客8的内容哈哈哈哈哈', collect:15, comments: 165, likes: 58},
    {blogId: 9, user: '用户09', createDate:'2021-07-23' ,content: '我是博客9的内容哈哈哈哈哈', collect:16, comments: 456, likes: 4},
    {blogId: 10, user: '用户10', createDate:'2021-06-09' ,content: '我是博客10的内容哈哈哈哈哈', collect:8, comments: 10, likes: 28},
    {blogId: 11, user: '用户11', createDate:'2021-06-01' ,content: '我是博客11的内容哈哈哈哈哈', collect:8, comments: 10, likes: 18},
  ],
  recommendBolgs: [
    {blogId: 1, user: '用户101', createDate:'2021-06-14' ,content: '我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈', collect:8, comments: 10, likes: 38},
    {blogId: 2, user: '用户102', createDate:'2021-06-13' ,content: '我是博客2的内容哈哈哈哈哈', collect:9, comments: 12, likes: 8},
    {blogId: 3, user: '用户103', createDate:'2021-05-14' ,content: '我是博客3的内容哈哈哈哈哈', collect:10, comments: 22, likes: 7},
    {blogId: 4, user: '用户104', createDate:'2021-06-16' ,content: '我是博客4的内容哈哈哈哈哈', collect:11, comments: 13, likes: 228},
    {blogId: 5, user: '用户105', createDate:'2021-06-17' ,content: '我是博客5的内容哈哈哈哈哈', collect:12, comments: 16, likes: 6},
    {blogId: 6, user: '用户106', createDate:'2021-06-14' ,content: '我是博客6的内容哈哈哈哈哈', collect:13, comments: 17, likes: 53},
    {blogId: 7, user: '用户107', createDate:'2021-06-18' ,content: '我是博客7的内容哈哈哈哈哈', collect:14, comments: 10, likes: 8},
    {blogId: 8, user: '用户108', createDate:'2021-06-21' ,content: '我是博客8的内容哈哈哈哈哈', collect:15, comments: 165, likes: 58},
    {blogId: 9, user: '用户109', createDate:'2021-07-23' ,content: '我是博客9的内容哈哈哈哈哈', collect:16, comments: 456, likes: 4},
    {blogId: 10,user: '用户110', createDate:'2021-06-09' ,content: '我是博客10的内容哈哈哈哈哈', collect:8, comments: 10, likes: 28},
    {blogId: 11,user: '用户111', createDate:'2021-06-01' ,content: '我是博客11的内容哈哈哈哈哈', collect:8, comments: 10, likes: 18},
  ],
  allMyBlogs: [
    {blogId: 1, user: '方头', createDate:'2021-06-14' ,content: '我就是方头的博客123哈哈哈~我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈我是博客1的内容哈哈哈哈哈', collect:8, comments: 10, likes: 38},
    {blogId: 2, user: '方头', createDate:'2021-06-13' ,content: '我就是方头的博客123哈哈哈~我是博客2的内容哈哈哈哈哈', collect:9, comments: 12, likes: 8},
    {blogId: 3, user: '方头', createDate:'2021-05-14' ,content: '我就是方头的博客123哈哈哈~我是博客3的内容哈哈哈哈哈', collect:10, comments: 22, likes: 7},
    {blogId: 4, user: '方头', createDate:'2021-06-16' ,content: '我就是方头的博客123哈哈哈~我是博客4的内容哈哈哈哈哈', collect:11, comments: 13, likes: 228},
    {blogId: 5, user: '方头', createDate:'2021-06-17' ,content: '我就是方头的博客123哈哈哈~我是博客5的内容哈哈哈哈哈', collect:12, comments: 16, likes: 6},
    {blogId: 6, user: '方头', createDate:'2021-06-14' ,content: '我就是方头的博客123哈哈哈~我是博客6的内容哈哈哈哈哈', collect:13, comments: 17, likes: 53},
    {blogId: 7, user: '方头', createDate:'2021-06-18' ,content: '我就是方头的博客123哈哈哈~我是博客7的内容哈哈哈哈哈', collect:14, comments: 10, likes: 8},
    {blogId: 8, user: '方头', createDate:'2021-06-21' ,content: '我就是方头的博客123哈哈哈~我是博客8的内容哈哈哈哈哈', collect:15, comments: 165, likes: 58},
    {blogId: 9, user: '方头', createDate:'2021-07-23' ,content: '我就是方头的博客123哈哈哈~我是博客9的内容哈哈哈哈哈', collect:16, comments: 456, likes: 4},
    {blogId: 10,user: '方头', createDate:'2021-06-09' ,content: '我就是方头的博客123哈哈哈~我是博客10的内容哈哈哈哈哈', collect:8, comments: 10, likes: 28},
    {blogId: 11,user: '方头', createDate:'2021-06-01' ,content: '我就是方头的博客123哈哈哈~我是博客11的内容哈哈哈哈哈', collect:8, comments: 10, likes: 18},
  ],
  userDatas: {
    id: 0,
  }
};

const store = new Vuex.Store({
  state,
  mutations,
  // 异步操作方法
  actions,
  getters,
  modules: {
    a: moduleA
  }
})

// 3.导出store对象
export default store
