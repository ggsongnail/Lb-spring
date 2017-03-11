package com.spoom.respository.material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.order.OrderProduct;

/**
 * 
 * @author songnail
 *
 */
public interface MaterialClassifyDao extends JpaRepository<MaterialClassify, Integer>,JpaSpecificationExecutor<OrderProduct>{
	public List<MaterialClassify> findByDictionaryClassifyOrderByName(DictionaryClassify dictionaryClassify);
	public List<MaterialClassify> findByName(String name);
}
