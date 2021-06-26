<template>
  <div id="profile">
    <info-frame class="inf" :info="userData"></info-frame>
    <tab-control class="tab-control"
                 :titles="['全部', '热门']" @tabClick="tabClick"></tab-control>
    <blog-list class="blog-list" :blogs="showBlogs" :is-me="true"></blog-list>
  </div>

</template>

<script>
  import BlogList from "components/content/blogs/BlogList";
  import NavBar from "components/common/navbar/NavBar";
  import TabControl from "components/content/tabControl/TabControl";
  import InfoFrame from "components/content/infoFrame/InfoFrame";
  import {findByTitle} from "../../api/blog";

  export default {
    name: "Profile",
    components: {
      NavBar,
      BlogList,
      TabControl,
      InfoFrame
    },
    data() {
      return {
        userData: {
          'name': '',
          'gender': '',
          'introduction': '',
          'followNum': 0,
          'fanNum': 0,
          'blogNum': 0
        },
        blogs: {
          'all': {page: 0, list: []},
          'recommend': {page: 0, list: []}
        },
        currentType: 'all',

      }
    },
    computed: {
      showBlogs() {
        return this.blogs[this.currentType].list
      }
    },
    created() {
      this.getHomeBlogs('all') // 请求关注博客数据
      this.getHomeBlogs('recommend') // 请求推荐博客数据
      this.getUserInfo()
    },
    methods: {
      /**
       * 事件监听相关方法
       */
      tabClick(index) {
        switch (index) {
          case 0:
            this.currentType = 'all'
            break
          case 1:
            this.currentType = 'recommend'
            break
        }
      },

      /**
       * 网络请求相关方法
       */
      getHomeBlogs(type) {
        const page = this.blogs[type].page + 1 // 获取当前请求的页号
        let res
        switch (type) {
          case 'follow':
            res = this.$store.state.followBlogs
            break
          case 'all':
            // res = this.$store.state.recommendBolgs
            findByTitle({title: ""}).then(res => {
              for(let item of res.data) {
                console.log(item.authorId);
                let current = JSON.parse(sessionStorage.getItem("id"));
                if(item.authorId === current) {
                  this.blogs[type].list.push(item)
                }
              }
              console.log(this.blogs[type].list);
            })
        }
      },
      getUserInfo() {
        this.userData = {
          name: sessionStorage.getItem("email"),
          introduction: sessionStorage.getItem("introduction")
        }
        console.log(this.userData);
      }
    }
  }
</script>

<style scoped>
  .tab-control {
    position: sticky;
    top: 60px;
    /*height: 100px;*/
    /*width: 150px;*/
    margin-left: 150px;
    margin-right: 150px;
    /*flex-direction: row;*/
  }
  .blog-list {
    background-color: #ffffff;
    /*position: sticky;*/
    margin-left: 150px;
    margin-top: 20px;
    margin-right: 150px;
  }
  .inf {
    margin-top: 49px;
    margin-left: 150px;
    margin-right: 150px;
    background-color: #ffffff;
  }
</style>
