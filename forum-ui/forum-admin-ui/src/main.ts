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

// 路由
import router from './router'

// pinia
import pinia from '@/store'

const app = createApp(App)
app.use(ElementPlus)
app.use(elementIcons)
app.component('svg-icon', SvgIcon)
app.use(router)
app.use(pinia)

app.mount('#app')
