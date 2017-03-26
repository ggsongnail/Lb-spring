package com.spoom.service.order;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.material.MaterialProduct;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderProduct;
import com.spoom.respository.order.OrderProductDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class OrderProductService {
	@Autowired
	private OrderProductDao orderProductDao;
	@Autowired
	private EntityManager em;

	public List<OrderProduct> findAll() {
		return orderProductDao.findAll();
	}

	public OrderProduct findById(int id) {
		return orderProductDao.findOne(id);
	}

	public void save(OrderProduct orderProduct) {
		orderProductDao.save(orderProduct);
	}

	public void delete(int id) {
		orderProductDao.delete(id);
	}

	public OrderProduct findByOrderLbAndMaterialProduct(OrderLb order, MaterialProduct materialProduct) {
		return orderProductDao.findByOrderLbAndMaterialProduct(order, materialProduct);
	}

	public List<OrderProduct> findByOrderLb(OrderLb orderLb) {
		return orderProductDao.findByOrderLb(orderLb);
	}

	public void batchInsert(List list) {
		for (int i = 0; i < list.size(); i++) {
			em.persist(list.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
	}

	public void batchUpdate(List list) {
		for (int i = 0; i < list.size(); i++) {
			em.merge(list.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
	}

}
