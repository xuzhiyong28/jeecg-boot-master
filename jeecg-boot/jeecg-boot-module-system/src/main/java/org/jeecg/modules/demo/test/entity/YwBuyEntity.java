package org.jeecg.modules.demo.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

@Getter
@Setter
public class YwBuyEntity {
    @Excel(name = "采购日期", width = 15, orderNum = "0")
    private String execdate; //采购日期

    //@Excel(name = "供应商ID", width = 15)
    private String vencusno; //供应商ID

    @Excel(name = "供应商名称", width = 15,orderNum = "1")
    private String vencusname; //供应商名称

    @Excel(name = "批号", width = 15,orderNum = "8")
    private String makeno; //批号

    @Excel(name = "数量", width = 15,orderNum = "6")
    private String wareqty; //数量

    @Excel(name = "有效期至", width = 15,orderNum = "9")
    private String invalidate ; //有效期至

    @Excel(name = "单价", width = 15,orderNum = "7")
    private String purprice; //单价

    //@Excel(name = "货品", width = 15)
    private String wareid;//货品

    @Excel(name = "货品名称", width = 15,orderNum = "2")
    private String waregeneralname;//货品名称

    @Excel(name = "生产厂家", width = 15,orderNum = "3")
    private String factoryname;//生产厂家

    @Excel(name = "规格", width = 15,orderNum = "4")
    private String warespec;//规格

    @Excel(name = "计量单位", width = 15,orderNum = "5")
    private String wareunit;//计量单位

    @Excel(name = "金额", width = 15,orderNum = "5")
    private String money;

}
