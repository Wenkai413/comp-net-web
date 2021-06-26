<template>
  <v-dialog
    v-model="isShowed"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn icon
        v-bind="attrs"
        v-on="on"
      >
        <v-icon>
          mdi-plus
        </v-icon>
      </v-btn>
    </template>
    <v-card v-model="isShowed">
      <v-textarea v-model="content">

      </v-textarea>
      <v-btn
        @click="submit"
      >
        评论
      </v-btn>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "InputBox",
  data:()=>({
    content:"",
    isShowed:false,
  }),
  methods:{
    submit() {
      this.isShowed=false;
      this.$emit("submit", this.content);
    },
  }
}
</script>

<style scoped>

</style>
