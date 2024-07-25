<script lang="ts" setup>
import { reactive, ref, watch } from 'vue'

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

const formLabelWidth = '100px'

const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: ''
})

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
  <el-dialog v-model="visible" :before-close="cancel" title="修改资料" width="500">
    <el-form :model="form">
      <el-form-item :label-width="formLabelWidth" label="昵称">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="性别">
        <el-select v-model="form.region" placeholder="Please select a zone">
          <el-option label="Zone No.1" value="shanghai" />
          <el-option label="Zone No.2" value="beijing" />
        </el-select>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="电话">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button type="primary" @click="confirm()"> 确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
