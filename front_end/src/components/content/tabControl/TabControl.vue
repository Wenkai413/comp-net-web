<template>
  <div class="tab-control">
    <div v-for="(item, index) in titles"
         class="tab-control-item" :class="{active: index === currentIndex}" @click="itemClick(index)">
      <span>{{item}}</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "TabControl",
    props: {
      titles: {
        type: Array,
        default() {
          return []
        }
      }
    },
    data() {
      return {
        currentIndex: 0
      }
    },
    methods: {
      itemClick(index) {
        this.currentIndex = index
        this.$emit('tabClick', index)
      }
    }
  }
</script>

<style scoped>
  .tab-control {
    display: flex;
    text-align: center;
    font-size: 15px;

    line-height: 40px;
    box-shadow: 0px 1px 1px rgba(100,100,100,0.2);
    background-color: #ffffff;
    /*height: 100px;*/
    /*!*width: 150px;*! 三个注释掉就变成横着的*/
    /*flex-direction: column;*/


  }
  .tab-control-item {
    flex: 1;
  }
  .tab-control-item.active,
  .tab-control-item:hover {
    background-color: #f6f6f6;
  }
  .tab-control-item span {
    padding: 5px;
  }

  .active span {
    border-bottom: 2px solid darkorange;

  }
</style>
