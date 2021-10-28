package org.jeecg.modules.demo.test.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.demo.test.entity.TWareCertificateChk;
import org.jeecg.modules.demo.test.entity.TWareCertificateImage;
import org.jeecg.modules.demo.test.entity.YwQueryEntity;
import org.jeecg.modules.demo.test.mapper.TWareCertificateChkMapper;
import org.jeecg.modules.demo.test.service.ITWareCertificateChkService;
import org.jeecg.modules.demo.test.service.TWareCertificateImageService;
import org.jeecg.modules.demo.test.util.ImageSyUtils;
import org.jeecg.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2020-04-11-20:33
 */
@RestController
@RequestMapping("/test/ywOrderQueryMain")
@Slf4j
public class YwOrderQueryMainController extends JeecgController<TWareCertificateChk, ITWareCertificateChkService> {

    @Autowired
    private ITWareCertificateChkService itWareCertificateChkService;

    @Autowired
    private TWareCertificateImageService tWareCertificateImageService;

    @Resource
    private TWareCertificateChkMapper tWareCertificateChkMapper;


    @Autowired
    private ISysDictService sysDictService;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    public Result<?> queryPageList(YwQueryEntity ywQueryEntity, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        if (ywQueryEntity == null || StringUtils.isBlank(ywQueryEntity.getBatsaleno())) {
            Map<String, Object> resultMap = Maps.newHashMap();
            resultMap.put("records", Lists.newArrayList());
            resultMap.put("total", "0");
            return Result.ok(resultMap);
        }
        List<YwQueryEntity> ywOrderQuery = itWareCertificateChkService.getYwOrderQuery(ywQueryEntity.getBatsaleno());
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("records", ywOrderQuery);
        resultMap.put("total", ywOrderQuery.size());
        return Result.ok(resultMap);
    }


    @GetMapping(value = "/imgList")
    public Result<?> getIWareModel(@RequestParam(name = "wareid") String wareid , @RequestParam(name = "makeno") String makeno) {
        QueryWrapper<TWareCertificateChk> tWareCertificateChkQueryWrapper = new QueryWrapper();
        //tWareCertificateChkQueryWrapper.eq("wareid", wareid);
        //List<TWareCertificateChk> tWareCertificateChks = itWareCertificateChkService.list(tWareCertificateChkQueryWrapper);

        QueryWrapper<TWareCertificateImage> tWareCertificateImageQueryWrapper = new QueryWrapper<>();
        tWareCertificateImageQueryWrapper.eq("WAREID",wareid);
        if(StringUtils.isNotBlank(makeno)){
            tWareCertificateImageQueryWrapper.eq("MAKENO" , makeno);
        }
        List<TWareCertificateImage> tWareCertificateChks = tWareCertificateImageService.list(tWareCertificateImageQueryWrapper);

        if (CollectionUtils.isNotEmpty(tWareCertificateChks)) {
            tWareCertificateChks.forEach(ware -> {
                if(ware.getIMG() != null && isSyByCode()){
                    ware.setIMG(ImageSyUtils.imgAddSy(ware.getIMG() , ware.getCOMPID()));
                }
            });
            return Result.ok(tWareCertificateChks);
        }
        return Result.error("找不到对象");
    }


    /***
     * 根据字典判断是否需要加水印
     * @return
     */
    public boolean isSyByCode(){
        List<DictModel> imgsy = sysDictService.queryDictItemsByCode("imgsy");
        if(CollectionUtils.isEmpty(imgsy)){
            return true;
        }
        DictModel dictModel = imgsy.get(0);
        if(StringUtils.equals(dictModel.getValue(),"1")) {
            return true;
        }else {
            return false;
        }
    }


}
