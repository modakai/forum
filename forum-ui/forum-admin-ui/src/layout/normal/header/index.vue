<script lang="ts" name="Header" setup>
import MenuCollapse from '@/layout/components/MenuCollapse.vue'
import AppTab from '@/layout/components/AppTab.vue'
import SvgIcon from '@/components/SvgIcon/index.vue'
import UserAvatar from '@/layout/components/UserAvatar.vue'
</script>

<template>
  <div class="header">
    <MenuCollapse />

    <AppTab class="w-0 flex-1 px-12" />

    <span class="mx-6 opacity-20">|</span>

    <div class="flex header-right px-12">
      <!--  主题切换图标：light dark    -->
      <svg-icon class="mr-16 cursor-pointer" icon-class="sun" />

      <!-- 全屏图标 -->
      <svg-icon class="mr-16 cursor-pointer" icon-class="maximize" />

      <!-- 头像 -->
      <user-avatar />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.header {
  display: flex;
  align-content: center;
  align-items: center;
  width: 100%;
  height: 60px;
  background-color: var(--el-bg-color);

  .header-right {
    height: 42px;
    align-items: center;
    font-size: 18px;
    flex-shrink: 0;
  }
}
</style>
