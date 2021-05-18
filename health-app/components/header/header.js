Component({

    properties: {
        title: {type: String}
    },

    data: {
        showProgress: false,
    },

    // 在组件实例进入页面节点树时执行
    attached: function () {
        this.setData({showProgress: true})
    },

    // 在组件实例被从页面节点树移除时执行
    detached: function () {
    },

    methods: {
        onClickLeft() {
            wx.navigateBack({delta: 1})
        },
        progressEnd() {
            this.setData({
                showProgress: false,
            })
        },
    }

});
