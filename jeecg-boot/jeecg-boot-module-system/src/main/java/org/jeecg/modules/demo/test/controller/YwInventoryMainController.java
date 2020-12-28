package org.jeecg.modules.demo.test.controller;

import com.google.api.client.util.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo.test.entity.YwBuyEntity;
import org.jeecg.modules.demo.test.entity.YwInventoryEntity;
import org.jeecg.modules.demo.test.mapper.YwBuyInventoryMapper;
import org.jeecg.modules.demo.test.service.YwInventoryService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test/ywInventory")
@Slf4j
public class YwInventoryMainController extends JeecgController<YwInventoryEntity, YwInventoryService> {
    @Autowired
    private YwInventoryService ywInventoryService;

    @Resource
    private YwBuyInventoryMapper ywBuyInventoryMapper;

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
                                   HttpServletRequest req) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> resultMap = Maps.newHashMap();
        YwInventoryEntity yw = new YwInventoryEntity();
        yw.setFactoryname("哈哈");
        yw.setInvalidate("2020-10-10");
        yw.setKxstate("2020-10-10");
        yw.setWarename("哈哈");
        List<YwInventoryEntity> ywInventoryEntityArrayList = Lists.newArrayList();
        ywInventoryEntityArrayList.add(yw);
        resultMap.put("records", ywInventoryEntityArrayList);
        resultMap.put("total", ywInventoryEntityArrayList.size());
        return Result.ok(resultMap);
    }


    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        YwInventoryEntity yw = new YwInventoryEntity();
        yw.setFactoryname("哈哈");
        yw.setInvalidate("2020-10-10");
        yw.setKxstate("2020-10-10");
        yw.setWarename("哈哈");
        List<YwInventoryEntity> ywInventoryEntityArrayList = Lists.newArrayList();
        ywInventoryEntityArrayList.add(yw);
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        String title = "库存";
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, YwInventoryEntity.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, ywInventoryEntityArrayList);
        return mv;
    }

}
