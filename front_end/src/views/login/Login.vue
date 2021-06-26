<template>
  <div class="bg">
  <div class="nav clearfix">


    <!--        <div class="logoimg "><a href="./index.html"></a></div>-->

    <div class="sign_in">
      <h1>LOGIN</h1>
      <form action=""></form>

      <div class="items">
        <img src="../../assets/img/pic/邮箱2.png" alt="邮箱">
        <input v-model="userInfo.email" style="color:#ffffff" id="email" type="email" placeholder="请输入登录邮箱" name='email'>

      </div>
      <div class="items">
        <img src="../../assets/img/pic/密码.png" alt="密码">
        <input v-model="userInfo.password" style="color:#ffffff" id="password" type="password" placeholder="请输入登录密码" name="password">
      </div>
      <div class="forget">
        <div>
          <input id="memery" type="checkbox">
          <label for="memery">记住密码</label>
        </div>
        <a href="javascript:">忘记密码？</a>
      </div>

      <div class="sign">
        <button id="btn" @click="loginClick">登录</button>

      </div>
      <div class="userlog">
        <span>还没有账号？</span>
        <p @click="signUp">立即注册</p>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
  import {login} from "../../api/user";

  export default {
  name: "Login",
  data(){
    return{
      userInfo: {
        email: "",
        password: ""
      }
    }
  },
  methods: {
    signUp() {
      this.$router.push('/signUp')
    },
    loginClick() {
      let email = document.getElementById("email");
      let password = document.getElementById("password");
      if (email.value == "") {
        email.setAttribute("placeholder", '邮箱不能为空')
        email.style.border = "1px solid red";
      } else if (password.value == "") {
        password.setAttribute("placeholder", '密码长度为6-20')
        password.style.border = "1px solid red"
        // } else if (email.value != "123@123.com" || password.value != "123456") {
        //     alert("账号或密码输入错误，请重新输入");
      }else{
        alert("登陆成功！");
        const payload = {...this.userInfo}
        login(payload).then(res => {
          console.log(res.data.id);
          sessionStorage.setItem("id", res.data.id)
          sessionStorage.setItem("name", res.data.name)

          sessionStorage.setItem("email", res.data.email)
          sessionStorage.setItem("introduction", "我是" + res.data.name)
          console.log(res.data);
        })
        this.$router.push('/home')
      }
    }
  }
}

</script>

<style scoped>
@import "../../assets/css/reset.css";

/* 解决外边距重叠问题 */
.clearfix::before,
.clearfix::after {
  content: '';
  display: table;
  clear: both;
}

/* a标签样式 */
p {
  text-decoration: none;
  color: rgb(204, 203, 194);
}

/* 鼠标移入时的状态样式 */
p:hover {
  color: rgb(98, 171, 231);
  text-shadow: 1px 2px 2px rgb(141, 196, 241);
}
.v-application p {
  all: unset;
}
/* 设置页面背景图片 */
.bg{
  background-image: url('../../assets/img/pic/1223.jpeg');
  background-repeat: no-repeat;
  background-size: cover;
  height:100%;
}

body {
  background-color: rgba(0, 0, 0, .2);
  height: 700px;
}

/* 设置登录容器 */
.nav {
  margin: 0 auto;
  width: 400px;
}

/* 设置表单样式位置*/
.sign_in {
  margin-top: 200px;
  text-align: center;
  height: 300px;
  display: flex;
  flex-flow: column;
  justify-content: space-around;
  background-color: #00000090;
  border-radius: 20px;
}

.sign_in h1{
  color:white;
  font-size: 30px;
  margin-top: 10px;
}

.items {
  position: relative;
}


/* 设置邮箱,密码图片 */
.items img {
  position: absolute;
  width: 20px;
  height: 20px;
  left: 84px;
  top: 9px;
  color: white;
}

/* 设置表单样式 */
.items input {
  height: 40px;
  width: 250px;
  box-sizing: border-box;
  border-radius: 10px;
  outline: none;
  border: 1px solid #6d6270;
  padding: 0 10px 0 40px;

}

/* 设置点击输入时的边框和阴影 */
.items input:focus {
  border: 1px solid #888;
  box-shadow: 1px 2px 3px #e5cfeb;
}

/* 设置记住密码和忘记密码 */
.forget {
  margin: 0 auto;
  width: 250px;
  display: flex;
  justify-content: space-between;

}

.forget {
  font-size: 12px;
  color: #eee;
}

/* 设置登录按钮样式 */
.sign button {
  height: 40px;
  width: 250px;
  border-radius: 20px;
  outline: none;
  background-color: rgb(179, 212, 221);
  border: none;
}

.userlog {
  font-size: 12px;
  color: #eee;
}
</style>
