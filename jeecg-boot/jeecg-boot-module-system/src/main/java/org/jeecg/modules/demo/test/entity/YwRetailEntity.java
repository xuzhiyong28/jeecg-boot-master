package org.jeecg.modules.demo.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

/***
 * 零售
 */
@Getter
@Setter
public class YwRetailEntity {
    @Excel(name = "销售日期", width = 15)
    private String execdate;//销售日期

    @Excel(name = "客户ID", width = 15)
    private String vencusno ;// 客户ID

    @Excel(name = "客户名称", width = 15)
    private String vencusname ;// 客户名称

    @Excel(name = "配送地址", width = 15)
    private String address ;// 配送地址

    @Excel(name = "批号", width = 15)
    private String makeno ;// 批号

    @Excel(name = "数量", width = 15)
    private String wareqty ;// 数量

    @Excel(name = "有效期至", width = 15)
    private String invalidate ;// 有效期至

    @Excel(name = "单价", width = 15)
    private String whlprice ;// 单价

    @Excel(name = "货品ID", width = 15)
    private String wareid ;// 货品ID

    @Excel(name = "货品名称", width = 15)
    private String warename ;// 货品名称

    @Excel(name = "规格", width = 15)
    private String warespec ;// 规格

    @Excel(name = "计量单位", width = 15)
    private String wareunit ;// 计量单位

    @Excel(name = "生产厂家", width = 15)
    private String factoryname; //生产厂家
}
