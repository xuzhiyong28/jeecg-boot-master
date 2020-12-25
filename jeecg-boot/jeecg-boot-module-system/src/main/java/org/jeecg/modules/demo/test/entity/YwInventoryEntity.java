package org.jeecg.modules.demo.test.entity;

import lombok.Getter;
import lombok.Setter;

/***
 * 库存
 */
@Getter
@Setter
public class YwInventoryEntity {
    private String sysdate; //库存日期
    private String wareid; //货品
    private String warename; //货品名称
    private String factoryname; //生产厂家
    private String warespec; //规格
    private String wareunit; //计量单位
    private String makeno;//批号
    private String invalidate; //有效日期
    private String wareqty; //日期
    private String kxstate; //可销状态
}
