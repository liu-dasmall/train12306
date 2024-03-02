import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 引入APP.vue， 并将其填充到id=‘app’的div中（在public/index.html中）
createApp(App).use(store).use(router).mount('#app')
