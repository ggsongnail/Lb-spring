package com.spoom.service.artificial;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.order.OrderArtificialFeeFinal;
import com.spoom.entity.order.OrderProductFinal;
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
	
	@Autowired
	private EntityManager em ;
	
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
	
	//多表关联的查询一步到位只能用原生的速度快些
	public List<OrderArtificialFeeFinal> getOrderArtificialFeeFinals(int orderId){
		String sql = "select mp.id mId,op.id oId,op.order_id orderId,mp.name,mp.style,mp.standard,mp.price,op.count,op.total,op.total_real totalReal,op.dif_count difCount,op.dif_total difTotal from artificial_fee mp left join order_artificial op on mp.id = op.artificial_id where op.order_id = ?1";
		return (List<OrderArtificialFeeFinal>) em.createNativeQuery(sql).setParameter(1, orderId).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();  
	}
	//多表关联的查询一步到位只能用原生的速度快些
	public List<OrderArtificialFeeFinal> getOrderArtificialFeeFinalsHis(int orderId){
		String sql = "select mp.id mId,op.id oId,op.order_id orderId,mp.name,mp.style,mp.standard,mp.price,op.count,op.total,op.total_real totalReal,op.dif_count difCount,op.dif_total difTotal from artificial_fee mp left join order_artificial op on mp.id = op.artificial_id where op.order_id = ?1 and op.dif_count <> 0";
		return (List<OrderArtificialFeeFinal>) em.createNativeQuery(sql).setParameter(1, orderId).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();  
	}
}
