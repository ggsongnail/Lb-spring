package com.spoom.sandbox.inherit;

public class Son extends Father{

	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	Son(String name) {
		super(name);
		show();
	}
	
	/*public void show(){
		System.out.println("Son");
	}*/
	
	public static void main(String[] args){
		Son son = new Son("Jony");
		son.show();
	}
}
