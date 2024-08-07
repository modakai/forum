<script lang="ts" name="Login" setup>
import { onMounted, reactive, ref } from 'vue'
import { Key, Lock, User } from '@element-plus/icons-vue'
import { ElNotification, type FormInstance, type FormRules } from 'element-plus'
import type { NormalLoginForm, SmsLoginForm } from '@/api/user/type'
import { genNanoId, getGreeting } from '@/utils/util'
import { getCaptcha } from '@/api/captcha'
import { useUserStore } from '@/store'
import router from '@/router'

const title = import.meta.env.VITE_APP_TITLE

// 获取userStore
const userStore = useUserStore()

// 密码登入表单
const normalForm = reactive<NormalLoginForm>({
  username: '',
  password: '',
  captcha: '',
  rememberMe: true,
  loginType: 'normal',
  // 密码登入的验证码key
  key: genNanoId()
})
const ruleNormalForm = ref<FormInstance>()
const normalFormRules = reactive<FormRules<NormalLoginForm>>({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
  ],
  captcha: [
    { required: true, message: '请输入验证码', trigger: 'blur' },
    { min: 4, max: 4, message: '长度为 4 个字符', trigger: 'blur' }
  ]
})

// 短信登入类型
const smsForm = reactive<SmsLoginForm>({
  username: '',
  captcha: '',
  rememberMe: true,
  loginType: 'sms'
})
const ruleSmsForm = ref<FormInstance>()
const smsFormRules = reactive<FormRules<SmsLoginForm>>({
  username: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { min: 11, max: 11, message: '长度为 11 个字符', trigger: 'blur' },
    // 正则表达式校验手机号
    { pattern: /^1[3456789]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ],
  captcha: [
    { required: true, message: '请输入验证码', trigger: 'blur' },
    { min: 6, max: 6, message: '长度为 4 个字符', trigger: 'blur' }
  ]
})

// 切换登入类型
const activeTabName = ref<string>('normal')
const loading = ref<boolean>(false)
// 登入请求
const submitLogin = async () => {
  loading.value = true
  let ruleForm
  let formData
  // 根据不同的类型去校验不同的表单对象
  if (activeTabName.value === 'normal') {
    ruleForm = ruleNormalForm
    formData = normalForm
  } else {
    ruleForm = ruleSmsForm
    formData = smsForm
  }
  // 校验参数
  try {
    await ruleForm.value?.validate()
  } catch (e) {
    loading.value = false
    return
  }

  try {
    await userStore.login(formData)
    ElNotification({
      type: 'success',
      message: '登入成功',
      title: `HI,${getGreeting()}`
    })

    const currentRoute = router.currentRoute.value
    const redirectPath = currentRoute.query.redirect || currentRoute.meta.redirect
    // 跳转页面，如果没有重定向路由，就跳转首页
    const targetPath = redirectPath ? redirectPath.toString() : '/'
    console.log('跳转路径', targetPath)
    await router.push({ path: '/' })
  } catch (e) {
    loading.value = false
    ElNotification({
      type: 'error',
      message: (e as Error).message
    })
  }
}

const captchaUrl = ref<string>('')
// 刷新验证码
const refreshCaptcha = async (key: string, loginType: string) => {
  // 发起请求更换验证码
  const response = await getCaptcha(key, loginType)
  if (response.code === 200) {
    if (response.data.indexOf('data:image') > -1) {
      captchaUrl.value = response.data
    } else {
      captchaUrl.value = 'data:image/png;base64,' + response.data
    }
  }
}
onMounted(() => {
  refreshCaptcha(normalForm.key, normalForm.loginType)
})
</script>

