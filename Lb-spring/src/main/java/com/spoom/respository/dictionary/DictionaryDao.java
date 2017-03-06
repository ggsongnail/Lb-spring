package com.spoom.respository.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.dictionary.Dictionary;

/**
 * 
 * @author songnail
 *
 */
public interface DictionaryDao extends JpaRepository<Dictionary, Integer>{

}
