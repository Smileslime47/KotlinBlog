<script setup lang="ts">
import {useDark} from "@vueuse/core";
const isReverse = ref("")
useDark({
  onChanged(dark: boolean) {
    if (dark) {
      isReverse.value = "invert(1)"
    } else {
      isReverse.value = ""
    }
  },
})
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <el-space direction="vertical">
          <el-text tag="b" size="large">Powered By</el-text>
        </el-space>
      </div>
    </template>

    <div class="frame-card-container">
      <el-space direction="vertical">
        <el-text style="font-size: 35px">
          <el-image style="width: 30px; height: 30px" fit="fill" src="kotlin.svg"/>Kotlin
        </el-text>
        <el-text style="font-size: 20px">
          with SpringBoot,MybatisPlus ...
        </el-text>

        <el-text style="font-size: 35px">
          <el-image style="width: 30px; height: 30px" fit="fill" src="typescript.svg"/>TypeScript
        </el-text>
        <el-text style="font-size: 20px">
          with Vue3,Element-Plus,Vite ...
        </el-text>

        <el-text style="font-size: 35px">
          <el-image style="width: 30px; height: 30px" fit="fill" src="digitalocean.svg"/>Digital Ocean
        </el-text>
        <el-text style="font-size: 20px">
          with Ubuntu ...
        </el-text>
      </el-space>
    </div>
  </el-card>
</template>

<style scoped>
.ep-card{
  margin:10px
}
.card-header {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.frame-card-container {
  display: flex;
  flex-direction: column;
  //justify-content: center;
}

.ep-image {
  --reverse: v-bind(isReverse);
  filter: var(--reverse);
}
</style>
