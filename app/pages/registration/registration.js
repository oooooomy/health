Page({

    data: {
        form: {
            uid: '',
            fever: '',
            cough: '',
            weak: '',
            contact: '',
            area: '',
            quarantine: '',
            location: '',
        },
        location: '',
    },

    onLoad: function (options) {

    },

    onLocation() {
        wx.showLoading({title: '发起地图组件'})
        wx.chooseLocation({
            success: (res) => {
                this.setData({
                    location: res.address + res.name
                })
            },
            fail: (res) => {
                wx.showToast({
                    title: res.errMsg,
                    icon: "error",
                    duration: 2000
                })
            }
        })
    },

    submit(e) {
        wx.showLoading({
            title: '正在上传中'
        })
        let form = e.detail.value
        form.uid = "oHfNa46QgAqy6wIMIzPFWDwM6LDw"
        console.log(form)
        setTimeout(() => {
            wx.hideLoading({})
        }, 500)
    },

});