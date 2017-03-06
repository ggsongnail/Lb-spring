package com.spoom.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.Dictionary;
import com.spoom.respository.dictionary.DictionaryDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;
	
	public List<Dictionary> findAll() {
		return dictionaryDao.findAll();
	}
	
	public Dictionary findById(int id){
		return dictionaryDao.findOne(id);
	}
	
	public void save(Dictionary dictionary){
		dictionaryDao.save(dictionary);
	}
	
	public void delete(int id){
		dictionaryDao.delete(id);
	}
}
