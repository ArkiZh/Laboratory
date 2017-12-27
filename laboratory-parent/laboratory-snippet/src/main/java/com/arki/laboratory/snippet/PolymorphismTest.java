package com.arki.laboratory.snippet;

import org.junit.Test;

public class PolymorphismTest {
	private I i;
	{
		this.i = new A();
	}
	
	@Test
	public void test1(){
		System.out.println(i.f1());
		B b = (B) i;
		System.out.println(b.f1());
	}
}

interface I {
	public int f1();
}

class A implements I {
	public int f1(){
		return 1;
	}
}

class B implements I {
	public int f1(){
		return 2;
	}
}
