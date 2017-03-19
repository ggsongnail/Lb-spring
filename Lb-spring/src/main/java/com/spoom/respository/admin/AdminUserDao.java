package com.spoom.respository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spoom.entity.admin.AdminUser;

/**
 * 
 * @author songnail
 *
 */
public interface AdminUserDao extends JpaRepository<AdminUser, Integer>{
	public AdminUser findByNameAndPassword(String name,String password);
} 
