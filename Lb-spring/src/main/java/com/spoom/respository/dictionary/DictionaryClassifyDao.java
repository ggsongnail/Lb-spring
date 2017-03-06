package com.spoom.respository.dictionary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.dictionary.Dictionary;
import com.spoom.entity.dictionary.DictionaryClassify;

/**
 * 
 * @author songnail
 *
 */
public interface DictionaryClassifyDao extends JpaRepository<DictionaryClassify, Integer>{
	public List<DictionaryClassify> findAllByDictionary(Dictionary dictionary);
}
