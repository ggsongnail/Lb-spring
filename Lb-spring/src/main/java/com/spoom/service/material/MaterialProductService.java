package com.spoom.service.material;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.dictionary.DictionaryClassify;
import com.spoom.entity.material.MaterialClassify;
import com.spoom.entity.material.MaterialProduct;
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
		String sql = "select mp.id,mp.name,mp.standard,mp.price,op.count,op.total,op.dif_count,op.dif_total from material_product mp left join order_product op on mp.id = op.product_id where op.order_id = ?1";
		return (List<OrderProductFinal>) em.createNativeQuery(sql).setParameter(1, orderId).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();  
	}
	
	public List<OrderProductFinal> getOrderProductFinalsHis(int orderId){
		String sql = "select mp.id,mp.name,mp.standard,mp.price,op.count,op.total,op.dif_count,op.dif_total from material_product mp left join order_product op on mp.id = op.product_id where op.order_id = ?1 and op.dif_count is not null";
		return (List<OrderProductFinal>) em.createNativeQuery(sql).setParameter(1, orderId).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();  
	}
}
