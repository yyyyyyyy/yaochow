// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import global from './components/Global'

Vue.prototype.global = global

Vue.use(mavonEditor)

axios.defaults.withCredentials = true

Vue.config.debug = true

Vue.prototype.$axios = axios

Vue.config.productionTip = false

axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error.response) {
      if (error.response.status === 401) {
        router.replace({
          name: 'Login'
        })
      }
    }
    return Promise.reject(error.response.data)
  }
)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
