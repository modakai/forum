import { createApp } from 'vue'
import App from './App.vue'
// elementPlus
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

// svg图标
import 'virtual:svg-icons-register'
import SvgIcon from '@/components/SvgIcon/index.vue'

import elementIcons from '@/components/SvgIcon/svgicon'
import './styles/index.scss'

const app = createApp(App)
app.use(ElementPlus, {
  locale: zhCn
})
app.use(elementIcons)
app.component('svg-icon', SvgIcon)

app.mount('#app')
