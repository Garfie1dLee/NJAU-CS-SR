// pages/login/login.js
const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'
var app = getApp();
const db=wx.cloud.database();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl:defaultAvatarUrl,
    nickName:"",

  },
  onChooseAvatar:function(res){
    console.log(res)
    console.log(res.detail.avatarUrl)
    this.setData({avatarUrl:res.detail.avatarUrl})
  },
  loginsys:function(){  
    let that = this
    console.log(this.data.nickName+this.data.avatarUrl)
    wx.cloud.uploadFile({
      cloudPath: this.data.nickName+this.data.avatarUrl.substring(this.data.avatarUrl.lastIndexOf(".")),
      filePath: this.data.avatarUrl, // 文件路径
      success: res => {
        // get resource ID
        console.log(res.fileID)
        let user={
          user_nickname:that.data.nickName,
          user_avatar:res.fileID,
          user_regtime:new Date(),
          user_usercount:0,
        }
        
        db.collection('users').add({
          // data 字段表示需新增的 JSON 数据
          data: user,
        })
        .then(dbres => {
            console.log("新增成功",dbres)
            user._id=dbres._id;
            app.globalData.cur_user=user;
            wx.switchTab({
              url: '/pages/newindex/newindex',
            })
        })
      },
      fail: err => {
        // handle error
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    wx.cloud.callFunction({
      name:"getOpenid",
      success:function(res){
        console.log(res.result.openid);
        let openid = res.result.openid;
        db.collection('users').where({
          _openid: openid,
         
        })    
        .get({
          success: function(res) {
            console.log(res.data)
            if(res.data.length>0)
            {
              let user=res.data[0];
              app.globalData.cur_user=user;
              wx.switchTab({
                url: '/pages/newindex/newindex',
              })
            }
          }
        })
      }
    });


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