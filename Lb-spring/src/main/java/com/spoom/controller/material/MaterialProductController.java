package com.spoom.controller.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.material.MaterialProduct;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderProduct;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;
import com.spoom.service.material.MaterialProductService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderProductService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("materialproduct")
public class MaterialProductController {
	@Autowired
	private MaterialProductService materialProductService;
	@Autowired
	private MaterialClassifyService materialClassifyService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	@Autowired
	private OrderLbService orderService;
	@Autowired
	private OrderProductService orderProductService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "MaterialProduct";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public Map getMaterialProduct(@PathVariable int id){
		MaterialProduct materialProduct = materialProductService.findById(id);
		List<MaterialClassify> materialClassifys = materialClassifyService.findAll();
		Map map = new HashMap();
		map.put("materialClassifys", materialClassifys);
		map.put("materialProduct", materialProduct);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public MaterialProduct saveMaterialProduct(@RequestBody MaterialProduct materialProduct){
		materialProduct.setCreateDate(new Date());
		materialProduct.setUpdateDate(new Date());
		materialProduct.setDeleteDate(new Date());
		materialProductService.save(materialProduct);
		return materialProduct;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<MaterialProduct> listjson(){
		return materialProductService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="listformainbill/json/{orderId}/{type}",method = RequestMethod.GET)
	public Map listformainbill(@PathVariable int orderId,@PathVariable int type){//0:main 1:assist
		OrderLb orderLb = orderService.findById(orderId);
		if(type==0){
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(1);set.add(5);set.add(6);
			List<OrderProduct> orderProductDtos = materialProductService.getOrderProductDtos(orderId,set);
			Map<String,List<OrderProduct>> leftmap = new HashMap<String,List<OrderProduct>>();
			Map<String,List<OrderProduct>> rightmap = new HashMap<String,List<OrderProduct>>();
			Map<String,Object> mainMap = new HashMap<String,Object>();
			for(OrderProduct dto:orderProductDtos){
				String key = dto.getMaterialClassifyName();
				if(dto.getPlace()==0){
					if(leftmap.containsKey(key)){
						leftmap.get(key).add(dto);
					}else{
						List<OrderProduct> list = new ArrayList<OrderProduct>();
						list.add(dto);
						leftmap.put(key, list);
					}
				}
				if(dto.getPlace()==1){
					if(rightmap.containsKey(key)){
						rightmap.get(key).add(dto);
					}else{
						List<OrderProduct> list = new ArrayList<OrderProduct>();
						list.add(dto);
						rightmap.put(key, list);
					}
				}
			}
			mainMap.put("left", leftmap);
			mainMap.put("right", rightmap);
			mainMap.put("orderLb", orderLb);
			return mainMap;
		}else if(type==1){
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(7);
			Map<String,Object> assistMap = new HashMap<String,Object>();
			List<OrderProduct> materialProductDtos = materialProductService.getOrderProductDtos(orderId,set);
			List<OrderProduct> leftList = new ArrayList<OrderProduct>();
			List<OrderProduct> rightList = new ArrayList<OrderProduct>();
			int beak = materialProductDtos.size()/2;
			int i = 0;
			for(OrderProduct dto : materialProductDtos){
				if(i<beak){
					leftList.add(dto);
				}else{
					rightList.add(dto);
				}
				i++;
			}
			assistMap.put("left", leftList);
			assistMap.put("right", rightList);
			assistMap.put("orderLb", orderLb);
			return assistMap;
		}else{
			return null;
		}
	}
}
