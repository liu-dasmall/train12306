import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue'

// 引入APP.vue， 并将其填充到id=‘app’的div中（在public/index.html中）
// createApp(App).use(Antd).use(store).use(router).mount('#app')
const app = createApp(App)
app.use(Antd).use(store).use(router).mount('#app')

const icons = Icons
for (const i in icons){
    app.component(i, icons[i]);
}
