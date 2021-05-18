// app.js
App({

  globalData: {
    userInfo: null,
    baseUrl: 'http://192.168.31.162:8080/api'
  },

  onLaunch() {
    // 登录
    wx.login({
      success: res => {
        this.userLogin(res.code)
      }
    })
  },

  //用户登录
  userLogin(code) {
    const url = this.globalData.baseUrl + '/user/sign?code=' + code
    wx.request({
      url: url,
      method: 'GET',
      success: (res) => {
        console.log(res)
      }
    })
  },

})
