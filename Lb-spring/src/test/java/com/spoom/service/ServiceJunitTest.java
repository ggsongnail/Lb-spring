package com.spoom.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialProduct;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;
import com.spoom.service.material.MaterialProductService;

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
	
	
	public void testInsert(){
		MaterialProduct mp = new MaterialProduct();
		mp.setName("儿童漆");
		mp.setStandard("5L");
		mp.setPrice(858);
		mp.setCreateDate(new Date());
		mp.setUpdateDate(new Date());
		mp.setDeleteDate(new Date());
		
		materialProductService.save(mp);
	}
	
	public void testUpdate(){
		Dictionary dictionary = dictionaryService.findById(4);
		dictionary.setName("施工类型");
		dictionaryService.save(dictionary);
	}
	
	@Test
	public void testFindJoin(){
		DictionaryClassify dictionaryClassify = dictionaryClassifyService.findById(1);
		List<MaterialProduct> mps = materialProductService.findByMaterialClassifyDictionaryClassify(dictionaryClassify);
		System.out.println(mps.size()/7);
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
