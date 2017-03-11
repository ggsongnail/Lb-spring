package com.spoom.controller.material;

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

import com.spoom.entity.dictionary.DictionaryClassify;
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
	@RequestMapping(value="listformainbill/json/{orderNo}/{type}",method = RequestMethod.GET)
	public Map listformainbill(@PathVariable String orderNo,@PathVariable int type){//0主要材料　１辅助材料
		List<MaterialClassify> materialClassifys = new ArrayList<MaterialClassify>();
		if(type==0){
			//1在dictionary_classify表示主要材料
			DictionaryClassify dictionaryClassify = dictionaryClassifyService.findById(1);
			materialClassifys = materialClassifyService.findByDictionaryClassifyOrderByName(dictionaryClassify);
		}else{
			materialClassifys = materialClassifyService.findByName("辅助材料");
		}
		
		Map<String,List<OrderProduct>> left = new HashMap<String,List<OrderProduct>>();
		Map<String,List<OrderProduct>> right = new HashMap<String,List<OrderProduct>>();
		int l = 0;
		int r = 0;
		OrderLb order = orderService.findByOrderNo(orderNo);
		for(MaterialClassify mc:materialClassifys){
			List<MaterialProduct> mps = materialProductService.findByMaterialClassify(mc);
			//将通过order 和 product查出相关order_product与之对应上
			List<OrderProduct> ops = new ArrayList<OrderProduct>();
			for(MaterialProduct mp:mps){
				OrderProduct op = orderProductService.findByOrderLbAndMaterialProduct(order, mp);
				if(op==null){
					OrderProduct ghost = new OrderProduct();
					ghost.setOrderLb(order);
					ghost.setMaterialProduct(mp);
					ghost.setCount(0);
					ghost.setTotal(0);
					//mp.setOrderProduct(ghost);
					ops.add(ghost);
					continue;
				}
				//mp.setOrderProduct(op);
				ops.add(op);
			}
			if(mc.getPlace()!=null&&mc.getPlace()==0){//0左
				left.put(mc.getName(), ops);
				l = l+mps.size()+1;
			}else{
				right.put(mc.getName(), ops);
				r = r+mps.size()+1;
			}
		}
		//Map<String,Map<String,List<OrderProduct>>> returnMap = new HashMap<String,Map<String,List<OrderProduct>>>();
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("left", left);
		returnMap.put("right", right);
		//returnMap.put("mainPrice", order.getMainPrice());
		//returnMap.put("assistPrice",order.getAssistPrice());
		returnMap.put("orderLb",order);
		return returnMap;
	}
}
