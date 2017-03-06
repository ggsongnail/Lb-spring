package com.spoom.respository.material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;

/**
 * 
 * @author songnail
 *
 */
public interface MaterialClassifyDao extends JpaRepository<MaterialClassify, Integer>{
	public List<MaterialClassify> findByDictionaryClassify(DictionaryClassify dictionaryClassify);
}
