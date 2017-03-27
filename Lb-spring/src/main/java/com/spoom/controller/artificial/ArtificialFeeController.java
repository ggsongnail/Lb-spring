package com.spoom.controller.artificial;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.order.OrderArtificial;
import com.spoom.entity.order.OrderLb;
import com.spoom.service.artificial.ArtificialFeeService;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;
import com.spoom.service.order.OrderArtificialFeeService;
import com.spoom.service.order.OrderLbService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("artificialfee")
public class ArtificialFeeController {
	@Autowired
	private ArtificialFeeService artificialFeeService;
	@Autowired
	private MaterialClassifyService materialClassifyService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	@Autowired
	private OrderLbService orderService;
	@Autowired
	private OrderArtificialFeeService orderArtificialFeeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "artificialDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public Map getArtificialDetail(@PathVariable int id){
		ArtificialFee artificialFee = artificialFeeService.findById(id);
		Dictionary dictionary = dictionaryService.findById(4);
		List<DictionaryClassify> dictionaryClassifys = dictionaryClassifyService.findAllByDictionary(dictionary);
		Map map = new HashMap();
		map.put("dictionaryClassifys", dictionaryClassifys);
		map.put("artificialFee", artificialFee);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public ArtificialFee saveArtificialDetail(@RequestBody ArtificialFee artificialFee){
		artificialFee.setCreateDate(new Date());
		artificialFee.setUpdateDate(new Date());
		artificialFee.setDeleteDate(new Date());
		artificialFeeService.save(artificialFee);
		return artificialFee;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<ArtificialFee> listjson(){
		return artificialFeeService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="listforbill/json/{orderId}",method = RequestMethod.GET)
	public Map listformainbill(@PathVariable int orderId){
		OrderLb order = orderService.findById(orderId);
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,List<OrderArtificial>> artiMap = new HashMap<String,List<OrderArtificial>>();
		Set<Integer> ids = new HashSet<Integer>();
		List<OrderArtificial> orderArtificials = artificialFeeService.getOrderArtificalDtos(orderId);
		
		for(OrderArtificial oa:orderArtificials){
			String key = returnFlag(oa.getDictionaryClassifyId());
			if(artiMap.containsKey(key)){
				artiMap.get(key).add(oa);
			}else{
				List<OrderArtificial> oaList = new ArrayList<OrderArtificial>();
				oaList.add(oa);
				artiMap.put(key, oaList);
			}
		}
		map.put("orderLb", order);
		map.put("artiMap", artiMap);
		return map;
	}
	
	public String returnFlag(int dictionaryClassifyId){
		String s = "new";
		if(dictionaryClassifyId==8)
			s = "latexs";
		if(dictionaryClassifyId==9)
			s = "woods";
		if(dictionaryClassifyId==10)
			s = "waters";
		if(dictionaryClassifyId==11)
			s = "basics";
		if(dictionaryClassifyId==12)
			s = "others";
		return s;
	}
}
