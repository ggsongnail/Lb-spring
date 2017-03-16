package com.spoom.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.order.OrderLb;
import com.spoom.respository.order.OrderLbDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class OrderLbService {
	@Autowired
	private OrderLbDao orderDao;
	
	public List<OrderLb> findAll() {
		return orderDao.findAll();
	}
	public List<OrderLb> findAll(Specification<OrderLb> spec, Sort sort){
		return orderDao.findAll(spec, sort);
	}
	
	public OrderLb findById(int id){
		return orderDao.findOne(id);
	}
	
	public void save(OrderLb Order){
		orderDao.save(Order);
	}
	
	public void delete(int id){
		orderDao.delete(id);
	}
	
	public OrderLb findByOrderNo(String orderNo){
		return orderDao.findByOrderNo(orderNo);
	}
	
	public OrderLb findBySysNo(String sysNo){
		return orderDao.findBySysNo(sysNo);
	}
	
}
