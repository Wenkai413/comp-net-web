<template>
  <div>
    <blog-list-item v-for="(item, index) in blogs" :blog-item="item" :is-me="isMe" :blog-index="index" @dropDownClick="dropClick">
    </blog-list-item>
  </div>
</template>

<script>
  import BlogListItem from "./BlogListItem";



  export default {
    name: "BlogList",
    components: {
      BlogListItem,
    },
    props: {
      blogs: {
        type: Array,
        default() {
          return []
        }
      },
      isMe: false
    },
    methods: {
      dropClick(index) {
        console.log(this.popUpShow);
        this.popUpShow = true
      }
    },
  }
</script>

<style scoped>

</style>
