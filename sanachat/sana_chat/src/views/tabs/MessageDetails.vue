<template>
    <div class="msgDetailsBox">
        <div class="msgDetailsBoxHeader">
            <div class="msgUsername">
                <h3>TBD</h3>
            </div>
            <div>
                <div class="operationArea">操作区</div>
            </div>
        </div>
        <div class="msgDetailsBoxMain">
            <div class="msgInfo">
                <div class="msg" v-for="item in msgList" :key="item.id.timestamp" :style="{flexDirection: item.senderId !== userStore.id ? 'row' : 'row-reverse',justifyContent: item.senderId !== userStore.id ? 'start' : 'end'}">
                    <div>
                        <img :src="item.senderId !== userStore.id ? item.receiverAvatar : userStore.avatar"  class="msg_avatar"/>
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
import { reactive, ref, watch,computed, onMounted } from 'vue';
import { useUserStore } from '@/stores/user';
import { useMessageStore } from '@/stores/message';
import { sendMessage } from '@/services/websocket';
const userStore = useUserStore();
const messageStore = useMessageStore();
const msgList = ref()
const currentMessages = computed(() => {
    return messageStore.getMessagesByReceiverId(userStore.currentChatUser).flat();
});

const msgEditContent = ref()
const msgDTO = reactive({
    belongToContact: undefined,//所属用户
    chatter: undefined,//聊天对象
    senderId: undefined,
    receiverId: undefined,
    receiverAvatar: null, // 按理来说这个应该和nickname一样都是动态查询获取的(毕竟你QQ上好友改了头像你基本上也就立马能看见,不是定时查询就是有更新推送)
    message: undefined,
    timestamp: undefined,
    status: undefined,
    
})

onMounted(()=>{console.log(msgList);
})

watch(currentMessages, (newMessages) => {
    msgList.value = newMessages;
}, { immediate: true });

function handleSend() {
    msgDTO.belongToContact = userStore.id;
    msgDTO.chatter = userStore.currentChatUser;
    msgDTO.senderId = userStore.id;
    msgDTO.receiverId = userStore.currentChatUser;
    msgDTO.receiverAvatar = userStore.getUserAvatar(userStore.currentChatUser)
    msgDTO.message = msgEditContent.value;
    msgDTO.timestamp = new Date().getTime();
    msgDTO.status = undefined;// 丢给后端判断(不是好友状态码为xxx,黑名单发送失败状态码为xxx)
    const jsonPayload = JSON.stringify(msgDTO)    
    sendMessage(jsonPayload)    
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