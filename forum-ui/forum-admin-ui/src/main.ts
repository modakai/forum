import { createApp } from 'vue'
import App from './App.vue'
// elementPlus
// import 'element-plus/dist/index.css'
// import 'element-plus/theme-chalk/dark/css-vars.css'
import ElementPlus from 'element-plus'
// svg图标
import 'virtual:svg-icons-register'
import SvgIcon from '@/components/SvgIcon/index.vue'
import elementIcons from '@/components/SvgIcon/svgicon'
import './styles/index.scss'
import './styles/element/index.scss'
import 'element-plus/theme-chalk/dark/css-vars.css'
import './styles/element/dark.scss'
import './styles/global.scss'
// pinia
import pinia from '@/store'
// 路由
import router from './router'
import './permission'

const app = createApp(App)

app.use(pinia)
app.use(router)

app.use(ElementPlus)
app.use(elementIcons)
app.component('svg-icon', SvgIcon)

app.mount('#app')
