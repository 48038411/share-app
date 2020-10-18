// pages/auditDetail/auditDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    share: null,
    isOriginal: ''
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
  const id = this.data.share.id
  console.log(e);
  
  if(this.data.isOriginal == 0){
      this.data.isOriginal = 'PASS'
  }else{
      this.data.isOriginal = 'REJECT'
  }
  console.log(this.data.isOriginal);
  wx.request({
    url: 'http://localhost:8040/admin/shares/audit/'+id,//请求地址路径
    data:{
      auditStatusEnum: 'PASS',
      reson: e.detail.value.reason
    },//请求参数
    method:"put",//请求方式
    header:{//请求头
      "content-type": "application/json",
      "X-Token": wx.getStorageSync('token')
    },
    success(res){
      console.log(res);
      if(res.data.success){
        wx.showToast({
          title: '审核成功！',
        })
        wx.navigateBack({
          delta: 1,
        })
      }else{
        console.log(res)
      }
    }
  })
  
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      share: JSON.parse(options.share)
  })  
  console.log(this.data.share);
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
    // const id = e.currentTarget.dataset.item.id
    // console.log(id);
    
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