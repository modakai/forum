<script lang="ts" setup></script>

<template>
  <section class="app-main">
    <router-view v-slot="{ Component, route }">
      <transition mode="out-in" name="el-fade-in">
        <keep-alive>
          <component :is="Component" :key="route.fullPath" />
        </keep-alive>
      </transition>
    </router-view>
  </section>
  <footer class="f-c-c text-gray-500">
    <p>
      Copyright © 2024
      <a class="transition" href="https://github.com/modakai" target="__blank"> modakai </a>
    </p>
  </footer>
</template>
<style lang="scss" scoped>
footer {
  background-color: var(--el-bg-color);
  font-size: 14px;
  padding: 12px 0;
  color: rgb(107 114 128 / 1);

  .transition {
    transition-property: color, background-color, border-color, outline-color, text-decoration-color,
      fill, stroke, opacity, box-shadow, transform, filter, backdrop-filter;
    transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
    transition-duration: 0.15s;
  }

  a:hover {
    color: var(--el-color-primary);
    //  打开下划线
    text-decoration: underline;
  }
}
</style>
