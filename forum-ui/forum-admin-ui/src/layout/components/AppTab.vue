<script lang="ts" setup>
import '@/styles/components/appTab.scss'
import type { TabPaneName, TabsPaneContext } from 'element-plus'
import { useTabsStore } from '@/store'
import router from '@/router'
import ContextMenu from '@/layout/components/ContextMenu.vue'
import { nextTick, reactive } from 'vue'

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

const contextMenuOption = reactive({
  show: false,
  x: 0,
  y: 0,
  currentPath: ''
})

/**
 * 唤醒上下文
 * @param e
 */
async function handlerContextMenu(e: PointerEvent) {
  const { x, y } = e
  if (e.target) {
    contextMenuOption.show = false
    // tab-path 要截取test中tab-后的值
    let currentPath = e.target['id'].substring(4)

    Object.assign(contextMenuOption, { x, y, currentPath })
    await nextTick()
    contextMenuOption.show = true
  }
}
</script>

<template>
  <div>
    <el-tabs
      v-model="$router.currentRoute.value.path"
      class="tabs"
      type="card"
      @tab-remove="tabsRemove"
      @tab-click="goRoute"
      @contextmenu.prevent="handlerContextMenu($event)"
    >
      <el-tab-pane
        v-for="tab in tabsStore.tabPans"
        :key="tab.path"
        :closable="!(tabsStore.tabPans.length === 1)"
        :label="tab.label"
        :name="tab.path"
      >
      </el-tab-pane>
    </el-tabs>

    <!-- TODO 功能尚未实现 TabPan右键单价出现菜单   -->
    <ContextMenu
      v-if="contextMenuOption.show"
      v-model:show="contextMenuOption.show"
      :current-path="contextMenuOption.currentPath"
      :x="contextMenuOption.x"
      :y="contextMenuOption.y"
    />
  </div>
</template>

<style lang="scss" scoped></style>
