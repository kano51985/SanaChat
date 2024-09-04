// src/stores/user.js
import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
    state: () => ({
        id: undefined,
        token: undefined,
        isAuthenticated: false,
        websocketConnected: false,
        currentChatUser: undefined,
    }),
    getters: {
        String:  (state) => state.id,
        String:  (state) => state.token,
        Boolean: (state) => state.isAuthenticated,
        Boolean: (state) => state.websocketConnected,
        String: (state) => state.currentChatUser,
    },
    actions: {
        setId(id) {
            console.log("truly setting========>",id);
            this.id = id;
        },
        setUserStatus() {
            this.isAuthenticated = true;
        },
        setToken(token) {
            this.token = token;
        },
        setWebSocketConnected(status) {
            this.websocketConnected = status;
        },
        setCurrentChatUser(uid) {
            this.currentChatUser = uid;
        },
        logout() {
            this.userInfo = null;
            this.isAuthenticated = false;
            this.websocketConnected = false;
        },
    },
    // 使用 pinia 持久化. path 填需要持久化的数据
    persist: {
        enabled: true,
        storage: localStorage,
        key: 'userInfo',
        path: ['userId']
        },
});
