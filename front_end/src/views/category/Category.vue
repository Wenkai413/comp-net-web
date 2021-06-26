<template>
<!--  <div id="category">-->
    <div class="search">
       <header class="header">搜索页面</header>
              <form action="search" class="searchtool">
                <div class="searchdiv">
<!--                  <label class="searchin" for="in">-->
                    <input type="text" maxlength="100" autocomplete="off" v-model="inputtext">
                    <v-btn @click="btnClick" aria-label="搜索" type="button" class="btn">搜索</v-btn>
<!--                  </label>-->
                </div>
              </form>
      <div class="bl">
      <blog-list class="blog-list" :blogs="showBlogs"></blog-list>
      </div>
    </div>

</template>

<script>
import BlogList from "components/content/blogs/BlogList";
import {findByTitle} from "@/api/blog"

export default {
    name: "Category",
    components: {
      BlogList
    },
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        inputtext: "",
        bloglist: [],
        curPage: 0,
        totalPage: 0,
        currentType: 'recommend'
      }
    },
  computed: {
    showBlogs() {
      return this.bloglist
    }
  },
  methods: {
    btnClick() {
      console.log(this.inputtext);
      this.getHomeBlogs(this.inputtext)
    },

        /**
     * 网络请求相关方法
     */
    getHomeBlogs(text) {
          console.log(text);
          findByTitle({keyword:text}).then(res=>{
        console.log(res);
        this.bloglist=res.data;
      })
    }
  }
}
</script>

<style scoped>

.v-application p{
  all:unset;
}
  .search {
    background-color: rgb(247, 240, 216);
    height:100%;
    width:100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .header{
    display: flex;
    flex-direction: row;
    justify-content: center;
    font-size:30px;
    margin-bottom: 20px;
  }
  .searchdiv{
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  .btn{
    border:2px solid;
    margin-left:5px;
  }

  .bl{
    margin-top:40px;
    width:80%;
  }
p{
  text-align: left;
}

.buttons{
  display: flex;
  flex-direction: row;
  justify-content: center;
}
/*.border{*/
/*  align-items:center;*/
/*}*/

/*.content{*/
/*  border-style: solid;*/
/*  border-width: 1px;*/
/*  width: 50%;*/
/*  margin: 0 auto;*/
/*}*/
  .pagebutton{
    color: #ff0033;
    margin-left: 15px;
    margin-right: 15px;
    border: #ff0033 solid;
  }


.pagenum{
  text-align: center;
  margin-top: 20px;
}
input{
  border: 2px solid black
}
</style>
