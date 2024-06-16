import * as components from '@element-plus/icons-vue'

export default {
  install: (app: any) => {
    for (const [key, component] of Object.entries(components)) {
      app.component(key, component)
    }
  }
}
