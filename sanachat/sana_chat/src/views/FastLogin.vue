<template>
    <div class="outer_img">
        <div class="inner">
            <div class="inner_form">
                <div class="upper">
                    <div class="upper_title">
                        <span>Sana Chat</span>
                    </div>
                    <div class="upper_avatar">
                        avatar
                    </div>
                    <div class="upper_nickname">
                        <span>hitoritabi</span>
                    </div>
                </div>
                <div class="lower">
                    <span>登录</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.outer_img {
    background-image: url(../assets/bgimg/10e9e31fdf7147ec7b84f62a5e7ce77c354336533.gif);
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
                .upper_title {
                    height: 150px;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    span {
                        background-image: linear-gradient(to right, #56cfed, #dda6ff);
                        background-clip: text;
                        -webkit-background-clip: text;
                        -webkit-text-fill-color: transparent;
                        font-size: 30px;
                        font-weight: bold;
                    }
                }
                .upper_avatar {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    position: relative;
                    left: 125px;
                    background-color: #FFFFFF;
                    height: 150px;
                    width: 150px;
                    border-radius: 75px;
                }
                .upper_nickname {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    position: relative;
                    top: 20px;
                    span {
                        color: #FFFFFF;
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
                left: 100px;
                top: 50px;
                border-radius: 10px;
                span {
                    color: #FFFFFF;
                }
            }
        }
    }
}
</style>