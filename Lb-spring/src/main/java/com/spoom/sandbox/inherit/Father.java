package com.spoom.sandbox.inherit;

public class Father {
	private String name;
	Father(String name){
		this.name = name;
	}
	public void show(){
		System.out.println(this);
		System.out.println("Father");
	}
}
