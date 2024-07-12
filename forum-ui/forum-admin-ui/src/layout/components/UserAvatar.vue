<script lang="ts" setup>
const errorHandler = () => true

const dropdownList = [
  {
    label: '个人中心',
    icon: 'user',
    path: '/user/center'
  },
  {
    label: '退出登录',
    icon: 'logout',
    path: '/login'
  }
]
</script>

<template>
  <el-dropdown class="avatar" style="line-height: normal">
    <div class="cursor-pointer mr-16 flex" style="align-items: center">
      <el-avatar :size="36" src="@/src/assets/images/img.png" @error="errorHandler" />
      <div class="userInfo">
        <span>超级管理员</span>
        <span>[超级管理员]</span>
      </div>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item v-for="(item, index) in dropdownList" :key="index">
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<style lang="scss" scoped>
.userInfo {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-shrink: 0;
  margin-left: 12px;

  span {
    &:first-child {
      font-size: 14px;
    }

    &:last-child {
      font-size: 12px;
    }
  }
}
</style>
