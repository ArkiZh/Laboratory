package com.arki.laboratory.snippet;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @(#) MyTest.java
 * @Package 
 * 
 * Copyright © Singlewindow Corporation. All rights reserved.
 *
 */

/**
 *  Description : 
 * 
 *  @author:  k
 *
 *  History:  2017年7月3日 下午9:23:24   k   Created.
 *           
 */
public class MyTest {
	private static String SPARATOR_LINE = System.getProperty("line.separator", "\n");
	public static void main(String[] args) {
		System.out.println(SPARATOR_LINE.length());
		System.out.println(SPARATOR_LINE.toCharArray());
		System.out.println("---------");
		System.out.println(Arrays.toString(SPARATOR_LINE.toCharArray()));
		System.out.println(SPARATOR_LINE.getBytes());
		System.out.println(Arrays.toString(SPARATOR_LINE.getBytes()));
		String srcStr = SPARATOR_LINE+"hello"+SPARATOR_LINE+"world";
		System.out.println(srcStr);
		System.out.println(srcStr.indexOf(SPARATOR_LINE));
		System.out.println(srcStr.indexOf("w"));
		for (int i = 0; i < 10; i++) {
			if(i%2==0)continue;
			System.out.println(i);
			if(i==8) break;
		}
		Map<String,String> map = new HashMap();
		String str = map.get("ads");
		System.out.println(str);
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		System.out.println(sb.toString());
	}
	
	@Test
	public void test1(){
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String appLimitDateStr = sdf2.format(null);
		System.out.println("结果："+appLimitDateStr);
	}
}
