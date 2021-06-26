<template>
  <div>
    <p class="username">{{info.name}}
      <img src="~assets/img/userInfo/male.svg" alt="" v-if="info.gender === '男'">
      <img src="~assets/img/userInfo/female.svg" alt="" v-if="info.gender === '女'">

    </p>
    <p class="intro">简介：{{info.introduction}}</p>
    <div class="userInfo">
      <div class="userInfoItem">{{info.blogNum}}</div>
      <div class="userInfoItem">{{info.followNum}}</div>
      <div class="userInfoItem">{{info.fanNum}}</div>
    </div>
    <div class="textInfo">
      <span class="textItem">博客</span>
      <span class="textItem">关注</span>
      <span class="textItem">粉丝</span>
    </div>


  </div>
</template>

<script>
  export default {
    name: "InfoFrame",
    props: {
      info: {
        type: Object,
        default() {
          return {}
        }
      }
    },

  }
</script>

<style scoped>
  .username {
    font-size: 36px;
    font-weight: 700;
    text-align: center;
    padding-top: 10px;
  }
  .username img {
    margin-bottom: 5px;
    vertical-align: middle;
    width: 18px;
    height: 18px;
  }
  .intro {
    text-align: center;
    font-size: 12px;
    padding-top: 6px;

  }
  .userInfo {
    display: flex;
    text-align: center;
    font-size: 16px;
    font-weight: 700;
    /*height: 32px;*/
    /*line-height: 40px;*/
    /*bottom: 0;*/
    margin-top: 10px;
  }
  .userInfoItem {
    flex: 1;
  }
  .textInfo {
    display: flex;
    text-align: center;
    font-size: 10px;
    line-height: 20px;
  }
  .textItem {
    flex: 1;

  }
</style>
