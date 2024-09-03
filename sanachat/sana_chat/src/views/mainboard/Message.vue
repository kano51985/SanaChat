<template>
    <div class="mid">
        <div class="contact" v-for="item in testData">
            <div class="left_info">
                <!-- 头像链接 -->
                <div>
                    <img :src="item.avatar" class="left_avatar">
                </div>
                <div class="left_info">
                    <div class="info_nickname">
                        <div class="nickname"><h3>{{ item.nickname }}</h3></div>
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
import { ref } from 'vue';

const testData = ref([
    {
        nickname : "白日",
        avatar : "https://lovesana.oss-cn-beijing.aliyuncs.com/sana.jpg",
        recentMsg : "this is my msg",
        sendTime : "12:46",
    },
    {
        nickname : "sana",
        avatar : "https://lovesana.oss-cn-beijing.aliyuncs.com/sana.jpg",
        recentMsg : "this is your msg",
        sendTime : "12:47",
    },
])
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
                    }
                }
            }
        }
    }

}
</style>