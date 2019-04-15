
import Axios from 'axios'
const axiosConfig = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
    'Access-Control-Allow-Origin': '*'
  },
  crossDomain: true
}

export async function GET_MESSAGES (context, payload) {
  let { data } = await Axios.get('http://localhost:8080/chat/chat')
  context.commit('SET_MESSAGES', data)
}
export async function GET_USERS (context, payload) {
  let { data } = await Axios.get('http://localhost:8080/chat/online')
  context.commit('SET_USERS', data)
}
export async function LOGIN (context, payload) {
  const params = new URLSearchParams()
  params.append('name', payload)
  return Axios.post('http://localhost:8080/chat/login', params, axiosConfig)
}
export async function LOGOUT (context, payload) {
  const params = new URLSearchParams()
  params.append('name', payload)
  return Axios.post('http://localhost:8080/chat/logout', params, axiosConfig)
}
export async function SAY (context, payload) {
  const params = new URLSearchParams()
  params.append('name', payload.login)
  params.append('msg', payload.text)
  return Axios.post('http://localhost:8080/chat/say', params, axiosConfig)
}
