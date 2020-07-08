package learnTestNG;

import org.testng.annotations.Test;

public class Try_out {
    //If we have both test and main method depending upon whether we execute it as a Java application
	//or testNgTest,it will execute and print result accordingly ,both of them can't be executed simultaneously 
	@Test(dependsOnMethods="dummy_test_2")
	public void dummy_test_1() {
		System.out.println("Hello_World_Test1");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello_World_main");
	}
	@Test(dependsOnMethods="dummy_test_1")
	public void dummy_test_2() {
		System.out.println("Hello_World_Test2");
	}
	
	@Test
	public void dummy_test_3() {
		System.out.println("Hello_World_Test3");
	}

}
