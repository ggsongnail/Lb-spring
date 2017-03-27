package com.spoom.respository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.order.OrderArtificial;
import com.spoom.entity.order.OrderLb;

/**
 * 
 * @author songnail
 *
 */
public interface OrderArtificialFeeDao extends JpaRepository<OrderArtificial, Integer>,JpaSpecificationExecutor<OrderArtificial>{
	
}