<template>
  <div class="login">
    <div class="login-container">
      <!--   背景区   -->
      <div class="login-form-bg hidden">
        <h2 class="title">
          <!--          <div class="logo"></div>-->
          {{ title }}
        </h2>

        <el-text class="title-info" type="info">xxxxxxxxxxxxxx</el-text>
        <!--        <img src="@/assets/images/login_banner.webp" alt="论坛管理系统" />-->
      </div>
      <!--   表单区域   -->
      <div class="login-form-data">
        <el-tabs v-model="activeTabName">
          <el-tab-pane label="密码登录" name="normal">
            <el-form
              ref="ruleNormalForm"
              :model="normalForm"
              :rules="normalFormRules"
              label-width="auto"
              size="large"
              status-icon
              style="width: 100%"
            >
              <!--用户名-->
              <el-form-item prop="username">
                <el-input v-model="normalForm.username" :prefix-icon="User" placeholder="用户名" />
              </el-form-item>
              <!--     密码     -->
              <el-form-item prop="password">
                <el-input
                  v-model="normalForm.password"
                  :prefix-icon="Lock"
                  placeholder="密码"
                  type="password"
                />
              </el-form-item>
              <el-form-item prop="captcha">
                <el-input
                  v-model="normalForm.captcha"
                  :prefix-icon="Key"
                  maxlength="4"
                  placeholder="验证码"
                  style="flex: 1"
                />

                <img
                  :src="captchaUrl"
                  alt="看不起？切换验证码"
                  class="captcha"
                  @click="refreshCaptcha(normalForm.key, normalForm.loginType)"
                />
              </el-form-item>
              <el-form-item>
                <!--   记住我   -->
                <el-checkbox v-model="normalForm.rememberMe">记住我</el-checkbox>
              </el-form-item>
              <el-form-item>
                <el-button
                  :loading="loading"
                  style="width: 100%"
                  type="primary"
                  @click.keyup.enter="submitLogin"
                  >登录
                </el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane disabled label="手机号登录" name="sms">
            <el-form
              ref="ruleSmsForm"
              :model="smsForm"
              :rules="smsFormRules"
              label-width="auto"
              size="large"
            >
              <el-form-item prop="username">
                <el-input v-model="smsForm.username" :prefix-icon="User" placeholder="手机号" />
              </el-form-item>
              <el-form-item prop="captcha">
                <el-input
                  v-model="smsForm.captcha"
                  :prefix-icon="Key"
                  maxlength="6"
                  placeholder="验证码"
                >
                  <template #suffix>
                    <el-button link type="primary">获取验证码</el-button>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item>
                <!--   记住我   -->
                <el-checkbox v-model="smsForm.rememberMe">记住我</el-checkbox>
              </el-form-item>
              <el-form-item>
                <el-button style="width: 100%" type="primary">登录</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <div class="login-footer">
      <span>xxxxxxxxxxxxxxxxxxxxxxxxxxx</span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-image: url('../assets/images/login_bg.jpeg');
  background-size: cover;

  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 12px;
    margin: auto;
    min-width: 345px;
    max-width: 780px;
    border-radius: 8px;
    background-size: cover;
    background: rgba(255, 255, 255, 0.2);
    box-shadow:
      0 1px 2px -2px #00000029,
      0 3px 6px #0000001f,
      0 5px 12px 4px #00000017;

    .login-form-bg {
      width: 380px;
      text-align: center;
      padding: 35px 20px;

      // 大于等于768px时，显示
      @media (min-width: 768px) {
        & {
          display: block;
        }
      }

      img {
        width: 100%;
      }

      .title {
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: 550;
        font-size: 32px;
        --un-text-opacity: 1;
        color: rgb(106 106 106 / var(--un-text-opacity));
        background: linear-gradient(to right, red, blue);
        -webkit-background-clip: text; /* 将渐变应用于文本 */
        -webkit-text-fill-color: transparent; /* 设置文本颜色为透明 */
        .logo {
          width: 50px;
          height: 50px;
          margin-right: 12px;
          background-color: #bfbfbf;
        }
      }

      .title-info {
        display: block;
        margin-top: 24px;
        font-size: 18px;
      }
    }

    .login-form-data {
      padding: 32px 20px;
      width: 330px;

      .el-form {
        .el-form-item {
          height: 40px;
          margin-bottom: 0;

          .el-input {
            height: 100%;
          }
        }

        .el-form-item:nth-child(1) {
          margin-top: 20px;
        }

        .el-form-item:nth-child(n + 2) {
          margin-top: 20px;
        }
      }

      .captcha {
        width: 80px;
        height: 40px;
        margin-left: 12px;
        cursor: pointer;
      }
    }
  }

  .login-footer {
    display: flex;
    justify-content: center;
    align-items: center;
    padding-top: 12px;
    padding-bottom: 12px;
    font-size: 14px;
    --un-text-opacity: 1;
    color: rgb(107 114 128 / var(--un-text-opacity));
  }
}
</style>
