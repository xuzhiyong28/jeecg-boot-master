package org.jeecg.modules.demo.test.controller;

import com.google.api.client.util.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.mapper.YwBuyMapper;
import org.jeecg.modules.demo.test.service.YwBuyService;
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
@RequestMapping("/test/ywBuy")
@Slf4j
public class YwBuyMainController extends JeecgController<YwBuyEntity, YwBuyService> {
    @Autowired
    private YwBuyService ywBuyService;

    @Resource
    private YwBuyMapper ywBuyMapper;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    public Result<?> queryPageList(
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
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
        YwBuyEntity yw = new YwBuyEntity();
        yw.setExecdate("2020-10-10");
        yw.setFactoryname("哈哈");
        yw.setInvalidate("2020-10-10");
        yw.setVencusno("1");
        yw.setVencusname("哈哈");
        yw.setMakeno("1");
        yw.setPurprice("2020-10-10");
        yw.setWaregeneralname("嫩额");
        yw.setWareqty("ccc");
        List<YwBuyEntity> ywBuyEntityList = Lists.newArrayList();
        ywBuyEntityList.add(yw);
        resultMap.put("records", ywBuyEntityList);
        resultMap.put("total", ywBuyEntityList.size());
        return Result.ok(resultMap);
    }
}
