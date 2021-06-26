<template>
  <tab-bar>
    <tab-bar-item path="/home" activeColor="deeppink">
<!--      别称需要使用~ -->
      <img slot="item-icon" src="~assets/img/tabbar/home.svg" alt="">
      <img slot="item-icon-active" src="~assets/img/tabbar/home_active.svg" alt="">
      <div slot="item-text">首页</div>
    </tab-bar-item>
    <tab-bar-item path="/category" activeColor="deeppink">
      <img slot="item-icon" src="~assets/img/tabbar/search.svg" alt="">
      <img slot="item-icon-active" src="~assets/img/tabbar/search_active.svg" alt="">
      <div slot="item-text">发现</div>
    </tab-bar-item>

    <tab-bar-item v-show="isLogin" path="/profile" activeColor="deeppink">
      <img slot="item-icon" src="~assets/img/tabbar/profile.svg" alt="">
      <img slot="item-icon-active" src="~assets/img/tabbar/profile_active.svg" alt="">
      <div slot="item-text">我的</div>
    </tab-bar-item>

    <tab-bar-item v-show="!isLogin" path="/profile" activeColor="deeppink">
      <img slot="item-icon" src="~assets/img/userInfo/login.svg" alt="">
      <img slot="item-icon-active" src="~assets/img/userInfo/login.svg" alt="">
      <div slot="item-text">注册/登录</div>
    </tab-bar-item>


    <write-bar-item v-show="isLogin" @click.native="writeClick">
      <img slot="item-icon" src="~assets/img/tabbar/write.svg" alt="">
      <div slot="item-text">写博客</div>
    </write-bar-item>


    <pop-up-window v-show="writeShow" @quitPopUp="writeShow = false">
      <p slot="head">有什么新鲜事想告诉大家？</p>
      <input type="text" slot="textTitle" v-model="blogInfo.title" placeholder="请输入标题">
      <textarea slot="bodyInput" type="text" class="input" v-model="blogInfo.content" placeholder="请输入内容">
      </textarea>
      <p slot="bottom" @click="submit">确定发布</p>

    </pop-up-window>
  </tab-bar>
</template>

<script>
  import TabBar from "components/common/tabbar/TabBar";
  import TabBarItem from "components/common/tabbar/TabBarItem";
  import WriteBarItem from "components/common/tabbar/WriteBarItem";
  import PopUpWindow from "components/common/popUpWindow/PopUpWindow";

  import {createBlog} from "../../../api/blog";

  export default {
    name: "MainTabBar",
    components: {
      TabBar,
      TabBarItem,
      WriteBarItem,
      PopUpWindow

    },
    data() {
      return {
        writeShow: false,
        blogInfo: {
          title: "",
          content: "",
        },
        isLogin: true
      }
    },

    methods: {
      writeClick() {
        console.log('aaa');
        this.writeShow = true
      },
      submit() {
        console.log(this.blogInfo.content);
        const payload = {
          ...this.blogInfo
        }
        console.log(payload);
        createBlog(payload).then(res => {
          console.log(res);
          location.reload()

        })
      }
    },

  }
</script>

<style scoped>
  .input {
    width: 480px;
    height: 145px;
    border-width: 1px;
    border-color: darkgray;
    background-color: #f6f6f6;
    resize: none;
    outline: none;
  }
  input {
    width: 480px;
    border-width: 1px;
    border-color: darkgray;
    background-color: #f6f6f6;
  }
  .v-application p{
    all:unset;
  }
</style>
