<script setup>
import {reactive, ref} from 'vue'
import httpService from "~/server/http";
import Constant from "~/constant/Constant";
import routeTo from "~/router/routeTo";

const userForm = reactive({
  username: '',
  password: '',
})

const login = async () => {
  await httpService.post(
      Constant.login,
      {
         username: userForm.username, password: userForm.password
      },
  ).then((response) => {
    // console.log(response.data.data.accessToken)
    window.localStorage.setItem('jwtToken', response.data.data.accessToken)
    ElMessage.success("登陆成功！")
    // console.log(localStorage.getItem("jwtToken"))
    routeTo.home()
  }).catch((error) => {
    ElMessage.error("用户名或密码错误")
  })
}
</script>

<template>
  <el-container>
    <el-main>
      <div class="login-window">
        <el-card>
          <el-form
              :model="userForm"
              style="max-width: 460px;min-width:300px"
          >
            <el-form-item>
              <el-input v-model="userForm.username" placeholder="用户名"/>
            </el-form-item>
            <el-form-item>
              <el-input v-model="userForm.password" placeholder="密码"/>
            </el-form-item>

            <el-button-group>
              <el-button @click="login">登录</el-button>
              <el-button>注册</el-button>
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

.login-window {
  width: 100%;
  margin-top: 10rem;
  margin-bottom: 10rem;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>