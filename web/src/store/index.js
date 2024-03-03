import { createStore } from 'vuex'


const MEMBER = "MEMBER"
// 全局变量
export default createStore({
  state: {
    member: window.SessionStorage.get(MEMBER)||{}
  },
  getters: {
  },
  mutations: {
    setMember(state, _member){
      state.member = _member;
      window.SessionStorage.get(MEMBER, _member);
    }
  },
  actions: {
  },
  modules: {
  }
})
