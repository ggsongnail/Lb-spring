package com.spoom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.order.OrderSets;
import com.spoom.service.order.OrderSetsService;
import com.spoom.service.order.OrderLbService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("ordersets")
public class OrderSetsController {
	@Autowired
	private OrderSetsService orderSetsService;
	@Autowired
	private OrderLbService orderLbService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "orderSets";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public OrderSets getOrderArtificial(@PathVariable int id){
		OrderSets orderSets = orderSetsService.findById(id);
		return orderSets;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderSets saveOrderArtificial(@RequestBody OrderSets orderSets){
		orderSetsService.save(orderSets);
		return orderSets;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<OrderSets> listjson(){
		return orderSetsService.findAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="bill/save",method = RequestMethod.POST)
	public OrderSets saveOrderArtificialFromBill(@RequestBody List<OrderSets> orderSetss){
		orderLbService.save(orderSetss.get(0).getOrderLb());
		for(OrderSets op : orderSetss){
			orderSetsService.save(op);
		}
		return new OrderSets();
	}
	
}
