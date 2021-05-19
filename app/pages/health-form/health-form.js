const App = getApp()
const base = App.globalData.baseUrl

Page({

    data: {
        list: [],
        todayFrom: '',
    },

    onLoad: function (options) {
        this.load()
    },

    onShow() {
        console.log('页面显示')
    },

    load() {
        wx.showLoading({title: 'Loading'})
        wx.request({
            url: base + '/health/user/' + App.globalData.userInfo.id + '/today',
            method: 'GET',
            success: (res) => {
                const r = res.data
                if (r.status) {
                    this.setData({
                        todayFrom: r.data
                    })
                    console.log(this.data.todayFrom)
                }
            }
        })
        wx.request({
            url: base + '/health/user/' + App.globalData.userInfo.id,
            method: 'GET',
            success: (res) => {
                const r = res.data
                if (r.status) {
                    this.setData({
                        list: r.data
                    })
                    wx.hideLoading({})
                }
            }
        })
    },

    onclickPost(e) {
        const type = e.currentTarget.dataset.type
        const form = e.currentTarget.dataset.form
        wx.navigateTo({
            url: "/pages/registration/registration?type=" + type + "&form=" + JSON.stringify(form)
        })
    },

    onClick() {
        wx.navigateTo({
            url: "/pages/registration/registration"
        })
    },

});