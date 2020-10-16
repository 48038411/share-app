// pages/jifenLog/jifenLog.js
const API = require('../../utils/request')
const dateUtil = require('../../utils/util');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    logList: null,
    imgUrl: null
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
    API.myLog({
      id: wx.getStorageSync('user').id 
    }).then(res => {
      for(let c in res){
        console.log();
      }
      const req = JSON.parse(res)
      const that = this
      for(var t of req.data){        
        let date = dateUtil.formatTime(t.createTime)
        t.createTime = date
        const event = t.event;
        if(event == 'SIGN_IN'){
          that.setData({
            imgUrl: '../../images/qiandao.png'
          })
          console.log(this.data.imgUrl);
          
        }else if(event == 'CONTRIBUTE'){  
          that.setData({
            imgUrl: '../../images/contribute.png'
          })
          console.log(this.data.imgUrl);
          
        }
      }
      
      that.setData({
        logList: req.data
      })
      
    ;
      
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

  }
})