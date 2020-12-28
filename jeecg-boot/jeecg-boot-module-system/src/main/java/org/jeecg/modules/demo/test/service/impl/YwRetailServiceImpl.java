package org.jeecg.modules.demo.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.test.entity.YwInventoryEntity;
import org.jeecg.modules.demo.test.entity.YwRetailEntity;
import org.jeecg.modules.demo.test.mapper.YwBuyInventoryMapper;
import org.jeecg.modules.demo.test.mapper.YwRetailMapper;
import org.jeecg.modules.demo.test.service.YwInventoryService;
import org.jeecg.modules.demo.test.service.YwRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:58
 */
@DS("hydee")
@Service
public class YwRetailServiceImpl extends ServiceImpl<YwRetailMapper, YwRetailEntity> implements YwRetailService {
    @Autowired
    private YwRetailMapper ywRetailMapper;

    @Override
    public List<YwRetailEntity> getYwRetailQuery(String classcode, String startdate, String enddate) {
        return ywRetailMapper.getYwRetailQuery(classcode,startdate,enddate);
    }
}
