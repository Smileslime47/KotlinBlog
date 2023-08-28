<script setup lang="ts">
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import FrameCard from "~/components/SideCard/FrameCard.vue";
import axios from "axios";

const route = useRoute()
const articleId = ref(route.params.aid)
const article = ref({
  id: "",
  title: "",
  createTime: ""
})

onMounted(async () => {
  await axios.request({
    method: 'get',
    url: 'http://localhost:7777/article/info-by-id',
    params: {
      articleId: articleId.value
    }
  }).then((response) => {
    article.value = response.data.data
  })
})
</script>

<template>
  <el-container>
    <el-aside width="400px">
      <BaseSide/>
    </el-aside>
    <el-main>
      <div class="HomePanel">
        <el-card>
          <template #header>
            <div class="title">
              <span>{{ article.title }}</span>
            </div>
            <el-text>{{ article.createTime }}</el-text>
          </template>
          <MdTextArea :articleId="articleId"></MdTextArea>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.HomePanel {
  margin-top: 10px;
  padding: 10px 20px
}

.title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 30px;
}
</style>