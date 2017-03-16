package com.spoom.service.artificial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.respository.artificial.ArtificialFeeDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class ArtificialFeeService {
	@Autowired
	private ArtificialFeeDao artificialFeeDao;
	
	public List<ArtificialFee> findAll() {
		return artificialFeeDao.findAll();
	}
	
	public ArtificialFee findById(int id){
		return artificialFeeDao.findOne(id);
	}
	
	public void save(ArtificialFee ArtificialDetail){
		artificialFeeDao.save(ArtificialDetail);
	}
	
	public void delete(int id){
		artificialFeeDao.delete(id);
	}
	
	public List<ArtificialFee> findByDictionaryClassify(DictionaryClassify dictionaryClassify){
		return artificialFeeDao.findByDictionaryClassify(dictionaryClassify);
	}
	
}
