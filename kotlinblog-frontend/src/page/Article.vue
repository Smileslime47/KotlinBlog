<script setup lang="ts">
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import InfoPage from "~/page/InfoPage.vue";
import {getPathParam} from "~/router/route";

const directCategory = ref()
const rootCategory = ref()
const articleId = ref()
const article = ref({
  id: "",
  title: "",
  createTime: ""
})

const fresh()=>{
  //获取文章ID
  articleId.value = getPathParam("aid")
  //获取直接分类ID
  directCategory.value = getPathParam("cid")
  //获取根分类ID
  await httpService.get(
      Constant.category.api + Constant.category.getRootCategory,
      {
        params: {id: getPathParam("cid")}
      })
      .then((response) => {
        rootCategory.value = response.data.data.id
      })
  //获取文章信息
  await httpService.get(
      Constant.article.api + Constant.article.getInfoById,
      {
        params: {id: getPathParam("aid")}
      })
      .then((response) => {
        article.value = response.data.data
      })
}
onMounted(async () => fresh())
onBeforeRouteUpdate(async (to, from) => await fresh())

//返回归档页
const backToArchive = ( ) => routeTo.archive(rootCategory)
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
        <MdTextArea :articleId="articleId"></MdTextArea>
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