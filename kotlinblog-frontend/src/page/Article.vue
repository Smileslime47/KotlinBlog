<script setup lang="ts">
import {onMounted} from "vue";
import {useRoute, useRouter} from "vue-router";
import axios from "axios";

const route=useRoute()

onMounted(async ()=>{
  console.log(route)
  await axios.request({
    method:'get',
    url:'http://localhost:7777/article/all-by-parent',
    params:{
      path:route.path
    }
  }).then((response) => {
    console.log(response.data)
  })
})
</script>

<template>
  <div class="HomePanel">
    <el-row>
      <el-col :offset="0" :span="18">
        <el-timeline>
          <el-timeline-item timestamp="2018/4/12" placement="top">
            <el-card>
              <h4>Update Github template</h4>
              <p>Tom committed 2018/4/12 20:46</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-col>

      <el-col :span="6">
        <ProfileCard/>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.HomePanel{
  margin-top:10px;
  padding:10px 20px
}
</style>