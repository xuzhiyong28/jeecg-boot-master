package org.jeecg.modules.demo.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.test.entity.TWareCertificateChk;
import org.jeecg.modules.demo.test.entity.YwQueryEntity;

import java.util.List;

public interface ITWareCertificateChkService extends IService<TWareCertificateChk> {
    public List<YwQueryEntity> getYwOrderQuery(String batsaleno);
}
