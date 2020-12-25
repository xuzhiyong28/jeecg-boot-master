package org.jeecg.modules.demo.test.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YwBuyEntity {
    private String execdate; //采购日期
    private String vencusno; //供应商ID
    private String vencusname; //供应商名称
    private String makeno; //批号
    private String wareqty; //数量
    private String invalidate ; //有效期至
    private String purprice; //单价
    private String wareid;//货品
    private String waregeneralname;//货品名称
    private String factoryname;//生产厂家
    private String warespec;//规格
    private String wareunit;//计量单位
}
