package org.jeecg.modules.demo.test.entity;

import lombok.Getter;
import lombok.Setter;

/***
 * 零售
 */
@Getter
@Setter
public class YwRetailEntity {
    private String execdate;//销售日期
    private String vencusno ;// 客户ID
    private String vencusname ;// 客户名称
    private String address ;// 配送地址
    private String makeno ;// 批号
    private String wareqty ;// 数量
    private String invalidate ;// 有效期至
    private String whlprice ;// 单价
    private String wareid ;// 货品ID
    private String warename ;// 货品名称
    private String warespec ;// 规格
    private String wareunit ;// 计量单位
    private String factoryname; //生产厂家
}
