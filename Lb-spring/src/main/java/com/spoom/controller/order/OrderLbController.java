package com.spoom.controller.order;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.entity.material.MaterialProduct;
import com.spoom.entity.order.OrderArtificialFee;
import com.spoom.entity.order.OrderArtificialFeeFinal;
import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderProduct;
import com.spoom.entity.order.OrderProductFinal;
import com.spoom.service.artificial.ArtificialFeeService;
import com.spoom.service.material.MaterialProductService;
import com.spoom.service.order.OrderArtificialFeeService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderProductService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("orderlb")
public class OrderLbController {
	@Autowired
	private OrderLbService orderService;
	
	@Autowired
	private MaterialProductService materialProductService;
	
	@Autowired
	private ArtificialFeeService artificialFeeService;
	
	@Autowired
	private OrderProductService orderProductService;
	
	@Autowired
	private OrderArtificialFeeService orderArtificialFeeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "order";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public OrderLb getOrder(@PathVariable int id){
		OrderLb order = orderService.findById(id);
		return order;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderLb saveOrder(@RequestBody OrderLb order){
		/*if(StringUtils.isBlank(order.getOrderNo())){
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sf.format(new Date());
			order.setOrderNo(time);
		}*/
		orderService.save(order);
		return order;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.POST)
	public List<OrderLb> listjson(@RequestBody Map<String,Object> params){
		Sort sort = new Sort(Sort.Direction.DESC,"signingDate");
		return orderService.findAll(getWhereClause(params),sort);
	}
	
	@ResponseBody
	@RequestMapping(value="validity/{field}/{parameter}/{id}",method = RequestMethod.GET)
	public Boolean getOrder(@PathVariable String field,@PathVariable String parameter,@PathVariable int id){
		OrderLb order = null;
		if("orderNo".equals(field))
			order = orderService.findByOrderNo(parameter);
		if("sysNo".equals(field))
			order = orderService.findBySysNo(parameter);
		if(order!=null&&order.getId()!=id)
			return false;
		return true;
	}
	
	private Specification<OrderLb> getWhereClause(final Map<String,Object> params){
		final SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return new Specification<OrderLb>() {
			public Predicate toPredicate(Root<OrderLb> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				try {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						if ("signingDate_GTE".equals(entry.getKey())&&entry.getKey()!=null) {
							System.out.println(sf.parse(entry.getValue().toString()));
							predicates.add(cb.greaterThanOrEqualTo(root.get("signingDate").as(Date.class), sf.parse(entry.getValue().toString())));
						}
						if ("signingDate_LTE".equals(entry.getKey())&&entry.getKey()!=null) {
							predicates.add(cb.lessThanOrEqualTo(root.get("signingDate").as(Date.class), sf.parse(entry.getValue().toString())));
						}
					}
					Predicate[] p = new Predicate[predicates.size()];  
			        return cb.and(predicates.toArray(p)); 
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null; 
			}
		};
	}
	
	@ResponseBody
	@RequestMapping(value="finalorder/json/{orderId}",method = RequestMethod.GET)
	public Map finalorder(@PathVariable int orderId){
		List<OrderProductFinal> orderProductFinals = materialProductService.getOrderProductFinals(orderId);
		List<OrderArtificialFeeFinal> orderArtificialFeeFinal = artificialFeeService.getOrderArtificialFeeFinals(orderId);
		List<OrderProductFinal> orderProductFinalsHis = materialProductService.getOrderProductFinalsHis(orderId);
		List<OrderArtificialFeeFinal> orderArtificialFeeFinalHis = artificialFeeService.getOrderArtificialFeeFinalsHis(orderId);
		OrderLb orderLb = orderService.findById(orderId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("materials",orderProductFinals);
		map.put("mans", orderArtificialFeeFinal);
		map.put("materialsHis", orderProductFinalsHis);
		map.put("mansHis", orderArtificialFeeFinalHis);
		map.put("orderLb", orderLb);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="final/material/save",method = RequestMethod.POST)
	public OrderLb saveFinalMaterial(@RequestBody List<OrderProductFinal> ops){
		for(OrderProductFinal op:ops){
			if(op.getDifCount()!=0){
				OrderProduct orderProduct  = orderProductService.findById(op.getoId());
				if(orderProduct!=null){
					orderProduct.setDifCount(op.getDifCount());
					orderProduct.setDifTotal(op.getDifTotal());
					orderProductService.save(orderProduct);
				}else{
					OrderProduct ghost = new OrderProduct();
					OrderLb orderLb = orderService.findById(op.getOrderId());
					MaterialProduct materialProduct = materialProductService.findById(op.getmId());
					//ghost.setOrderLb(orderLb);
					//ghost.setMaterialProduct(materialProduct);
					ghost.setDifCount(op.getDifCount());
					ghost.setDifTotal(op.getDifTotal());
					orderProductService.save(ghost);
				}
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="final/man/save",method = RequestMethod.POST)
	public OrderLb saveFinalMan(@RequestBody List<OrderArtificialFeeFinal> ops){
		for(OrderArtificialFeeFinal op:ops){
			if(op.getDifCount()!=0){
				OrderArtificialFee orderArtificialFee  = orderArtificialFeeService.findById(op.getoId());
				if(orderArtificialFee!=null){
					orderArtificialFee.setDifCount(op.getDifCount());
					orderArtificialFee.setDifTotal(op.getDifTotal());
					orderArtificialFeeService.save(orderArtificialFee);
				}else{
					OrderArtificialFee ghost = new OrderArtificialFee();
					OrderLb orderLb = orderService.findById(op.getOrderId());
					ArtificialFee artificialFee = artificialFeeService.findById(op.getmId());
					ghost.setOrderLb(orderLb);
					ghost.setArtificialFee(artificialFee);
					ghost.setDifCount(op.getDifCount());
					ghost.setDifTotal(op.getDifTotal());
					orderArtificialFeeService.save(ghost);
				}
			}
		}
		return null;
	}
	

}
