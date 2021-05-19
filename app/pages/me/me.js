const App = getApp()
const base = App.globalData.baseUrl

Page({

    data: {
        userInfo: {},
        picker: ['男性', '女性'],
        date: '',
        region: [],
        imgList: [],
        gender: '',
    },

    onLoad: function (options) {
        const u = base + '/image/' + App.globalData.userInfo.cardImageId
        this.setData({
            userInfo: App.globalData.userInfo,
            date: App.globalData.userInfo.returnAt,
            region: App.globalData.userInfo.address ? App.globalData.userInfo.address.split("-") : [],
            imgList: [u],
            gender: App.globalData.userInfo.gender,
        })
    },

    submit(e) {
        wx.showLoading({title: 'Loading'})
        const form = e.detail.value
        let user = {
            id: App.globalData.userInfo.id,
            avatar: this.data.userInfo.avatar,
            name: form.name,
            mobile: form.mobile,
            number: form.number,
            gender: this.data.gender,
            address: this.data.region.length === 3 ? this.data.region[0] + '-' + this.data.region[1] + '-' + this.data.region[2] : '',
            returnAt: this.data.date,
            cardImageId: this.data.userInfo.cardImageId,
        }
        wx.request({
            url: base + '/user',
            method: 'PUT',
            data: user,
            success: (res) => {
                const r = res.data
                if (r.status) {
                    App.globalData.userInfo = r.data
                    this.setData({userInfo: r.data})
                    setTimeout(() => {
                        wx.hideLoading()
                        wx.showToast({
                            title: '用户信息保存成功',
                        })
                    }, 500)
                }
            }
        })
    },

    getUserInfo() {
        if (!App.globalData.userInfo.avatarUrl) {
            wx.getUserProfile({
                desc: '用于完善会员资料',
                success: (res) => {
                    App.globalData.userInfo.avatar = res.userInfo.avatarUrl
                    let str = 'userInfo.avatar'
                    this.setData({
                        [str]: res.userInfo.avatarUrl
                    })
                },
                fail: (res) => {
                    wx.showToast({
                        title: "用户取消授权",
                        icon: 'error',
                        duration: 2000
                    })
                }
            })
        }
    },

    PickerChange(e) {
        this.setData({
            gender: this.data.picker[e.detail.value]
        })
    },

    DateChange(e) {
        this.setData({
            date: e.detail.value
        })
    },

    RegionChange(e) {
        this.setData({
            region: e.detail.value
        })
    },

    ChooseImage() {
        wx.chooseImage({
            count: 1,
            sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], //从相册选择
            success: (res) => {
                this.setData({
                    imgList: res.tempFilePaths
                })
                wx.uploadFile({
                    timeout: 1000 * 60 * 60 * 10,
                    filePath: this.data.imgList[0],
                    name: 'file',
                    url: base + '/image',
                    success: (res) => {
                        const parse = JSON.parse(res.data);
                        if (parse.status) {
                            this.setData({
                                ['userInfo.cardImageId']: parse.data
                            })
                            wx.showToast({title: "照片上传成功"})
                        }
                    }
                })
            }
        });
    },

    ViewImage(e) {
        wx.previewImage({
            urls: this.data.imgList,
            current: e.currentTarget.dataset.url
        });
    },

    DelImg(e) {
        wx.showModal({
            title: '提示',
            content: '确定要删除这张照片吗吗？',
            cancelText: '取消',
            confirmText: '确定',
            success: res => {
                if (res.confirm) {
                    this.data.imgList.splice(e.currentTarget.dataset.index, 1);
                    this.setData({
                        imgList: this.data.imgList
                    })
                }
            }
        })
    },

});