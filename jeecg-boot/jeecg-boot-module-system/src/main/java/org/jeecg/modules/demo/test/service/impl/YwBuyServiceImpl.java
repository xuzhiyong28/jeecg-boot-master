package org.jeecg.modules.demo.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.mapper.YwBuyMapper;
import org.jeecg.modules.demo.test.service.YwBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:58
 */
@DS("hydee")
@Service
public class YwBuyServiceImpl extends ServiceImpl<YwBuyMapper, YwBuyEntity> implements YwBuyService {
    @Autowired
    private YwBuyMapper ywBuyMapper;

    @Override
    public List<YwBuyEntity> getYwOrderQuery(String batsaleno) {
        return ywBuyMapper.getYwOrderQuery(batsaleno);
    }
}
