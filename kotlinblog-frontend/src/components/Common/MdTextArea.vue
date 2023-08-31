<script setup lang="ts">
import MarkDownIt from "markdown-it";
import hljs from "markdown-it-highlightjs";
import mathjax from "markdown-it-mathjax3";
import '~/styles/tokyo-night-dark.css'

import Constant from "~/constant/Constant";
import httpService from "~/server/http";

const props = defineProps(["articleId"])
const articleTitle = ref()
const articleContent = ref()
const htmlCode = shallowRef()

const md = MarkDownIt()
    //代码高亮
    .use(hljs, {inline: true})
    //Mathjax公式
    .use(mathjax)

onMounted(async () => {
  //渲染文章
  await httpService.get(
      Constant.BASE_URL + Constant.article.api + Constant.article.getDetailById,
      {
        params: {
          id: props.articleId
        }
      }).then((response) => {
    //获取文章标题
    articleTitle.value = response.data.data.title
    //获取文章内容
    articleContent.value = response.data.data.content
    //渲染文章内容为HTML代码
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