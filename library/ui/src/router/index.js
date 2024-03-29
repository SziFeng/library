import Vue from 'vue'
import VueRouter from 'vue-router'
import {
  routes
} from './route'
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
Vue.use(VueRouter)

//  路由配置
const RouterConfig = {
  routes: routes
}
export const router = new VueRouter(RouterConfig)
router.beforeEach((to, from, next) => {
  next()
})
