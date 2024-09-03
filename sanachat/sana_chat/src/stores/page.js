// 引入pinia
import { defineStore } from 'pinia'
// 定义 store 并导出
export const usePageStore = defineStore('page', {
    // 定义状态【数据】
    state: () => ({ 
        tableList: [
            {
                title: '默认页面',
                path: '/index',
            },// 所有的 tab
        ],// 所有的 tab
        activePage: '/messager', // 中间部分
        activeTab: '/index',// 当前选中的 tab，通过 path 体现
     }),
    // 获取数据的方法
    getters: {
      Array: (state) => state.tableList,
      String: (state) => state.activeTab,
      String: (state) => state.activePage,
    },
    // 修改数据的方法
    actions: {
        // 设置 tableList
        setTableList(data) {
            this.tableList.push(data)
        },

        // 删除 tableList
        delTableList(name) {
            // 重新赋值
            this.tableList = this.tableList.filter(item => {
                if (item.path == '/index') {
                    return true // 首页 table 不让删
                }else if(item.path == name) {
                    return false
                }else {
                    return true
                }
            })
        },

        // 设置 activeTab
        setActiveTab(name) {
            this.activeTab = name
        },

        setActivePage(name) {
            this.activePage = name
        },

    },

})