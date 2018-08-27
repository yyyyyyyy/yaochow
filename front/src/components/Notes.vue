<template>
  <div class="log">
    <div class="content3">
      <ol>
        <li class="li-add">
          Note List
        </li>
        <li class="li-note" v-if="!add_flag" @click="add_on">
          Click Here 2 Add Note
        </li>
        <li class="li-note" v-if="add_flag">
          <input v-model="current_note.name" @keyup.enter="edit_on" placeholder="NOTE NAME"/>
        </li>
        <li class="li-note" @click="get_content(index)" v-for="(note, index) in notes" :key="note.id">
          {{note.name}}
        </li>
        <li class="li-add" @click="recycle_bin">
          Recycle Bin
        </li>
      </ol>
    </div>
    <div class="content4" v-if="edit_flag">
      <div class="delete-note" @click="delete_note(current_note.id)">Click Here To Remove This Note</div>
      <mavon-editor @save="operate" v-model="current_note.content"/>
    </div>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: 'login',
  data () {
    return {
      notes: [],
      current_note: {},
      new_note: {},
      page_size: 10,
      page_number: 1,
      total_count: 0,
      total_pages: 0,
      add_flag: false,
      edit_flag: false
    }
  },
  methods: {
    list_note (pageNumber) {
      const postData = {}
      axios.post(this.global.serverPath + '/core/note/listNotes', JSON.stringify(postData), {
        headers: {
          'Content-Type': 'application/json',
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          if (response.data.result) {
            this.notes = response.data.result
          }
        }
      }).catch((response) => {
        console.info(response)
      })
    },
    operate () {
      if (this.add_flag === true) {
        this.save()
      } else {
        this.update()
      }
    },
    save () {
      axios.post(this.global.serverPath + '/core/note/insert', JSON.stringify(this.new_note), {
        headers: {
          'Content-Type': 'application/json',
          withCredentials: true
        }

      }).then((response) => {
        if (response.data.success) {
          this.add_flag = false
          this.edit_flag = false
          this.new_note = {}
          this.list_note(this.page_number)
        } else {
          alert(response.data.errorMsg)
        }
      }).catch((response) => {
        alert(response)
      })
    },
    update () {
      axios.post(this.global.serverPath + '/core/note/update', JSON.stringify(this.current_note), {
        headers: {
          'Content-Type': 'application/json',
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          this.get_note_by_id(this.current_note.id)
        } else {
          alert(response.data.errorMsg)
        }
      }).catch((response) => {
        alert(response.data.errorMsg)
      })
    },
    get_note_by_id (id) {
      axios.get(this.global.serverPath + '/core/note/getNoteById/' + id, {
        headers: {
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          if (response.data.result) {
            alert('Successful')
            this.current_note = response.data.result
          }
        }
      }).catch((response) => {
        console.info(response)
        alert(response.data.errorMsg)
      })
    },
    delete_note (id) {
      axios.get(this.global.serverPath + '/core/note/delete/' + id, {
        headers: {
          withCredentials: true
        }
      }).then((response) => {
        if (response.data.success) {
          if (response.data.result) {
            this.edit_flag = false
            alert('Successful')
            this.list_note(this.page_number)
          }
        }
      }).catch((response) => {
        console.info(response)
        alert(response.data.errorMsg)
      })
    },
    add_on () {
      this.current_note = this.new_note
      this.add_flag = true
      if (!this.current_note.name) {
        this.edit_flag = false
      }
    },
    edit_on () {
      if (!this.current_note.name || typeof (this.current_note.name) === 'undefined') {
        this.add_flag = false
        this.edit_flag = false
      } else {
        this.edit_flag = true
      }
    },
    get_content (index) {
      if (this.add_flag === true) {
        this.new_note = this.current_note
        this.add_flag = false
      }
      this.current_note = this.notes[index]
      this.edit_flag = true
    },
    recycle_bin () {
      this.$router.push({name: 'RecycleBin'})
    }
  },
  mounted () {
    this.list_note(1)
  }
}
</script>
