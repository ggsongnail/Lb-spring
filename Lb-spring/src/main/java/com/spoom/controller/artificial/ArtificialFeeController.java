package com.spoom.controller.artificial;

import java.util.ArrayList;
import java.util.Date;
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

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.order.OrderArtificialFee;
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
		Dictionary dictionary = dictionaryService.findById(4);
		List<DictionaryClassify> dictionaryClassifys = dictionaryClassifyService.findAllByDictionary(dictionary);
		Map<String,Object> map = new HashMap<String,Object>();
		for(DictionaryClassify dc : dictionaryClassifys){
			List<ArtificialFee> afs = artificialFeeService.findByDictionaryClassify(dc);
			List<OrderArtificialFee> oafs = new ArrayList<OrderArtificialFee>();
			for(ArtificialFee af : afs){
				OrderArtificialFee oaf = orderArtificialFeeService.findByOrderLbAndOrderArtificialFee(order, af);
				if(oaf==null){
					OrderArtificialFee ghost = new OrderArtificialFee();
					ghost.setOrderLb(order);
					ghost.setArtificialFee(af);
					ghost.setCount(0);
					ghost.setTotal(0.0);
					ghost.setTotalReal(0.0);
					
					oafs.add(ghost);
				}else{
					oafs.add(oaf);
				}
			}
			map.put(returnFlag(dc.getId()), oafs);
		}
		map.put("orderLb", order);
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
