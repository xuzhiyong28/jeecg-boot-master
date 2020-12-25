package org.jeecg.modules.demo.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.entity.YwInventoryEntity;

import java.util.List;

/***
 * 库存Service
 */
public interface YwInventoryService extends IService<YwInventoryEntity> {
    public List<YwInventoryEntity> getYwInventoryQuery(String batsaleno);
}
