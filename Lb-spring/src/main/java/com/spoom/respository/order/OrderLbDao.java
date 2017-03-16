package com.spoom.respository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.order.OrderLb;

/**
 * 
 * @author songnail
 *
 */
public interface OrderLbDao extends JpaRepository<OrderLb, Integer>,JpaSpecificationExecutor<OrderLb>{
	public OrderLb findByOrderNo(String orderNo);
	public OrderLb findBySysNo(String sysNo);
}
