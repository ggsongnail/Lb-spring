package com.spoom.controller.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.dictionary.Dictionary;
import com.spoom.service.dictionary.DictionaryService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "dictionary";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public Dictionary getDictionary(@PathVariable int id){
		Dictionary dictionary = dictionaryService.findById(id);
		return dictionary;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public Dictionary saveDictionary(@RequestBody Dictionary dictionary){
		dictionaryService.save(dictionary);
		return dictionary;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<Dictionary> listjson(){
		return dictionaryService.findAll();
	}
}
