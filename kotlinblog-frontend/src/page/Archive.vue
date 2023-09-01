<script setup lang="ts">
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import InfoPage from "~/page/InfoPage.vue";
import routeTo from "~/router/routeTo";

const articleList = ref([])

//根据指定CID刷新文章列表
const fresh = async () => {
  await httpService.get(
      Constant.article.api + Constant.article.getInfoByCategory,
      {
        params: {
          id: getPathParam("cid"),
          deep: true,
        }
      })
      .then((response) => {
        articleList.value = []
        //将获取到的文章列表加入List
        articleList.value.push(...response.data.data)
        //根据日期排序文章列表
        articleList.value.sort((a, b) => a.createTime <= b.createTime ? 1 : -1)
      })
}
onMounted(async () => await fresh())
onBeforeRouteUpdate(async (to, from) => await fresh())
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