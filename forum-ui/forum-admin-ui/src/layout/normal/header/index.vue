<script lang="ts" name="Header" setup>
import Navbar from '@/layout/components/Navbar.vue'
</script>

<template>
  <div class="header">
    <Navbar />
    <!--    <TagsView />-->
  </div>
</template>

<style lang="scss" scoped>
.header {
  align-content: center;
  width: 100%;
  height: 60px;
  background-color: var(--el-bg-color);
}
</style>
