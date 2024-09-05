// 请求接口
import request from '@/utils/request.js'

// 获取用户对话列表
export function getUserMessages(userId) {

    return request({
        url: `/message/list/${userId}`,
        method: 'GET'
    })
}

// 获取对话列表详细
export function getUserMessagesDetail(data) {

    return request({
        url: `/message/list/detail`,
        method: 'POST',
        data: data
    })
}
