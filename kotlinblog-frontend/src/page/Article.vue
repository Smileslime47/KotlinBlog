<script setup lang="ts">
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import InfoPage from "~/page/InfoPage.vue";
import {getPathParam} from "~/router/route";
import routeTo from "~/router/routeTo";
import fresh from "~/composables/fresh"
import mdRender from "~/composables/mdRender";

const articleId = ref(getPathParam("aid"))
const directCategory = ref(getPathParam("cid"))
const rootCategory = ref()
const htmlContent = ref("")
let article = ref({
  title:"",
  content:"",
  createTime:"",
  updateTime:"",
})

fresh(async (route) => {
  articleId.value = route.params.aid
  directCategory.value = route.params.cid
  //获取根分类ID
  await httpService.get(
      Constant.category.api + Constant.category.getRootCategory,
      {
        params: {id: directCategory.value}
      })
      .then((response) => {
        rootCategory.value = response.data.id
      })
  //获取文章信息
  await httpService.get(
      Constant.article.api + Constant.article.getDetailById,
      {
        params: {id: articleId.value}
      })
      .then((response) => {
        console.log(response)
        article.value = response.data
        htmlContent.value = mdRender(article.value.content)
      })
})

//返回归档页
const backToArchive = () => routeTo.archive(rootCategory.value)
</script>

<template>
  <InfoPage>
    <div class="HomePanel">
      <el-card>
        <template #header>
          <div class="card-header">
            <el-page-header @click="backToArchive">
              <template #content>
                <span class="text-large font-600 mr-3"> {{ article.title }} </span>
              </template>
            </el-page-header>
            <el-text>{{ article.createTime }}</el-text>
          </div>
        </template>
        <div class="mdText" v-html="htmlContent"></div>
      </el-card>
    </div>
  </InfoPage>
</template>

<style scoped>
.HomePanel {
  margin-top: 10px;
  padding: 10px 20px
}

.card-header {
  flex-direction: row;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>