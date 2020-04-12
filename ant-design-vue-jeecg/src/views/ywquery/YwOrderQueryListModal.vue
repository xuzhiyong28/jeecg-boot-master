<template>
  <a-modal
    :title="title"
    :width="1200"
    :heigth="1200"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <!--<div style="float: left;width:200px;height:200px;">
      <img style="width: 100%;" src="http://img0.imgtn.bdimg.com/it/u=1138935709,3573339285&fm=26&gp=0.jpg">
    </div>-->

    <div style="background-color:#ffffff">
      <a-row>
        <a-col :span="8">
          <a-button type="primary" icon="download" :size="size" @click="downImg">一键下载图片</a-button>
        </a-col>
      </a-row>
      <a-row v-for="item in showImgList">
        <a-col :span="8" v-for="tempIndex in item">
          <a-card :title="tempIndex.title" :bordered="false">
            <!--http://img0.imgtn.bdimg.com/it/u=1138935709,3573339285&fm=26&gp=0.jpg-->
            <p><img style="width: 300px;height: 200px" :src="tempIndex.img"></p>
          </a-card>
        </a-col>
      </a-row>

    </div>



  </a-modal>
</template>

<script>
  import {httpAction, getAction} from '@/api/manage'
  import JDate from '@/components/jeecg/JDate'
  import pick from 'lodash.pick'
  import moment from "moment"
  import ZyUpload from '@/components/jeecg/ZyUpload'
  import JSZip from 'jszip'
  import FileSaver from "file-saver"

  export default {
    name: "YwOrderQueryListModal",
    components: {
      JDate,
      JSZip,
      FileSaver
    },
    data() {
      return {
        size : 'large',
        title: "图片查看",
        visible: false,
        showImgList : [],
        batchSize : 1,
        imgMap : [],
      }
    },
    created() {
    },
    methods: {
      edit(record,base64MapList) {
        this.showImgList = [];
        this.imgMap = [];
        for(let i = 0 ; i < base64MapList.length ; i++){
          if(base64MapList[i].img){
            let tempMap = {
              "scanno" :　base64MapList[i].scanno,
              "title" : "扫描件编号 :" + base64MapList[i].scanno,
              "img" : "data:image/png;base64," + base64MapList[i].img
            };
            this.imgMap.push(tempMap);
          }
        }
        this.batchSize = parseInt(this.imgMap.length / 3 + 1);
        for(let i = 1 ; i <= this.batchSize; i++){
          let tempList = [];
          for(let j = 1 ; j <= 3; j++){
            if(this.imgMap[j * i - 1]){
              tempList.push(this.imgMap[j * i - 1]);
            }
          }
          this.showImgList.push(tempList);
        }
        this.visible = true;
      },
      handleOk() {

      },
      handleCancel() {
        this.close()
      },
      downImg(){
        //this.downloadFile('测试.png',this.imgMap[1]['img']);
        let blogTitle = '打包图片';
        let zip = new JSZip();
        let imgs = zip.folder(blogTitle);
        let baseList = [];
        let imgNameList = [];
        let arr = [];
        for(let i = 0 ; i < this.imgMap.length ; i++){
          arr.push(this.imgMap[i]['img']);
          imgNameList.push(this.imgMap[i]['scanno']);
        }
        for (let i = 0; i < arr.length; i++) {
          let image = new Image();
          // 解决跨域 Canvas 污染问题
          image.setAttribute('crossOrigin', 'anonymous');

          image.onload = function () {
            let canvas = document.createElement('canvas');
            canvas.width = image.width;
            canvas.height = image.height;

            let context = canvas.getContext('2d');
            context.drawImage(image, 0, 0, image.width, image.height);

            let url = canvas.toDataURL(); // 得到图片的base64编码数据
            canvas.toDataURL('image/png');

            baseList.push(url.substring(22));    // 去掉base64编码前的 data:image/png;base64,
            if (baseList.length === arr.length && baseList.length > 0) {
              for (let k = 0; k < baseList.length; k++) {
                imgs.file(imgNameList[k] + '.png', baseList[k], {base64: true})
              }
              zip.generateAsync({type: 'blob'}).then(function (content) {
                // see FileSaver.js
                FileSaver.saveAs(content, blogTitle + '.zip');
              });
            }

          };
          image.src = arr[i];
        }

      },
      base64ToBlob(code) {
        let parts = code.split(';base64,');
        let contentType = parts[0].split(':')[1];
        let raw = window.atob(parts[1]);
        let rawLength = raw.length;

        let uInt8Array = new Uint8Array(rawLength);

        for (let i = 0; i < rawLength; ++i) {
          uInt8Array[i] = raw.charCodeAt(i);
        }
        return new Blob([uInt8Array], {type: contentType});
      },

      //下载
      downloadFile(fileName, content) {
        let aLink = document.createElement('a');
        let blob = this.base64ToBlob(content); //new Blob([content]);

        let evt = document.createEvent("HTMLEvents");
        evt.initEvent("click", true, true);//initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
        aLink.download = fileName;
        aLink.href = URL.createObjectURL(blob);

        // aLink.dispatchEvent(evt);
        //aLink.click()
        aLink.dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));//兼容火狐
      },



      close() {
        this.$emit('close');
        this.visible = false;
      },
    }
  }
</script>

<style scoped>
  .ant-btn {
    padding: 0 10px;
    margin-left: 3px;
  }

  .ant-form-item-control {
    line-height: 0px;
  }

  /** 主表单行间距 */
  .ant-form .ant-form-item {
    margin-bottom: 10px;
  }

  /** Tab页面行间距 */
  .ant-tabs-content .ant-form-item {
    margin-bottom: 0px;
  }
</style>