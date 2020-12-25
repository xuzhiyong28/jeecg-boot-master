package org.jeecg.modules.demo.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.entity.YwInventoryEntity;
import org.jeecg.modules.demo.test.mapper.YwBuyInventoryMapper;
import org.jeecg.modules.demo.test.mapper.YwBuyMapper;
import org.jeecg.modules.demo.test.service.YwBuyService;
import org.jeecg.modules.demo.test.service.YwInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:58
 */
@DS("hydee")
@Service
public class YwInventoryServiceImpl extends ServiceImpl<YwBuyInventoryMapper, YwInventoryEntity> implements YwInventoryService {
    @Autowired
    private YwBuyInventoryMapper ywBuyInventoryMapper;

    @Override
    public List<YwInventoryEntity> getYwInventoryQuery(String batsaleno) {
        return ywBuyInventoryMapper.getYwOrderQuery(batsaleno);
    }
}
