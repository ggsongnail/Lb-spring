package com.spoom.controller.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.order.OrderLb;
import com.spoom.entity.order.OrderRefuse;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderRefuseService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("orderrefuse")
public class OrderRefuseController {
	@Autowired
	private OrderRefuseService orderRefuseService;
	@Autowired
	private OrderLbService orderLbService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "orderRefuse";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public OrderRefuse getOrderRefuse(@PathVariable int id){
		OrderRefuse OrderRefuse = orderRefuseService.findById(id);
		return OrderRefuse;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderRefuse saveOrderRefuse(@RequestBody OrderRefuse OrderRefuse){
		orderRefuseService.save(OrderRefuse);
		return OrderRefuse;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<OrderRefuse> listjson(){
		return orderRefuseService.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="refuses/json/{orderId}",method = RequestMethod.GET)
	public Map<String,Object> refusesJson(@PathVariable int orderId){
		Map<String,Object> map = new HashMap<String,Object>();
		OrderLb orderLb = orderLbService.findById(orderId);
		List<OrderRefuse> orderRefuses = orderRefuseService.findByOrderLb(orderLb);
		map.put("orderLb", orderLb);
		map.put("orderRefuses", orderRefuses);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="save/refuses",method = RequestMethod.POST)
	public OrderRefuse saveRefuses(@RequestBody List<OrderRefuse> orderRefuses){
		orderRefuseService.batchUpdate(orderRefuses);
		return null;
	}
	
}
