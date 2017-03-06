package com.spoom.sandbox.inherit;

public class ClassA extends ClassB{
	
	//getBeanFactory总要有个父类继承实现
	public void begin(){
		//父类的方法
		getBeanFactory();
	}	
	
	public static void main(String[] args){
		ClassA a = new ClassA();
		a.begin();
	}
}
