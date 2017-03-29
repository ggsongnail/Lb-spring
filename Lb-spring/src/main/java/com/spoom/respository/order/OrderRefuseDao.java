package com.spoom.respository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderRefuse;

/**
 * 
 * @author songnail
 *
 */
public interface OrderRefuseDao extends JpaRepository<OrderRefuse, Integer>,JpaSpecificationExecutor<OrderRefuse>{
	List<OrderRefuse> findByOrderLbOrderByTalkTimeAsc(OrderLb orderLb);
}
