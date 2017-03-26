package com.spoom.service.material;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.material.MaterialProduct;
import com.spoom.entity.order.OrderProduct;
import com.spoom.entity.order.OrderProductFinal;
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
	@Autowired
	private EntityManager em ;
	
	/*@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }*/
	
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
	
	//多表关联的查询一步到位只能用原生的速度快些
	public List<OrderProductFinal> getOrderProductFinals(int orderId){
		String sql = "select mp.id mId,op.id oId,op.order_id orderId,mp.name,mp.standard,mp.price,op.count,op.total,op.dif_count difCount,op.dif_total difTotal from material_product mp left join order_product op on mp.id = op.product_id where op.order_id = ?1";
		return (List<OrderProductFinal>) em.createNativeQuery(sql).setParameter(1, orderId).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();  
	}
	
	public List<OrderProductFinal> getOrderProductFinalsHis(int orderId){
		String sql = "select mp.id mId,op.id oId,op.order_id orderId,mp.name,mp.standard,mp.price,op.count,op.total,op.dif_count difCount,op.dif_total difTotal from material_product mp left join order_product op on mp.id = op.product_id where op.order_id = ?1 and op.dif_count <> 0";
		return (List<OrderProductFinal>) em.createNativeQuery(sql).setParameter(1, orderId).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();  
	}
	
	public List<OrderProduct> getOrderProductDtos(int orderId,Set ids){
		
		String sql = "select mp.id productId, "+
			       "mc.name materialClassifyName, "+
			       "mp.name productName, "+
			       "mp.standard, "+
			       "mp.price, "+
			       "opp.order_id orderId, "+
			       "opp.count, "+
			       "mc.place, dc.id dictionaryClassifyId from material_product mp left join "+ 
			"(SELECT op.order_id,op.product_id pid,op.count,op.total FROM order_product op WHERE order_id = ?1) opp on mp.id = opp.pid "+
			"LEFT JOIN material_classify mc on mc.id = mp.material_classify_id "+
			"LEFT JOIN dictionary_classify dc on mc.dictionary_classify_id = dc.id "+
			"where mc.dictionary_classify_id in (?2) "+
			"order by mp.id";  
		return (List<OrderProduct>) em.createNativeQuery(sql).setParameter(1, orderId).setParameter(2, ids).unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(OrderProduct.class)).list();  
		
	}
}
