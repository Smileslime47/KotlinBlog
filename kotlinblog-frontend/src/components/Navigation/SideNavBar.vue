<script lang="ts" setup>
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import routeTo from "~/router/routeTo";
import fresh from "~/composables/fresh";

const rootArticles = ref([])
const subCategories = ref([])
const directCategory = ref()
const rootCategory = ref()

//折叠状态
const asideCollapse: any = ref(true)
const switchCollapse = () => asideCollapse.value = !asideCollapse.value;

fresh(async (route) => {
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
  //获取根分类的子分类
  await httpService.get(
      Constant.category.api + Constant.category.getSubCategories,
      {params: {id: rootCategory.value}})
      .then((response) => {
        subCategories.value = []
        subCategories.value.push(...response.data)
      })
  //获取子分类的文章列表
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
          subCategory.articles = response.data
        })
  }
  //获取根分类的文章列表
  await httpService.get(
      Constant.article.api + Constant.article.getInfoByCategory,
      {
        params: {
          id: rootCategory.value,
          deep: false
        }
      })
      .then((response) => {
        rootArticles.value = []
        rootArticles.value.push(...response.data)
      })
})
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

    <el-menu-item v-if="!asideCollapse" v-for="article in rootArticles"
                  @click="routeTo.article(article.category,article.id)">
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
      <el-menu-item v-for="article in subCategory.articles" @click="routeTo.article(article.category,article.id)">
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
