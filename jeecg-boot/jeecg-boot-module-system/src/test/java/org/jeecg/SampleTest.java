package org.jeecg;

import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.demo.mock.MockController;
import org.jeecg.modules.demo.test.entity.*;
import org.jeecg.modules.demo.test.mapper.JeecgDemoMapper;
import org.jeecg.modules.demo.test.service.*;
import org.jeecg.modules.system.service.ISysDataLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleTest {

	@Resource
	private JeecgDemoMapper jeecgDemoMapper;
	@Resource
	private IJeecgDemoService jeecgDemoService;
	@Resource
	private ISysDataLogService sysDataLogService;
	@Resource
	private MockController mock;

	@Resource
	private ITWareCertificateChkService itWareCertificateChkService;


	@Resource
	private TWareCertificateImageService tWareCertificateImageService;


	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<JeecgDemo> userList = jeecgDemoMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void testXmlSql() {
		System.out.println(("----- selectAll method test ------"));
		List<JeecgDemo> userList = jeecgDemoMapper.getDemoByName("Sandy12");
		userList.forEach(System.out::println);
	}

	/**
	 * 测试事务
	 */
	@Test
	public void testTran() {
		jeecgDemoService.testTran();
	}
	
	//author:lvdandan-----date：20190315---for:添加数据日志测试----
	/**
	 * 测试数据日志添加
	 */
	@Test
	public void testDataLogSave() {
		System.out.println(("----- datalog test ------"));
		String tableName = "jeecg_demo";
		String dataId = "4028ef81550c1a7901550c1cd6e70001";
		String dataContent = mock.sysDataLogJson();
		sysDataLogService.addDataLog(tableName, dataId, dataContent);
	}


	@Test
	public void testDb(){
		DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
		System.out.println(dataSource);
	}

	@Test
	public void testItWare(){
		System.out.println(itWareCertificateChkService);
		List<YwQueryEntity> ywOrderQuery = itWareCertificateChkService.getYwOrderQuery("2004050003");
		System.out.println(ywOrderQuery);
	}

	@Test
	public void img(){
		List<TWareCertificateImage> list = tWareCertificateImageService.list();
		System.out.println(list);
	}

	@Test
	public void newXq(){
		YwBuyService ywBuyService = SpringContextUtils.getBean(YwBuyService.class);
		List<YwBuyEntity> ywBuyQuery = ywBuyService.getYwBuyQuery("5401", "2020-10-01", "2020-12-25");
		System.out.println(ywBuyQuery);
	}


	@Test
	public void newXq2(){
		YwInventoryService ywInventoryService = SpringContextUtils.getBean(YwInventoryService.class);
		List<YwInventoryEntity> ywInventoryQuery = ywInventoryService.getYwInventoryQuery("5401");
		System.out.println(ywInventoryQuery);
	}

	@Test
	public void newXq3(){
		YwRetailService ywRetailService = SpringContextUtils.getBean(YwRetailService.class);
		List<YwRetailEntity> ywRetailQuery = ywRetailService.getYwRetailQuery("5401", "2020-10-01", "2020-12-25");
		System.out.println(ywRetailQuery);

	}


}
