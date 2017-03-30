package com.spoom.respository.order;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderRefuse;

/**
 * 
 * @author songnail
 *
 */
public interface OrderRefuseDao extends JpaRepository<OrderRefuse, Integer>,JpaSpecificationExecutor<OrderRefuse>{
	List<OrderRefuse> findByOrderLbOrderByTalkTimeAsc(OrderLb orderLb);
	@Query(value="select max(rd.couted) from (SELECT count(*) couted FROM order_refuse os LEFT JOIN order_lb ol on os.order_id = ol.id where ol.signing_date > ?1 and ol.signing_date < ?2 GROUP BY ol.id )rd",nativeQuery=true)
	public int getMaxCount(Date beginDate,Date endDate);
}
