package com.spoom.service;

import java.util.HashMap;
import java.util.Map;

public class ArrayCutting {  
	  
    private int avg;  
      
    private int[][] k;  
      
    private void checkit(int[] array){  
        if (array == null || array.length==0) {  
            throw new IllegalArgumentException();  
        }  
    }  
    // 初始化定义target值和边界值  
    private void init(int[] array) {  
        int sum = 0;  
        for(int i=0;i<array.length;i++) {  
            sum += array[i];  
        }  
        avg = Math.round(sum / 2);  
          
        k = new int[avg+1][array.length+1];  
          
        for (int w=1; w<=avg; w++) {  
            for(int j=1; j<=array.length; j++) {  
                if (j==1){  
                    k[w][j]=getValueJ(array,j);  
                    continue;  
                }  
            }  
        }  
    }  
      
    public int[] cutit(int[] array) {  
        checkit(array);  
          
        init(array);  
          
                // 自底向上构造矩阵  
        for (int j=2; j<=array.length; j++) {  
            for (int w=1; w<=avg; w++) {  
                int valueAfterCutJ = w-getValueJ(array,j);  
                int lastJ = j-1;  
                  
                if (valueAfterCutJ == 0) {  
                    k[w][j] = getValueJ(array,j);   //选择J后差值为0则选择J为结果值  
                    continue;  
                }  
                int valueChooseJ = 0;  
                if (valueAfterCutJ < 0) {  
                    valueChooseJ = getValueJ(array, j); //期望值比J小则取J为选择J后的值  
                } else {  
                    valueChooseJ = k[valueAfterCutJ][lastJ] + getValueJ(array,j);  
                }  
                  
                if (Math.abs(k[w][lastJ]-w) < Math.abs(valueChooseJ-w)  ) {  
                    k[w][j]=k[w][lastJ];  
                } else {  
                    k[w][j]=valueChooseJ;  
                }  
            }  
        }  
          
        return findPath(array);  
    }  
      
        // 最后一步：构造出最优解  
    private int[] findPath(int[] array) {  
        int[] result = new int[array.length];  
        int p=0;  
        int j=array.length;  
        int w=avg;  
        while(j>0){  
            int valueAfterCutJ = w-getValueJ(array,j);  
            int lastJ = j-1;  
              
            if (valueAfterCutJ == 0) {  //清0跳出  
                result[p++]=getValueJ(array,j);  
                w=w-getValueJ(array,j);  
                break;  
            }  
            int valueChooseJ = 0;  
            if (valueAfterCutJ < 0) {  
                valueChooseJ = getValueJ(array, j); //期望值比J小则取J为选择J后的值  
            } else {  
                valueChooseJ = k[valueAfterCutJ][lastJ] + getValueJ(array,j);  
            }  
              
            if (Math.abs(k[w][lastJ]-w) > Math.abs(valueChooseJ-w)  ) {  
                result[p++]=getValueJ(array,j);  
                w=w-getValueJ(array,j);  
            }  
            j=j-1;  
        }  
        return result;  
    }  
  
    public static Map returnMap(int[] a){
    	ArrayCutting ac = new ArrayCutting();
		int[] arrays = ac.cutit(a);  
        int selectedSum = 0;  
        Map map = new HashMap<Integer,Integer>();
        for (int i=0;i<arrays.length;i++){  
            if (arrays[i]>0){  
            	if(map.containsKey(arrays[i])){
            		map.put(arrays[i], Integer.sum((Integer) map.get(arrays[i]), 1));
            	}else{
            		map.put(arrays[i],1);
            	}
            }  
        } 
        return map;
    }
    
    public static void main(String[] args) {  
    	Map map = returnMap(new int[]{2,2,4,5,7,8,13});
    	System.out.println(map.size());
    }  
      
    // 返回第j个数组元素  
    private int getValueJ(int[]array, int j){  
        return array[j-1];  
    }  
} 
