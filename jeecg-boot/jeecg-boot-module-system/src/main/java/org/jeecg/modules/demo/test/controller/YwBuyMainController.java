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
import org.jeecg.modules.demo.test.mapper.YwBuyMapper;
import org.jeecg.modules.demo.test.service.YwBuyService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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


    public List<YwBuyEntity> getQueryList(Integer pageNo, Integer pageSize, String startdate, String enddate, String classcode) {
        if (StringUtils.isBlank(startdate)) {
            startdate = DateUtils.formatDate(new Date());
        }
        if (StringUtils.isBlank(enddate)) {
            enddate = DateUtils.formatDate(new Date());
        }
        //List<YwBuyEntity> ywBuyQueryList = ywBuyService.getYwBuyQuery(classcode, startdate, enddate);
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
        List<YwBuyEntity> ywBuyQueryList = Lists.newArrayList();
        ywBuyQueryList.add(yw);
        return ywBuyQueryList;
    }


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
            @RequestParam(name = "startdate", required = false) String startdate,
            @RequestParam(name = "enddate", required = false) String enddate,
            HttpServletRequest req) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> resultMap = Maps.newHashMap();
        List<YwBuyEntity> ywBuyEntityList = getQueryList(pageNo, pageSize, startdate, enddate, sysUser.getWorkNo());
        resultMap.put("records", ywBuyEntityList);
        resultMap.put("total", ywBuyEntityList.size());
        return Result.ok(resultMap);
    }


    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request,
                                  @RequestParam(name = "startdate", required = false) String startdate,
                                  @RequestParam(name = "enddate", required = false) String enddate) {

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<YwBuyEntity> ywBuyEntityList = getQueryList(1, 100000, startdate, enddate, sysUser.getWorkNo());
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        String title = "购进(" + startdate + " ~ " + enddate + ")";
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, YwBuyEntity.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, ywBuyEntityList);
        return mv;
    }
}
