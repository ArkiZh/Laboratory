package com.arki.laboratory.snippet;

import com.arki.laboratory.common.ArrayUtil;
import com.arki.laboratory.common.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

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
		String encode = URLEncoder.encode("http://localhost:8089/para-server/sw/base/para/depParaList?paraName=MOC_NALI_COUNTRY_CODE&filterCon=cny_cde<>EU,cny_cde!#0%&rowNum=5000");
		System.out.println(encode);
		System.out.println(URLEncoder.encode("#")); //%23
		System.out.println(URLEncoder.encode("%")); //%25
		System.out.println(URLEncoder.encode("!")); //%21
		System.out.println(URLEncoder.encode("@")); //%40
		System.out.println("".split(",").length);
		String decode = URLDecoder.decode("[route%3A%2F%2F0.0.0.0%2Fcom.sw.plat.para.api.service.ISwParaApiService%3Fcategory%3Drouters%26dynamic%3Dfalse%26enabled%3Dtrue%26force%3Dtrue%26group%3DLS%26name%3DLS%2Fcom.sw.plat.para.api.service.ISwParaApiService+blackwhitelist%26priority%3D0%26router%3Dcondition%26rule%3Dconsumer.host%2B%2521%253D%2B192.168.0.79%252C192.168.2.32%252C192.168.10.1%252C192.168.59.1%252C192.168.0.72%252C192.168.3.36%252C192.168.40.1%252C192.168.2.76%252C192.168.3.168%252C192.168.2.125%252C192.168.3.122%252C192.168.100.55%252C192.168.81.1%252C192.168.2.129%252C192.168.3.73%252C192.168.220.1%252C192.168.3.70%252C192.168.3.128%252C192.168.0.83%252C10.100.100.33%252C10.100.100.5%252C192.168.3.246%252C10.100.100.71%252C10.0.0.33%252C192.168.136.1%252C192.168.3.133%252C192.168.2.3%252C192.168.2.134%252C192.168.190.1%252C192.168.1.91%252C192.168.3.107%252C10.0.0.20%252C10.0.0.4%252C192.168.2.11%252C10.0.0.68%252C10.0.0.2%252C192.168.3.99%252C192.168.0.21%252C192.168.2.149%252C192.168.70.1%252C192.168.2.92%252C192.168.0.63%252C192.168.2.102%252C192.168.1.226%252C10.0.0.14%252C192.168.1.227%252C192.168.2.62%252C192.168.100.176%252C192.168.1.224%252C10.100.100.12%252C192.168.1.225%252C192.168.2.218%252C10.0.0.57%252C192.168.1.87%252C192.168.1.223%252C10.0.0.53%252C192.168.3.152%252C192.168.2.111%252C192.168.3.150%252C192.168.0.53%252C10.0.0.112%252C192.168.2.181%2B%253D%253E%2Bfalse%26runtime%3Dfalse%26type%3Djavascript]");
		System.out.println(URLDecoder.decode(decode));
	}
}
