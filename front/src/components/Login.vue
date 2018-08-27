<template>
  <div class="log">
    <div class="content1">
      <h2>Sign In</h2>
      <form @submit.prevent="submit">
        <input type="text" name="username" v-model="username" placeholder="USERNAME">
        <input type="password" name="password" v-model="password" placeholder="PASSWORD">
        <div class="button-row">
          <input type="submit" class="sign-in" value="Sign In">
          <input type="button" class="login-register" value="Register" @click="turn_to_register">
          <div class="clear"></div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'login',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    turn_to_register () {
      this.$router.push({name: 'Register'})
    },
    submit () {
      const postData = {
        username: this.username,
        password: this.password
      }
      axios.post(this.global.serverPath + '/user/login', JSON.stringify(postData), {
        headers: {
          'Content-Type': 'application/json',
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          this.$router.push({name: 'Notes'})
        } else {
          alert(response.data.errorMsg)
        }
      }).catch((response) => {
        alert(response.data)
      })
    },
    list_note () {
      const postData = {}
      axios.post(this.global.serverPath + '/core/note/listNotes', JSON.stringify(postData), {
        headers: {
          'Content-Type': 'application/json',
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          if (response.data.result) {
            this.$router.push({name: 'Notes'})
          }
        }
      }).catch((response) => {
        console.info(response)
      })
    }
  }
}
</script>
