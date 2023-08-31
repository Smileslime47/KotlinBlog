<script setup lang="ts">
import Constant from "~/constant/Constant";
import httpService from "~/server/http";
import InfoPage from "~/page/InfoPage.vue";

const route = useRoute()
const router = useRouter()
const parentId = ref(route.params.cid)
const articleId = ref(route.params.aid)
const article = ref({
  id: "",
  title: "",
  createTime: ""
})

const backToArchive = ( ) => router.push("/category/" + parentId.value)


onMounted(async () => {
  await httpService.get(
      Constant.article.api + Constant.article.getInfoById,
      {
        params: {id: articleId.value}
      })
      .then((response) => {
        article.value = response.data.data
      })
})
</script>

<template>
  <InfoPage>
    <div class="HomePanel">
      <el-card>
        <template #header>
          <div class="card-header">
            <el-page-header @click="backToArchive">
              <template #content>
                <span class="text-large font-600 mr-3"> {{ article.title }} </span>
              </template>
            </el-page-header>
            <el-text>{{ article.createTime }}</el-text>
          </div>
        </template>
        <MdTextArea :articleId="articleId"></MdTextArea>
      </el-card>
    </div>
  </InfoPage>
</template>

<style scoped>
.HomePanel {
  margin-top: 10px;
  padding: 10px 20px
}

.card-header {
  flex-direction: row;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>