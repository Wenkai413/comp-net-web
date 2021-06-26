import {
  WRITESHOWON,
  WRITESHOWOFF,
  BLOG_DROP_OPEN,
  BLOG_DROP_CLOSE,
  BLOG_DROP_INDEX
} from "./mutations-types";

export default {
  // // 引入常量写法
  //   // [INCREMENT](state){
  //   //   state.counter++
  //   // },
  //   // decrement(state){
  //   //   state.counter--
  //   // },
  //   // // 如果是特殊风格的封装，则传进来的是一个对象payload（负载）
  //   // incrementCount(state, payload){
  //   //   state.counter += payload.count
  //   // },
  //   // addStudent(state, stu) {
  //   //   state.students.push(stu)
  //   // },
  //   // updateInfo(state) {
  //   //   // setTimeout(() => {
  //   //   //   state.info.name = 'aaa'
  //   //   // }, 100)
  //   //
  //   //   // state.info['address'] = '洛杉矶' 新加进去的虽然在数据区有更新，但页面不会响应
  //   //
  //   //   // 根本原因：响应式的写法（之前讲过）
  //   //   // Vue.set(state.info, 'address', '洛杉矶')
  //   //
  //   //   // delete.state.info.age,也做不到响应式
  //   //   Vue.delete(state.info, 'age')
  //   // }

  [WRITESHOWON](state) {
    state.writeShow = true
  },
  [WRITESHOWOFF](state) {
    state.writeShow = false
  },
  [BLOG_DROP_OPEN](state) {
    state.blogDropdownShow = true
  },
  [BLOG_DROP_CLOSE](state) {
    state.blogDropdownShow = false
  },
  [BLOG_DROP_INDEX](state, index) {
    state.blogDropdownIndex = index
  }
}
