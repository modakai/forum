<script lang="ts" setup>
import AppLink from '@/layout/components/Link.vue'
import type { RouteRecord } from 'vue-router'
import { useTabsStore } from '@/store'
import { ref } from 'vue'
import { getNormalPath, isExternal } from '@/utils/util'

const tabsStore = useTabsStore()

const props = defineProps({
  item: {
    type: Object,
    required: true
  },
  isNest: {
    type: Boolean,
    default: false
  },
  basePath: {
    type: String,
    default: ''
  }
})

const onlyOneChild = ref()

const hasOneShowingChild = (children = [] as RouteRecord[], parent: any) => {
  if (!children) {
    children = []
  }
  const showingChildren = children.filter((item) => {
    if (item.meta.visible) {
      // Temp set(will be used if only has one showing child)
      onlyOneChild.value = item
      return true
    } else {
      return false
    }
  })

  // When there is only one child router, the child router is displayed by default
  if (showingChildren.length === 1) {
    return true
  }

  // Show parent if there are no child router to display
  if (showingChildren.length === 0) {
    onlyOneChild.value = { ...parent, path: '', noShowingChildren: true }
    console.log(!(!onlyOneChild.value.children || onlyOneChild.value.noShowingChildren))
    return true
  }

  return false
}

const resolvePath = (routePath: string) => {
  if (isExternal(routePath)) {
    return routePath
  }
  if (isExternal(props.basePath)) {
    return props.basePath
  }
  // if (routeQuery) {
  //   let query = JSON.parse(routeQuery);
  //   return {
  //     path: getNormalPath(props.basePath + "/" + routePath),
  //     query: query,
  //   };
  // }
  return getNormalPath(props.basePath + '/' + routePath)
}
</script>

<template>
  <template v-if="item.meta && item.meta.visible">
    <!--  判断是否只有一个子孩子  -->
    <template
      v-if="
        hasOneShowingChild(item.children, item) &&
        (!onlyOneChild.children || onlyOneChild.noShowingChildren)
      "
    >
      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path)">
        <el-menu-item :index="resolvePath(onlyOneChild.path)">
          <svg-icon :icon-class="onlyOneChild.meta.icon" />
          <template #title>
            <span class="ml-10">{{ onlyOneChild.meta.title }}</span>
          </template>
        </el-menu-item>
      </app-link>
    </template>

    <el-sub-menu v-else :index="resolvePath(item.path)">
      <template v-if="item.meta" #title>
        <svg-icon :icon-class="item.meta.icon" class="icon-color" />
        <span class="ml-10">{{ item.meta.title }}</span>
      </template>
      <side-menu-item
        v-for="child in item.children"
        :key="child.path"
        :base-path="resolvePath(child.path)"
        :item="child"
      />
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
