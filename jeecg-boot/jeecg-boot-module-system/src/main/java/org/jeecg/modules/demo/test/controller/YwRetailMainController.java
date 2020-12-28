package org.jeecg.modules.demo.test.controller;

import com.google.api.client.util.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.entity.YwRetailEntity;
import org.jeecg.modules.demo.test.mapper.YwBuyMapper;
import org.jeecg.modules.demo.test.mapper.YwRetailMapper;
import org.jeecg.modules.demo.test.service.YwBuyService;
import org.jeecg.modules.demo.test.service.YwRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test/ywRetail")
@Slf4j
public class YwRetailMainController extends JeecgController<YwRetailEntity, YwRetailService> {
    @Autowired
    private YwRetailService ywRetailService;
    @Resource
    private YwRetailMapper ywRetailMapper;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    public Result<?> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "startdate",required = false) String startdate,
                                   @RequestParam(name = "enddate",required = false) String enddate,
                                   HttpServletRequest req) {
        if(StringUtils.isBlank(startdate)){
            startdate = DateUtils.formatDate(new Date());
        }
        if(StringUtils.isBlank(enddate)){
            enddate = DateUtils.formatDate(new Date());
        }
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> resultMap = Maps.newHashMap();
        YwRetailEntity yw = new YwRetailEntity();
        yw.setAddress("福建省福州市");
        yw.setExecdate("2020-10-10");
        yw.setFactoryname("工厂");
        yw.setMakeno("1");
        List<YwRetailEntity> ywRetailEntityList = Lists.newArrayList();
        ywRetailEntityList.add(yw);
        resultMap.put("records", ywRetailEntityList);
        resultMap.put("total", ywRetailEntityList.size());
        return Result.ok(resultMap);
    }
}
