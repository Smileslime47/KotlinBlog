<script setup lang="ts">
import {onMounted, ref, shallowRef} from "vue";
import axios from "axios";
import MarkDownIt from "markdown-it";
import hljs from "markdown-it-highlightjs";
import mathjax from "markdown-it-mathjax3";

import '../styles/tokyo-night-dark.css'

const props = defineProps(["articleId"])
const articleTitle = ref()
const articleContent = ref()
const htmlCode = shallowRef()

const md = MarkDownIt()
    .use(hljs, { inline: true })
    .use(mathjax)


onMounted(async () => {
  await axios.request({
    method: 'get',
    url: 'http://localhost:7777/article/article-by-id',
    params: {
      articleId: props.articleId
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