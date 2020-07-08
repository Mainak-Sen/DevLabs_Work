package learnTestNG;

import org.testng.annotations.Test;

public class Data_Driven_testing {

@Test(dataProvider="get_data",dataProviderClass=Driving_Excel_data.class,groups="Smoke")
public void test_case_001(String no,String Name,String Age,String Role) {
	
System.out.print(no+" "+Name+" "+" "+Age+" "+Role);
System.out.println();
	
}

@Test(dataProvider="get_data",dataProviderClass=Driving_Excel_data.class,groups="Regression")
public void test_case_002(String no,String Name,String Age,String Role) {
	
System.out.print(no+" "+Name+" "+" "+Age+" "+Role);
System.out.println();
	
}
@Test(dataProvider="get_data",dataProviderClass=Driving_Excel_data.class,groups="Regression")
public void test_case_003(String no,String Name,String Age,String Role) {
	
System.out.print(no+" "+Name+" "+" "+Age+" "+Role);
System.out.println();
	
}
}
