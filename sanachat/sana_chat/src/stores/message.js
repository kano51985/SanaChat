// stores/message.js
import { defineStore } from 'pinia';

export const useMessageStore = defineStore('messageStore', {
  state: () => ({
    messages: {},  // 存储所有对话的对象，key 是 receiverId，value 是对应的消息数组
    fetchedUsers: new Set() // 用 Set 来存储已获取的用户
  }),
  getters: {
    getMessagesByReceiverId: (state) => (receiverId) => {
      return state.messages[receiverId] || [];
    }
  },
  actions: {
    addMessage(receiverId, msgDetail) {
      if (!this.messages[receiverId]) {
        this.messages[receiverId] = [];  // 如果当前对话不存在，初始化一个空数组
      }
      this.messages[receiverId].push(...msgDetail);  // 将消息推入对应的对话数组中
    },
    replaceMessage(receiverId, newMessages) {
      this.messages[receiverId] = newMessages;
    },
    setFetch(receiverId) {
      this.fetchedUsers.add(receiverId); // 标记用户为已获取
    },
    hasFetched(receiverId) {
      return this.fetchedUsers.has(receiverId); // 判断用户是否已经获取过
    }
  }
});
