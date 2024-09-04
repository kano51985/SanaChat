<template>
    <div class="mid">
        <div class="contact" v-for="item in msgList" :key="item.receiverId" @click="handleClick(item.receiverId)">
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
</template>

<script setup>
import { onMounted, defineProps } from 'vue';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore()
const props = defineProps({
    msgList: {
    type: Array,
    default: () => []
  },
});

onMounted(()=> {
    console.log("received msgList =>",props);
    
})
const emit = defineEmits(['current-chat'])
function handleClick(receiverId) {
    emit('current-chat',receiverId)
    userStore.currentChatUser = receiverId;
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