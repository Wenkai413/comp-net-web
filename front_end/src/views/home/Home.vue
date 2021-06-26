<template>
  <div id="home">
<!--    <nav-bar class="home-nav"><div slot="center">主页面</div></nav-bar>-->
    <tab-control class="tab-control"
                 :titles="['关注', '热门']" @tabClick="tabClick"></tab-control>
    <blog-list class="blog-list" :blogs="showBlogs"></blog-list>
  </div>

</template>

<script>
  import BlogList from "components/content/blogs/BlogList";
  import NavBar from "components/common/navbar/NavBar";
  import TabControl from "components/content/tabControl/TabControl";

  import {findByTitle} from "../../api/blog";

  export default {
    name: "Home",
    components: {
      NavBar,
      BlogList,
      TabControl
    },
    data() {
      return {
        blogs: {
          'follow': {list: []},
          'all': {list: []}
        },
        currentType: 'all'
      }
    },
    computed: {
      showBlogs() {
        return this.blogs[this.currentType].list
      }
    },
    created() {
      // this.getHomeBlogs('follow') // 请求关注博客数据
      this.getHomeBlogs('all') // 请求推荐博客数据
    },
    methods: {
      /**
       * 事件监听相关方法
       */
      tabClick(index) {
        switch (index) {
          case 0:
            this.currentType = 'follow'
            break
          case 1:
            this.currentType = 'all'
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
              this.blogs[type].list = res.data
              console.log(this.blogs[type].list);
            })
        }

      }
    }
  }
</script>

<style scoped>
  .home-nav {
    background-color: #f6f6f6;
    color: black;
  }

  .tab-control {
    position: sticky;
    top: 49px;
    height: 100px;
    width: 150px;
    flex-direction: column;
  }
  .blog-list {
    background-color: #fff;
    position: sticky;
    margin-left: 150px;
    margin-right: 150px;
    /*margin-top: 49px;*/
  }

</style>
