<template>
  <div>
    <!--  编辑跳窗  -->
    <pop-up-window v-show="editShow" @quitPopUp="editShow = false">
      <p slot="head">编辑博客</p>
      <input type="text" slot="textTitle" v-model="updateTitle" placeholder="请输入标题">
      <textarea v-model="updateContent" slot="bodyInput" type="text" class="input" placeholder="请输入内容">
      </textarea>
      <span slot="bottom" @click="updateBlog">确定修改</span>
    </pop-up-window>
    <!--  删除跳窗  -->
    <pop-up-window v-show="deleteSHow" @quitPopUp="deleteSHow = false">
      <p slot="head">删除博客</p>
      <p slot="bodyText">确定要删除这条博客吗？</p>
      <span slot="bottom" @click="deleteBlog" >确定删除</span>
    </pop-up-window>
    <div class="blog">
      <div class="top">
        <div class="topItem">
          <p class="user">{{blogItem.authorName}}</p>
        </div>
        <div class="topItem">
          <drop-down v-if="isMe" class="dropdown" :items="edits" @dropdownClick="dropDownClick"></drop-down>
        </div>
      </div>
      <p class="date">{{blogItem.createDate}}</p>
      <p class="title" v-on:click="goToBlog(blogItem.title)">{{blogItem.title}}</p>
      <p class="content">{{blogItem.content}}</p>
      <div class="bottom">
        <div class="bottom-item">
          <span><img src="~assets/img/tabbar/collect.svg" alt="">收藏：{{blogItem.collect}}</span>
        </div>
        <div class="bottom-item">
          <span><img src="~assets/img/tabbar/comment.svg" alt="">评论：{{blogItem.comments.length}}</span>
        </div>
        <div class="bottom-item" @click="likeClick">
          <span><img src="~assets/img/tabbar/like.svg" alt="">点赞：{{blogItem.likedBy.length}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import marked from 'marked';
  import DropDown from "components/common/dropDown/DropDown";
  import PopUpWindow from "components/common/popUpWindow/PopUpWindow";

  import
  {
    deleteBlog,
    updateBlog,
    like
  }
  from "../../../api/blog";

  export default {
    name: "BlogListItem",
    components: {
      DropDown,
      PopUpWindow
    },
    props: {
      blogItem: {
        type: Object,
        default() {
          return {};
        },
        getContentText() {
          let html=marked(this.content);

        }
      },
      isMe: false,
    },
    data() {
      return {
        edits: ['删除', '修改'],
        editShow: false,
        deleteSHow: false,
        updateTitle: "",
        updateContent: "",
      }
    },
    methods: {
      dropDownClick(index) {
        switch (index) {
          case 0:
            this.deleteSHow = true
            break
          case 1:
            this.editShow = true
            break
        }
      },

      likeClick() {
        like({blog_id: this.blogItem.id}).then(res => {
          this.blogItem.likedBy.push("2");
          console.log(res);
        })
      },

      goToBlog(title) {
        this.$router.push(`/blog/${title}`)
      },
      deleteBlog() {
        deleteBlog({blog_id: this.blogItem.id}).then(res => {
          console.log(res);
          location.reload()
        })
      },
      updateBlog() {

        updateBlog({blog_id: this.blogItem.id, title: this.updateTitle, content: this.updateContent}).then(res => {
          console.log(res);
          location.reload()
        })
      }
    }
  }
</script>

<style scoped>
  .blog {
    border: 1px solid #f6f6f6;
  }
  .head {
    margin-bottom: 2px;
  }
  .top {
    display: flex;
    justify-content: space-between;
    padding-top: 5px;
    padding-left: 20px;
    margin-top: 6px;
    /*background-color: orangered;*/
  }
  .dropdown {
    margin-right: 20px;
  }
  .user {
    font-size: 18px;
    font-weight: 700;
    margin-bottom: 3px;

  }
  .date {
    padding: 0px 20px 0;
    color: darkgray;
    font-size: 8px;
    margin-bottom: 0px;

  }
  .title {
    padding: 0px 20px 0;
    font-size: 13px;
    font-weight: 700;
    margin-bottom: 3px;

  }
  .content {
    padding: 0px 20px 0px;

    box-shadow: 0 1px 1px rgba(100,100,100,.15);
    font-size: 14px;
    height: 100px;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-bottom: 3px;
  }
  .bottom {
    display: flex;
    text-align: center;
    font-size: 10px;
    line-height: 24px;
    box-shadow: 0 1px 1px rgba(100,100,100,0.15);
    /*background-color: #fff;*/
  }
  .bottom-item {
    flex: 1;
  }
  .bottom-item span {
    padding: 3px;

  }
  .bottom-item img {
    margin-bottom: 3px;
    margin-right: 8px;
    vertical-align: middle;
    width: 18px;
    height: 18px;
  }

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
  /*.v-application p{*/
  /*  all:unset;*/
  /*}*/
</style>
