package com.colt.fieldengineerapp.util;

import org.testng.annotations.DataProvider;


/**
 * This class can be used to pass data to testcases as per need. Entire test will run as many data we will provide through 
 * this by adding additional methods and relevant data
 * @author Alok Verma
 *
 */
public class TestData {
	
	@DataProvider(name="InputData")
	public Object[][] getDataForEditField(){
		
		Object[][] dataObject = new Object[][] {
			
			{"coltonlinedcp", "Colt@1234"}, {"coltonlinedcp", "Colt@1234"}
			
		};
		
		return dataObject;
	}
	
	@DataProvider(name="TextFieldsData")
	public Object[][] getDataForRaisePlannedWorksTest(){
		
		Object[][] dataObject = new Object[][] {
			
			{"12345", "1234","123456","Test_Alok"}
			
		};
		
		return dataObject;
	}
	
	@DataProvider(name="TextFieldsDataForTemplates")
	public Object[][] getDataForTemplatesTest(){
		
		Object[][] dataObject = new Object[][] {
			
			{"1234","123456","Test_Alok"}
			
		};
		
		return dataObject;
	}
	
	@DataProvider(name="InputDataForAllTasks")
	public Object[][] getDataForCompletedTestTest(){
		
		Object[][] dataObject = new Object[][] {
			
			{"alok.verma@colt.net","This is test message from Alok"}
			
		};
		
		return dataObject;
	}


}
