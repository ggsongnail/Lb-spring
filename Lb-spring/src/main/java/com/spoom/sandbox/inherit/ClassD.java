package com.spoom.sandbox.inherit;

public abstract class ClassD implements IterfaceE{
	private String name;
	
	public void refresh(){
		System.out.println("ClassD's refresh");
	}
	
	public void getBeanFactory(){
		getBeanAbstractFactory();
		System.out.println("Dʵ��E�ӿ�");
	};
	
	//��������ʵ��
	public abstract void getBeanAbstractFactory();

}
