<script lang="ts" setup>
import { ref, watch } from 'vue'

const props = defineProps({
  isShow: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['cancel', 'ok'])

let visible = ref(false)
const cancel = () => {
  // visible.value = !visible.value
  // 触发自定义事件
  emit('cancel')
}
const confirm = () => {
  emit('ok')
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
  <el-dialog v-model="visible" :before-close="cancel" title="修改密码" width="800">
    <span>This is a message</span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button type="primary" @click="confirm()"> 确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
