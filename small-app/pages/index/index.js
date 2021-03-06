// pages/index/index.js
const app = getApp();
const API = require('../../utils/request.js')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tab: 0,
        shareList: null,
        notice: null,
        pageNo: 1,
        pageSize: 5
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        // var that = this
        // API.getNotic().then(res => {
        //     that.setData({
        //         notice: res.data
        //     })
        // })
        // that.setData({
        //     user: app.globalData.user
        // })
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

        var that = this
        API.getNotic().then(res => {
            that.setData({
                notice: res.data
            })
        })
        that.setData({
            user: app.globalData.user
        })
        API.getList({
            pageNo: that.data.pageNo,
            pageSize: that.data.pageSize
        }).then(res => {
            that.setData({
                shareList: res.data
            })
        })
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {
        this.setData({
            pageNo:1
          })
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
        var that = this
        that.setData({
          pageNo:that.data.pageNo+1
        })
        API.getList({
          pageNo:that.data.pageNo,
          pageSize:that.data.pageSize
        }).then(res =>{
          const shares = res.data
          that.setData({
            shareList:that.data.shareList.concat(shares)
          })
        
        })
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    changeTab(e) {
        this.setData({
            tab: e.currentTarget.dataset.tab
        })
    },
    // 前往详情页
    duihuan(e) {
        //取出绑定对象
        console.log(e);
        var share = e.currentTarget.dataset.item
        if (share.downloadUrl == null) {
            wx.navigateTo({
                url: '../shareDetail/shareDetail?share=' + JSON.stringify(share),
            })
        } else {
            wx.navigateTo({
                url: '../duihuanSuccess/duihuanSuccess?share=' + JSON.stringify(share),
            })
        }
    },
    // 兑换
    goDetail(e) {
        // 取出绑定对象
        console.log(e)
        var share = e.currentTarget.dataset.item
        wx.navigateTo({
            url: '../shareDetail/shareDetail?share=' + JSON.stringify(share),
        })
    },
    search(e) {
        console.log(e.detail);
        API.getList({
            title: e.detail.value
        }).then(res => {
            console.log(res);

            // const req = JSON.parse(res)            
            const that = this
            that.setData({
                shareList: res.data
            })
        })
    }
})