package com.colt.fieldengineerapp.pages;

import org.openqa.selenium.support.PageFactory;

import com.colt.fieldengineerapp.base.TestBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AllTasksListPage extends TestBase {
	
	
	@AndroidFindBy(id = "com.colt.coltengineering:id/img_next")
	AndroidElement viewTaskBtn;
	
	
	public AndroidElement getViewTaskBtn() {
		return viewTaskBtn;
	}

	public AllTasksListPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver, getDuration(30)), this);
	}
	
	
}
