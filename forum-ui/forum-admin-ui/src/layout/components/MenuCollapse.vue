<script lang="ts" setup>
import { useAppStore } from '@/store'

const appStore = useAppStore()
</script>

<template>
  <div
    class="f-c-c cursor-pointer rounded-4 p-6 text-22 transition-all-300 collapse"
    @click="appStore.toggleCollapsed()"
  >
    <i :class="appStore.collapsed ? 'i-line-md-menu-unfold-left' : 'i-line-md-menu-fold-left'" />
  </div>
</template>

<style lang="scss" scoped>
// 折叠面板
.collapse {
  height: 34px;

  .i-line-md-menu-fold-left {
    --un-icon: url('@/assets/icons/svg/close-collapse.svg');
    -webkit-mask: var(--un-icon) no-repeat;
    mask: var(--un-icon) no-repeat;
    -webkit-mask-size: 100% 100%;
    mask-size: 100% 100%;
    background-color: currentColor;
    color: inherit;
    display: inline-block;
    width: 1em;
    height: 1em;
  }

  .i-line-md-menu-unfold-left {
    --un-icon: url('@/assets/icons/svg/open-collapse.svg');
    -webkit-mask: var(--un-icon) no-repeat;
    mask: var(--un-icon) no-repeat;
    -webkit-mask-size: 100% 100%;
    mask-size: 100% 100%;
    background-color: currentColor;
    color: inherit;
    display: inline-block;
    width: 1em;
    height: 1em;
  }
}

.collapse:hover {
  background-color: var(--el-color-primary-light-9);
}
</style>
