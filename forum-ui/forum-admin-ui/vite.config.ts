import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
// 组件起名插件
import VueSetupExtend from 'vite-plugin-vue-setup-extend'
// svg
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import path from 'path'
// mock
import { viteMockServe } from 'vite-plugin-mock'

// https://vitejs.dev/config/
export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, process.cwd())
  return {
    plugins: [
      vue(),
      // 组件命名插件
      VueSetupExtend(),
      // svg配置
      createSvgIconsPlugin({
        // 以后有需要用到的svg图标就直接放到下面的文件夹中
        iconDirs: [path.resolve(process.cwd(), 'src/assets/icons')],
        // Specify symbolId format
        symbolId: 'icon-[dir]-[name]'
      }),
      // mock配置
      viteMockServe({
        localEnabled: command === 'serve' // 开发环境开启mock
      })
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: `@import "@/styles/variables.scss";`
        }
      }
    },
    // 开启代理
    server: {
      proxy: {
        [env.VITE_APP_BASE_API]: {
          target: env.VITE_SERVE,
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    }
  }
})
