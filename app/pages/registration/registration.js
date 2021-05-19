const App = getApp()
const base = App.globalData.baseUrl

Page({

    data: {
        canEdit: false,
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
    },

    onLoad: function (options) {
        if (options.type === 'old') {
            this.setData({
                form: options.form ? JSON.parse(options.form) : {}
            })
        }
        this.setData({
            canEdit: options.type === 'new',
        })
        console.log(this.data.form)
    },

    onLocation() {
        wx.showLoading({title: '发起地图组件'})
        wx.chooseLocation({
            success: (res) => {
                wx.hideLoading({})
                this.setData({
                    ['form.location']: res.address + res.name
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
        if (!this.data.form.location) {
            wx.showToast({
                title: "请选择你的定位",
                icon: "error"
            })
            return
        }
        wx.showLoading({
            title: '正在上传中'
        })
        let form = e.detail.value
        form.uid = App.globalData.userInfo.id
        wx.request({
            url: base + '/health',
            method: 'POST',
            data: form,
            success: (res) => {
                console.log(res.data)
                if (res.data.status) {
                    setTimeout(() => {
                        this.setData({
                            canEdit: false,
                        })
                        wx.hideLoading({})
                        wx.showToast({title: '健康上报成功'})
                    }, 500)
                } else {
                    setTimeout(() => {
                        this.setData({
                            canEdit: false,
                        })
                        wx.hideLoading({})
                        wx.showToast({
                            title: res.data.msg,
                            icon: 'error'
                        })
                    }, 500)
                }
            }
        })
    },

});