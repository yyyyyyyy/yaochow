<template>
  <div class="log">
    <div class="content3">
      <ol>
        <li class="li-add" @click="return_notes">
          Note List
        </li>
        <li class="li-add">
          Recycle Bin
        </li>
        <li class="li-note" @click="get_bin_content(index)" v-for="(note, index) in bin_notes" :key="note.id">
          {{note.name}}
        </li>
      </ol>
    </div>
    <div class="content4" v-if="resume_flag">
      <div class="delete-note" @click="resume_note(current_bin_note.id)">Click Here To Resume This Note</div>
      <mavon-editor :editable="false" v-model="current_bin_note.content"/>
    </div>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'login',
  data () {
    return {
      bin_notes: [],
      current_bin_note: [],
      resume_flag: false
    }
  },
  methods: {
    recycle_bin () {
      axios.get(this.global.serverPath + '/core/note/listDeletedNotes', {
        headers: {
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          this.bin_notes = response.data.result
        }
      }).catch((response) => {
        console.info(response)
        alert(response.data.errorMsg)
      })
    },
    resume_note (id) {
      axios.get(this.global.serverPath + '/core/note/resume/' + id, {
        headers: {
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          if (response.data.result) {
            this.resume_flag = false
            alert('Successful')
            this.recycle_bin()
          }
        }
      }).catch((response) => {
        console.info(response)
        alert(response.data.errorMsg)
      })
    },
    get_bin_content (index) {
      this.current_bin_note = this.bin_notes[index]
      this.resume_flag = true
    },
    return_notes () {
      this.$router.push({name: 'Notes'})
    }
  },
  mounted () {
    this.recycle_bin()
  }
}
</script>
