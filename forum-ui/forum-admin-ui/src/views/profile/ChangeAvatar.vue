<script lang="ts" setup>
import { ref, watch } from 'vue'
import UploadAvatar from '@/views/profile/UploadAvatar.vue'
import { changeAvatar } from '@/api/user'

const props = defineProps({
  isShow: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['cancel', 'ok'])

let loading = ref(false)
let visible = ref(false)
let disabled = ref(true)
const avatar = ref<InstanceType<typeof UploadAvatar>>()

// 获取upload-avatar组件是否已经上传有头像，如果没上传则禁用确认按钮
// 监听子组件的 hasAvatar 状态
const updateDisabledStatus = () => {
  console.log(avatar.value?.hasAvatar.valueOf())
  disabled.value = !avatar.value?.hasAvatar.valueOf()
}

const cancel = () => {
  // visible.value = !visible.value
  // 触发自定义事件
  emit('cancel')
}
const confirm = (avatarUrl: string) => {
  emit('ok', avatarUrl)
}

// 表单提交方法
const submit = async () => {
  loading.value = true
  // 发送更改头像请求
  let avatarUrl = avatar.value?.getAvatarUrl() as string
  try {
    await changeAvatar(avatarUrl)
    confirm(avatarUrl)
  } finally {
    loading.value = false
  }
}

watch(
  () => props.isShow,
  (val) => {
    visible.value = val
  },
  {
    immediate: true
  }
)
watch(
  () => avatar.value?.hasAvatar.valueOf(),
  () => {
    updateDisabledStatus()
  }
)
updateDisabledStatus()
</script>

<template>
  <el-dialog v-model="visible" :before-close="cancel" draggable title="上传头像" width="350">
    <upload-avatar ref="avatar" :limit="1" style="display: flex; justify-content: center" />
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button :disabled="disabled" :loading="loading" type="primary" @click="submit()">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
