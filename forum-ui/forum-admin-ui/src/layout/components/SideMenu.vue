<script lang="ts" setup>
//获取父组件传递过来的全部路由数组
import router from '@/router'

const { menuList } = defineProps(['menuList'])

const goRoute = (path: string) => {
  router.push(path)
}
</script>

<template>
  <template v-for="item in menuList" :key="item.path">
    <!--没有子路由-->
    <template v-if="!item.children">
      <el-menu-item v-if="item.meta.visible" :index="item.path" @click="goRoute(item.path)">
        <svg-icon :icon-class="item.meta.icon" />
        <template #title>
          <span class="ml-10">{{ item.meta.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 有子路由且一个路由 -->
    <template v-if="item.children && item.children.length == 1">
      <el-menu-item
        v-if="item.children[0].meta.visible"
        :index="item.children[0].path"
        @click="goRoute(item.children[0].path)"
      >
        <svg-icon :icon-class="item.children[0].meta.icon" />
        <template #title>
          <span class="ml-10">{{ item.children[0].meta.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 有子路由且个数大于一个1 -->
    <el-sub-menu v-if="item.children && item.children.length > 1" :index="item.path">
      <template #title>
        <svg-icon :icon-class="item.meta.icon" class="icon-color" />
        <span class="ml-10">{{ item.meta.title }}</span>
      </template>
      <SideMenu :menuList="item.children"></SideMenu>
    </el-sub-menu>
  </template>
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
</style>
