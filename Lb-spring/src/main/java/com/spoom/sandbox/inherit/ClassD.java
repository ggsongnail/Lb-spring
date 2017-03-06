package com.spoom.sandbox.inherit;

public abstract class ClassD implements IterfaceE{
	private String name;
	
	public void refresh(){
		System.out.println("ClassD's refresh");
	}
	
	public void getBeanFactory(){
		getBeanAbstractFactory();
		System.out.println("D实现E接口");
	};
	
	//交给子类实现
	public abstract void getBeanAbstractFactory();

}
