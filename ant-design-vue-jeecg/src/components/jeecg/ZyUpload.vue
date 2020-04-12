<template>
  <div>
    <a-upload
      name="file"
      :multiple="true"
      :accept="uploadType?accept1:accept2"
      :action="uploadAction"
      :headers="headers"
      :data="{ isup: 1, bizPath: bizPath }"
      :listType="uploadType ? 'picture-card' : 'picture'"
      :fileList="fileList"
      :beforeUpload="beforeUpload"
      @preview="handlePreview"
      @change="handleChange"
      class="upload-list-inline"
    >
      <div v-if="fileList.length < picSize">
        <div v-if="uploadType">
          <a-icon type="plus" />
          <div class="ant-upload-text">图片上传</div>
        </div>
        <a-button v-else>
          <a-icon type="upload" />
          {{ text }}
        </a-button>
      </div>
    </a-upload>
    <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel" v-if="uploadType">
      <img alt="example" style="width: 100%" :src="previewImage" />
    </a-modal>
  </div>
</template>

<script>
import Vue from 'vue'
import { ACCESS_TOKEN } from '@/store/mutation-types'

const FILE_TYPE_ALL = 'all'
const FILE_TYPE_IMG = 'image'
const FILE_TYPE_TXT = 'file'
const uidGenerator = () => {
  return '-' + parseInt(Math.random() * 10000 + 1, 10)
}
const getFileName = path => {
  if (path.lastIndexOf('\\') >= 0) {
    let reg = new RegExp('\\\\', 'g')
    path = path.replace(reg, '/')
  }
  return path.substring(path.lastIndexOf('/') + 1)
}
export default {
  name: 'JUpload',
  data() {
    return {
      uploadAction: window._CONFIG['domianURL'] + '/sys/common/upload',
      urlDownload: window._CONFIG['domianURL'] + '/sys/common/download/',
      headers: {},
      fileList: [],
      previewVisible: false,
      previewImage: '',
      accept1: 'image/*',
      accept2:'',   //'.doc,.docx,.xml,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document',

    }
  },
  props: {
    text: {
      type: String,
      required: false,
      default: '点击上传'
    },
    fileType: {
      type: String,
      required: false,
      default: FILE_TYPE_ALL
    },
    /*这个属性用于控制文件上传的业务路径*/
    bizPath: {
      type: String,
      required: false,
      default: 'temp'
    },
    value: {
      type: String,
      required: false
    },
    //此属性被废弃了
    triggerChange: {
      type: Boolean,
      required: false,
      default: false
    },
    picSize: {
      type: Number,
      required: false,
      default: 1
    },
    // 文件上传类型 图片 - true  文件 - false
    uploadType: {
      type: Boolean,
      required: false,
      default: true
    }
  },
  watch: {
    value(val) {
      this.initFileList(val)
    }
  },
  created() {
    const token = Vue.ls.get(ACCESS_TOKEN)
    this.headers = { 'X-Access-Token': token }
  },

  methods: {
    initFileList(paths) {
      if (!paths || paths.length == 0) {
        //return [];
        // update-begin- --- author:os_chengtgen ------ date:20190729 ---- for:issues:326,Jupload组件初始化bug
        this.fileList = []
        return
        // update-end- --- author:os_chengtgen ------ date:20190729 ---- for:issues:326,Jupload组件初始化bug
      }
      let fileList = []
      let arr = paths.split(',')
      for (var a = 0; a < arr.length; a++) {
        fileList.push({
          uid: uidGenerator(),
          name: getFileName(arr[a]),
          status: 'done',
          url: this.urlDownload + arr[a],
          response: {
            status: 'history',
            message: arr[a]
          }
        })
      }
      this.fileList = fileList
    },
    handlePathChange() {
      let uploadFiles = this.fileList
      let path = ''
      if (!uploadFiles || uploadFiles.length == 0) {
        path = ''
      }
      let arr = []

      for (var a = 0; a < uploadFiles.length; a++) {
        arr.push(uploadFiles[a].response.message)
      }
      if (arr.length > 0) {
        path = arr.join(',')
      }
      this.$emit('change', path)
    },
    beforeUpload(file) {
      var fileType = file.type
      if (fileType === FILE_TYPE_IMG) {
        if (fileType.indexOf('image') < 0) {
          this.$message.warning('请上传图片')
          return false
        }
      } else if (fileType === FILE_TYPE_TXT) {
        if (fileType.indexOf('image') >= 0) {
          this.$message.warning('请上传文件')
          return false
        }
      }
      //TODO 扩展功能验证文件大小
      return true
    },
    handleChange(info) {
      console.log('--文件列表改变--')
      let fileList = info.fileList
      if (info.file.status === 'done') {
        if (info.file.response.success) {
          fileList = fileList.map(file => {
            if (file.response) {
              file.url = this.urlDownload + file.response.message
            }
            return file
          })
        }
        this.$message.success(`${info.file.name} 上传成功!`)
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} 上传失败.`)
      } else if (info.file.status === 'removed') {
        this.handleDelete(info.file)
      }
      this.fileList = fileList
      if (info.file.status === 'done' || info.file.status === 'removed') {
        this.handlePathChange()
      }
    },
    handleDelete(file) {
      //如有需要新增 删除逻辑
      console.log(file)
    },
    handleCancel() {
      this.previewVisible = false
    },
    handlePreview(file) {
      this.previewImage = file.url || file.thumbUrl
      this.previewVisible = true
    }
  },
  model: {
    prop: 'value',
    event: 'change'
  }
}
</script>

<style scoped>
.upload-list-inline >>> .ant-upload-list-picture-card{
      float: none;
}
.upload-list-inline >>> .ant-upload-list-item {
  float: left;
  margin-right: 8px;
}
.upload-list-inline >>> .ant-upload-animate-enter {
  animation-name: uploadAnimateInlineIn;
}
.upload-list-inline >>> .ant-upload-animate-leave {
  animation-name: uploadAnimateInlineOut;
}
</style>
