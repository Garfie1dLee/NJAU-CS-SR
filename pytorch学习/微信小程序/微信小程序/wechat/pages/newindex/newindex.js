// pages/newindex/newindex.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  jump(){
    wx.navigateTo({
      url: '../result/result'
  })},
  jump1(){
    wx.navigateTo({
      url: '../result/result1'
  })},
  jump2(){
    wx.navigateTo({
      url: '../result/result2'
  })},
  jump3(){
    wx.navigateTo({
      url: '../result/result3'
  })},
  jump4(){
    wx.navigateTo({
      url: '../result/result4'
  })},
  jumpp(){
    wx.navigateTo({
      url: '../result/intro'
  })},
  jumppp(){
    wx.navigateTo({
      url: '../result/intro2'
  })},
  jumpppp(){
    wx.navigateTo({
      url: '../result/intro3'
  })},
  jumppppp(){
    wx.navigateTo({
      url: '../result/intro4'
  })},
  login(){
    if(app.globalData.cur_user=='')
    wx.navigateTo({
      url: '../login/login'
  })
  else
  {
    wx.showToast({
      title: '您已登录',
      icon: 'success',
      duration: 750
    })
  }
  },
  onLoad(){
    


  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})