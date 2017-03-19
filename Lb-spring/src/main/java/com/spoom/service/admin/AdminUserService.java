package com.spoom.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spoom.entity.admin.AdminUser;
import com.spoom.respository.admin.AdminUserDao;

/**
 * 
 * @author songnail
 *
 */
@Service
@Transactional
public class AdminUserService {
	@Autowired
	private AdminUserDao adminUserDao;
	
	public List<AdminUser> findAll() {
		return adminUserDao.findAll();
	}
	
	public AdminUser findById(int id){
		return adminUserDao.findOne(id);
	}
	
	public void save(AdminUser ArtificialDetail){
		adminUserDao.save(ArtificialDetail);
	}
	
	public void delete(int id){
		adminUserDao.delete(id);
	}
	
	public AdminUser findByNameAndPassword(String name,String password){
		return adminUserDao.findByNameAndPassword(name,password);
	}
	
}
