<script setup lang="ts">
import '~/styles/index.scss'
import kotlinIcon from '../../assets/kotlin.svg'
import typescriptIcon from '../../assets/typescript.svg'
import digitaloceanIcon from '../../assets/digitalocean.svg'
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
          <el-text style="height:35px;font-size: 35px;vertical-align:central">
            <AdaptiveIcon size="30px" :src="kotlinIcon"/>
            Kotlin
          </el-text>
          <el-text style="font-size: 20px">
            with SpringBoot,MybatisPlus ...
          </el-text>

        <el-text style="font-size: 35px">
          <AdaptiveIcon size="30px" :src="typescriptIcon"/>
          TypeScript
        </el-text>
        <el-text class="icon" style="font-size: 20px">
          with Vue3,Element-Plus,Vite ...
        </el-text>

        <el-text style="font-size: 35px">
          <AdaptiveIcon size="30px" :src="digitaloceanIcon"/>
          Digital Ocean
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
}
</style>
