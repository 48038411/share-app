// pages/personal/personal.js

const app = getApp();
const API = require('../../utils/request.js');
const dateUtil = require('../../utils/util')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:null,
    isSignin: 0,
    userRole: null
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
    // const that = this
    // API.login({
    //   wxId: app.globalData.wxId,
    //   wxNickName: app.globalData.userInfo.nickName,
    //   avatar: app.globalData.userInfo.avatarUrl
    // }).then( res =>{
    //   const request = JSON.parse(res)
      
    //   app.globalData.user = request.user
    //   console.log(request);
    //   app.globalData.token = request.token.token
    //   wx.setStorageSync('user', app.globalData.user)
    //   wx.setStorageSync('token', app.globalData.token)
    //   that.setData({
    //     userInfo:app.globalData.user,
    //     isSignin: request.isUserSignin
    //   })
    // })
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
              const time = dateUtil.formatTimeTwo(request.token.expirationTime,'Y-M-D h:m:s')
              
              app.globalData.user = request.user
              app.globalData.token = request.token.token
              wx.setStorageSync('user', app.globalData.user)
              wx.setStorageSync('token', app.globalData.token)
              that.setData({
                userInfo:app.globalData.user,
                isSignin: request.isUserSignin,
                userRole: request.token.role
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
  },
  myDuihuan(){  
    wx.navigateTo({
      url: '../myDuihuan/myDuihuan'
    })
  },
  signIn(){
    API.signIn({
      userId: wx.getStorageSync('user').id
    }).then(res => {
      const req = JSON.parse(res)      
      console.log(req);
      if(req.code == 200){
        wx.showToast({
          title: '签到成功',
          icon: "success",
          tx: '签到成功，记得每天都要来哦'

        })
        this.setData({
          isSignin: 1,
          userInfo: res.data
        })
      }else {
        wx.showModal({
          cancelColor: 'cancelColor',
          title: '签到失败',
          content: '今天已经签到过了哦'
        })
      }
    })
  },
  jifenLog(){
    wx.navigateTo({
      url: '../jifenLog/jifenLog',
    })
  },
  myContribute(){
    wx.navigateTo({
      url: '../myContribute/myContribute',
    })
  },
  audit(){
    wx.navigateTo({
      url: '../audit/audit',
    })
  },
  notice(){
    wx.navigateTo({
      url: '../notice/notice',
    })
  }
})