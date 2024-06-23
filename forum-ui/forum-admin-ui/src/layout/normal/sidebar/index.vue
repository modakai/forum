<script lang="ts" name="Sidebar" setup>
import SideLogo from '@/layout/components/SideLogo.vue'
import SideMenu from '@/layout/components/SideMenu.vue'
import { constantsRoutes } from '@/router'
// 获取路由菜单
</script>

<template>
  <div class="sidebar">
    <!--  Logo区域  -->
    <SideLogo class="side-logo" />
    <!--  菜单  -->
    <!--  菜单区域  -->
    <el-scrollbar class="menu_scrollbar" style="margin-top: 10px">
      <el-menu :collapse="false" :default-active="'/'">
        <SideMenu :menu-list="constantsRoutes" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.side-logo {
  border-bottom: 1px solid var(--el-border-color-light);
}
</style>
