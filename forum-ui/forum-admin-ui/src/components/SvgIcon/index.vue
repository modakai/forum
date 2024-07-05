<script lang="ts" setup>
import { computed } from 'vue'

interface Props {
  iconClass: string
  className?: string
  color?: string
}

const props = withDefaults(defineProps<Props>(), {
  className: '',
  color: '#000'
})

// icon的名字
const iconName = computed(() => `#icon-${props.iconClass}`)

// svg的class
const svgClass = computed(() => {
  if (props.className) {
    return `svg-icon ${props.className}`
  }
  return 'svg-icon'
})
</script>

<template>
  <svg :class="svgClass" aria-hidden="true">
    <use :fill="color" :xlink:href="iconName" />
  </svg>
</template>

<style lang="scss" scoped>
.sub-el-icon,
.nav-icon {
  display: inline-block;
  font-size: 15px;
  margin-right: 12px;
  position: relative;
}

.svg-icon {
  width: 1em;
  height: 1em;
  position: relative;
  fill: currentColor;
  vertical-align: -2px;
}
</style>
