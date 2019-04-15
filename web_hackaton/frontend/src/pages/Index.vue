<template>
  <q-page class="flex justify-center ">

    <div class="q-pa-md row justify-center items-end full-width" >
        <div class="col-12" style="width: 100%; max-width: 1000px">

            <q-chat-message
              v-for="(message, i) in MESSAGES"
              :key="i"
              :name="message.author"
              :text="message.body"
              :sent="message.author!==nickname"
            />

        </div>
        <div class="col-12">
          <div v-show="isLogin" class="row">
            <div class="col">
                <q-input
                  v-model="newMessage"
                  clearable
                  maxlength="255"
                  rounded
                  />
                  <!-- @keyup.enter.native="nickname.length<4 && login(nickname)" -->

            </div>
            <div class="col-1">

                <q-btn color="primary" :disable="nickname.length<4" @click="say(nickname,newMessage)">Send</q-btn>
                <q-btn color="primary" :disable="nickname.length<4" @click="logout(newMessage)">Logout</q-btn>
            </div>
          </div>
          <div v-show="!isLogin" class="row">
            <div class="col">
              <q-input

                v-model="newNickname"
                clearable
                type="textarea"
                @keydown.enter.prevent="say"
                maxlength="15"
                rounded
              />

            </div>
            <div class="col-2">
                <q-btn class="q-mx-xs" color="primary" :disable="newNickname.length<4" @click="login(newNickname)">Login</q-btn>
                <q-btn color="primary" :disable="newNickname.length<4" @click="logout(newNickname)">Logout</q-btn>

            </div>
          </div>
        </div>
      </div>
  </q-page>
</template>

<style>
</style>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'PageIndex',
  data () {
    return {
      leftDrawerOpen: this.$q.platform.is.desktop,
      newMessage: '',
      nickname: '',
      newNickname: '',
      isLogin: false
    }
  },
  computed: {
    ...mapGetters(['MESSAGES', 'USERS'])
  // Другие вычисляемые свойства
  },
  methods: {
    ...mapActions(['GET_MESSAGES', 'GET_USERS', 'LOGIN', 'LOGOUT', 'SAY']),
    async login (newNickname) {
      try {
        await this.LOGIN(newNickname)
        this.isLogin = true
        this.nickname = newNickname
      } catch (e) {
        alert(e)
      }
    },
    async logout (logoutNickname) {
      try {
        if (this.nickname === logoutNickname) {
          this.nickname = ''
          this.isLogin = false
        }
        await this.LOGOUT(logoutNickname)
      } catch (e) {
        alert(e)
      }
    },
    async say (login, text) {
      this.SAY({ login, text })
    }
  },
  created () {
    setInterval(this.GET_MESSAGES, 1000)
    setInterval(this.GET_USERS, 1000)
  }
}
</script>
