<script setup lang="ts">
// 根据当前主题自动反色的图标组件
import {useDark} from "@vueuse/core";
const props = defineProps(["src", "size"])
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
  <el-image class="icon" :style="'width:'+size+';height:'+size" fit="fill" :src="props.src"/>
</template>

<style scoped>
.ep-image {
  --reverse: v-bind(isReverse);
  filter: var(--reverse);
}
</style>