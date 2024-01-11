// pages/image/image.js
var app = getApp();
const db=wx.cloud.database();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    src:'',
    modpath:'',
    listData:[
      {"code":"1","text":"unknown","type":"unknown"},
      {"code":"2","text":"unknown","type":"unknown"},
      {"code":"3","text":"unknown","type":"unknown"},
      {"code":"4","text":"unknown","type":"unknown"},
      {"code":"5","text":"unknown","type":"unknown"},
      ],

  },
  select(){
    let that = this
    wx.showActionSheet({
      itemList: ['VGG16', 'Transformer','Efficient-Net', 'Vit16(早期)',
    'Vit16(推荐)'],
      success: function (res) {
        if (!res.cancel) {
          console.log(res.tapIndex) //这里是点击了那个按钮的下标
          if(res.tapIndex == 4)
             that.data.modpath = 'h10'

        }
      }
    })
  },
  uploadimage(){

    let that = this
   wx.chooseImage({
    count: 1,
    sizeType: ['original','compressed'],
    sourceType: ['album', 'camera'],
    success (res) {
      that.setData(
        {
          src:res.tempFilePaths
        }
      )
      
    }
  })
  },
  upcloud(){
    let that = this
    if(that.data.src == '')
    {
      wx.showToast({
        title: '请先上传图片！',
        icon: 'error',
        duration: 2000 //持续的时间
      })
    }
    else if(that.data.modpath == '')
    {
      wx.showToast({
        title: '请先选择模型！',
        icon: 'error',
        duration: 2000 //持续的时间
      })
    }
    else{
    
    wx.uploadFile({
      url: 'https://www.garfieldlee.work/predict', //仅为示例，非真实的接口地址
      filePath: that.data.src[0],
      name: 'file',
      formData: {
        'modname': that.data.modpath
      },
      success: function(res){
        console.log("success")
        var listData1=[
          {"code":"1","text":"unknown","type":"unknown"},
          {"code":"2","text":"unknown","type":"unknown"},
          {"code":"3","text":"unknown","type":"unknown"},
          {"code":"4","text":"unknown","type":"unknown"},
          {"code":"5","text":"unknown","type":"unknown"},
          ]
          console.log(res.data)
        let json = JSON.parse(res.data)
        {
          for (let i = 0; i < 5; i++) {
            listData1[i].text=json.result[i].slice(6,13).split(/[\t\r\f\n\s]*/g).join('')
            listData1[i].type=json.result[i].substr(json.result[i].lastIndexOf('.')-1,5).split(/[\t\r\f\n\s]*/g).join('')
          }
          that.setData({
            listData:listData1
          })
         
           console.log(that.data.listData)
        }
        that.onLoad()

        console.log(json.result[0])
        var data = json.result[0]
        var data1 = data.slice(6,13)
        data1 = data1.split(/[\t\r\f\n\s]*/g).join('')
        console.log(data1)
        var date = new Date()
        var date1 = date.toLocaleDateString()+date.toLocaleTimeString()
        db.collection('history').add({
          // data 字段表示需新增的 JSON 数据
          data: {
            'user_id': app.globalData.cur_user._id,
            'info': data ,
            'time': date1

          },
        })
        that.setData({
          text: data
        })
        setTimeout(()=>
        {
          console.log("loading");
          if(data1 == "Healthy")
        {
          wx.navigateTo({
            url: '../result/result4'
        })
        }
        else if (data1 == "CMD")
        {
          wx.navigateTo({
            url: '../result/result3'
        })
        }
        else if (data1 == "CGM")
        {
          wx.navigateTo({
            url: '../result/result2'
        })
        }
        else if (data1 == "CBSD")
        {
          wx.navigateTo({
            url: '../result/result1'
        })
        }
        else if (data1 == "CBB")
        {
          wx.navigateTo({
            url: '../result/result'
        })
        }
          
        }, 5000 ) 
        
        
        //do something
      }
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