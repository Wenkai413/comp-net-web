<template>
  <v-card class="mx-auto">
    <v-card-title>
      {{ author }}
    </v-card-title>
    <v-card-subtitle>
      {{ date }}
    </v-card-subtitle>
    <v-card-text>
      {{ this.content }}
    </v-card-text>
    <v-container>
      <v-btn icon
             @click="showReply=!showReply">
        <v-icon>
          mdi-comment
        </v-icon>
        {{reply.length}}
      </v-btn>
      <InputBox
        @submit="submit"
      ></InputBox>
    </v-container>
    <v-list v-show="showReply">
      <template v-for="item in reply">
        <reply
          :key="item"
          :id="item"
        >
        </reply>
      </template>
    </v-list>
  </v-card>
</template>

<script>
import {createComment, createReply, getComment} from "@/api/comment"
import Reply from "@/views/blog/Reply";
import InputBox from "@/views/blog/InputBox";
import {update} from "@/api/blog";

export default {
  components: {InputBox, Reply},
  name: "Comment",
  props: {
    id: {
      type: Number,
      default:1,
    },
  },
  methods: {
    submit(content) {
      this.showAddReply = false;
      let cid;
      cid = this.id;
      createReply({content: content, id: cid});
      location.reload();
    },
    getBlog(id) {
      getComment({id}).then(res => {
        console.log(res);
        let com = res.data;
        this.content = com.content;
        this.date = com.createDate;
        this.reply = com.replies;
        this.author = com.authorName;
      })
    },
  },
  data: () => ({
    showReply: false,
    author:"无作者",
    date: null,
    content: null,
    likes: 0,
    reply: [],
  }),
  created(){
    this.getBlog(this.id);
  },
  // updated() {
  //   this.getBlog(this.id);
  // }
}
</script>

<style scoped>
.bottom {
  display: flex;
  text-align: center;
  font-size: 16px;
  height: 40px;
  line-height: 40px;
  box-shadow: 0 1px 1px rgba(100, 100, 100, 0.15);
  /*background-color: #fff;*/
}

.bottom-item {
  flex: 1;
}

.bottom-item span {
  padding: 5px;

}
</style>
