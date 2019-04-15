
import * as linkifyHtml from 'linkifyjs/html'
export function SET_MESSAGES (state, payload) {
  if (state.origMessagesString.length !== payload.length) {
    state.origMessagesString = '' + payload
    state.messages = payload.split('\n').map(message => {
      const init = message.indexOf('[')
      const fin = message.indexOf(']')
      return {
        author: (message + '').substr(init + 1, fin - init - 1),
        body: [linkifyHtml((message + '').replace(/\[(.*)\]/, ''))]
      }
    })
  }
}
export function SET_USERS (state, payload) {
  if (state.origUsersString !== payload) {
    state.origUsersString = payload
    state.usersOnline = payload.length === 0 ? payload.split('\n') : []
  }
}
