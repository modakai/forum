<script lang="ts" name="App" setup>
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { computed, defineAsyncComponent, markRaw } from 'vue'

const elConfig = {
  zIndex: 3000,
  locale: zhCn
}
const Layout = computed(() => {
  return markRaw(defineAsyncComponent(() => import('@/layout/normal/index.vue')))
})
</script>

<template>
  <el-config-provider :locale="elConfig.locale" :z-index="elConfig.zIndex">
    <router-view></router-view>
  </el-config-provider>
</template>
