<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
        >
          <q-icon name="menu" />
        </q-btn>

        <q-toolbar-title>
          Atom chat
        </q-toolbar-title>

        <!-- <div>Quasar v{{ $q.version }}</div> -->
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      bordered
      content-class="bg-grey-2"
    >
    <q-list>
      <q-item-label header>Online</q-item-label>
        <!-- <q-separator /> -->
      <q-item v-for="(user, i) in USERS" :key="i" class="q-my-sm" >
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white">
            {{ user[0] }}
          </q-avatar>
        </q-item-section>

        <q-item-section>
          <q-item-label>{{ user }}</q-item-label>
          <!-- <q-item-label caption lines="1">{{ contact.email }}</q-item-label> -->
        </q-item-section>
      </q-item>

      <!-- <q-separator /> -->
      <!-- <q-item-label header>Offline</q-item-label> -->

      <!-- <q-item v-for="contact in offline" :key="contact.id" class="q-mb-sm" clickable v-ripple>

        <q-item-section>
          <q-item-label>{{ contact.name }}</q-item-label>
          <q-item-label caption lines="1">{{ contact.email }}</q-item-label>
        </q-item-section>
      </q-item> -->
    </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { openURL } from 'quasar'
import { mapGetters } from 'vuex'

export default {
  name: 'MyLayout',
  data () {
    return {
      leftDrawerOpen: this.$q.platform.is.desktop,
      contacts: [{
        id: 1,
        name: 'Ruddy Jedrzej',
        email: 'rjedrzej0@discuz.net',
        letter: 'R'
      }, {
        id: 2,
        name: 'Mallorie Alessandrini',
        email: 'malessandrini1@marketwatch.com',
        letter: 'M'
      }, {
        id: 3,
        name: 'Elisabetta Wicklen',
        email: 'ewicklen2@microsoft.com',
        letter: 'E'
      }, {
        id: 4,
        name: 'Seka Fawdrey',
        email: 'sfawdrey3@wired.com',
        letter: 'S'
      }]
    }
  },
  computed: {
    ...mapGetters(['MESSAGES', 'USERS'])
  // Другие вычисляемые свойства
  },
  methods: {
    openURL
  }
}
</script>

<style>
</style>
