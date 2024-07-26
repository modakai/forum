<script lang="ts" setup>
import { reactive, ref, watch } from 'vue'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import type { ChangePasswordForm } from '@/api/user/type'
import { changePassword } from '@/api/user'

const props = defineProps({
  isShow: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['cancel', 'ok'])

let loading = ref(false)

const form = reactive<ChangePasswordForm>({
  oldPassword: '',
  newPassword: ''
})
const formRef = ref<FormInstance>()
const formRules = reactive<FormRules<ChangePasswordForm>>({
  oldPassword: [
    {
      required: true,
      message: '请输入旧密码',
      trigger: 'blur'
    },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
  ],
  newPassword: [
    {
      required: true,
      message: '请输入新密码',
      trigger: 'blur'
    },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
  ]
})

let visible = ref(false)
const cancel = () => {
  // visible.value = !visible.value
  // 清空数据
  formRef.value?.resetFields()
  form.oldPassword = ''
  form.newPassword = ''
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

/**
 * 提交表单
 */
const submitForm = () => {
  loading.value = true
  formRef.value?.validate((valid) => {
    if (valid) {
      // confirm()
      // setTimeout(() => {
      //   loading.value = false
      //   confirm()
      // }, 1000)
      // 发送请求
      changePassword(form).then((res) => {
        loading.value = false
        if (res.code === 200) {
          ElMessage.success({
            message: '修改成功！下次登入请使用新密码',
            type: 'success',
            duration: 5 * 1000
          })
          confirm()
        } else if (res.code === 101) {
          ElMessage.error({
            message: '旧密码错误',
            type: 'error',
            duration: 5 * 1000
          })
        }
      })
    } else {
      loading.value = false
    }
  })
}
</script>

<template>
  <el-dialog v-model="visible" :before-close="cancel" title="修改密码" width="430">
    <el-form
      ref="formRef"
      :model="form"
      :rules="formRules"
      label-width="auto"
      size="large"
      status-icon
      style="width: 100%"
    >
      <!--旧密码-->
      <el-form-item prop="oldPassword">
        <el-input v-model="form.oldPassword" placeholder="旧密码" type="password" />
      </el-form-item>
      <!--新密码-->
      <el-form-item prop="newPassword">
        <el-input v-model="form.newPassword" placeholder="新密码" type="password" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button :loading="loading" type="primary" @click="submitForm()"> 确认</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped></style>
