package com.spoom.service.order;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.order.OrderArtificial;
import com.spoom.entity.order.OrderLb;
import com.spoom.respository.order.OrderArtificialFeeDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class OrderArtificialFeeService {
	@Autowired
	private OrderArtificialFeeDao orderArtificialFeeDao;
	@Autowired
	private EntityManager em;
	
	public List<OrderArtificial> findAll() {
		return orderArtificialFeeDao.findAll();
	}
	
	public OrderArtificial findById(int id){
		return orderArtificialFeeDao.findOne(id);
	}
	
	public void save(OrderArtificial OrderArtificialFee){
		orderArtificialFeeDao.save(OrderArtificialFee);
	}
	
	public void delete(int id){
		orderArtificialFeeDao.delete(id);
	}
	
	public void batchInsert(List list) {
		for (int i = 0; i < list.size(); i++) {
			em.persist(list.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
	}

	public void batchUpdate(List list) {
		for (int i = 0; i < list.size(); i++) {
			em.merge(list.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
	}
	
}
