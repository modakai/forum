<script lang="ts" name="SideLogo" setup>
import { Logo } from '@/components'
import { useAppStore } from '@/store'

const title = import.meta.env.VITE_APP_TITLE

const appStore = useAppStore()
</script>

<template>
  <router-link class="h-60 f-c-c" style="flex: none" to="/">
    <Logo />
    <!--    v-show="!appStore.collapsed"-->
    <h2
      v-show="appStore.collapsed"
      class="ml-10 max-w-140 flex-shrink-0 text-16 color-primary font-bold"
    >
      {{ title }}
    </h2>
  </router-link>
</template>

<style lang="scss" scoped>
.h-60 {
  height: 60px;
}

.f-c-c {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.ml-10 {
  margin-left: 10px;
}

.max-w-140 {
  max-width: 140px;
}

.flex-shrink-0 {
  flex-shrink: 0;
}

.text-16 {
  font-size: 16px;
}

.color-primary {
  color: var(--el-color-primary);
}

.font-bold {
  font-weight: bold;
}
</style>
