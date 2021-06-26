<template>
  <v-card class="mx-auto">
    <v-card-title>
      {{author}}
    </v-card-title>
    <v-card-subtitle>
      {{date}}
    </v-card-subtitle>
    <v-card-text>
      {{this.content}}
    </v-card-text>
  </v-card>
</template>

<script>
import {getReply} from "@/api/comment";

export default {
  name: "Reply",
  props:{
    id:{
      type:Number,
    },

  },
  data:()=>({
    author:null,
    date:null,
    content:null,
    likes:0,
  }),
  methods:{
    getBlog(id) {
      getReply({id}).then(res => {
        console.log(res);
        let com = res.data;
        this.content = com.content;
        this.date = com.createDate;
        this.author = com.authorName;
      })
    },
  },
  created() {
    this.getBlog(this.id);
  }
}
</script>

<style scoped>

.bottom-item span {
  padding: 5px;

}
</style>
