<script setup>
import {reactive, ref} from 'vue'
import httpService from "~/server/http";
import Constant from "~/constant/Constant";
import routeTo from "~/router/routeTo";
import fresh from "~/composables/fresh";

const loginForm = reactive({
  userName: '',
  password: '',
})
const registerForm = reactive({
  userName: '',
  nickName: '',
  email:'',
  password: '',
  confirmPassword: '',
})
const loginWindows = ref(true)
const switchOperation = () => {loginWindows.value=!loginWindows.value}
const login = async () => {
  await httpService.post(
      Constant.user.login,
      loginForm
  ).then(async (response) => {
    // console.log(response.data.data.accessToken)
    window.localStorage.setItem('jwtToken', response.data.accessToken)
    ElMessage.success("登陆成功！")
    // console.log(localStorage.getItem("jwtToken"))
    routeTo.fresh()
  }).catch((error) => {
    ElMessage.error("用户名或密码错误")
  })
}

const register = async () => {
  await httpService.post(
      Constant.user.register,
      registerForm
  ).then(async (response) => {
    // console.log(response.data.data.accessToken)
    window.localStorage.setItem('jwtToken', response.data.accessToken)
    ElMessage.success("注册成功！")
    // console.log(localStorage.getItem("jwtToken"))
    routeTo.fresh()
  })
}
fresh(async () => {
  await httpService.get(
      Constant.user.api + Constant.user.getCurrentUser
  ).then((response) => {
    console.log(response.data)
    if (response.data.permissionGroup !== 0) {
      routeTo.back()
    }
  })
})
</script>

<template>
  <el-container>
    <el-main>
      <div class="window">
        <el-card v-if="loginWindows">
          <template #header>
            登录
          </template>
          <el-form
              :model="loginForm"
              style="max-width: 460px;min-width:300px"
          >
            <el-form-item>
              <el-input v-model="loginForm.userName" placeholder="用户名"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="loginForm.password" placeholder="密码"/>
            </el-form-item>

            <el-button-group>
              <el-button @click="login">登录</el-button>
              <el-button @click="switchOperation">注册→</el-button>
            </el-button-group>
          </el-form>
        </el-card>
        <el-card v-else>
          <template #header>
            注册
          </template>
          <el-form
              :model="registerForm"
              style="max-width: 460px;min-width:300px"
          >
            <el-form-item>
              <el-input v-model="registerForm.userName" placeholder="用户名"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="registerForm.nickName" placeholder="昵称"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="registerForm.email" placeholder="邮箱"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="registerForm.password" placeholder="密码"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="registerForm.confirmPassword" placeholder="确认密码"/>
            </el-form-item>

            <el-button-group>
              <el-button @click="switchOperation">←登录</el-button>
              <el-button @click="register">注册</el-button>
            </el-button-group>
          </el-form>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.ep-button-group {
  display: flex;
}

.ep-button {
  flex: 1;
}

.window {
  width: 100%;
  margin-top: 10rem;
  margin-bottom: 10rem;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>