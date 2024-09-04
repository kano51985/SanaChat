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
                <div class="msg" v-for="item in msgList">
                    <div>
                        <img :src="item.avatar"  class="msg_avatar"/>
                    </div>
                    <div class="msg_detail">
                        <h5>{{ item.msgContent }}</h5>
                    </div>
                </div>
            </div>
            <div class="msgEditArea">
                <textarea :model="msgEditContent" :value="msgEditContent"></textarea>
            </div>
            <div class="submitButton">
                <span class="button">发送</span>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getUserMessagesDetail } from '@/api/message/index'
import { useUserStore } from '@/stores/user';
const userStore = useUserStore();

const msgList = ref([
    {
        avatar: "https://lovesana.oss-cn-beijing.aliyuncs.com/sana.jpg",
        msgContent: "测试数据11111",
    },
])
const msgListDto = ref([
    {
        belongToContact: undefined,
        receiverId: undefined
    }
])
onMounted(()=>{
    console.log("currentChatUser=======================>",userStore.currentChatUser);
    msgListDto.value.receiverId = userStore.currentChatUser
    msgListDto.value.belongToContact = userStore.id
    getUserMessagesDetail(userStore.currentChatUser)
})
const msgEditContent = ref("111")
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