package com.spoom.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.respository.dictionary.DictionaryClassifyDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class DictionaryClassifyService {
	@Autowired
	private DictionaryClassifyDao dictionaryClassifyDao;
	
	public List<DictionaryClassify> findAll() {
		return dictionaryClassifyDao.findAll();
	}
	
	public DictionaryClassify findById(int id){
		return dictionaryClassifyDao.findOne(id);
	}
	
	public void save(DictionaryClassify dictionaryClassify){
		dictionaryClassifyDao.save(dictionaryClassify);
	}
	
	public void delete(int id){
		dictionaryClassifyDao.delete(id);
	}
	
	public List<DictionaryClassify> findAllByDictionary(Dictionary dictionary){
		return dictionaryClassifyDao.findAllByDictionary(dictionary);
	}
}
