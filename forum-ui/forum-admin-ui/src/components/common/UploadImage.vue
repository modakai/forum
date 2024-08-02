<script lang="ts" setup>
import { ref } from 'vue'
import type { UploadProps } from 'element-plus'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

const props = defineProps({
  limit: {
    type: Number,
    default: 1
  },
  action: {
    type: String,
    default: '/upload/img'
  }
})

const imageUrl = ref('')

const handleAvatarSuccess: UploadProps['onSuccess'] = (response) => {
  console.log('图片上传')
  console.log(response)
  if (response.code !== 200) {
    ElMessage.error(response.msg)
  } else {
    imageUrl.value = response.data
  }
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' || rawFile.type !== 'image/jpeg') {
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
    :action="action"
    :before-upload="beforeAvatarUpload"
    :limit="limit"
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
