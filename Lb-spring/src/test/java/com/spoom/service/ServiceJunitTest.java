package com.spoom.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.service.artificial.ArtificialFeeService;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;
import com.spoom.service.material.MaterialProductService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-jpa.xml")
public class ServiceJunitTest {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	@Autowired
	private MaterialClassifyService materialClassifyService;
	@Autowired
	private MaterialProductService materialProductService;
	@Autowired
	private OrderLbService orderService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ArtificialFeeService artificialDetailService;
	
	
	@Test
	public void testInsert(){
		ArtificialFee af = new ArtificialFee();
		DictionaryClassify dc = dictionaryClassifyService.findById(8);
		af.setName("内墙");
		af.setDictionaryClassify(dc);
		af.setStyle("基础型");
		af.setStandard("平方米");
		af.setPrice(18.0);
		af.setLowFee(1000.0);
		af.setRemark("适用于墙体基层牢固,局部磕碰部位用腻子修补,干燥打磨后涂刷2遍乳胶漆");
		af.setCreateDate(new Date());
		af.setUpdateDate(new Date());
		artificialDetailService.save(af);
	}
	
	public void testUpdate(){
		Dictionary dictionary = dictionaryService.findById(4);
		dictionary.setName("施工类型");
		dictionaryService.save(dictionary);
	}
	
	
	@Transactional
	public void testFindJoin(){
		Dictionary dc = dictionaryService.findById(1);
		for(DictionaryClassify dcc:dc.getDictionaryClassifys()){
			System.out.println(dcc.getName());
		}
	}
	public void testFindAll(){
		List<Dictionary> ds = dictionaryService.findAll();
		List<DictionaryClassify> dss = dictionaryClassifyService.findAll();
		for(DictionaryClassify dd:dss){
			System.out.println(dd.getDictionary().getName());
		}
	}
	/*@Autowired
	private UserService userService;
	
	@Autowired
	private KilledService killedService;
	
	
	
	
	public void testDelete(){
		//Killed killed = killedService.findById(18);
		killedService.delete(18);
	}*/
	
}
