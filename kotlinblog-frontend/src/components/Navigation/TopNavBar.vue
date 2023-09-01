<script lang="ts" setup>
import {toggleDark} from "~/composables/dark";
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import {useDark} from "@vueuse/core";
import routeTo from "~/router/routeTo";

const router = useRouter()

//黑暗模式
const navColor = ref()
useDark({
  onChanged(dark: boolean) {
    if (dark) {
      navColor.value = "#323232"
    } else {
      navColor.value = "#FFFFFF"
    }
  },
})
const toggleDarkTheme = () => {
  toggleDark();
}

//挂载时获取根分类
onMounted(() => getCategories())
//初始化分类列表
const categoryList = ref([])
//获取所有根分类
const getCategories = async () => {
  await httpService.get(
      Constant.BASE_URL + Constant.category.api + Constant.category.getRootCategories
  ).then((response) => {
    categoryList.value.push(...response.data.data)
  })
}
</script>
<template>
  <el-menu :ellipsis="false" class="header-menu" mode="horizontal">
    <el-button size="large" link @click="routeTo.home()">
      <el-text size="large" tag="b">47 Saikyo</el-text>
    </el-button>

    <div class="flex-grow"/>

    <el-menu-item @click="routeTo.archive(parent.id)" v-for="parent in categoryList">
      <template #title>{{ parent.name }}</template>
    </el-menu-item>

    <el-menu-item @click="routeTo.about()">
      <template #title>About</template>
    </el-menu-item>

    <el-divider direction="vertical"/>
    <el-menu-item h="full" @click="toggleDarkTheme">
      <button
          class="border-none w-full bg-transparent cursor-pointer"
          style="height: var(--ep-menu-item-height)">
        <i inline-flex i="dark:ep-moon ep-sunny"/>
      </button>
    </el-menu-item>
    <el-divider direction="vertical"/>
    <el-menu-item>
      <template #title>Login</template>
    </el-menu-item>
  </el-menu>
</template>

<style>
.header-menu {
  padding-left: 20px;
  padding-right: 20px;
}


.flex-grow {
  flex-grow: 1;
}

.ep-menu {
  --navColor: v-bind(navColor);
  background: var(--navColor);
}

.ep-menu.dark {
  background: #323232;
}

.ep-divider--vertical {
  height: auto;
}
</style>
