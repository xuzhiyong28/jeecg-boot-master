package org.jeecg.modules.demo.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.entity.YwInventoryEntity;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:57
 */
public interface YwBuyInventoryMapper extends BaseMapper<YwInventoryEntity> {

    public List<YwInventoryEntity> getYwInventoryQuery(@Param("classcode") String classcode);
}
