<script setup lang="ts">
import MarkDownIt from "markdown-it";
import hljs from "markdown-it-highlightjs";
import mathjax from "markdown-it-mathjax3";

import '~/styles/tokyo-night-dark.css'
import Constant from "~/Constant";
import httpService from "~/server/http";

const props = defineProps(["articleId"])
const articleTitle = ref()
const articleContent = ref()
const htmlCode = shallowRef()

const md = MarkDownIt()
    .use(hljs, {inline: true})
    .use(mathjax)


onMounted(async () => {
  await httpService.get(
      Constant.BASE_URL + Constant.article.api + Constant.article.getDetailById,
      {
        params: {
          id: props.articleId
        }
      }).then((response) => {
    articleContent.value = response.data.data.content
    articleTitle.value = response.data.data.title
    htmlCode.value = md.render(articleContent.value)
  })
})
</script>

<template>
  <div class="mdText" v-html="htmlCode"></div>
</template>

<style scoped>
.mdText {
  font-size: 17px;
}
</style>