package com.spoom.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSql {
	public static void main(String[] args) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String str = "2017/03/02 20:00";
		System.out.println(sf.parse(str));
	}

}
