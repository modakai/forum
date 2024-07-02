<script lang="ts" setup>
//获取父组件传递过来的全部路由数组
const { menuList } = defineProps(['menuList'])
</script>

<template>
  <div class="menu-items">
    <template v-for="item in menuList" :key="item.path">
      <!--没有子路由-->
      <template v-if="!item.children">
        <el-menu-item v-if="!item.meta.hidden" :index="item.path">
          <svg-icon :icon-class="item.meta.icon" />
          <template #title>
            <span class="ml-10">{{ item.meta.title }}</span>
          </template>
        </el-menu-item>
      </template>
      <!-- 有子路由且一个路由 -->
      <template v-if="item.children && item.children.length == 1">
        <el-menu-item v-if="!item.children[0].meta.hidden" :index="item.children[0].path">
          <svg-icon :icon-class="item.children[0].meta.icon" />
          <template #title>
            <span class="ml-10">{{ item.children[0].meta.title }}</span>
          </template>
        </el-menu-item>
      </template>
      <!-- 有子路由且个数大于一个1 -->
      <el-sub-menu v-if="item.children && item.children.length > 1" :index="item.path">
        <template #title>
          <svg-icon :icon-class="item.meta.icon" />
          <span class="ml-10">{{ item.meta.title }}</span>
        </template>
        <SideMenu :menuList="item.children"></SideMenu>
      </el-sub-menu>
    </template>
  </div>
</template>

<style lang="scss" scoped>
.ml-10 {
  margin-left: 10px;
}

// 样式穿透
.el-menu-item,
.el-sub-menu {
  margin-top: 6px;
}

.menu-items:not(.el-menu--collapse):not(.el-menu--popup-container) .el-menu-item {
  &::before {
    z-index: auto;
    position: absolute;
    left: 8px;
    right: 8px;
    top: 0;
    bottom: 0;
    content: '';
    pointer-events: none;
    border-radius: var(--el-border-radius-base);
    transition: background-color 0.3s var(--el-transition-function-ease-in-out-bezier);
  }

  &.is-active::before {
    background-color: rgb(var(--el-color-primary-rgb), 0.13);
    border-left: 4px solid var(--el-menu-active-color);
  }

  &.is-active:hover {
    background-color: transparent;
  }
}
</style>
