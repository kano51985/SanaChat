// src/stores/user.js
import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
    state: () => ({
        id: undefined,
        avatar: undefined,
        token: undefined,
        isAuthenticated: false,
        websocketConnected: false,
        currentChatUser: undefined,
        userAvatars: {}, 
    }),
    getters: {
        String:  (state) => state.id,
        String: (state) => state.avatar,
        String:  (state) => state.token,
        Boolean: (state) => state.isAuthenticated,
        Boolean: (state) => state.websocketConnected,
        String: (state) => state.currentChatUser,
        String: (state) => state.UserAvatars,
        getUserAvatar: (state) => (uid) => state.userAvatars[uid], 
    },
    actions: {
        setId(id) {
            this.id = id;
        },
        setAvatar(avatar) {
            this.avatar = avatar;
        },
        setUserAvatars(uid, avatar) {
            this.userAvatars[uid] = avatar;
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
            this.id = undefined;
            this.avatar = undefined;
            this.token = undefined;
            this.isAuthenticated = false;
            this.websocketConnected = false;
            this.currentChatUser = undefined;
            this.userAvatars = {}; // Clear avatars on logout
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
