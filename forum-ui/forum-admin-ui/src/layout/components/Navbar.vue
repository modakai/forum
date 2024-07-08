<script lang="ts" name="Navbar" setup>
import { useAppStore } from '@/store'
import AppTab from '@/layout/components/AppTab.vue'

const appStore = useAppStore()
</script>

<template>
  <div class="navBar">
    <div
      class="f-c-c cursor-pointer rounded-4 p-6 text-22 transition-all-300 collapse"
      @click="appStore.collapsed = !appStore.collapsed"
    >
      <i :class="appStore.collapsed ? 'i-line-md-menu-unfold-left' : 'i-line-md-menu-fold-left'" />
    </div>

    <AppTab class="px-12" />
  </div>
</template>

<style lang="scss" scoped>
.navBar {
  display: flex;
  align-content: center;
}
</style>
