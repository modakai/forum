<script setup lang="ts" name="Login">
import { reactive } from 'vue'
import { Key, Lock, User } from '@element-plus/icons-vue'

const title = import.meta.env.VITE_APP_TITLE

const form = reactive({
  username: '',
  password: '',
  captcha: '',
  rememberMe: true
})
</script>

<template>
  <div class="login">
    <div class="login-container">
      <!--   背景区   -->
      <div class="login-form-bg hidden">
        <img src="@/assets/images/login_banner.webp" alt="论坛管理系统" />
      </div>
      <div class="login-form-data">
        <h2 class="title">
          <div class="logo"></div>
          {{ title }}
        </h2>
        <el-form :model="form" size="large" label-width="auto" status-icon>
          <!--用户名-->
          <el-form-item>
            <el-input :prefix-icon="User" v-model="form.username" placeholder="用户名" />
          </el-form-item>
          <!--     密码     -->
          <el-form-item>
            <el-input
              :prefix-icon="Lock"
              type="password"
              v-model="form.password"
              placeholder="密码"
            />
          </el-form-item>
          <el-form-item>
            <el-input
              :prefix-icon="Key"
              v-model="form.captcha"
              style="flex: 1"
              placeholder="验证码"
            />
            <div class="captcha">验证码</div>
          </el-form-item>
          <el-form-item>
            <!--   记住我   -->
            <el-checkbox v-model="form.rememberMe">记住我</el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="login-footer">
      <span>Copyright © 2018-2021 ruoyi.vip All Rights Reserved.</span>
    </div>
  </div>
</template>

<style scoped lang="scss">
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
    max-width: 700px;
    border-radius: 8px;
    background-size: cover;
    background: rgba(255, 255, 255, 0.2);
    box-shadow:
      0 1px 2px -2px #00000029,
      0 3px 6px #0000001f,
      0 5px 12px 4px #00000017;

    .login-form-bg {
      width: 380px;
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
    }

    .login-form-data {
      padding: 32px 20px;
      width: 320px;

      .title {
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: 400;
        font-size: 24px;
        --un-text-opacity: 1;
        color: rgb(106 106 106 / var(--un-text-opacity));

        .logo {
          width: 50px;
          height: 50px;
          margin-right: 12px;
          background-color: #bfbfbf;
        }
      }

      .el-form {
        .el-form-item {
          height: 40px;
          margin-bottom: 0;

          .el-input {
            height: 100%;
          }
        }

        .el-form-item:nth-child(1) {
          margin-top: 32px;
        }

        .el-form-item:nth-child(n + 2) {
          margin-top: 20px;
        }
      }

      // 验证码
      .captcha {
        width: 80px;
        margin-left: 12px;
        cursor: pointer;
        background-color: red;
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
