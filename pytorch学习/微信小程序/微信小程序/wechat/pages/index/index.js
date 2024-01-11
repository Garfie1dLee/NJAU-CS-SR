// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    motto: '木薯病害识别系统',
    user:null,
  },
  // 事件处理函数
  openabout(){
    wx.navigateTo({
      url: '../about/about'})
  
  },
  openopinion(){
    wx.navigateTo({
      url: '../opinion/opinion'})
  
  },
  openhistory(){
    wx.navigateTo({
      url: '../history/history'})
  
  },
  onLoad() {
    if(app.globalData.cur_user!='')
    this.setData({
    user:app.globalData.cur_user})
    else
    {
      user1:{
        user_nickname:'未登录'
      }
      this.setData({
        user:user1
      })
    }
    
    
  },
  onRefresh:function(){
    //导航条加载动画
    wx.showNavigationBarLoading()
    //loading 提示框
    wx.showLoading({
      title: 'Loading',
    })
    this.onLoad();
    console.log("下拉刷新");
    setTimeout(function () {
  
      wx.hideLoading();
      wx.hideNavigationBarLoading();
      //停止下拉刷新
      wx.stopPullDownRefresh();
    }, 2000)
  },
  onPullDownRefresh:function(){
    this.onRefresh();
  },


})
