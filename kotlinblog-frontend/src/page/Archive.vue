<script setup lang="ts">
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import InfoPage from "~/page/InfoPage.vue";
import routeTo from "~/router/routeTo";
import {getPathParam} from "~/router/route"
import {onBeforeRouteUpdate, useRoute} from "vue-router";
import fresh from "~/composables/fresh"

const articleList = ref([])
const rootCategory = ref(getPathParam("cid"))

//根据指定CID刷新文章列表
fresh(async (route) => {
  rootCategory.value = route.params.cid
  await httpService.get(
      Constant.article.api + Constant.article.getInfoByCategory,
      {
        params: {
          id: rootCategory.value,
          deep: true,
        }
      })
      .then((response) => {
        console.log(rootCategory.value)
        articleList.value = []
        //将获取到的文章列表加入List
        articleList.value.push(...response.data.data)
        //根据日期排序文章列表
        articleList.value.sort((a, b) => a.createTime <= b.createTime ? 1 : -1)
        console.log(articleList.value)
      })
})
</script>

<template>
  <InfoPage>
    <div class="HomePanel">
      <el-timeline>
        <el-timeline-item v-for="article in articleList" :timestamp="article.createTime" placement="top">
          <el-card>
            <el-link @click="routeTo.article(article.category,article.id)">{{ article.title }}</el-link>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </InfoPage>
</template>

<style scoped>
.HomePanel {
  margin-top: 10px;
  padding: 10px 20px
}
</style>