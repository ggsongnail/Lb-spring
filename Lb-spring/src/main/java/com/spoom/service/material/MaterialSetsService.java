package com.spoom.service.material;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialSets;
import com.spoom.respository.material.MaterialSetsDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class MaterialSetsService {
	@Autowired
	private MaterialSetsDao materialSetsDao;
	
	public List<MaterialSets> findAll() {
		return materialSetsDao.findAll();
	}
	
	public MaterialSets findById(int id){
		return materialSetsDao.findOne(id);
	}
	
	public void save(MaterialSets MaterialSets){
		materialSetsDao.save(MaterialSets);
	}
	
	public void delete(int id){
		materialSetsDao.delete(id);
	}
	
	public List<MaterialSets> findByDictionaryClassify(DictionaryClassify dictionaryClassify){
		return materialSetsDao.findByDictionaryClassify(dictionaryClassify);
	}
}
