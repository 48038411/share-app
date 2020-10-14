// pages/personal/personal.js

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

  // 点击授权成功之后的方法
  bindgetuserinfo() {
    var app = getApp()
    var that = this;
        wx.login({
          success (res) {
        wx.request({
          url: 'https://api.weixin.qq.com/sns/jscode2session',
          data:{
            appid: 'wx05dcf498882dad7a',
            secret: '17e0608a74fe35fdfe66bd5efb6fc8a2',
            js_code: res.code,
            grant_type: 'authorization_code'
          },

          success: res =>{
              app.globalData.wxId = res.data.openid                

          wx.getUserInfo({
          success: function(res) {
            console.log("昵称是:" + res.userInfo.nickName)
            app.globalData.userInfo = res.userInfo
            console.log(res.userInfo)
            API.login({
              wxId: app.globalData.wxId,
              wxNickName: app.globalData.userInfo.nickName,
              avatar: app.globalData.userInfo.avatarUrl
            }).then( res =>{
              const request = JSON.parse(res)
              
              app.globalData.user = request.user
              app.globalData.token = request.token['token'] 
              
              wx.setStorageSync('user', app.globalData.user)
              wx.setStorageSync('token', app.globalData.token)
              that.setData({
                userInfo:app.globalData.user
              })
            })
          
          }
        })
          }
        })
       }
    }) 
  },
  /**
   * 登录，目前只是走个形式
   */
  weixinLogin(){
    console.log("进入登录方法")
    wx.getSetting({
      success(res) {
        if(res.authSetting['scope.userInfo']) {
          console.log("微信授权")
          
        }
      }
    })
  }
})