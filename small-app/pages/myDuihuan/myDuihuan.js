// pages/myDuihuan/myDuihuan.js
const API = require('../../utils/request')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: wx.getStorageSync('user'),
    shareList: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(this.data.user);
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
    API.myShare({
      id: this.data.user.id
    }).then(res => {
      const req = JSON.parse(res)
      var that = this
      if(req.succ){
        that.setData({
          shareList: req.data
        })
      }else{
        wx.showToast({
          title: '查询失败',
        })
        wx.navigateBack({
          delta: 1,
        })
      }
      console.log(req);      
    })
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
    }
})