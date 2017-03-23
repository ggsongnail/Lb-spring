package com.spoom.respository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.material.MaterialSets;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderSets;

/**
 * 
 * @author songnail
 *
 */
public interface OrderSetsDao extends JpaRepository<OrderSets, Integer>,JpaSpecificationExecutor<OrderSets>{
	public OrderSets findByOrderLbAndMaterialSets(OrderLb orderLb,MaterialSets materialSets);
}
