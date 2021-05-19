App({

    globalData: {
        //用户信息
        userInfo: {},
        //后端api地址
        baseUrl: 'http://192.168.31.162:8080/api'
    },

    onLaunch: function () {

        // 登录
        wx.login({
            success: res => {
                // 发送 res.code 到后台换取 openId, sessionKey, unionId
                this.userLogin(res.code)
            }
        })

        // 获取用户信息
        wx.getSetting({
            success: res => {
                if (res.authSetting['scope.userInfo']) {
                    // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
                    wx.getUserInfo({
                        success: res => {
                        }
                    })
                }
            }
        })

        // 获取系统状态栏信息
        wx.getSystemInfo({
            success: e => {
                this.globalData.StatusBar = e.statusBarHeight;
                let capsule = wx.getMenuButtonBoundingClientRect();
                if (capsule) {
                    this.globalData.Custom = capsule;
                    this.globalData.CustomBar = capsule.bottom + capsule.top - e.statusBarHeight;
                } else {
                    this.globalData.CustomBar = e.statusBarHeight + 50;
                }
            }
        })

    },

    //用户登录
    userLogin(code) {
        wx.showLoading({
            title: '用户登录中',
            mask: true,
        })
        const url = this.globalData.baseUrl + '/user/sign?code=' + code
        wx.request({
            url: url,
            method: 'GET',
            success: (res) => {
                const r = res.data
                if (r.status) {
                    this.globalData.userInfo = r.data
                    console.log('login user: ', this.globalData.userInfo)
                    wx.hideLoading({})
                }
            }
        })
    },

})