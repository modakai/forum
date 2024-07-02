<script lang="ts" name="App" setup>
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const elConfig = {
  zIndex: 3000,
  locale: zhCn
}
</script>

<template>
  <el-config-provider :locale="elConfig.locale" :z-index="elConfig.zIndex">
    <router-view></router-view>
  </el-config-provider>
</template>
