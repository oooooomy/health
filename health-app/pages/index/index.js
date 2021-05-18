Page({

    data: {
        show: true,
        scrollTop: 0,
    },

    onLoad: function (options) {

    },

    scroll(e) {
        if (e.detail.scrollTop > 90) {
            this.setData({
                show: false,
            })
        } else {
            this.setData({
                show: true,
            })
        }
    },

    onClickMe() {
        wx.navigateTo({
            url: '/pages/me/me'
        })
    },

});