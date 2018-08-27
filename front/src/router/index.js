import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Notes from '@/components/Notes'
import RecycleBin from '@/components/RecycleBin'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    }, {
      path: '/register',
      name: 'Register',
      component: Register
    }, {
      path: '/',
      name: 'Notes',
      component: Notes
    }, {
      path: '/recycle',
      name: 'RecycleBin',
      component: RecycleBin
    }
  ]
})
