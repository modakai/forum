<script lang="ts" setup>
//获取父组件传递过来的全部路由数组
const { menuList } = defineProps(['menuList'])
console.log(menuList)
</script>

<template>
  <template v-for="item in menuList" :key="item.path">
    <!--没有子路由-->
    <template v-if="!item.children">
      <el-menu-item v-if="!item.meta.hidden" :index="item.path">
        <svg-icon :icon-class="item.meta.icon" class="" />
        <template #title>
          <span class="ml-10">{{ item.meta.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 有子路由且个数大于一个1 -->
    <el-sub-menu v-if="item.children" :index="item.path">
      <template #title>
        <svg-icon :icon-class="item.meta.icon" />
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
.el-menu-item {
  height: 42px;
}

.el-sub-menu__title {
  height: 42px !important;
}
</style>
