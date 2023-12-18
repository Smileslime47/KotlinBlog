<template>
  <el-config-provider namespace="ep">
    <el-container>
      <el-header>
        <TopNavBar />
      </el-header>

      <el-main>
        <div style="height:60px"/>
        <router-view v-if="isRouterAlive"></router-view>
      </el-main>
    </el-container>
  </el-config-provider>
</template>

<style>
.ep-header,.ep-main{
  padding:0;
}

.ep-header{
  height: 60px;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1;
}

.home{
  flex-grow: 1;/*铺满剩余空间*/
}
</style>

<script setup>
const isRouterAlive = ref(true)
const reload = () => {
  console.log("reload test")
  isRouterAlive.value = false
  nextTick(()=>{
    isRouterAlive.value =true
  })
  // this.$nextTick(()=>{
  //   isRouterAlive.value =true
  // })
}
provide("reload",reload)
</script>