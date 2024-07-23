<script lang="ts" name="Sidebar" setup>
import SideLogo from '@/layout/components/SideLogo.vue'
import { useAppStore, usePermissionStore } from '@/store'
import { computed } from 'vue'
import SideMenuItem from '@/layout/components/SideMenuItem.vue'
// 获取路由菜单
const appStore = useAppStore()

// 菜单路由
const permissionStore = usePermissionStore()

const sidebarRouters = computed(() => permissionStore.sidebarRouters)
</script>

<template>
  <div class="sidebar">
    <!--  Logo区域  -->
    <SideLogo class="side-logo" />
    <!--  菜单  -->
    <!--  菜单区域  -->
    <el-scrollbar class="menu_scrollbar" style="margin-top: 4px">
      <el-menu
        :collapse="!appStore.collapsed"
        :collapse-transition="false"
        :default-active="$route.path"
        class="menu-items"
        style="height: 100%"
        unique-opened
      >
        <side-menu-item
          v-for="(route, index) in sidebarRouters"
          :key="route.path + index"
          :base-path="route.path"
          :item="route"
        />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.side-logo {
  border-bottom: 1px solid var(--el-border-color-light);
}

.menu_scrollbar {
  width: 100%;
  height: calc(100vh - 60px);
}

.el-menu {
  border-right: none;
}

.sidebar {
  border-right: 1px solid var(--el-menu-border-color);
}
</style>
