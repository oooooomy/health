const App = getApp()

Page({

    data: {
        show: true,
        scrollTop: 0,
    },

    onLoad: function (options) {

    },

    scroll(e) {
        if (e.detail.scrollTop > 100) {
            this.setData({
                show: false,
            })
        } else {
            this.setData({
                show: true,
            })
        }
    },

    onClickRegistration() {
        wx.navigateTo({
            url: '/pages/health-form/health-form'
        })
    },

    onClickMessage() {
        wx.navigateTo({
            url: '/pages/message/message'
        })
    },

    onClickRequestOut() {
        wx.navigateTo({
            url: '/pages/out-request/out-request'
        })
    },

    onClickRequestMaterial() {
        wx.navigateTo({
            url: '/pages/material-request/material-request'
        })
    },

    onClickNotice() {
        wx.navigateTo({
            url: '/pages/notice/notice'
        })
    },

    onClickMe() {
        wx.navigateTo({url: '/pages/me/me'})
    },

});