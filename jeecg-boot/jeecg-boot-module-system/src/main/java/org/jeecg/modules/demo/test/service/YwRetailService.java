package org.jeecg.modules.demo.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.test.entity.YwInventoryEntity;
import org.jeecg.modules.demo.test.entity.YwRetailEntity;

import java.util.List;

/***
 * 库存Service
 */
public interface YwRetailService extends IService<YwRetailEntity> {
    public List<YwRetailEntity> getYwRetailQuery(String batsaleno);
}
