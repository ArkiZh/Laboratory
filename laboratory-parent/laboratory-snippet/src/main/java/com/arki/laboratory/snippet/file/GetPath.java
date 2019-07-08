package com.arki.laboratory.snippet.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class GetPath {
	public static void main(String[] args) throws IOException {
		Class<GetPath> clazz = GetPath.class;
        File f = new File(clazz.getResource("/").getPath());	//类加载的根路径   C:\workspace\forTest\Laboratory\bin
        System.out.println(f);

        File f2 = new File(clazz.getResource("").getPath());	// 获取当前类的所在工程路径; 如果不加“/”  当前类的加载目录 C:\workspace\forTest\Laboratory\bin\com\k\lab
        System.out.println(f2);

        
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();		// 获取项目路径	C:\workspace\forTest\Laboratory
        System.out.println(courseFile);

        URL xmlpath = clazz.getClassLoader().getResource("");	// file:/C:/workspace/forTest/Laboratory/bin/
        System.out.println(xmlpath);

        System.out.println(System.getProperty("user.dir"));		// C:\workspace\forTest\Laboratory
        
        //  获取所有的类路径 包括jar包的路径
        //C:\workspace\forTest\Laboratory\bin;C:\DevSoft\eclipse-jee-luna-SR2-win32-x86_64\plugins\org.junit_4.11.0.v201303080030\junit.jar;C:\DevSoft\eclipse-jee-luna-SR2-win32-x86_64\plugins\org.hamcrest.core_1.3.0.v201303031735.jar
        System.out.println(System.getProperty("java.class.path"));

        
        String absolutePath = new File("").getAbsolutePath();
        System.out.println(absolutePath);
	}
	
	@Test
	public void canonicalPathTest(){
		System.out.println(System.getProperty("user.dir"));
		  
		  try {
		   System.out.println("-----默认相对路径：取得路径不同------");
		   File file1 =new File("..\\src\\test1.txt");
		   System.out.println(file1.getPath());
		   System.out.println(file1.getAbsolutePath());
		   System.out.println(file1.getCanonicalPath());
		   System.out.println("-----默认相对路径：取得路径不同------");
		   File file =new File(".\\test1.txt");
		   System.out.println(file.getPath());
		   System.out.println(file.getAbsolutePath());
		   System.out.println(file.getCanonicalPath());
		   
		   System.out.println("-----默认绝对路径:取得路径相同------");
		   File file2 =new File("D:\\workspace\\test\\test1.txt");
		   System.out.println(file2.getPath());
		   System.out.println(file2.getAbsolutePath());
		   System.out.println(file2.getCanonicalPath());
		   
		   File file3 = new File("d:/a/./a.t");
		   System.out.println(file3.getAbsolutePath());
		   System.out.println(file3.getCanonicalPath());
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
	}
}
