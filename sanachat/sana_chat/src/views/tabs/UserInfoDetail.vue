<template>
    <!-- 详细页面 -->
    <div class="right">
        <div class="header">
            <div class="header_left">
                <div class="header_left_avatar">
                    <!-- 动态显示用户头像，如果有的话 -->
                    <img :src="user.avatar || '默认头像路径'" alt="Avatar" class="info_avatar" />
                </div>
                <div class="header_left_details">
                    <h2>{{ user.nickname }}</h2>
                    <p>UID {{ user.userId }}</p>
                    <h3>{{ user.isOnline ? 'Online' : 'Offline' }}</h3>
                </div>
            </div>
            <div class="header_right">Liked</div>
        </div>
        <hr/>
        <div class="main">
            <div class="remark">
                <h2>备注</h2>
                <h2>{{ user.remark || '暂无备注' }}</h2>
            </div>
            <div class="signature">
                <h2>签名</h2>
                <h2>{{ user.signature || '暂无签名' }}</h2>
            </div>
        </div>
        <hr/>
        <div class="footer">
            <div class="button" @click="handlePM(user.userId)">
                <span>发消息</span>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { useUserStore } from '@/stores/user';
import { onMounted, defineProps } from 'vue';
const userStore = useUserStore()
const emit = defineEmits(['send-message']);
// 接收父组件传递的用户数据
const props = defineProps({
    user: {
        type: Object,
        default: () => ({})
    }
});
onMounted(() => {
  console.log('UserInfoDetail.vue 加载成功');
});
function handlePM(uid) {
    emit('send-message',uid);
    userStore.setCurrentChatUser(uid)
    // 首先跳转到中间的 message 组件
    router.push({
        path: '/mainboard/message',
        //query: { userId: user.userId }  // 如果需要传递用户信息
    }).then(() => {
        // 在中间组件加载完后，再切换右侧的 messageDetails 组件
        router.push('/mainboard/message/messageDetails');
    });
}
</script>

<style lang="scss" scoped>
    .info_avatar {
    background-color: white;
    width: 150px;
    height: 150px;
    border-radius: 75px;
    margin-left: 50%;
}
</style>