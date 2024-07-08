<!--



TODO 这里需要右键出现菜单，右键菜单需要有刷新，关闭，关闭其他，关闭左侧，关闭右侧的功能
element-plus 下拉菜单和选择器没有默认展开的功能
要结合AppTab组件使用，在AppTab组件中右键后，展开下拉列表或者选择器，需要浮动在被右键单价的tabPan上
如果element-plus 中实现不了的话，可以自己实现一个；
参考 若依 https://gitee.com/lyforvue/ruoyi_vue3_ts/blob/master/ruoyi_ui_ts/src/layout/components/TagsView/index.vue
中的 contextmenu 类

-->

<script lang="ts" name="ContextMenu" setup>
import { computed, h } from 'vue'
import { useTabsStore } from '@/store'

const props = defineProps({
  show: {
    type: Boolean,
    default: false
  },
  currentPath: {
    type: String,
    default: ''
  },
  x: {
    type: Number,
    default: 0
  },
  y: {
    type: Number,
    default: 0
  }
})

const tabStore = useTabsStore()

const options = computed(() => [
  {
    label: '重新加载',
    key: 'reload',
    disabled: props.currentPath !== tabStore.activeTab,
    icon: () => h('i', { class: 'i-mdi:refresh text-14' })
  },
  {
    label: '关闭',
    key: 'close',
    disabled: tabStore.tabPans.length <= 1,
    icon: () => h('i', { class: 'i-mdi:close text-14' })
  },
  {
    label: '关闭其他',
    key: 'close-other',
    disabled: tabStore.tabPans.length <= 1,
    icon: () => h('i', { class: 'i-mdi:arrow-expand-horizontal text-14' })
  },
  {
    label: '关闭左侧',
    key: 'close-left',
    disabled: tabStore.tabPans.length <= 1 || props.currentPath === tabStore.tabPans[0].path,
    icon: () => h('i', { class: 'i-mdi:arrow-expand-left text-14' })
  },
  {
    label: '关闭右侧',
    key: 'close-right',
    disabled:
      tabStore.tabPans.length <= 1 ||
      props.currentPath === tabStore.tabPans[tabStore.tabPans.length - 1].path,
    icon: () => h('i', { class: 'i-mdi:arrow-expand-right text-14' })
  }
])
</script>

<template>
  <!--  <el-dropdown>-->
  <!--    <span class="el-dropdown-link">-->
  <!--      Dropdown List-->
  <!--      <el-icon class="el-icon&#45;&#45;right">-->
  <!--        <arrow-down />-->
  <!--      </el-icon>-->
  <!--    </span>-->
  <!--    <template #dropdown>-->
  <!--      <el-dropdown-menu>-->
  <!--        <el-dropdown-item v-for="(item, index) in options" :key="index">-->
  <!--          {{ item.label }}-->
  <!--        </el-dropdown-item>-->
  <!--      </el-dropdown-menu>-->
  <!--    </template>-->
  <!--  </el-dropdown>-->
</template>

<style lang="scss" scoped></style>
