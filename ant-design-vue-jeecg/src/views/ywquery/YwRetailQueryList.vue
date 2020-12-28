<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="12" :sm="24">
            <a-form-item label="查询时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-range-picker
                style="width: 210px"
                v-model="queryParam.createTimeRange"
                format="YYYY-MM-DD"
                :placeholder="['开始时间', '结束时间']"
                @change="onDateChange"
                @ok="onDateOk"
              />
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
          <!--<a @click="showPhoto(record)">查看图片</a>-->
        </span>

      </a-table>
    </div>
  </a-card>
</template>

<script>
  import { httpAction,getAction } from '@/api/manage';
  import { JeecgListMixin } from '@/mixins/JeecgListMixin';
  import { filterObj } from '@/utils/util';
  export default {
    name: "YwOrderQueryList",
    mixins: [JeecgListMixin],
    components: {
    },
    data () {
      return {
        queryParam: {
          createTimeRange:[],
        },
        columns: [
          {
            title: '序号',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '销售日期',
            align:"center",
            dataIndex: 'execdate'
          },
          {
            title: '客户ID',
            align:"center",
            dataIndex: 'vencusno'
          },

          {
            title: '客户名称',
            align:"center",
            dataIndex: 'vencusname'
          },
          {
            title: '配送地址',
            align:"center",
            dataIndex: 'address'
          },
          {
            title: '批号',
            align:"center",
            dataIndex: 'makeno'
          },
          {
            title: '数量',
            align:"center",
            dataIndex: 'wareqty'
          },
          {
            title: '有效期至',
            align:"center",
            dataIndex: 'invalidate'
          },
          {
            title: '单价',
            align:"center",
            dataIndex: 'whlprice'
          },
          {
            title: '货品ID',
            align:"center",
            dataIndex: 'wareid'
          },
          {
            title: '货品名称',
            align:"center",
            dataIndex: 'warename'
          },
          {
            title: '规格',
            align:"center",
            dataIndex: 'warespec'
          },
          {
            title: '计量单位',
            align:"center",
            dataIndex: 'wareunit'
          },
          {
            title: '生产厂家',
            align:"center",
            dataIndex: 'factoryname'
          }
        ],
        url: {
          list: "/test/ywRetail/list",
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
      getQueryParams(){
        console.log(this.queryParam.createTimeRange)
        var param = Object.assign({}, this.queryParam,this.isorter);
        param.pageNo = this.ipagination.current;
        param.pageSize = this.ipagination.pageSize;
        delete param.createTimeRange; // 时间参数不传递后台
        return filterObj(param);
      },
      onDateChange: function (value, dateString) {
        console.log(dateString[0],dateString[1]);
        this.queryParam.startdate=dateString[0];
        this.queryParam.enddate=dateString[1];
      },
      onDateOk(value) {
        console.log(value);
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