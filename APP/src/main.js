import { registerPlugins } from '@/plugins'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080'

import App from './App.vue'

import { createApp } from 'vue'

import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const app = createApp(App)

registerPlugins(app)

app.component('VueDatePicker', VueDatePicker);

app.mount('#app')
