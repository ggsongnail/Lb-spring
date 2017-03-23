package com.spoom.respository.material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialSets;

/**
 * 
 * @author songnail
 *
 */
public interface MaterialSetsDao extends JpaRepository<MaterialSets, Integer>,JpaSpecificationExecutor<MaterialSets>{
	public List<MaterialSets> findByDictionaryClassify(DictionaryClassify dictionaryClassify);
}
