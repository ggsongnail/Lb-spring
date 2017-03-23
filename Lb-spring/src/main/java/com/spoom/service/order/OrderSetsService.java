package com.spoom.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.material.MaterialSets;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderSets;
import com.spoom.respository.order.OrderSetsDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class OrderSetsService {
	@Autowired
	private OrderSetsDao orderSetsDao;
	
	public List<OrderSets> findAll() {
		return orderSetsDao.findAll();
	}
	
	public OrderSets findById(int id){
		return orderSetsDao.findOne(id);
	}
	
	public void save(OrderSets OrderSets){
		orderSetsDao.save(OrderSets);
	}
	
	public void delete(int id){
		orderSetsDao.delete(id);
	}
	
	public OrderSets findByOrderLbAndMaterailSets(OrderLb orderLb,MaterialSets materialSets){
		return orderSetsDao.findByOrderLbAndMaterialSets(orderLb, materialSets);
	}
}
