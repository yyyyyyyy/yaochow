<template>
  <div class="log">
    <div class="content2">
      <h2>Sign Up</h2>
      <form @submit.prevent="register">
        <input type="text" name="username" v-model="username" placeholder="UESRNAME">
        <input type="email" name="email" v-model="email" placeholder="EMAIL">
        <input type="password" name="password" v-model="password" placeholder="PASSWORD">
        <input type="submit" class="register" value="Register">
        <input type="button" class="register-login" value="Sign In" @click="turn_to_login">
      </form>
      <div class="clear"></div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'register',
  data () {
    return {
      username: '',
      password: '',
      email: ''
    }
  },
  methods: {
    turn_to_login () {
      this.$router.push({name: 'Login'})
    },
    register () {
      const postData = {
        username: this.username,
        password: this.password,
        email: this.email
      }
      axios.post(this.global.serverPath + '/user/register', JSON.stringify(postData), {
        headers: {
          'Content-Type': 'application/json',
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          alert('Register Successful, Confirm Your E-mail Before Login')
          this.$router.push({name: 'Login'})
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
