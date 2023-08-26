<template>
  <el-menu
    default-active="2"
    :collapse="asideCollapse"
  >
    <el-menu-item index="1" @click="switchCollapse">
        <el-icon><ArrowRightBold /></el-icon>
      <template #title>Navigator Two</template>
    </el-menu-item>

    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <template #title>Navigator Two</template>
    </el-menu-item>

    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <template #title>Navigator Three</template>
    </el-menu-item>

    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <template #title>Navigator Four</template>
    </el-menu-item>
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
import {ref} from "vue";

const asideCollapse:any = ref(true)
const switchCollapse = () =>{
  asideCollapse.value = !asideCollapse.value;
}
</script>
