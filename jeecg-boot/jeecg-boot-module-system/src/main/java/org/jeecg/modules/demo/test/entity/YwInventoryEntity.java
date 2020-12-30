package org.jeecg.modules.demo.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

/***
 * 库存
 */
@Getter
@Setter
public class YwInventoryEntity {
    //@Excel(name = "库存日期", width = 15)
    private String kcdate; //库存日期

    @Excel(name = "货品", width = 15)
    private String wareid; //货品

    @Excel(name = "货品名称", width = 15)
    private String warename; //货品名称

    @Excel(name = "生产厂家", width = 15)
    private String factoryname; //生产厂家

    @Excel(name = "规格", width = 15)
    private String warespec; //规格

    @Excel(name = "计量单位", width = 15)
    private String wareunit; //计量单位

    @Excel(name = "批号", width = 15)
    private String makeno;//批号

    @Excel(name = "有效日期", width = 15)
    private String invalidate; //有效日期

    @Excel(name = "数量", width = 15)
    private String wareqty;//数量

    //@Excel(name = "可销状态", width = 15)
    private String kxstate; //可销状态
}
