// pages/opinion/opinion.js
var app = getApp();
const db=wx.cloud.database();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    loading: false,
    contact: '',
    content: ''

  },
  formSubmit: function (e) {
    let that = this;
    let content = e.detail.value.opinion;
    let contact = e.detail.value.contact;
    let regPhone = /^1[3578]\d{9}$/;
    let regEmail = /^[a-z\d_\-\.]+@[a-z\d_\-]+\.[a-z\d_\-]+$/i;
    if (content == "") {
      wx.showModal({
        title: '提示',
        content: '反馈内容不能为空!',
      })
      return false
    }
    if (contact == "") {
      wx.showModal({
        title: '提示',
        content: '手机号或者邮箱不能为空!',
      })
      return false
    }
    if (contact == "" && content == "") {
      wx.showModal({
        title: '提示',
        content: '反馈内容,手机号或者邮箱不能为空!',
      })
      return false
    }
    if ((!regPhone.test(contact) && !regEmail.test(contact)) || (regPhone.test(contact) && regEmail.test(contact))) { //验证手机号或者邮箱的其中一个对
      wx.showModal({
        title: '提示',
        content: '您输入的手机号或者邮箱有误!',
      })
      return false
    } else {
      this.setData({
        loading: true
      })
      let model, system, platform;
      wx.getSystemInfo({
        success: function (res) {
          model = res.model;
          system = res.system;
          platform = res.platform;
        }
      })
    
      db.collection('opinions').add({
        // data 字段表示需新增的 JSON 数据
        data: {
          'content': content,
          'contact': contact,
          'device_model': model, //手机型号
          'device_system ': system, //操作系统版本
          'app_version': platform  //客户端平台
        },
      })
      .then(dbres => {
          console.log("新增成功",dbres)
          that.setData({
            loading: false,
            contact: '',
            content: ''
          })
          wx.showToast({
            title: '成功',
            icon: 'success',
            duration: 1500
          })
      })
    }
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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