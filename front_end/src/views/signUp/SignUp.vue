<template>
  <div class="bg">
  <div class="wrap">


    <img src="https://rg2wdzy1.oss-cn-beijing.aliyuncs.com/icon/bg2.jpg" alt="logo">
    <div class="login">
      <span>已有账号？</span>
      <p @click="login">立即登录</p>
    </div>
    <div class="user">
      <form action="">
        <div class=form_list>
          <div class="logo"></div>
          <div class="item">
            <input id="username" type="text" placeholder="请输入用户昵称" name='username' v-model="userInfo.name">
          </div>
          <div class="item">
            <input id="email" type="email" placeholder="请输入注册邮箱" name='username' v-model="userInfo.email">
          </div>
          <div class="item">
            <input id="password" type="password" placeholder="请输入登录密码" name='password' v-model="userInfo.password">
          </div>
          <div class="item">
            <input id="password1" type="password" placeholder="请再次输入密码" name="password">
          </div>
          <div class="view">
            <input id="rule" type="checkbox">
            <label for="rule">
              <span>我已仔细阅读并同意</span>
              <a href="javascript:">《博客网站协议》</a>
            </label>
          </div>

        </div>

      </form>
      <div class="submit">
        <button id="submit" value="注册" name="submit" @click="signUpClick">注册</button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import {signUp} from "@/api/user";
export default {
  name: "signUp",
  data(){
    return {
      userInfo: {
        email: "",
        password: "",
        name: ""
      }
    }
  },
  methods: {
    login() {
      this.$router.push('/login')
    },
    signUpClick() {
      this.flag = false;
      let username = document.getElementById("username")
      let email = document.getElementById("email");
      let password = document.getElementById("password");
      let password1 = document.getElementById("password1");
      if (username.value == "") {
        username.setAttribute("placeholder", '用户名不能为空')
        username.style.border = "1px solid red";
      } else if(email.value==""){
        email.setAttribute("placeholder", '邮箱不能为空')
        email.style.border = "1px solid red";
      }
      else if (password.value == "") {
        password.setAttribute("placeholder", '密码不能为空')
        password.style.border = "1px solid red";
      } else if(password1.value == ""){
        password1.setAttribute("placeholder", '请再次输入密码')
        password1.style.border = "1px solid red";
      }else if((password.value) != (password1.value)){
        alert("确认密码错误");
        password1.setAttribute("placeholder", '请再次输入密码')
        password1.style.border = "1px solid red";


      }else{
        alert("注册成功！");
        console.log(email);
        const payload = {...this.userInfo}
        signUp(payload).then(res => {
          console.log(res);
        })
        this.$router.push('/login')
      }


    }
  }
}




</script>

<style scoped>
/* 设置背景图片 */
.bg {
  background-image: url("../../assets/img/pic/city.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  height:100%;
}

/*  设置整体外部容器*/
.wrap {
  margin: 50px auto;
  width: 60%;
  height: 500px;
  border: 1px solid #eee;
  box-shadow: 2px 3px 10px rgba(0, 0, 0, .3);
  border-radius: 20px;
  background-color: #00000090;
  display: flex;
  position: relative;
}

/* 设置左侧图片 */
img {
  height: 500px;
  width: 45%;
  vertical-align: top;
  border-radius: 20px 0 0 20px;
}

/* 注册表单居中 */
.user {

  margin: 0 auto;
  text-align: center;
}

/* 设置logo图片 */
.logo {
  background-image: url("https://rg2wdzy1.oss-cn-beijing.aliyuncs.com/icon/%E7%94%B7%E7%94%9F.png");
  height: 100px;
  background-size: cover;
  width: 130px;
  margin: 0 auto;
  margin-bottom: 10px;
}

/* 设置表单项列表 */
.form_list {
  margin-top: 30px;
  height: 370px;
  display: flex;
  flex-flow: column;

  justify-content: space-around;
}

/* 设置input 样式 */
.item input {
  height: 40px;
  width: 200px;
  border: 1px solid rgb(116, 116, 212);
  border-radius: 10px;
  padding: 0 20px;
  outline: none;
}

/* 设置焦点表单样式 */
.item input:focus {
  border: 1px solid rgb(24, 231, 110);
  box-shadow: 1px 2px 3px springgreen;
}

/* 设置注册按钮 */
.submit button {
  width: 240px;
  height: 40px;
  border-radius: 20px;
  background-color: rgb(141, 241, 238);
  margin-top: 30px;
}

/* 设置顶部文字样式 */
.login {
  position: absolute;
  right: 0;
  top: -30px;
  color: #eee;
  font-size: 15px;
}

/* 设置鼠标移入效果 */
p:hover {
  color: rgb(98, 171, 231);
  text-shadow: 1px 2px 2px rgb(141, 196, 241);
}

p {
  color: rgb(178, 178, 211);
  text-decoration: none;

}
.v-application p {
  all: unset;
}

.view {
  font-size: 10px;
}

/* 响应式设计 */
@media only screen {
  @media (max-width:756px) {
    img {
      display: none;
    }
  }
}
</style>
