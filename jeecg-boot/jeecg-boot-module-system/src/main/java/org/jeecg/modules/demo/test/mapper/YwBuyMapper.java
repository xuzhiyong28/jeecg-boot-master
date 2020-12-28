package org.jeecg.modules.demo.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:57
 */
public interface YwBuyMapper extends BaseMapper<YwBuyEntity> {

    public List<YwBuyEntity> getYwBuyQuery(@Param("classcode") String classcode,@Param("startdate") String startdate, @Param("enddate") String enddate);
}
