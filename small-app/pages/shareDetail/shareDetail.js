// pages/shareDetail/shareDetail.js
const app = getApp();
const API = require('../../utils/request.js')
Page({
    /**
     * 页面的初始数据
     */
    data: {
        share: null,
        userId: wx.getStorageSync('user').id
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {        
        this.setData({
            share: JSON.parse(options.share)
        })
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
    /**
     * 兑换
     */
    duihuan(e) {
        //取出绑定对象
        console.log(e)
        var share = e.currentTarget.dataset.item
        API.duihuan({
            userId: this.data.userId,
            shareId: share.id
        }).then(res => {
            wx.navigateTo({
                url: '../duihuanSuccess/duihuanSuccess?share=' + JSON.stringify(share),
            })  
            
        })
    },
    down(e){
        var share = e.currentTarget.dataset.item
        wx.navigateTo({
            url: '../duihuanSuccess/duihuanSuccess?share=' + JSON.stringify(share),
        })   
    }
})