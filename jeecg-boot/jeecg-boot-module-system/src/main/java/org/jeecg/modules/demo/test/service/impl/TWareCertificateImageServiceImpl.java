package org.jeecg.modules.demo.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.test.entity.TWareCertificateImage;
import org.jeecg.modules.demo.test.mapper.TWareCertificateImageMapper;
import org.jeecg.modules.demo.test.service.TWareCertificateImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuzhiyong
 * @createDate 2020-04-18-14:54
 */
@DS("hydee")
@Service
public class TWareCertificateImageServiceImpl extends ServiceImpl<TWareCertificateImageMapper, TWareCertificateImage> implements TWareCertificateImageService {
    @Autowired
    private TWareCertificateImageMapper tWareCertificateImageMapper;
}
