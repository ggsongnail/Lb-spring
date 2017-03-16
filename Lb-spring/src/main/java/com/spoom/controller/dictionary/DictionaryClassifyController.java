package com.spoom.controller.dictionary;

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
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("dictionaryclassify")
public class DictionaryClassifyController {
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "dictionaryclassify";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public Map getDictionaryClassify(@PathVariable int id){
		List<Dictionary> dictionarys = dictionaryService.findAll();
		DictionaryClassify dictionaryClassify = dictionaryClassifyService.findById(id);
		Map map = new HashMap();
		map.put("dictionarys", dictionarys);
		map.put("dictionaryClassify", dictionaryClassify);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public DictionaryClassify saveDictionaryClassify(@RequestBody DictionaryClassify dictionaryClassify){
		dictionaryClassifyService.save(dictionaryClassify);
		return dictionaryClassify;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<DictionaryClassify> listjson(){
		return dictionaryClassifyService.findAll();
	}
	
	//材料分类字典类型
	@ResponseBody
	@RequestMapping(value="list/json/{dictionaryId}",method = RequestMethod.GET)
	public List<DictionaryClassify> listmaterialjson(@PathVariable int dictionaryId){
		Dictionary dictionary = dictionaryService.findById(dictionaryId);
		List<DictionaryClassify> dcs = dictionaryClassifyService.findAllByDictionary(dictionary);
		return dcs;
	}
	
	
}
