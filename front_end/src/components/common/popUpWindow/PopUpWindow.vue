<template>
  <div class="mask">
    <div class="box">

      <div class="head">
        <slot name="head"><p>标题</p></slot>
        <img src="~assets/img/userInfo/false.svg" @click="quitClick">
      </div>
      <div class="body">
        <div class="bodyItem">
          <slot name="bodyText"></slot>
          <slot name="textTitle"></slot>
          <slot name="bodyInput"></slot>
        </div>

      </div>
      <div class="bottom">
        <span class="bottomItem" @click="quitClick">
          <slot name="bottom" ><p>确定</p></slot>
        </span>

      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: "PopUpWindow",
    props: {
      blogId: 0
    },
    methods: {
      quitClick() {
        this.$emit('quitPopUp')
      }
    }
  }
</script>

<style scoped>

  * {
    padding: 0;
    margin: 0;
    list-style: none;
  }
  body, html {
    width: 100%;
    height: 100%;
  }
  .head {
    display: flex;
    justify-content: space-between;
    padding-top: 8px;
    padding-left: 10px;
    padding-right: 10px;
    font-weight: 700;
    border-bottom: 1px solid #f6f6f6;
  }
  .head img {
    width: 16px;
    margin-bottom: 0px;

  }
  .bodyItem {

    display: flex;
    flex-direction: column;
    justify-content: space-between;
    /*background-color: orangered;*/
    padding: 10px;
    height: 198px;

    margin-bottom: 3px;

  }
  .bottom {

    display: flex;
    justify-content: center;
    /*position: absolute;*/
    /*bottom: 10px;*/
    /*left: 10px;*/
    border-top: 1px solid #f6f6f6;
    padding-top: 5px;
    /*background-color: #42b983;*/
  }
  .bottomItem {
    color: #ffffff;
    background-color: orange;
    padding: 5px 14px;
    border-radius: 4px;
    margin-bottom: 0px;
  }
  .bottomItem.active,
  .bottomItem:hover {
    background-color: darkorange;
    font-weight: 700;

  }
  .mask {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top: 0;
    background: rgba(0,0,0, 0.2);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .box {
    position: absolute;
    border-top: 2px solid darkorange;
    background: #fff;
    width: 500px;
    height: 300px;
    border-radius: 3px;
  }
  .head img.active,
  .head img:hover {
    background-color: darkgray;
    /*border-bottom-color: darkorange;*/
    color: orange;
  }
</style>
