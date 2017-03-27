package com.spoom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.order.OrderArtificial;
import com.spoom.service.order.OrderArtificialFeeService;
import com.spoom.service.order.OrderLbService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("orderartificialfee")
public class OrderArtificialFeeController {
	@Autowired
	private OrderArtificialFeeService orderArtificialFeeService;
	@Autowired
	private OrderLbService orderLbService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "orderArtificialFee";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public OrderArtificial getOrderArtificial(@PathVariable int id){
		OrderArtificial orderArtificialFee = orderArtificialFeeService.findById(id);
		return orderArtificialFee;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderArtificial saveOrderArtificial(@RequestBody OrderArtificial OrderArtificial){
		orderArtificialFeeService.save(OrderArtificial);
		return OrderArtificial;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<OrderArtificial> listjson(){
		return orderArtificialFeeService.findAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="bill/save",method = RequestMethod.POST)
	public OrderArtificial saveOrderArtificialFromBill(@RequestBody List<OrderArtificial> orderArtificials){
		orderArtificialFeeService.batchUpdate(orderArtificials);
		return new OrderArtificial();
	}
	
}
