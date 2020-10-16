// pages/audit/audit.js
const API = require('../../utils/request')
const { getUnAudit } = require('../../utils/request')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    auditList: null
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
    API.getUnAudit().then(res => {
      const req = JSON.parse(res)
      const that = this
      console.log(req);
      
      that.setData({
        auditList: req.data
      })
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
  audit(){

    // wx.request({
    //   url: 'http://',//请求地址路径
    //   data:'',//请求参数
    //   method:"post/get",//请求方式
    //   header:{//请求头
    //     "content-type": "application/x-www-form-urlencoded"
    //   },
    //   success(res){
    //     if(res.data.success){
    //       console.log(res)
    //     }else{
    //       console.log(res)
    //     }
    //   }
    // })
  }
})