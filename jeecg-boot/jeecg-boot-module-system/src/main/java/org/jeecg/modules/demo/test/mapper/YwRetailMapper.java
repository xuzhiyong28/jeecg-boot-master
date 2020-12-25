package org.jeecg.modules.demo.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.entity.YwRetailEntity;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:57
 */
public interface YwRetailMapper extends BaseMapper<YwRetailEntity> {

    public List<YwRetailEntity> getYwRetailQuery(@Param("batsaleno") String batsaleno);
}
