package com.spoom.respository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.order.OrderLb;

/**
 * 
 * @author songnail
 *
 */
public interface OrderLbDao extends JpaRepository<OrderLb, Integer>{
	public OrderLb findByOrderNo(String orderNo);
}
