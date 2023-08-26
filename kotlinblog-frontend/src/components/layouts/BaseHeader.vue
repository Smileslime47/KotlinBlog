<script lang="ts" setup>
import {isDark, toggleDark} from "~/composables";
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import axios from "axios";
import Article from "~/page/Article.vue";

const navColor = ref()
const router = useRouter()
const route = useRoute()
const toggleDarkTheme = () => {
  toggleDark();
  if(isDark.value){
    navColor.value="#323232"
  }else{
    navColor.value="#FFFFFF"
  }
}

onMounted(()=>{
  if(isDark.value){
    navColor.value="#323232"
  }else{
    navColor.value="#FFFFFF"
  }
  getCategories()
})

const routerArticle = (id,name) => { router.push("/"+id) }
const routerHomepage =() => { router.push("/Home")}

const categoryList = ref()

const getCategories = async () => {
  await axios.get("http://localhost:7777/category/parent").then((response) => {
    console.log(response)
    categoryList.value=response.data.data
    for (let i=0;i<response.data.data.length;i++){
      let category=response.data.data[i]
      router.addRoute({path:"/"+category.id,name:category.name,component:() => import('~/page/Article.vue')})
    }
  })
}

</script>
<template>
  <el-menu :ellipsis="false" class="header-menu" mode="horizontal">
    <el-button size="large" link @click="routerHomepage">
      <el-text size="large" tag="b">47 Saikyo</el-text>
    </el-button>

    <div class="flex-grow" />

    <el-menu-item @click="routerArticle(parent.id,parent.name)" v-for="parent in categoryList" >
      <template #title>{{ parent.name }}</template>
    </el-menu-item>

    <el-divider direction="vertical" />
    <el-menu-item h="full" @click="toggleDarkTheme">
      <button
          class="border-none w-full bg-transparent cursor-pointer"
          style="height: var(--ep-menu-item-height)">
        <i inline-flex i="dark:ep-moon ep-sunny" />
      </button>
    </el-menu-item>
    <el-divider direction="vertical" />
    <el-menu-item>
      <template #title>Lgoin</template>
    </el-menu-item>
  </el-menu>
</template>

<style>
.header-menu{
  padding-left: 20px;
  padding-right: 20px;
}


.flex-grow {
  flex-grow: 1;
}

.ep-menu{
  --navColor: v-bind(navColor);
  background: var(--navColor);
}

.ep-menu.dark{
  background: #323232;
}

.ep-divider--vertical{
  height: auto;
}
</style>
