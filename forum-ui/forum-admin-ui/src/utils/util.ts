// eslint-disable-next-line vue/prefer-import-from-vue
import { isObject } from '@vue/shared'
import { nanoid } from 'nanoid'

/**
 * @description 添加单位
 * @param {String | Number} value 值 100
 * @param {String} unit 单位 px em rem
 */
export const addUnit = (value: string | number, unit = 'px') => {
  return !Object.is(Number(value), NaN) ? `${value}${unit}` : value
}

/**
 * @description 添加单位
 * @param {unknown} value
 * @return {Boolean}
 */
export const isEmpty = (value: unknown) => {
  return value == null && typeof value == 'undefined'
}

/**
 * @description 获取正确的路经
 * @param {String} path  数据
 */
export function getNormalPath(path: string) {
  if (path.length === 0 || !path || path == 'undefined') {
    return path
  }
  const newPath = path.replace('//', '/')
  const length = newPath.length
  if (newPath[length - 1] === '/') {
    return newPath.slice(0, length - 1)
  }
  return newPath
}

/**
 * @description对象格式化为Query语法
 * @param { Object } params
 * @return {string} Query语法
 */
export function objectToQuery(params: Record<string, any>): string {
  let query = ''
  for (const props of Object.keys(params)) {
    const value = params[props]
    const part = encodeURIComponent(props) + '='
    if (!isEmpty(value)) {
      if (isObject(value)) {
        for (const key of Object.keys(value)) {
          if (!isEmpty(value[key])) {
            const params = props + '[' + key + ']'
            const subPart = encodeURIComponent(params) + '='
            query += subPart + encodeURIComponent(value[key]) + '&'
          }
        }
      } else {
        query += part + encodeURIComponent(value) + '&'
      }
    }
  }
  return query.slice(0, -1)
}

/**
 * @description 时间格式化
 * @param dateTime { number } 时间戳
 * @param fmt { string } 时间格式
 * @return { string }
 */
// yyyy:mm:dd|yyyy:mm|yyyy年mm月dd日|yyyy年mm月dd日 hh时MM分等,可自定义组合
export const timeFormat = (dateTime: number, fmt = 'yyyy-mm-dd') => {
  // 如果为null,则格式化当前时间
  if (!dateTime) {
    dateTime = Number(new Date())
  }
  // 如果dateTime长度为10或者13，则为秒和毫秒的时间戳，如果超过13位，则为其他的时间格式
  if (dateTime.toString().length == 10) {
    dateTime *= 1000
  }
  const date = new Date(dateTime)
  let ret
  const opt: any = {
    'y+': date.getFullYear().toString(), // 年
    'm+': (date.getMonth() + 1).toString(), // 月
    'd+': date.getDate().toString(), // 日
    'h+': date.getHours().toString(), // 时
    'M+': date.getMinutes().toString(), // 分
    's+': date.getSeconds().toString() // 秒
  }
  for (const k in opt) {
    ret = new RegExp('(' + k + ')').exec(fmt)
    if (ret) {
      fmt = fmt.replace(ret[1], ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, '0'))
    }
  }
  return fmt
}
/**
 * @description 单词首字母大写
 * @param  { String } str
 * @return { String } id
 */
export const firstToUpperCase = (str = '') => {
  return str.toLowerCase().replace(/( |^)[a-z]/g, ($1) => $1.toUpperCase())
}

/**
 * 获取唯一ID
 * @return { String } id
 */
export const genNanoId = (): string => {
  // 获取当前时间戳
  const timestamp = Date.now()
  return nanoid() + timestamp
}
/**
 * 根据时间显示 早上好， 中午好，下午好，晚上好
 */
// 根据时间显示 早上好， 中午好，下午好，晚上好
export const getGreeting = () => {
  const hour = new Date().getHours()
  if (hour < 6) {
    return '凌晨好'
  } else if (hour < 9) {
    return '早上好'
  } else if (hour < 12) {
    return '上午好'
  } else if (hour < 14) {
    return '中午好'
  } else if (hour < 17) {
    return '下午好'
  } else if (hour < 19) {
    return '傍晚好'
  } else if (hour < 22) {
    return '晚上好'
  }
}
