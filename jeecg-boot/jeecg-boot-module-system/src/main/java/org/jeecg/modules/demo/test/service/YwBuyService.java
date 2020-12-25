package org.jeecg.modules.demo.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;

import java.util.List;

/***
 * 购进Service
 */
public interface YwBuyService extends IService<YwBuyEntity> {
    public List<YwBuyEntity> getYwOrderQuery(String batsaleno);
}
