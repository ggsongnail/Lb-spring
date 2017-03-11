package com.spoom.service;

import java.util.HashMap;
import java.util.Map;

import com.spoom.entity.order.OrderProduct;

public class NormalTest {
	public static void main(String[] args){
		OrderProduct[] s = new OrderProduct[3];
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(1, 3);
		map.put(2, 4);
		map.put(1, 3-1);
		int j = map.get(1);
		System.out.println(map.get(3));
		/*for(int i=0;i<3;i++){
			if(i==1){
				continue;
			}
			System.out.println(i);
		}*/
	}
}
