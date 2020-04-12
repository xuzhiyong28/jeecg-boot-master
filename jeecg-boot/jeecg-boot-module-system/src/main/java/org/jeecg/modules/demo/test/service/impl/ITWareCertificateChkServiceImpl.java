package org.jeecg.modules.demo.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.test.entity.TWareCertificateChk;
import org.jeecg.modules.demo.test.entity.YwQueryEntity;
import org.jeecg.modules.demo.test.mapper.TWareCertificateChkMapper;
import org.jeecg.modules.demo.test.service.ITWareCertificateChkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-11:58
 */
@DS("hydee")
@Service
public class ITWareCertificateChkServiceImpl extends ServiceImpl<TWareCertificateChkMapper, TWareCertificateChk> implements ITWareCertificateChkService {
    @Autowired
    private TWareCertificateChkMapper tWareCertificateChkMapper;

    @Override
    public List<YwQueryEntity> getYwOrderQuery(String batsaleno) {
        return tWareCertificateChkMapper.getYwOrderQuery(batsaleno);
    }
}
