<script lang="ts" name="Profile" setup>
import { useUserStore } from '@/store'
import { Edit } from '@element-plus/icons-vue'
import '@/styles/components/profile.scss'
import { ref } from 'vue'
import UpdatePW from '@/views/profile/UpdatePW.vue'
import { ElMessage } from 'element-plus'
import UploadAvatar from '@/views/profile/UploadAvatar.vue'
import UpdateInfo from '@/views/profile/UpdateInfo.vue'

const userStore = useUserStore()
const userInfo = userStore.userInfo

let pwDialogVisible = ref(false)
// 修改密码-取消密码修改
const cancelUpdatePW = () => {
  pwDialogVisible.value = false
}
// 修改密码-确认修改密码
const confirmUpdatePW = () => {
  pwDialogVisible.value = false
  ElMessage.warning('功能待开发')
}

let uploadAvatarVisible = ref(false)
// 上传头像-取消上传头像
const cancelUploadAvatar = () => {
  uploadAvatarVisible.value = false
}
// 上传头像-确认上传头像
const confirmUploadAvatar = () => {
  uploadAvatarVisible.value = false
  ElMessage.warning('功能待开发')
  // 发送更新头像请求
}

let updateInfoVisible = ref(false)
// 修改资料-取消修改资料
const cancelUpdateInfo = () => {
  updateInfoVisible.value = false
}
// 修改资料-确认修改资料
const confirmUpdateInfo = () => {
  updateInfoVisible.value = false
  ElMessage.warning('功能待开发')
}
</script>

<template>
  <div class="profile">
    <!--  个人简介  -->
    <el-card>
      <el-space alignment="center">
        <!--  avatar    -->
        <el-avatar :size="100" :src="userInfo.avatar" />
        <div class="ml-20">
          <div class="flex info">
            <span>用户名:</span>
            <span class="username ml-12">{{ userInfo.username }}</span>
            <el-button
              :icon="Edit"
              class="ml-32"
              text
              type="primary"
              @click="() => (pwDialogVisible = true)"
              >修改密码
            </el-button>
          </div>
          <div class="update-avatar">
            <el-button plain @click="() => (uploadAvatarVisible = true)">修改头像</el-button>
          </div>
        </div>
      </el-space>
    </el-card>

    <el-card class="profile-card" title="个人资料信息">
      <template #header>
        <div class="card-header">
          <span>个人资料信息</span>
          <el-button :icon="Edit" text type="primary" @click="() => (updateInfoVisible = true)"
            >修改资料</el-button
          >
        </div>
      </template>
      <el-descriptions :column="1" :size="'large'" border>
        <el-descriptions-item label="昵称" label-align="center">
          {{ userInfo.nickName }}
        </el-descriptions-item>
        <el-descriptions-item label="性别" label-align="center"
          >{{ userInfo.gender ? '女' : '男' }}
        </el-descriptions-item>
        <el-descriptions-item label="电话" label-align="center"
          >{{ userInfo.phone }}
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
  <!-- 修改密码组件 -->
  <update-p-w :is-show="pwDialogVisible" @cancel="cancelUpdatePW" @ok="confirmUpdatePW" />
  <upload-avatar
    :is-show="uploadAvatarVisible"
    @cancel="cancelUploadAvatar"
    @ok="confirmUploadAvatar"
  />
  <update-info :is-show="updateInfoVisible" @cancel="cancelUpdateInfo" @ok="confirmUpdateInfo" />
</template>

<style lang="scss" scoped>
.profile {
  margin: 12px;

  .el-card {
    &.el-card__body {
      padding: 0 24px;
    }
  }

  .info {
    font-size: 16px;
    align-items: center;

    .username {
      opacity: 0.8;
    }
  }

  .update-avatar {
    display: flex;
    align-items: center;
    margin-top: 16px;
  }

  .profile-card {
    margin-top: 16px;

    .card-header {
      display: flex;

      & > span {
        flex: 1;
        font-weight: 500;
        font-size: 18px;
        color: rgb(31, 34, 37);
        transition: color 0.3s cubic-bezier(0.4, 0, 0.2, 1);
      }
    }

    .w-200 {
      width: 200px;
    }
  }
}
</style>
