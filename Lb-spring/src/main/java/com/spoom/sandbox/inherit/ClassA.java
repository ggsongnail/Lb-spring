package com.spoom.sandbox.inherit;

public class ClassA extends ClassB{
	
	//getBeanFactory��Ҫ�и�����̳�ʵ��
	public void begin(){
		//����ķ���
		getBeanFactory();
	}	
	
	public static void main(String[] args){
		ClassA a = new ClassA();
		a.begin();
	}
}
