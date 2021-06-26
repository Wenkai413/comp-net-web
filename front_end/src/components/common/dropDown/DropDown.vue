<template>
  <div class="menu-item" @click="btnClick">
    <img src="~assets/img/tabbar/dropdown.svg" alt="">
    <transition name="fade" appear>
      <div class="sub-menu" v-if="isOpen">
        <div v-for="(item, index) in items" class="menu-items" @click="dropdownClick(index)">
          {{item}}
        </div>
      </div>
    </transition>

  </div>
</template>

<script>
  export default {
    name: "DropDown",
    props: {
      'items': []
    },
    data() {
      return {
        isOpen: false
      }
    },
    methods: {
      btnClick() {
        // console.log(this.isOpen);
        this.isOpen = !this.isOpen;
      },
      dropdownClick(index) {
        console.log('111');
        this.$emit('dropdownClick', index)
      }
    }
  }
</script>

<style scoped>
  .menu-item img {
    width: 20px;
  }
  .menu-items {

    padding: 10px 20px;
    position: relative;
    font-size: 16px;
    /*border-bottom: 3px solid orangered;*/
    /*display: flex;*/
    transition: 0.4s
  }
  .menu-item .sub-menu {
    border-style: solid;
    border-color: #f6f6f6;
    border-width: 2px;
    position: absolute;
    /*left: 50%;*/
    transform: translateX(-70px);
    width: max-content;
    background-color: #fff;
    /*border-radius: 0px 0px 16px 16px;*/
  }
  .menu-items.active,
  .menu-items:hover {
    background-color: #f6f6f6;
    /*border-bottom-color: darkorange;*/
    color: orange;
    font-weight: 700;
  }
  .menu-item.active,
  .menu-item:hover {
    background-color: #f6f6f6;

  }

  .fade-enter-active,
  .fade-leave-active {
    transition: all .15s ease-out;
  }

  .fade-enter,
  .fade-leave-to {
    opacity: 0;
  }
</style>
