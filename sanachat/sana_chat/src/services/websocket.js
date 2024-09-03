// src/services/websocket.js
import { ref } from 'vue';
import { useUserStore } from '@/stores/user';
import axios from 'axios';

const socket = ref(null);

export function connectWebSocket() {
    const userStore = useUserStore();
    const userId = userStore.id;
    // 发送HTTP请求，包含自定义请求头
    // WebSocket连接代码
    if (!socket.value || socket.value.readyState !== WebSocket.OPEN) {
        socket.value = new WebSocket(`ws://localhost:8081/ws?userId=${userId}`);

        socket.value.onopen = () => {
            console.log("WebSocket connection established.");
            userStore.setWebSocketConnected(true);
        };

        socket.value.onmessage = (event) => {
            console.log("Received from server: ", event.data);
            // 这里可以触发其他的store来处理消息
        };

        socket.value.onclose = () => {
            console.log("WebSocket connection closed.");
            userStore.setWebSocketConnected(false);
        };

        socket.value.onerror = (error) => {
            console.error("WebSocket error: ", error);
        };
    }
}

export function sendMessage(message) {
    if (socket.value && socket.value.readyState === WebSocket.OPEN) {
        socket.value.send(message);
    } else {
        console.error("WebSocket is not connected.");
    }
}

export function closeWebSocket() {
    if (socket.value) {
        socket.value.close();
    }
}
