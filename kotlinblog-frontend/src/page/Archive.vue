<script setup lang="ts">
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import InfoPage from "~/page/InfoPage.vue";

const route = useRoute()
const router = useRouter()
const params = ref(route.params)
const articleList = ref([])

//导航至文章
const routeArticle = (articleId) => {
  router.push("/article/" + route.params.cid + "/" + articleId)
}

//根据指定CID刷新文章列表
const freshArticleList = async (cid) => {
  console.log(Constant.BASE_URL + Constant.article.api + Constant.article.getInfoByCategory,)
  await httpService.get(
      Constant.article.api + Constant.article.getInfoByCategory,
      {
        params: {
          id: cid,
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

//初始化分类ID及文章列表
onMounted(async () => {
  await freshArticleList(params.value.cid)
})

//每次改变分类ID时刷新文章列表
onBeforeRouteUpdate(async (to, from) => {
  // 对路由变化做出响应...
  await freshArticleList(to.params.cid)
})
</script>

<template>
  <InfoPage>
    <div class="HomePanel">
      <el-timeline>
        <el-timeline-item v-for="article in articleList" :timestamp="article.createTime" placement="top">
          <el-card>
            <el-link @click="routeArticle(article.id)">{{ article.title }}</el-link>
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