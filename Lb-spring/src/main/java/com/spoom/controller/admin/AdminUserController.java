package com.spoom.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spoom.entity.admin.AdminUser;
import com.spoom.entity.artificial.ArtificialFee;
import com.spoom.service.admin.AdminUserService;
import com.spoom.service.dictionary.DictionaryClassifyService;
import com.spoom.service.dictionary.DictionaryService;
import com.spoom.service.material.MaterialClassifyService;
import com.spoom.service.order.OrderArtificialFeeService;
import com.spoom.service.order.OrderLbService;

/**
 * 
 * @author songnail
 *
 */
@Controller
@RequestMapping("adminuser")
public class AdminUserController {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private MaterialClassifyService materialClassifyService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryClassifyService dictionaryClassifyService;
	@Autowired
	private OrderLbService orderService;
	@Autowired
	private OrderArtificialFeeService orderArtificialFeeService;
	
	//登录验证
	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	public AdminUser login(@RequestBody AdminUser adminUser){
		AdminUser user = adminUserService.findByNameAndPassword(adminUser.getName(),adminUser.getPassword());
		return user;
	}
	
	//注册
	@RequestMapping(value="register",method = RequestMethod.POST)
	public String register(){
		return "adminUser";
	}
	
	//更新密码
	@RequestMapping(value="update",method = RequestMethod.POST)
	public String update(){
		return "adminUser";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(){
		return "adminUser";
	}
	
	@ResponseBody
	@RequestMapping(value="save",method = RequestMethod.POST)
	public AdminUser saveArtificialDetail(@RequestBody AdminUser adminUser){
		adminUser.setUpdateDate(new Date());
		adminUserService.save(adminUser);
		return adminUser;
	}
	
	@ResponseBody
	@RequestMapping(value="list/json",method = RequestMethod.GET)
	public List<AdminUser> listjson(){
		return adminUserService.findAll();
	}
	
}
