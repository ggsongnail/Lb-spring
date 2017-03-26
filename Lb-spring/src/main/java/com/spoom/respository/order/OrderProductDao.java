package com.spoom.respository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.material.MaterialProduct;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderProduct;

/**
 * 
 * @author songnail
 *
 */
public interface OrderProductDao extends JpaRepository<OrderProduct, Integer>,JpaSpecificationExecutor<OrderProduct>{
	//public OrderProduct findByOrderLbAndMaterialProduct(OrderLb orderLb,MaterialProduct materialProduct);
	//public List<OrderProduct> findByOrderLb(OrderLb orderLb);
}
