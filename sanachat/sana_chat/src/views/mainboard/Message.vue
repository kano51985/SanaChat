<template>
    <div class="mid">
        <!-- 第一层循环遍历 msgList -->
        <div v-for="msgGroup in msgList" :key="msgGroup.id.timestamp">
            <!-- 第二层循环遍历每个 msgGroup 下的 list -->
            <div class="contact" v-for="item in msgGroup.list" :key="item.receiverId" @click="handleClick(item.receiverId)">
                <div class="left_info">
                    <!-- 头像链接 -->
                    <div>
                        <img :src="item.receiverAvatar" class="left_avatar">
                    </div>
                    <div class="left_info">
                        <div class="info_nickname">
                            <div class="nickname"><h3>{{ item.receiverNickname }}</h3></div>
                        </div>
                        <div class="info_recentMsg">
                            <div class="recentMsg"><h4>{{ item.recentMsg }}</h4></div>                       
                        </div>
                    </div>
                </div>
                <div class="right_info">
                    <span style="color: #e8f2f9;">{{ item.sendTime }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { defineProps, onBeforeMount, ref } from 'vue';
import { useUserStore } from '@/stores/user';
import { useMessageStore } from '@/stores/message'
import { getUserMessagesDetail } from '@/api/message/index'

const userStore = useUserStore()
const messageStore = useMessageStore()
const props = defineProps({
    msgList: {
    type: Array,
    default: () => []
  },
});

const msgListDto = ref({
    belongToContact: undefined,
    chatter: undefined
})

const emit = defineEmits(['current-chat'])
function handleClick(receiverId) {
    emit('current-chat', receiverId)
    
    userStore.currentChatUser = receiverId
    if (!messageStore.hasFetched(receiverId)) {
        msgListDto.value.chatter = userStore.currentChatUser
        msgListDto.value.belongToContact = userStore.id
        getUserMessagesDetail(msgListDto.value).then((res) => {
            if (res.data.code == 200) {
                messageStore.replaceMessage(receiverId, res.data.data)
                messageStore.setFetch(receiverId) // 成功获取后标记用户
            }
        })
    }
}
</script>

<style lang="scss" scoped>
.contact {
    background-color: #486faa;
    width: 100%;
    height: 10vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
    &:hover {
        background-color: #5f82b4;
    }
    .left_info {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 70%;
        .left_avatar {
            background-color: #FFFFFF;
            width: 4em;
            height: 4em;
            border-radius: 2em;
        }
        .left_info {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            .info_nickname {
                display: flex;
                justify-content: start;
                width: 100%;
                .nickname {
                    h3 {
                    color: #e8f2f9;
                    font-style: italic;
                    }
                }

            }
            .info_recentMsg {
                display: flex;
                justify-content: start;
                width: 100%;
                .recentMsg {
                    display: flex;
                    justify-content: start;
                    width: 100%;
                    h4 {
                        color: #e8f2f9;
                        overflow: hidden;
                        white-space: nowrap;
                        text-overflow: ellipsis;
                    }
                }
            }
        }
    }

}
</style>
