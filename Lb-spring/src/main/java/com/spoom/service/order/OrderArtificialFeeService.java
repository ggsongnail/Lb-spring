package com.spoom.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.order.OrderArtificialFee;
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
	
	public List<OrderArtificialFee> findAll() {
		return orderArtificialFeeDao.findAll();
	}
	
	public OrderArtificialFee findById(int id){
		return orderArtificialFeeDao.findOne(id);
	}
	
	public void save(OrderArtificialFee OrderArtificialFee){
		orderArtificialFeeDao.save(OrderArtificialFee);
	}
	
	public void delete(int id){
		orderArtificialFeeDao.delete(id);
	}
	
	public OrderArtificialFee findByOrderLbAndOrderArtificialFee(OrderLb order,ArtificialFee artificialFee){
		return orderArtificialFeeDao.findByOrderLbAndArtificialFee(order, artificialFee);
	}
	
}
