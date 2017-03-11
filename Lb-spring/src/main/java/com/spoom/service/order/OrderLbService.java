package com.spoom.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
