// pages/tougao/tougao.js
var API = require("../../utils/request.js")
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isOriginal: ''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //判断用户是否登录
        if (wx.getStorageSync('user')) {
            wx.switchTab({
                url: '../../pages/tougao/tougao'
            })
        } else {
            wx.reLaunch({
                url: '../personal/personal'
            })
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    radioChange(e) {
        var that = this
        
        that.setData({
            isOriginal: e.detail.value
        })
        // if(e.detail.value == 1){
        //     that.setData({
        //         isOriginal: false
        //     })
        // }else{
        //     that.setData({
        //         isOriginal: true
        //     })
        // }
        console.log('radio发生change事件，携带value值为：', e.detail.value);
    },
    formSubmit: function (e) {
        if(this.data.isOriginal == 1){
            this.data.isOriginal = true
        }else{
            this.data.isOriginal = false
        }
        console.log(this.data.isOriginal);
        API.contribute({
            userId: wx.getStorageSync('user').id,
            title: e.detail.value.title,
            isOriginal: this.data.isOriginal,
            author: e.detail.value.author,
            summary: e.detail.value.summary,
            price: e.detail.value.price,
            downloadUrl: e.detail.value.downloadUrl
        }).then(res => {
            wx.showToast({
              title: '投稿成功', 
              icon: 'success'
            })
        })
        
    }
})