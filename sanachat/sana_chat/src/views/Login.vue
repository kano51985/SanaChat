<template>
    <div class="outer_img">
        <div class="inner">
            <div class="inner_form">
                <div class="upper">
                    <div class="upper_avatar">
                        avatar
                    </div>
                    <div class="login_form">
                        <form>
                            <input type="text" v-model="loginForm.id" placeholder="输入Sana账号">
                            <input type="password" v-model="loginForm.password" placeholder="输入您的密码">   
                            <div class="lower" @click="handleLogin">
                                <span>登录</span>
                            </div>                             
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { login } from '../api/auth/index'
import { setUserState } from '../api/user/index'
import router from '@/router';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore();

const loginForm = ref({
    id: undefined,
    password: undefined,
})

function handleLogin() {
    login(loginForm.value).then((res) => {
        if(res.data.code == 200) {
            console.log("res===============>",res.data);
            userStore.setToken(res.data.data)
            // 设置用户信息
            setUserState(loginForm.value).then((res) => {
                if(res.data.code == 200) {
                    console.log(res);
                    userStore.setId(loginForm.value.id)
                    console.log("设置用户状态中.....用户ID：" + loginForm.value.id);
                    router.push("/mainboard")
                }else {
                    console.log("设置用户状态失败！请检查服务端！");
                }

            })


        }else {
            console.log("登录失败！错误信息：" + res)
        }
    })
}
</script>

<style lang="scss" scoped>
.outer_img {
    // background-image: url(../assets/bgimg/10e9e31fdf7147ec7b84f62a5e7ce77c354336533.gif);
    background-size: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    .inner {
        background-image: linear-gradient(to bottom right, #36244d, #4b1650);
        border-radius: 5px;
        width: 400px;
        height: 500px;
        .inner_form {
            .upper {
                .upper_avatar {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    position: relative;
                    top: 75px;
                    left: 150px;
                    background-color: #FFFFFF;
                    height: 100px;
                    width: 100px;
                    border-radius: 50px;
                }
                .login_form {
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    align-items: center;
                    position: relative;
                    top: 100px;
                    input[type="text"],#btn1,#btn2{
                        box-sizing: border-box;
                        text-align:center;
                        background-image: linear-gradient(to bottom right, #463448, #483545);
                        font-size:1.4em;
                        height:2em;
                        border-radius:10px;
                        border:1px solid #463448;
                        color: #e9e7e9;
                        display:block;
                        outline:0;
                        padding:0 0em;
                        text-decoration:none;
                        width:100%;
                    }
                    input[type="password"],#btn1,#btn2{
                        margin-top: 15px;
                        box-sizing: border-box;
                        text-align:center;
                        background-image: linear-gradient(to bottom right, #463448, #483545);
                        font-size:1.4em;
                        height:2em;
                        border-radius:10px;
                        border:1px solid #463448;
                        color:#edebed;
                        -web-kit-appearance:none;
                        -moz-appearance: none;
                        display:block;
                        outline:0;
                        padding:0 1em;
                        text-decoration:none;
                        width:100%;
                    }
                }
            }
            .lower {
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
            }
        }
    }
}
</style>