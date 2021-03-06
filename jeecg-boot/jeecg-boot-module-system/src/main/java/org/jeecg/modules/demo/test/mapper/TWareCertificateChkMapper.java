package org.jeecg.modules.demo.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.test.entity.TWareCertificateChk;
import org.jeecg.modules.demo.test.entity.YwQueryEntity;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:57
 */
public interface TWareCertificateChkMapper extends BaseMapper<TWareCertificateChk> {

    public List<YwQueryEntity> getYwOrderQuery(@Param("batsaleno") String batsaleno);
}
