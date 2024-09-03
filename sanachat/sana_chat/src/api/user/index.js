// 请求接口
import request from '@/utils/request.js'

// 设置用户状态
export function setUserState(data) {

    return request({
        url: '/user/state',
        method: 'POST',
        data: data
    })
}

// 获取用户联系人
export function getUserContacts(userId) {

    return request({
        url: `/user/contacts/${userId}`,
        method: 'GET'
    })
}
