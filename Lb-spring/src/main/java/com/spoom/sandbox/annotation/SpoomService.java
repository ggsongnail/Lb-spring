package com.spoom.sandbox.annotation;

public class SpoomService {
	@SpoomResource
	public SpoomDao spoomDao;
	
	public void show(){
		spoomDao.show();
	}
	
}
