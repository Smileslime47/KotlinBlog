<template>
  <el-menu
      default-active="1"
      :collapse-transition="false"
  >
    <el-menu-item index="1" @click="switchCollapse">
      <el-icon>
        <ArrowRightBold v-if="asideCollapse"/>
        <ArrowLeftBold v-if="!asideCollapse"/>
      </el-icon>
    </el-menu-item>

    <ProfileCard v-if="asideCollapse"/>
    <FrameCard v-if="asideCollapse"/>
    
    <el-sub-menu :index="(index+2).toString()" v-if="!asideCollapse" v-for="[index,subCategory] in subCategories.entries()">
      <template #title>
        <el-icon>
          <location/>
        </el-icon>
        <el-text>{{ subCategory.name }}</el-text>
      </template>
      <el-menu-item v-for="article in subCategory.articles">
        {{ article.title }}
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import {
  ArrowRightBold,
  ArrowLeftBold,
  Location,
  Document,
  Menu as IconMenu,
  Setting,
} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import axios from "axios";
import {onBeforeRouteUpdate, useRoute} from "vue-router";
import FrameCard from "~/components/SideCard/FrameCard.vue";

const route = useRoute()
const parentId = ref(route.params.cid)
const asideCollapse: any = ref(true)
const switchCollapse = () => {
  asideCollapse.value = !asideCollapse.value;
}

const subCategories = ref([])

const freshArticleList = async (cid) =>{
  await axios.request({
    method: 'get',
    url: 'http://localhost:7777/category/sub-category',
    params: {
      parentId: cid
    }
  }).then((response) => {
    subCategories.value=[]
    subCategories.value.push(...response.data.data)
  })
  for (const subCategory of subCategories.value) {
    await axios.request({
      method: 'get',
      url: 'http://localhost:7777/article/all-by-subcategory',
      params: {
        categoryId: subCategory.id
      }
    }).then((response) => {
      subCategory.articles = response.data.data
      console.log(subCategory.articles)
    })
  }
}
onMounted(async () => {
  await freshArticleList(parentId.value)
})
//每次改变分类ID时刷新文章列表
onBeforeRouteUpdate(async (to, from) => {
  // 对路由变化做出响应...
  await freshArticleList(to.params.cid)
})

</script>

<style scoped>
.ep-menu{
  min-height: 100%;
}
</style>
