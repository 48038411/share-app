const app = getApp();
const API = require('../../utils/request.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
  getUserInfo(event) {
    console.log(event.detail);

},
  /**
   * 登录，目前只是走个形式
   */
  weixinLogin(){
    API.login({
      wxId: app.globalData.wxId,
      wxNickName: app.globalData.userInfo.nickName,
      avatar: app.globalData.userInfo.avatarUrl
    }).then( res =>{
      const request = JSON.parse(res)
      // console.log(app.globalData.userInfo);
      
      app.globalData.user = request.user
      app.globalData.token = request.token['token'] 
      this.setData({
        userInfo:app.globalData.user
      })
    })
  }
})