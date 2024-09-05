<template>
    <div class="msgDetailsBox">
        <div class="msgDetailsBoxHeader">
            <div class="msgUsername">
                <h3>username</h3>
            </div>
            <div>
                <div class="operationArea">操作区</div>
            </div>
        </div>
        <div class="msgDetailsBoxMain">
            <div class="msgInfo">
                <div class="msg" v-for="item in msgList" :key="item.id.timestamp" :style="{flexDirection: item.operation === 1 ? 'row' : 'row-reverse',justifyContent: item.operation === 1 ? 'start' : 'end'}">
                    <div>
                        <img :src="item.operation === 1 ? item.receiverAvatar : userStore.avatar"  class="msg_avatar"/>
                    </div>
                    <div class="msg_detail">
                        <h5>{{ item.message }}</h5>
                    </div>
                </div>
            </div>
            <div class="msgEditArea">
                <textarea v-model="msgEditContent"></textarea>
            </div>
            <div class="submitButton">
                <span class="button" @click="handleSend()">发送</span>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useUserStore } from '@/stores/user';
import { useMessageStore } from '@/stores/message';
import { sendMessage } from '@/services/websocket';
const userStore = useUserStore();
const messageStore = useMessageStore();
const msgList = ref()
const fetchMessages = () => {
    msgList.value = messageStore.getMessagesByReceiverId(userStore.currentChatUser).flat();
    
}

watch(() => userStore.currentChatUser, () => {
    msgList.value = []; // 先清空消息列表
    // TODO:因为时侦听值变动，所以首次进入时并不会触发
    fetchMessages();
}, { immediate: true });

const msgEditContent = ref()

function handleSend() {
    sendMessage(msgEditContent.value)
    console.log(msgEditContent.value);
    
}


</script>

<style lang="scss" scoped>
.msgDetailsBox {
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    .msgDetailsBoxHeader {
        background-color: #4b79b5;
        width: 100%;
        height: 8vh;
        display: flex;
        justify-content: space-between;
        align-items: center;
        .msgUsername {
            margin-left: 3%;
        }
    }
    .msgDetailsBoxMain {
        background-color: bisque;
        height: 100%;
        width: 100%;
        display: flex;
        flex-direction: column;
        .msgInfo {
            background-color: aqua;
            height: 70%;
            width: 100%;
            overflow-y: auto; 
            .msg {
                background-color: aquamarine;
                min-height: 15%;
                width: 100%;
                display: flex;
                justify-content: start;
                align-items: center ;
                .msg_avatar {
                    margin-left: 10px;
                    margin-right: 10px;
                    background-color: #4b79b5;
                    width: 3em;
                    height: 3em;
                    border-radius: 1.5em;
                }
                .msg_detail {
                    padding: 5px 10px 5px 10px;
                    min-height: 3em;
                    max-width: 80%;
                    border-radius: 10px;
                    background-color: aliceblue;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                }
            }
        }
        .msgEditArea {  
            min-height: 30%; 
            textarea {
                width: calc(100% - 20px);
                height: 100%;
            }

        }

    }
    .submitButton {
        min-height: 6%;
        display: flex;
        justify-content: end;
        align-items: center;
        .button {
            color: #bfdbef;
            background-color: #177bc5;
            margin-right: 5%;
            padding: 6px 20px 6px 20px;
            border-radius: 6px;
            cursor: default;
            &:hover {
                background-color: #2985ca;
                color: #d0e4f3;
            }
        }
    }
}

</style>