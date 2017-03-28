package com.spoom.service.order;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderRefuse;
import com.spoom.respository.order.OrderRefuseDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class OrderRefuseService {
	@Autowired
	private OrderRefuseDao orderRefuseDao;
	@Autowired
	private EntityManager em ;
	
	public List<OrderRefuse> findAll() {
		return orderRefuseDao.findAll();
	}
	public List<OrderRefuse> findAll(Specification<OrderRefuse> spec, Sort sort){
		return orderRefuseDao.findAll(spec, sort);
	}
	
	public OrderRefuse findById(int id){
		return orderRefuseDao.findOne(id);
	}
	
	public void save(OrderRefuse Order){
		orderRefuseDao.save(Order);
	}
	
	public void delete(int id){
		orderRefuseDao.delete(id);
	}
	
	public List<OrderRefuse> findByOrderLb(OrderLb orderLb){
		return orderRefuseDao.findByOrderLb(orderLb);
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
