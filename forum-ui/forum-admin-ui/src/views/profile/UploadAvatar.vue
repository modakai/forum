<script lang="ts" setup>
import { ref } from 'vue'
import type { UploadProps } from 'element-plus'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import { getToken } from '@/utils/tokenUtil'

const props = defineProps({
  limit: {
    type: Number,
    default: 1
  },
  action: {
    type: String,
    default: import.meta.env.VITE_APP_BASE_API + '/upload/img'
  }
})

const imageUrl = ref()

const hasAvatar = ref(false)
// 提供对外获取头像地址的方法
defineExpose({
  getAvatarUrl: (): string => imageUrl.value,
  hasAvatar: hasAvatar,
  onAvatarChange
})

// 当头像发生变化时，更新 hasAvatar 的值
function onAvatarChange(hasAvatarValue: boolean) {
  hasAvatar.value = hasAvatarValue
}

const handleAvatarSuccess: UploadProps['onSuccess'] = (response) => {
  if (response.code !== 200) {
    ElMessage.error(response.msg)
  } else {
    imageUrl.value = response.data
    onAvatarChange(true)
  }
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  console.log(rawFile.type)
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('上传图片类型错误！仅支持jpg、jpeg、png格式')
    return false
  } else if (rawFile.size / 1024 / 1024 > 10) {
    ElMessage.error('上传图片大小不能超过10M')
    return false
  }
  return true
}
</script>

<template>
  <el-upload
    :action="action + '?type=avatar'"
    :before-upload="beforeAvatarUpload"
    :headers="{ 'Authorization-ADMIN': 'Bearer' + getToken() }"
    :limit="limit"
    :name="'file'"
    :on-success="handleAvatarSuccess"
    :show-file-list="false"
    class="avatar-uploader"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon">
      <Plus />
    </el-icon>
  </el-upload>
</template>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
