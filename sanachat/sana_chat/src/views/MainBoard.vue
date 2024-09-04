<template>
    <div class="main">
      <!-- 最左侧头像，消息以及联系人按钮 -->
      <div class="left_function">
        <div class="avatar"></div>
        <div class="message_area" @click="handleMessage"><h1>message</h1></div>
        <div class="messager_area" @click="handleMessager"><h1>messager</h1></div>
      </div>
  
      <!-- 中间部分的 RouterView -->
      <div>
        <!-- ！！！！！使用子传父 Emit 来传递信息！！！！！-->
        <RouterView :contactList="contactList" :msgList="msgList" @select-user="selectUser" @current-chat="currentchat"/>
      </div>
  
      <!-- 右侧部分，根据条件渲染 -->
      <div class="right">
        <div v-if="showDefault">
          <!-- 默认内容 -->
          <DefaultIndex />
        </div>
        <div v-else-if="showUserInfoDetail">
          <!-- 用户信息详情 -->
          <UserInfoDetail :user="selectedUser" @send-message="handlePM"/>
        </div>
        <div v-else-if="showMsgDetails">
            <MessageDetails/>
        </div>
      </div>
    </div>
  </template>

<script setup>
import DefaultIndex from '@/views/tabs/DefaultIndex.vue';
import UserInfoDetail from '@/views/tabs/UserInfoDetail.vue';
import MessageDetails from './tabs/MessageDetails.vue';
import { onMounted } from 'vue';
import { closeWebSocket } from '@/services/websocket';
import { getUserContacts } from '../api/user/index'
import { getUserMessages } from '../api/message/index'
import { useUserStore } from '@/stores/user';
import { connectWebSocket } from '@/services/websocket';
import { onUnmounted } from 'vue';
import router from '@/router';
import { ref } from 'vue';

const userStore = useUserStore();
const contactList = ref();
const msgList = ref([]);
const showDefault = ref(true);
const showUserInfoDetail = ref(false);
const showMsgDetails = ref(false)
const selectedUser = ref(null);
const currentChatUser = ref(null)



onMounted(() => {
    
    getUserContacts(userStore.id).then((res) => {
        if (res.data.code == 200) {
            contactList.value = res.data.data
            console.log("联系人获取成功！联系人为：" , res.data.data)
        }
    })
    connectWebSocket();
    getUserMessages(userStore.id).then((res) => {
        msgList.value = res.data.data
        console.log("msgList.value :" , msgList.value);
    });
});
onUnmounted(() => {
    closeWebSocket();
})

function handleMessager() {
    // 点击切换到默认页面
    showDefault.value = true;
    selectedUser.value = null; // 清空选中的用户信息
    router.push("/mainboard/messager")
}
function handleMessage() {
    showDefault.value = true;
    selectedUser.value = null; // 清空选中的用户信息
    router.push("/mainboard/message")
}
function selectUser(user) {
  selectedUser.value = user;
  showDefault.value = false;
  showMsgDetails.value = false;
  showUserInfoDetail.value = true;
}
function currentchat(receiverId) {
    currentChatUser.value = receiverId;
    showDefault.value = false;
    showMsgDetails.value = true;
}
function handlePM() {
  showUserInfoDetail.value = false;
  showMsgDetails.value = false;
    showDefault.value = true;
  router.push("/mainboard/message");
}
</script>

<style lang="scss">
.main {
    display: flex;
    justify-content: start;
    align-items: center;
.left_function {
    background-color: #353453;
    height: 100vh;
    width: 20vh;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    .avatar {
        background-color: aqua;
        margin-top: 5vh;
        width: 10vh;
        height: 10vh;
        border-radius: 5vh;
        margin-bottom: 5vh;
    }
    h1 {
    font-size: large;
    }
    .message_area {
        cursor: pointer;
        width: 20vh;
        height: 8vh;
        border: 1px solid bisque;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        &:hover {
            background-color: azure;
        }

    }
    .messager_area {
        cursor: pointer;
        width: 20vh;
        height: 8vh;
        border-bottom: 1px solid bisque;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        &:hover {
            background-color: azure;
        }
    }
}
.mid {
    background-color: #171c23;
    width: 40vh;
    height: 100vh;
    border-right: 1px solid #d0d0d562;
}
.right {
    background-color: rgb(23, 28, 35);
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    .header {
        height: 30vh;
        display: flex;
        justify-content: center;
        align-items: center;
        .header_left {
            width: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            .header_left_avatar {
                width: 50%;
            }
            .header_left_details {
                width: 50%;
            }
        }
        .header_right {
            width: 50%;
            position: relative;
            left: 20vh;
        }
    }
    hr {
        border: 0;
        padding-top: 1px;
        background: linear-gradient(to right, transparent, #d0d0d5, transparent);
    }
    .main {
        height: 35vh;
        display: flex;
        flex-direction: column;
        .remark {
            position: relative;
            left: 8vh;
            margin-bottom: 1vh;
            width: 80%;
            display: flex;
            justify-content: space-between;
        }
        .signature {
            position: relative;
            left: 8vh;
            margin-bottom: 1vh;
            width: 80%;
            display: flex;
            justify-content: space-between;
        }
    }
    .footer {
        height: 35vh;
        width: 100%;
        display: flex;
        justify-content: center;
        .button {
            background-color: #1472d0;
            height: 50px;
            width: 200px;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
            left: 60px;
            top: 50px;
            border-radius: 10px;
            cursor: pointer;
            span {
                color: #FFFFFF;
            }
            &:hover {
                -webkit-transform:scale(0.9);-moz-transform:scale(0.9);-o-transform:scale(0.9);transform:scale(0.9);-webkit-transition:all 0.3s ease;-moz-transition:all 0.3s ease;-o-transition:all 0.3s ease;transition:all 0.3s ease;
            }
        }

    }
    h2 {
        color: #d4e4fa;
    }
    p {
        color: #838477;
    }
    h3 {
        color: #d4e4fa;
    }
}
.default_page {
    background-color: #171c23;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: calc(100% - 0px);
}
}
</style>