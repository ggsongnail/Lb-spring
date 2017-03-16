package com.spoom.service.material;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.respository.material.MaterialClassifyDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class MaterialClassifyService {
	@Autowired
	private MaterialClassifyDao materialClassifyDao;
	
	public List<MaterialClassify> findAll() {
		return materialClassifyDao.findAll();
	}
	
	public MaterialClassify findById(int id){
		return materialClassifyDao.findOne(id);
	}
	
	public void save(MaterialClassify MaterialClassify){
		materialClassifyDao.save(MaterialClassify);
	}
	
	public void delete(int id){
		materialClassifyDao.delete(id);
	}
	
	public List<MaterialClassify> findByDictionaryClassifyOrderByName(DictionaryClassify dictionaryClassify){
		return materialClassifyDao.findByDictionaryClassifyOrderByName(dictionaryClassify);
	}
	
	public List<MaterialClassify> findAll(Sort sort){
		return materialClassifyDao.findAll(sort);
	}
	
	public List<MaterialClassify> findByName(String name){
		return materialClassifyDao.findByName(name);
	}
	
	public List<MaterialClassify> findByDictionaryClassifyIdIn(int[] ids){
		return materialClassifyDao.findByDictionaryClassifyIdIn(ids);
	}
	
}
