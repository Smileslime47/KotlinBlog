<script lang="ts" setup>
import { ref } from 'vue'
import avatar from '../assets/avatar.jpg'
import fresh from "~/composables/fresh";
import mdRender from "~/composables/mdRender";
import httpService from "~/server/http";
import Constant from "~/constant/Constant";

const aboutContent = ref("")
fresh(async () => {
  await httpService.get(
      Constant.article.api+Constant.article.getDetailById,
      {
        params: {id: 0}
      }).then((response)=>{
        aboutContent.value=mdRender(response.data.content)
  })
})
</script>

<template>
  <el-container>
    <el-aside style="width: 400px;min-height: 1000px">
        <el-card style="max-width: 260px" :body-style="{ padding: '0px' }">
          <img :src="avatar" class="image"/>
          <div style="padding: 14px">
            <span>Smile_slime_47</span>
            <div class="bottom">
              <el-button text class="button">刘一邦</el-button>
            </div>
          </div>
        </el-card>
    </el-aside>
    <el-main>
      <div class="mdText" v-html="aboutContent"></div>
    </el-main>
  </el-container>
</template>

<style scoped>
.time {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
.ep-main{
  padding: 1em;
}
.ep-aside{
  padding-top:1em;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>