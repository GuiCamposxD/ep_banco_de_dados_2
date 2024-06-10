import { registerPlugins } from '@/plugins'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:3030'

import App from './App.vue'

import { createApp } from 'vue'

const app = createApp(App)

registerPlugins(app)

app.mount('#app')
