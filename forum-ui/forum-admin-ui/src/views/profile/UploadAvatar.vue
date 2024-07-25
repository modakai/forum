<script lang="ts" setup>
import { ref, watch } from 'vue'
import Upload from '@/components/common/Upload.vue'

const props = defineProps({
  isShow: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['cancel', 'ok'])

let loading = ref(false)

let visible = ref(false)
const cancel = () => {
  // visible.value = !visible.value
  // 触发自定义事件
  emit('cancel')
}
const confirm = () => {
  loading.value = true
  // 发送请求
  // 请求成功后待用父组件方法
  setTimeout(() => {
    loading.value = false
    emit('ok')
  }, 1000)
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
</script>

<template>
  <el-dialog v-model="visible" :before-close="cancel" draggable title="上传头像" width="350">
    <upload :limit="1" style="display: flex; justify-content: center" />
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button :loading="loading" type="primary" @click="confirm()"> 确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
