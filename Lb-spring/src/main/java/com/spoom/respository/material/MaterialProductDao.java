package com.spoom.respository.material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.material.MaterialProduct;

/**
 * 
 * @author songnail
 *
 */
public interface MaterialProductDao extends JpaRepository<MaterialProduct, Integer>{

	public List<MaterialProduct> findByMaterialClassify(MaterialClassify materialClassify);
	
	public List<MaterialProduct> findByMaterialClassifyDictionaryClassify(DictionaryClassify dictionaryClassify);
	
}
