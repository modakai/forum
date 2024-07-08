<script lang="ts" name="Header" setup>
import MenuCollapse from '@/layout/components/MenuCollapse.vue'
import AppTab from '@/layout/components/AppTab.vue'
</script>

<template>
  <div class="header">
    <MenuCollapse />

    <AppTab class="w-0 flex-1 px-12" />

    <span class="mx-6 opacity-20">|</span>

    <div style="width: 325px"></div>
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
}
</style>
