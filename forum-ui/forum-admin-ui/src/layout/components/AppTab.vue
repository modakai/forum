<script lang="ts" setup>
import '@/styles/components/appTab.scss'
import type { TabPaneName, TabsPaneContext } from 'element-plus'
import { useTabsStore } from '@/store'
import router from '@/router'

const tabsStore = useTabsStore()

/**
 * 移除tab
 * @param name
 */
const tabsRemove = (name: TabPaneName) => {
  tabsStore.remove(name)
}

/**
 * 路由跳转
 * @param pane
 */
const goRoute = (pane: TabsPaneContext) => {
  router.push(pane.props.name as string)
}
</script>

<template>
  <el-tabs
    v-model="$router.currentRoute.value.path"
    class="tabs"
    type="card"
    @tab-remove="tabsRemove"
    @tab-click="goRoute"
  >
    <el-tab-pane
      v-for="tab in tabsStore.tabPans"
      :key="tab.path"
      :closable="!(tabsStore.tabPans.length === 1)"
      :label="tab.label"
      :name="tab.path"
    />
  </el-tabs>
</template>

<style lang="scss" scoped></style>
