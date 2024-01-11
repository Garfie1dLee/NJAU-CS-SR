// pages/result/result.js
const app = getApp()
const db=wx.cloud.database();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showIndex:null,//打开弹窗的对应下标
    height:'',//屏幕高度,
    user:null,

  },
  addcount(){
    const _ = db.command
db.collection('users').doc(this.data.user._id).update({
  data: {
    // 表示指示数据库将字段自增 10
    user_usercount: _.inc(1)
  },
  success: function(res) {
    app.globalData.cur_user.user_usercount=app.globalData.cur_user.user_usercount+1;
  }
})



   
  },
  
  openPopup(){
    wx.showModal({
      title: 'CGM', //提示的标题
      content: '我是内容', //提示的内容
      success: function(res) {
        if(res.confirm) {
          console.log('用户点击了确定')
        } else if (res.cancel) {
          console.log('用户点击了取消')
        }
      }
    })
  
  },
  //关闭弹窗
  
  



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      user:app.globalData.cur_user})
    
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