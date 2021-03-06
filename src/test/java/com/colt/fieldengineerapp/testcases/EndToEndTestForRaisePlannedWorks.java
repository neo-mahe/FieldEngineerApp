package com.colt.fieldengineerapp.testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.colt.fieldengineerapp.base.TestBase;
import com.colt.fieldengineerapp.pages.ConfirmPage;
import com.colt.fieldengineerapp.pages.HomePage;
import com.colt.fieldengineerapp.pages.LandingPage;
import com.colt.fieldengineerapp.pages.LoginPage;
import com.colt.fieldengineerapp.pages.RaisedPlanWorkPage;
import com.colt.fieldengineerapp.pages.SetTimePage;
import com.colt.fieldengineerapp.util.TestData;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class EndToEndTestForRaisePlannedWorks extends TestBase {

	LoginPage loginPage;
	LandingPage landingPage;
	HomePage homePage;
	RaisedPlanWorkPage raisedPlannedWorkPage;
	SetTimePage setTimePage;
	ConfirmPage confirmPage;
	AndroidDriver<AndroidElement> driver;
	List<AndroidElement> listofTextView;


	public EndToEndTestForRaisePlannedWorks() throws MalformedURLException, IOException {
		super();

	}
	
	@BeforeTest(alwaysRun = true)
	public void startServices() throws IOException, InterruptedException {
		TestBase.startAVD();
		Thread.sleep(20000);
		System.out.println("Starting Appium == " +this.getClass().getName());
		TestBase.startAppiumServer();
				
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws MalformedURLException, IOException, InterruptedException {
		driver = getDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		raisedPlannedWorkPage = new RaisedPlanWorkPage(driver);
		setTimePage = new SetTimePage(driver);
		confirmPage = new ConfirmPage(driver);
	}

	@Test(dataProvider = "TextFieldsData", dataProviderClass = TestData.class)
	public void endToEndTestForRaisePlanWork(String siteAddress, String orderNumber, String inputTextField,String templateName) throws MalformedURLException, IOException, InterruptedException {
		
		if(prop.getProperty(ELEMENT_RECORDING_NEEDED) != null) {
			if(prop.getProperty(ELEMENT_RECORDING_NEEDED).equals(ELEMENT_TRUE)) {
				TestBase.startRecording(driver);
			}
			
		}
		landingPage = loginPage.login(driver, prop.getProperty("userID"), prop.getProperty("password"));
		landingPage.getContinueBtn().click();
		Thread.sleep(3000);
		homePage.getRaisePlanWork().click();
			
		String label = raisedPlannedWorkPage.getTemplateLabel().getText();
		System.out.println("Label is " + label);
		
		//Click on Template dropdown and click on Standard SD option
		raisedPlannedWorkPage.getTemplateDropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_TEMPLATE_DROPDOWN_2).perform();	
			
		
		
		//click on Change Category drop down and select one of the value
		String categoryLabel = raisedPlannedWorkPage.getCategoryLabel().getText();
		System.out.println("Label is " + categoryLabel);
		raisedPlannedWorkPage.getCategoryDropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_CATEGORY_DROPDOWN_1).perform();		

		//click on Change Category drop down and select one of the value
		String changeDescriptionlabel = raisedPlannedWorkPage.getChangeDescriptionLabel().getText();
		System.out.println("Label is " + changeDescriptionlabel);
		raisedPlannedWorkPage.getChangeDescriptionDropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_CHANGE_DESCRIPTION_DROPDOWN_1).perform();
		
		String changeCatalogeLabel = raisedPlannedWorkPage.getChangeCatalogeLabel().getText();
		System.out.println("Change catalogue label is "+ changeCatalogeLabel);		
		String changeCatalogeTextField = raisedPlannedWorkPage.getChangeCatalogeTextField().getText();
		System.out.println("Change catalogue Text Field is "+ changeCatalogeTextField);
		
		
		String changeCatalogeTextFieldLabel = raisedPlannedWorkPage.getConfigIDTypeLabel().getText();
		System.out.println("Config Label is "+ changeCatalogeTextFieldLabel);
		
		String changeCatalogeTextFieldValue = raisedPlannedWorkPage.getConfigIdTextField().getText();
		System.out.println("Config Text Field  is "+ changeCatalogeTextFieldValue);
		
		raisedPlannedWorkPage.getNextBtn().click();
		
		//On Next page we meed to click on 2 of the drop downs and enter value in text field
		

		String labelLocationDetailsTier1 = raisedPlannedWorkPage.getLocationDetailsTier1Label().getText();
		System.out.println("Label is " + labelLocationDetailsTier1);
		
		//Click on Template dropdown and click on Standard SD option
		raisedPlannedWorkPage.getLocationDetailsTier1DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_LOCATION_TIER1_DROPDOWN_22).perform();
		
		
		String labelLocationDetailsTier2 = raisedPlannedWorkPage.getLocationDetailsTier2Label().getText();
		System.out.println("Label is " + labelLocationDetailsTier2);
		
		//Click on Template dropdown and click on Standard SD option
		raisedPlannedWorkPage.getLocationDetailsTier2DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_LOCATION_TIER2_DROPDOWN_2).perform();
		
		raisedPlannedWorkPage.getSiteAddressTextField().sendKeys(siteAddress);
		
		raisedPlannedWorkPage.getNextBtn().click();
		
		//Planned Start
		String labelPLannedStart = raisedPlannedWorkPage.getPlannedStartDateLabel().getText();
		System.out.println("Label is " + labelPLannedStart);
		
		//Click on Template dropdown and click on Standard SD option
		raisedPlannedWorkPage.getPlannedStartDateSelector().click();
		String timeZoneID = raisedPlannedWorkPage.getZoneGMTBtn().getText(); 
		raisedPlannedWorkPage.getZoneGMTBtn().click();
		Map<String,Integer> dateMap = raisedPlannedWorkPage.timeDateMapAsPerTimezone(timeZoneID);
		System.out.println(" Current day in dd is "+ dateMap.get(ELEMENT_DAY_KEY));
		
		
		int currentDate = dateMap.get(ELEMENT_DAY_KEY).intValue();
		
		//check if we can set date previous to current
		if((currentDate-1) != 0) {
		 raisedPlannedWorkPage.moveToScrollToElement(driver, String.valueOf(currentDate-1)).perform();
		raisedPlannedWorkPage.getOkBtn().click();
		raisedPlannedWorkPage.getOkBtn().click();
		
		//Alert alertBackDate = driver.switchTo().alert();
		String errorMessage = raisedPlannedWorkPage.getInavlalidDateLabel().getText();
		Assert.assertEquals(errorMessage, START_DATE_ERROR_MESSAGE, START_DATE_ERROR_MESSAGE_Label);
		raisedPlannedWorkPage.getOkBtn().click();

		raisedPlannedWorkPage.getPlannedStartDateSelector().click();
		raisedPlannedWorkPage.getZoneGMTBtn().click();
		
		
		//check if we can set date previous to current
		raisedPlannedWorkPage.moveToScrollToElement(driver, String.valueOf(currentDate)).perform();
		raisedPlannedWorkPage.getOkBtn().click();
		raisedPlannedWorkPage.getOkBtn().click();
		
		
		String errorTime = raisedPlannedWorkPage.getInavlalidDateLabel().getText();
		Assert.assertEquals(errorTime, START_DATE_ERROR_MESSAGE, START_DATE_ERROR_MESSAGE_Label);
		raisedPlannedWorkPage.getOkBtn().click();
		raisedPlannedWorkPage.getPlannedStartDateSelector().click();
		raisedPlannedWorkPage.getZoneGMTBtn().click();

		}
		

		//To take care or corner cases as calendar next button is not working so adjusting last month dates			
		if(currentDate  == 30 || currentDate == 31 || currentDate == 28 || currentDate == 29) {
			currentDate = currentDate + 0 ;
		}else {
			currentDate = currentDate + 1;
		}		
						
		raisedPlannedWorkPage.moveToScrollToElement(driver, String.valueOf(currentDate)).perform();
		raisedPlannedWorkPage.getOkBtn().click();
		
		
		//Using other keyboard with edit text to enter time
		raisedPlannedWorkPage.getDateToggleButton().click();
		
		
		
		int hourCurrent = dateMap.get(ELEMENT_HOUR_KEY).intValue();
		int hourToBeSet = hourCurrent + 2;
		System.out.println(" hourToBeSet "+ hourToBeSet);
		
		if(hourToBeSet > 12) {
			hourToBeSet = 1;
		}else if(hourToBeSet == 12) {
			hourToBeSet = 12;
		}
		
		setTimePage.getInPutHourTextField().clear();
		setTimePage.getInPutHourTextField().sendKeys(String.valueOf(hourToBeSet));
		System.out.println(" setTimePage hour "+ setTimePage.getInPutHourTextField().getText());
		
		int minutesCurrent = dateMap.get(ELEMENT_MINUTE_KEY).intValue();
		int minutesToBeSet = minutesCurrent + 40;
		
		if(minutesToBeSet > 60) {
			minutesToBeSet = 40;
		}else if(minutesToBeSet == 60) {
			minutesToBeSet = 50;
		}
		
		setTimePage.getInPutMinuteTextField().clear();
		setTimePage.getInPutMinuteTextField().sendKeys(String.valueOf(minutesToBeSet));
		
		System.out.println(" setTimePage minutes "+ setTimePage.getInPutMinuteTextField().getText());
		String currentAMPMValue = setTimePage.getCurrentAMPMValue().getText();
		setTimePage.getAmPMDropDown().click();		
		
		System.out.println("Current APPM Value is "+currentAMPMValue);
		
		if(currentAMPMValue.equals(ELEMENT_PM)) {
			setTimePage.moveToScrollToElement(driver, ELEMENT_PM);
		}else {
			setTimePage.moveToScrollToElement(driver, ELEMENT_AM);
		}
		
		setTimePage.getOkButton().click();
		String plannedStartTextField = raisedPlannedWorkPage.getPlannedStartTextField().getText();
		System.out.println("plannedStartTextField "+plannedStartTextField);
		
		
		//for end date check and try to set to previous date 
		if(currentDate-1 != 0) {
		raisedPlannedWorkPage.getPlannedEndDateSelector().click();
		
		raisedPlannedWorkPage.moveToScrollToElement(driver, String.valueOf(currentDate-1)).perform();
		raisedPlannedWorkPage.getOkBtn().click();
		raisedPlannedWorkPage.getOkBtn().click();
		//Alert alertBackDate = driver.switchTo().alert();
		String errorMessageEnd = raisedPlannedWorkPage.getErrorLabel().getText();
		Assert.assertEquals(errorMessageEnd, ERROR_MESSAGE_END_DATE, ERROR_MESSAGE_END_DATE_LABEL);
		raisedPlannedWorkPage.getOkBtn().click();
		String errorMessageEndDate = raisedPlannedWorkPage.getErrorLabel().getText();
		Assert.assertEquals(errorMessageEndDate, ERROR_MESSAGE_END_DATE, ERROR_MESSAGE_END_DATE_LABEL);
		raisedPlannedWorkPage.getOkBtn().click();
		}
		//alertBackDate.accept();
		
		raisedPlannedWorkPage.getPlannedEndDateSelector().click();				
		raisedPlannedWorkPage.moveToScrollToElement(driver, String.valueOf(currentDate)).perform();
		raisedPlannedWorkPage.getOkBtn().click();
		
		raisedPlannedWorkPage.getDateToggleButton().click();
		
		int hourCurrentEnd = dateMap.get(ELEMENT_HOUR_KEY).intValue();
		int hourToBeSetEnd = hourCurrentEnd + 2;
		System.out.println(" hourToBeSetEnd "+ hourToBeSetEnd);
		
		if(hourToBeSet > 12) {
			hourToBeSet = 1;
		}else if(hourToBeSet == 12) {
			hourToBeSet = 12;
		}
		
		setTimePage.getInPutHourTextField().clear();
		setTimePage.getInPutHourTextField().sendKeys(String.valueOf(hourToBeSet));
		System.out.println(" setTimePage hour "+ setTimePage.getInPutHourTextField().getText());
		
		int minutesCurrentEnd = dateMap.get(ELEMENT_MINUTE_KEY).intValue();
		int minutesToBeSetEnd = minutesCurrentEnd + 40;
		
		if(minutesToBeSetEnd > 60) {
			minutesToBeSetEnd = 40;
		}else if(minutesToBeSetEnd == 60) {
			minutesToBeSetEnd = 50;
		}
		
		setTimePage.getInPutMinuteTextField().clear();
		setTimePage.getInPutMinuteTextField().sendKeys(String.valueOf(minutesToBeSetEnd));
		
		System.out.println(" setTimePage minutes "+ setTimePage.getInPutMinuteTextField().getText());
		String currentAMPMValueEnd = setTimePage.getCurrentAMPMValue().getText();
		setTimePage.getAmPMDropDown().click();		
		
		System.out.println("Current APPM Value is "+currentAMPMValue);
		
		if(currentAMPMValueEnd.equals(ELEMENT_PM)) {
			setTimePage.moveToScrollToElement(driver, ELEMENT_PM);
		}else {
			setTimePage.moveToScrollToElement(driver, ELEMENT_AM);
		}
		
		setTimePage.getOkButton().click();
		
		raisedPlannedWorkPage.getNextBtn().click();
		
		//Next Page
		raisedPlannedWorkPage.getOrderNumberTextField().sendKeys(orderNumber);
		raisedPlannedWorkPage.getDeviceIdAddBtn().click();
		raisedPlannedWorkPage.getInputTextField().sendKeys(inputTextField);
		raisedPlannedWorkPage.getAddBtnForPopup().click();
		raisedPlannedWorkPage.getNextBtn().click();
		
		//Next page
		String labelOpCatTier1 = raisedPlannedWorkPage.getOpertaionalCatTier1Label().getText();
		System.out.println("labelOpCatTier1 "+labelOpCatTier1);
		
		raisedPlannedWorkPage.getOpertaionalCatTier1DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_DROP_DOWN_OPCAT_TIER1_1).perform();
		
		raisedPlannedWorkPage.getOpertaionalCatTier2DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_DROP_DOWN_OPCAT_TIER2_2).perform();
		
		raisedPlannedWorkPage.getOpertaionalCatTier3DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_DROP_DOWN_OPCAT_TIER3_1).perform();
		
		raisedPlannedWorkPage.getNextBtn().click();
		
		//Next Page
		raisedPlannedWorkPage.getProductCatTier1DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_DROP_DOWN_PRODCAT_TIER1_2).perform();
		
		raisedPlannedWorkPage.getProductCatTier2DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_DROP_DOWN_PRODCAT_TIER2_3).perform();
		
		raisedPlannedWorkPage.getProductCatTier3DropDown().click();
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_DROP_DOWN_PRODCAT_TIER3_2).perform();
		
		raisedPlannedWorkPage.getFinalSubmitButton().click();
		
		//Confirm Page elements
		raisedPlannedWorkPage.moveToScrollToElement(driver, ELEMENT_FINAL_CONFIRM_BUTTON).perform();	
		
		confirmPage.getOkButton().click();		
		confirmPage.getEnterTemplateNameTextField().sendKeys(templateName);		
		confirmPage.getOkButton().click();		
		confirmPage.getOkButtonForCongratsPopUp().click();	
		
		
		if(prop.getProperty(ELEMENT_RECORDING_NEEDED) != null) {
			if(prop.getProperty(ELEMENT_RECORDING_NEEDED).equals(ELEMENT_TRUE)) {
				TestBase.SaveRecording(driver, this.getClass().getSimpleName(),new Throwable().getStackTrace()[0].getMethodName());
			}
			
		}
		
	}

	

	@AfterTest(alwaysRun = true)
	public void tearDown() throws IOException, InterruptedException {
		System.out.println("Tearing  down AVD== " +this.getClass().getName());
		TestBase.shutDownAVD();
		Thread.sleep(3000);
		System.out.println("Tearing  down Appium== " +this.getClass().getName());
		TestBase.stopAppiumServer();
		Thread.sleep(8000);
	}

		
		
		 
	}


