package com.spoom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.order.OrderArtificialFee;
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
	public OrderArtificialFee getOrderArtificial(@PathVariable int id){
		OrderArtificialFee orderArtificialFee = orderArtificialFeeService.findById(id);
		return orderArtificialFee;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderArtificialFee saveOrderArtificial(@RequestBody OrderArtificialFee OrderArtificial){
		orderArtificialFeeService.save(OrderArtificial);
		return OrderArtificial;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<OrderArtificialFee> listjson(){
		return orderArtificialFeeService.findAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="bill/save",method = RequestMethod.POST)
	public OrderArtificialFee saveOrderArtificialFromBill(@RequestBody List<OrderArtificialFee> orderArtificials){
		orderLbService.save(orderArtificials.get(0).getOrderLb());
		for(OrderArtificialFee op : orderArtificials){
			orderArtificialFeeService.save(op);
		}
		return new OrderArtificialFee();
	}
	
}
