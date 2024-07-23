<script lang="ts" setup>
import { computed } from 'vue'
import { isExternal } from '@/utils/util'

const props = defineProps({
  to: {
    type: String,
    required: true
  }
})

const isExternalLink = computed(() => isExternal(props.to))
const type = computed(() => (isExternalLink.value ? 'a' : 'router-link'))
const linkProps = (to: string) => {
  if (isExternalLink.value) {
    return {
      href: to,
      target: '_blank',
      rel: 'noopener'
    }
  }
  return {
    to
  }
}
</script>

<template>
  <component :is="type" v-bind="linkProps(to)">
    <slot />
  </component>
</template>

<style lang="scss" scoped></style>
