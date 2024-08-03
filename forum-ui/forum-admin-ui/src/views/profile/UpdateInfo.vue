<script lang="ts" setup>
import { reactive, ref, watch } from 'vue'
import type { UserInfo } from '@/store/modules/types/types'
import type { ChangeProfileForm } from '@/api/user/type'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { changeProfile } from '@/api/user'

const props = defineProps({
  isShow: {
    type: Boolean,
    default: false
  },
  userInfo: {
    type: Object as () => UserInfo,
    default: () => ({})
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
  emit('ok', form)
}

const formLabelWidth = '100px'

// 把props的对象赋值给form
const form = reactive<ChangeProfileForm>({
  nickName: '',
  gender: false,
  phone: ''
})

const formRef = ref<FormInstance>()
const formRules = reactive<FormRules<ChangeProfileForm>>({
  nickName: [
    {
      required: true,
      message: '请输入昵称',
      trigger: 'blur'
    }
  ],
  gender: [
    {
      required: true,
      message: '请选择性别',
      trigger: 'blur'
    }
  ],
  phone: [
    {
      required: true,
      message: '请输入电话',
      trigger: 'blur'
    },
    {
      pattern: /^1[3456789]\d{9}$/,
      message: '请输入正确的手机号码',
      trigger: 'blur'
    }
  ]
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

/**
 * 提交表单
 */
const submitForm = () => {
  loading.value = true
  // 1 校验表单数据
  formRef.value?.validate((valid) => {
    if (valid) {
      // 发送请求
      changeProfile(form).then((res) => {
        loading.value = false
        if (res.code === 200) {
          ElMessage.success({
            message: '修改成功！',
            type: 'success',
            duration: 5 * 1000
          })
          confirm()
        }
      })
    } else {
      loading.value = false
    }
  })
}

/**
 * 设置form数据
 * @param data 数据
 */
const setForm = (data: UserInfo) => {
  form.phone = data.phone
  form.nickName = data.nickName
  form.gender = data.gender
}

defineExpose({
  setForm
})
</script>

<template>
  <el-dialog v-model="visible" :before-close="cancel" title="修改资料" width="500">
    <el-form ref="formRef" :model="form" :rules="formRules">
      <el-form-item :label-width="formLabelWidth" label="昵称" prop="nickName">
        <el-input v-model="form.nickName" autocomplete="off" />
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="性别" prop="gender">
        <el-select v-model="form.gender" placeholder="请选择性别">
          <el-option :value="false" label="男" />
          <el-option :value="true" label="女" />
        </el-select>
      </el-form-item>
      <el-form-item :label-width="formLabelWidth" label="电话" prop="phone">
        <el-input v-model="form.phone" autocomplete="off" />
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
