<script setup lang="ts">
import {onMounted, ref} from "vue";
import {useRoute, useRouter, onBeforeRouteUpdate} from "vue-router";
import axios from "axios";
import FrameCard from "~/components/SideCard/FrameCard.vue";

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
  await axios.request({
    method: 'get',
    url: 'http://localhost:7777/article/all-by-parent',
    params: {
      path: cid
    }
  }).then((response) => {
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
  <el-container>
    <el-aside width="400px">
      <BaseSide/>
    </el-aside>
    <el-main>
      <div class="HomePanel">
        <el-timeline>
          <el-timeline-item v-for="article in articleList" :timestamp="article.createTime" placement="top">
            <el-card>
              <el-link @click="routeArticle(article.id)">{{ article.title }}</el-link>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.HomePanel {
  margin-top: 10px;
  padding: 10px 20px
}
</style>