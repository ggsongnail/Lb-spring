package com.spoom.controller.material;

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
import com.spoom.entity.material.MaterialClassify;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("materialclassify")
public class MaterialClassifyController {
	@Autowired
	private MaterialClassifyService materialClassifyService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "MaterialClassify";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public Map getMaterialClassify(@PathVariable int id){
		MaterialClassify materialClassify = materialClassifyService.findById(id);
		Dictionary dictionary = dictionaryService.findById(1);
		List<DictionaryClassify> dictionaryClassifys = dictionaryClassifyService.findAllByDictionary(dictionary);
		Map map = new HashMap();
		map.put("dictionaryClassifys", dictionaryClassifys);
		map.put("materialClassify", materialClassify);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public MaterialClassify saveMaterialClassify(@RequestBody MaterialClassify MaterialClassify){
		materialClassifyService.save(MaterialClassify);
		return MaterialClassify;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<MaterialClassify> listjson(){
		return materialClassifyService.findAll();
	}
}
