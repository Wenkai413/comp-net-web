<template>
  <div>
    <v-navigation-drawer v-model="showComment" app width="500" class="nav-drawer">
      <v-toolbar>
        <v-btn icon
          @click="showComment=false">
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <v-toolbar-title>评论区</v-toolbar-title>
        <v-spacer></v-spacer>
        <InputBox
          @submit="submit"
        ></InputBox>

      </v-toolbar>
      <v-list>
        <template v-for="item in comments">
          <comment
            :key="item"
            :id="item"
          >
          </comment>
        </template>
      </v-list>
    </v-navigation-drawer>
    <v-row class="top-card">
      <div class="markdown-body card">
        <h1>{{ title }}</h1>
        <v-container>

          <strong>{{ author }}</strong>
          <p>{{date}}</p>
        </v-container>

        <div v-html="content"></div>
      </div>

    </v-row>
    <v-bottom-navigation
      fixed
    >
      <v-btn>
        <span>点赞{{likesBy.length}}</span>
        <v-icon
          :color="!liked ? 'black' : 'red'"
          @click="setLikeOrDisLike"
        >
          mdi-heart
        </v-icon>
      </v-btn>
      <v-btn>
        <span>收藏</span>
        <v-icon>mdi-star-outline</v-icon>
      </v-btn>
      <v-btn
        @click.stop="showComment=!showComment"
      >
        <span>评论:{{comments.length}}</span>
        <v-icon
          :color="!showComment ? 'black' : 'blue'"
        >mdi-comment</v-icon>
      </v-btn>
    </v-bottom-navigation>

  </div>


</template>

<script>
import Vue from 'vue';
import {createComment} from "@/api/comment"
import Comment from "@/views/blog/Comment";
import marked from 'marked';
import InputBox from "@/views/blog/InputBox";
import {findByTitle,like} from "@/api/blog";

export default {
  components: {InputBox, Comment},
  data: () => ({
    liked:false,
    likesBy :[],
    showComment: false,
    addingComment:false,
    id:0,
    title:"无标题",
    date:null,
    author:"无作者",
    content:"无内容",
    comments:[],
  }),
  name: "Blog",
  props: {
  },
  methods: {
    getBlog() {
      findByTitle(this.$route.params).then(res=>{
        console.log(res);
        let blogArr=res.data;
        let t=this.$route.params.title;
        let article;
        for(let i in blogArr){
          if(blogArr[i].title===t){
            article=blogArr[i];
            break;
          }
        }
        this.id=article.id;
        this.content=marked(article.content);
        this.date=article.createDate;
        this.title=article.title;
        this.comments=article.comments;
        this.likesBy=article.likedBy;
        this.author=article.authorName;
        console.log(this.comments);
      })
      // this.title = "近代史";
      // this.content = marked("## 近代史复习\n" +
      //   "\n" +
      //   "## 对国家出路的早期探索\n" +
      //   "\n" +
      //   "农民：太平天国\n" +
      //   "\n" +
      //   "地主：洋务运动、戊戌变法\n" +
      //   "## 资产阶级对国家出路的探索\n" +
      //   "![](http://img.smyhvae.com/20170727_2128.png)\n");
    },
    setLikeOrDisLike() {
      like({blog_id:this.id});
      this.liked=true;
      this.likesBy.push(this.id);
    },
    submit(content){
      this.addingComment=false;
      let blogId;
      blogId=this.id;
      createComment({content:content,id:blogId}).then(res=>{
        console.log(res);
      });
      // location.reload();
    }
  },
  created() {
    this.getBlog();
  },
  // updated() {
  //   this.getBlog();
  // }
}

</script>


<style scoped>
@import "~github-markdown-css";

.card {
  box-sizing: border-box;
  min-width: 200px;
  max-width: 980px;
  margin: 10px auto;
  padding: 45px;

}

.top-card {
  box-sizing: border-box;
  min-width: 200px;
  max-width: 980px;
  margin: 60px auto 10px auto;
  padding: 45px;
  z-index: 0;
  position: relative;
}

.nav-drawer{
  margin-top: 49px;
}

@media (max-width: 767px) {
  .card, .top-card {
    padding: 15px;
  }
}
</style>

