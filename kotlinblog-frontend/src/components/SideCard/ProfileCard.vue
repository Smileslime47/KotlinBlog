<script setup lang="ts">
import {ref} from "vue";

const githubProfileLink = ref("https://github.com/Smileslime47")
const leetCodeProfileLink = ref("https://leetcode.cn/u/smile_slime_47/")
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <el-space direction="vertical">
          <el-avatar :size="100" src="avatar.jpg"/>
          <el-text tag="b" size="large">Smile_slime_47</el-text>
        </el-space>
      </div>
    </template>

    <div class="profile-card-container">
      <el-space>
        <el-link :href="githubProfileLink">
          <el-image style="width: 30px; height: 30px" fit="fill" src="github.svg"/>
        </el-link>
        <el-link :href="leetCodeProfileLink">
          <el-image style="width: 30px; height: 30px" fit="fill" src="leetcode.svg"/>
        </el-link>
      </el-space>
    </div>
  </el-card>
</template>

<style>
.ep-card {
  margin: 10px
}

.card-header {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.profile-card-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>
