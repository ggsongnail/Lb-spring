package com.spoom.controller.material;

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
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;
import com.spoom.service.material.MaterialProductService;
import com.spoom.util.ArrayCutting;

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
	@RequestMapping(value="listformainbill/json",method = RequestMethod.GET)
	public Map listformainbill(){
		//1在dictionary_classify表示主要材料
		DictionaryClassify dictionaryClassify = dictionaryClassifyService.findById(1);
		List<MaterialClassify> materialClassifys = materialClassifyService.findByDictionaryClassify(dictionaryClassify);
		//List<MaterialProduct> mps = materialProductService.findByMaterialClassifyDictionaryClassify(dictionaryClassify);
		Map<MaterialClassify,List<MaterialProduct>> both = new HashMap<MaterialClassify,List<MaterialProduct>>();
		Map left = new HashMap();
		Map right = new HashMap();
		int i = 0;
		int[] arrays = new int[materialClassifys.size()];
		for(MaterialClassify mc:materialClassifys){
			List<MaterialProduct> mps = materialProductService.findByMaterialClassify(mc);
			both.put(mc, mps);
			arrays[i] = mps.size();
			i++;
		}
		Map<Integer,Integer> balance = ArrayCutting.returnMap(arrays);
		
		for (Map.Entry<MaterialClassify, List<MaterialProduct>> entry : both.entrySet()) {  
			int a = entry.getValue().size();
			if(balance.get(a)>0&&balance.containsKey(a)){
				left.put(entry.getKey().getName(), entry.getValue());
				balance.
			}
		}
		
		return null;
	}
}
