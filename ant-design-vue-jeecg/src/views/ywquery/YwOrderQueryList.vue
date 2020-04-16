<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="24">
            <a-form-item label="销售单号">
              <a-input placeholder="请输入销售单号" v-model="queryParam.batsaleno"></a-input>
            </a-form-item>
          </a-col>

          <a-col :md="6" :sm="24" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>


    <div>
      <!--<div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>-->

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <!--<a @click="handleEdit(record)">查看图片</a>-->
          <a @click="showPhoto(record)">查看图片</a>
          <!--<a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>-->
        </span>

      </a-table>
    </div>
    <YwOrderQueryListModel ref="showImgModel"></YwOrderQueryListModel>
  </a-card>
</template>

<script>
  import { httpAction,getAction } from '@/api/manage'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import YwOrderQueryListModel from './YwOrderQueryListModal'
  export default {
    name: "YwOrderQueryList",
    mixins: [JeecgListMixin],
    components: {
      YwOrderQueryListModel
    },
    data () {
      return {
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '单据号',
            align:"center",
            dataIndex: 'batsaleno'
          },
          /*{
            title: '识别号',
            align:"center",
            dataIndex: 'rowno'
          },
          {
            title: '商品编码',
            align:"center",
            dataIndex: 'warecode'
          },
          {
            title: '商品唯一编码',
            align:"center",
            dataIndex: 'wareid'
          },
          {
            title: '商品名称',
            align:"center",
            dataIndex: 'warename'
          },*/
          {
            title: '商品名称',
            align:"center",
            dataIndex: 'waregeneralname'
          },
          {
            title: '批号',
            align:"center",
            dataIndex: 'makeno'
          },
          {
            title: '商品规格',
            align:"center",
            dataIndex: 'warespec'
          },
          {
            title: '生产企业',
            align:"center",
            dataIndex: 'factoryname'
          },
          {
            title: '国药准字',
            align:"center",
            dataIndex: 'fileno'
          },
          {
            title: '单位',
            align:"center",
            dataIndex: 'wareunit'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/test/ywOrderQueryMain/list",
          delete: "/test/jeecgOrderMain/delete",
          deleteBatch: "/test/jeecgOrderMain/deleteBatch",
          exportXlsUrl: "/test/jeecgOrderMain/exportXls",
        },
        ipagination:{
          current: 1,
          pageSize: 500,
          pageSizeOptions: ['500', '550', '600'],
          showTotal: (total, range) => {
            return range[0] + "-" + range[1] + " 共" + total + "条"
          },
          showQuickJumper: true,
          showSizeChanger: true,
          total: 0
        },
      }
    },
    methods: {
      showPhoto(record){
        let _this = this;
        this.$message.loading({ content: '数据查询中,请稍后......'});
        getAction('/test/ywOrderQueryMain/imgList',{"wareid" : record.wareid}).then(res=>{
          if(res.success){
            _this.$refs.showImgModel.edit(record,res.result);
            _this.$refs.showImgModel.title = "图片查看";
            _this.$refs.showImgModel.disableSubmit = true;
            this.$message.loading({ content: '查询成功'});
          }else{
            this.$message.error('查询失败');
          }
        }).error(e => {
          this.$message.error('查询失败');
        });


        /*this.$refs.showImgModel.edit(record);
        this.$refs.showImgModel.title = "图片查看";
        this.$refs.showImgModel.disableSubmit = true;*/
      }
    }
  }
</script>
<style scoped>
  /** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }
  .ant-card-body .table-operator{
    margin-bottom: 18px;
  }
  .ant-table-tbody .ant-table-row td{
    padding-top:15px;
    padding-bottom:15px;
  }
  .anty-row-operator button{margin: 0 5px}
  .ant-btn-danger{background-color: #ffffff}

  .ant-modal-cust-warp{height: 100%}
  .ant-modal-cust-warp .ant-modal-body{height:calc(100% - 110px) !important;overflow-y: auto}
  .ant-modal-cust-warp .ant-modal-content{height:90% !important;overflow-y: hidden}
</style>