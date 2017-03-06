package com.spoom.service.material;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.material.MaterialProduct;
import com.spoom.respository.material.MaterialProductDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class MaterialProductService {
	@Autowired
	private MaterialProductDao materialProductDao;
	
	public List<MaterialProduct> findAll() {
		return materialProductDao.findAll();
	}
	
	public MaterialProduct findById(int id){
		return materialProductDao.findOne(id);
	}
	
	public void save(MaterialProduct MaterialProduct){
		materialProductDao.save(MaterialProduct);
	}
	
	public void delete(int id){
		materialProductDao.delete(id);
	}
	
	public List<MaterialProduct> findByMaterialClassify(MaterialClassify materialClassify){
		return materialProductDao.findByMaterialClassify(materialClassify);
	}
	
	public List<MaterialProduct> findByMaterialClassifyDictionaryClassify(DictionaryClassify dictionaryClassify){
		return materialProductDao.findByMaterialClassifyDictionaryClassify(dictionaryClassify);
	}
}
