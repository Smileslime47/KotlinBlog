<script lang="ts" setup>
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import router from "~/router/router";

const route = useRoute()

//根分类ID
const parentId = ref(route.params.cid)

//折叠状态
const asideCollapse: any = ref(true)
const switchCollapse = () => {
  asideCollapse.value = !asideCollapse.value;
}

//初始加载或者改变分类ID时刷新文章列表
const rootArticles = ref([])
const subCategories = ref([])
onMounted(async () => await freshArticleList(parentId.value))
onBeforeRouteUpdate(async (to, from) => await freshArticleList(to.params.cid))
const routeArticle = (articleId) => router.push("/article/" + route.params.cid + "/" + articleId)
const freshArticleList = async (cid) => {
  await httpService.get(
      Constant.category.api + Constant.category.getSubCategories,
      {params: {id: cid}})
      .then((response) => {
        subCategories.value = []
        subCategories.value.push(...response.data.data)
      })

  for (const subCategory of subCategories.value) {
    await httpService.get(
        Constant.article.api + Constant.article.getInfoByCategory,
        {
          params: {
            id: subCategory.id,
            deep: false
          }
        })
        .then((response) => {
          subCategory.articles = response.data.data
        })
  }

  await httpService.get(
      Constant.article.api + Constant.article.getInfoByCategory,
      {
        params: {
          id: cid,
          deep: false
        }
      })
      .then((response) => {
        rootArticles.value = []
        rootArticles.value.push(...response.data.data)
      })
}
</script>

<template>
  <el-menu
      default-active="1"
      :collapse-transition="false"
  >
    <el-menu-item index="1" @click="switchCollapse">
      <el-icon>
        <i-ep-ArrowRightBold v-if="asideCollapse"/>
        <i-ep-ArrowLeftBold v-if="!asideCollapse"/>
      </el-icon>
    </el-menu-item>

    <ProfileCard v-if="asideCollapse"/>
    <FrameCard v-if="asideCollapse"/>

    <el-menu-item v-if="!asideCollapse" v-for="article in rootArticles" @click="routeArticle(article.id)">
      <el-icon>
        <i-ep-Document/>
      </el-icon>
      {{ article.title }}
    </el-menu-item>

    <el-sub-menu :index="(index+2).toString()" v-if="!asideCollapse"
                 v-for="[index,subCategory] in subCategories.entries()">
      <template #title>
        <el-icon>
          <i-ep-Folder/>
        </el-icon>
        <el-text>
          {{ subCategory.name }}
        </el-text>
      </template>
      <el-menu-item v-for="article in subCategory.articles" @click="routeArticle(article.id)">
        <el-icon>
          <i-ep-Document/>
        </el-icon>
        {{ article.title }}
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<style scoped>
.ep-menu {
  min-height: 100%;
}
</style>
