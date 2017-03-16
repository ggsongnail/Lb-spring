package com.spoom.respository.artificial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.material.MaterialProduct;

/**
 * 
 * @author songnail
 *
 */
public interface ArtificialFeeDao extends JpaRepository<ArtificialFee, Integer>{
	public List<ArtificialFee> findByDictionaryClassify(DictionaryClassify dictionaryClassify);
}
