Page({

    data: {
        value: '',
    },

    onLoad: function (options) {

    },

    onChange(event) {
        // event.detail 为当前输入的值
        console.log(event.detail);
    },

});