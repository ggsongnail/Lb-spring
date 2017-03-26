package com.spoom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.order.OrderProduct;
import com.spoom.service.material.MaterialProductService;
import com.spoom.service.order.OrderLbService;
import com.spoom.service.order.OrderProductService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("orderproduct")
public class OrderProductController {
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private OrderLbService orderLbService;
	@Autowired
	private MaterialProductService materialProductService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "OrderProduct";
	}
	
	@ResponseBody
	@RequestMapping(value="get/{id}",method = RequestMethod.GET)
	public OrderProduct getOrderProduct(@PathVariable int id){
		OrderProduct OrderProduct = orderProductService.findById(id);
		return OrderProduct;
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public OrderProduct saveOrderProduct(@RequestBody OrderProduct OrderProduct){
		orderProductService.save(OrderProduct);
		return OrderProduct;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<OrderProduct> listjson(){
		return orderProductService.findAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="bill/save",method = RequestMethod.POST)
	public OrderProduct saveOrderProductFromBill(@RequestBody List<OrderProduct> materialProductDtos){
		return new OrderProduct();
	}
	
}
