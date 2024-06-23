<script lang="ts" name="SideLogo" setup>
import { Logo } from '@/components'

const title = import.meta.env.VITE_APP_TITLE
</script>

<template>
  <router-link class="h-60 f-c-c" to="/">
    <Logo />
    <!--    v-show="!appStore.collapsed"-->
    <h2 class="ml-10 max-w-140 flex-shrink-0 text-16 color-primary font-bold">
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
