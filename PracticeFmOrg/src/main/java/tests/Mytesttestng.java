package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mytesttestng {
	
	@BeforeSuite
	public void checkbs() {
		System.out.println("before suite");
	}
	@AfterSuite
	public void checkas() {
		System.out.println("after suite");
	}
	@BeforeClass
	public void checkbc() {
		System.out.println("before class");
	}
	@AfterClass
	public void checkac() {
		System.out.println("after class");
	}
	
	@BeforeTest
	public void checkbt() {
		System.out.println("before test");
	}
	@AfterTest
	public void checkat() {
		System.out.println("after test");
	}
	@Test
	public void test1() {
		System.out.println("test1");
	}
	@Test
	public void test2() {
		System.out.println("test2");
	}
	
	@BeforeMethod
	public void checkbm() {
		System.out.println("before method");
	}
	@AfterMethod
	public void checkam() {
		System.out.println("after method");
	}
	
	

}
