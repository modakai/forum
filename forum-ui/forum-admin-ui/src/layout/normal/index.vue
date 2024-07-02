<script lang="ts" name="Layout" setup>
import SideBar from '@/layout/normal/sidebar/index.vue'
import Header from '@/layout/normal/header/index.vue'
import { ref } from 'vue'

// 控制是否展开
const collapsed = ref(false)
</script>

<template>
  <div class="layout">
    <aside :class="collapsed ? 'w-64' : 'w-220'" class="aside">
      <SideBar />
    </aside>

    <article class="article">
      <Header class="header" />

      <div class="main-container"></div>
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
    border-right: 1px solid var(--el-border-color);

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
    background-color: #666e87;
  }

  .header {
    border-bottom: 1px solid var(--el-border-color);
  }
}
</style>
