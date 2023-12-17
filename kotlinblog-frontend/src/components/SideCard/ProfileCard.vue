<script setup lang="ts">
import {useDark} from "@vueuse/core";
import Constant from "~/constant/Constant";
import avatar from '../../assets/avatar.jpg'
import githubIcon from '../../assets/github.svg'
import leetCodeIcon from '../../assets/leetCode.svg'

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
const avatarUrl = ref(Constant.ASSETS_URL + "/avatar.jpg")
const githubProfileLink = ref("https://github.com/Smileslime47")
const leetCodeProfileLink = ref("https://leetcode.cn/u/smile_slime_47/")
</script>

<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <el-space direction="vertical">
          <el-avatar :size="100" :src="avatar"/>
          <el-text tag="b" size="large">Smile_slime_47</el-text>
        </el-space>
      </div>
    </template>

    <div class="profile-card-container">
      <el-space>
        <el-link :href="githubProfileLink">
          <AdaptiveIcon size="30px" :src="githubIcon"/>
        </el-link>
        <el-link :href="leetCodeProfileLink">
          <AdaptiveIcon size="30px" :src="leetCodeIcon"/>
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

.ep-image {
  --reverse: v-bind(isReverse);
  filter: var(--reverse);
}
</style>
