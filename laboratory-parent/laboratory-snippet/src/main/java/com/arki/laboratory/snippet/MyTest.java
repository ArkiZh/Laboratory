package com.arki.laboratory.snippet;

import com.arki.laboratory.common.ArrayUtil;
import com.arki.laboratory.common.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

	@Test
	public void test2(){
		int N = 2500;
		Integer[] src = new Integer[N];
		for (int i = 0; i < N; i++) {
			src[i] = i;
		}
		Logger.info(ArrayUtil.transferArrayToString(src));
		int len = src.length;
		int start = 0;
		int size = 0;

		for (int i = 0; i < len; i++) {
			size++;
			if(size>=900||(start+size==len)){
				Integer[] dest=new Integer[size];
				System.arraycopy(src, start, dest, 0, size);
				Logger.info(ArrayUtil.transferArrayToString(dest));
				start+=size;
				size=0;
			}
		}
	}
	@Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        Class<String> clazz = String.class;
        String s = clazz.newInstance();
        System.out.println("Result:[" + s.toString() + "]");
    }

    @Test
	public void testTimePeriod() {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.YEAR,2015);
		instance.set(Calendar.MONTH,3);
		instance.set(Calendar.DATE,15);
		instance.set(Calendar.HOUR_OF_DAY,8);
		long timeInMillis = instance.getTimeInMillis();
		double v = (System.currentTimeMillis() - timeInMillis) / (3600.0 * 24 * 1000);
		System.out.println(v);
	}

	@Test
	public void testRegex(){
		String a = "a01_";
		System.out.println(Pattern.matches("[a-zA-Z][a-zA-Z0-9_#$]*", a));
		StringBuilder sb = new StringBuilder();
		sb.append(a);
		for (int i = 0; i < 10; i++) {
			sb.append(sb);
			System.out.println("=== count " + i+"   length "+sb.length());
			System.out.println(sb.toString());
			long startTime = System.currentTimeMillis();
			boolean match = Pattern.matches("[a-zA-Z][a-zA-Z0-9_#$]*", sb.toString());
			System.out.println("=== match " + match + "   time " + (System.currentTimeMillis() - startTime));
		}
	}

	@Test
	public void switchTest() {
		int i = 11;
		switch (i) {
			case 0:
				System.out.println(0);
			case 1:
				System.out.println(1);
			case 2:
				System.out.println(2);
				return;
			case 3:
				System.out.println(3);
				break;
			default:
				System.out.println(4);
		}
		System.out.println("End!");
	}

	@Test
	public void testLog4j(){
		//Logger.info("Without config.");
		org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Debug log.");
	}

	@Test
	public void testUrlCode(){
		String encode = URLEncoder.encode("http://localhost:8089/xxx?XXX=XXX&XXX=XXX<>XXX,XXX!#0%&XXX=5000");
		System.out.println(encode);
		System.out.println(URLEncoder.encode("#")); //%23
		System.out.println(URLEncoder.encode("%")); //%25
		System.out.println(URLEncoder.encode("!")); //%21
		System.out.println(URLEncoder.encode("@")); //%40
		System.out.println("".split(",").length);
		String decode = URLDecoder.decode("[route%3A%2F%2F0.0.0.0%2Fcom.xxxService%3Fcategory%3Drouters%26dynamic%3Dfalse%26enabled%3Dtrue%26force%3Dtrue%26group%3DLS%26name%3DLS%2Fcom.xxxService+blackwhitelist%26priority%3D0%26router%3Dcondition%26rule%3Dconsumer.host%2B%2521%253D%2B192.168.0.0%2B%253D%253E%2Bfalse%26runtime%3Dfalse%26type%3Djavascript]");
		System.out.println(URLDecoder.decode(decode));
	}

	@Test
	public void testCloseStream() throws IOException {
		File file = new File("com.arki.laboratory.log");
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				fileInputStream.close();
				throw new IOException();
				//此处不用catch块的话会把异常抛到调用者，后面的finally块会继续执行。
			}finally {
				try {
					fileOutputStream.close();
					System.out.println("Output stream closed.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testCloseStreamTwice() throws IOException {
		//测试可不可以关两遍
		File file = new File("com.arki.laboratory.log");
		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.close();
		System.out.println("1");
		fileInputStream.close();
		System.out.println("2");
	}

	@Test
	public void testTryCatch() {
		try {
			triggerRuntimeException();
			System.out.println("Normal.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Using return, break, throw, and so on from a finally block
	 * suppresses the propagation of
	 * any unhandled Throwable which was thrown in the try or catch block.
	 *
	 * This rule raises an issue when a jump statement (break, continue, return, throw, and goto)
	 * would force control flow to leave a finally block.
	 */
	private void triggerRuntimeException() {
		try{
			throw new RuntimeException("A");
		}finally {
			//在finally里执行return会抑制本方法抛出的异常，导致调用者不会获取异常
			//问题详细说明：CWE-584: Return Inside Finally Block：
			// http://cwe.mitre.org/data/definitions/584.html
			return;
		}
	}

	@Test
	public void testNullInstanceOf() {
		System.out.println(null instanceof String);
	}


	@Test
	public void testPower() {
		int temp = 1;
		int i = 1;
		while (true){
			temp = 2 * temp;
			int power = 100 * i * i;
			System.out.println(i + " " + power + " " + temp);
			if (power < temp) break;
			i++;
		}
		System.out.println(i);
	}
}
