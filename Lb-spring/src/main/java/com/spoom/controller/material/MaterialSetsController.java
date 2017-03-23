package com.spoom.controller.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialSets;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderSets;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialSetsService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderSetsService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("materialsets")
public class MaterialSetsController {
	@Autowired
	private MaterialSetsService materialSetsService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	@Autowired
	private OrderLbService orderLbService;
	@Autowired
	private OrderSetsService orderSetsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "MaterialSets";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public Map getMaterialSets(@PathVariable int id){
		MaterialSets materialSets = materialSetsService.findById(id);
		Dictionary dictionary = dictionaryService.findById(5);
		List<DictionaryClassify> dictionaryClassifys = dictionaryClassifyService.findAllByDictionary(dictionary);
		Map map = new HashMap();
		map.put("dictionaryClassifys", dictionaryClassifys);
		map.put("materialSets", materialSets);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public MaterialSets saveMaterialSets(@RequestBody MaterialSets MaterialSets){
		materialSetsService.save(MaterialSets);
		return MaterialSets;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<MaterialSets> listjson(){
		return materialSetsService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="setsbill/json/{orderId}/{type}",method = RequestMethod.GET)
	public Map<String,Object> kidsetbill(@PathVariable int orderId,@PathVariable int type){
		DictionaryClassify dc = dictionaryClassifyService.findById(type);
		List<MaterialSets> materialSetss = materialSetsService.findByDictionaryClassify(dc);
		OrderLb orderLb = orderLbService.findById(orderId);
		List<OrderSets> orderSetss = new ArrayList<OrderSets>();
		for(MaterialSets ms:materialSetss){
			OrderSets orderSets = orderSetsService.findByOrderLbAndMaterailSets(orderLb, ms);
			if(orderSets==null){
				OrderSets ghost = new OrderSets();
				ghost.setCount(0);
				ghost.setTotal(0.0);
				ghost.setMaterialSets(ms);
				orderSetss.add(ghost);
			}else{
				orderSetss.add(orderSets);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderSetss", orderSetss);
		map.put("orderLb", orderLb);
		return map;
	}
}
