<script lang="ts" name="Layout" setup>
import SideBar from '@/layout/normal/sidebar/index.vue'
import Header from '@/layout/normal/header/index.vue'
import AppMain from '@/layout/components/AppMain.vue'
import { useAppStore } from '@/store'

const appStore = useAppStore()
</script>

<template>
  <div class="layout">
    <!--  菜单列表  -->
    <SideBar :class="appStore.collapsed ? 'w-220' : 'w-64'" class="aside" />

    <article class="article">
      <Header class="header" />

      <div
        :class="appStore.isDark ? 'main-dark-bg-color' : 'main-light-bg-color'"
        class="main-container cus-scroll h-full flex-col flex-1"
      >
        <app-main />
      </div>
    </article>
  </div>
</template>

<style lang="scss" scoped>
.layout {
  display: flex;
  width: 100%;
  height: 100%;

  .aside {
    display: flex;
    flex-direction: column;

    // 动画
    transition-property: width;
    transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
    transition-duration: 0.3s;
  }

  .article {
    display: flex;
    flex-direction: column;
    flex: 1 1 0%;
    width: 0;

    .header {
      padding-right: 12px;
      padding-left: 12px;
      border-bottom: 1px solid var(--el-border-color);
    }

    .main-container {
      justify-content: space-between;
    }
  }
}
</style>
