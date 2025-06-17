package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginTesting_DDT extends BaseClass
{
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class) // Getting Data Provider from Different																				// class
	public void verify_login_ddt(String username, String pwd, String expresult) throws InterruptedException
	{
		logger.info("***Execution started for TC002_LoginTestingDDT***");
		try
		{
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(username);
			lp.setPassword(pwd);
			lp.clicklogin();

			// MyAccountPage
			MyAccountPage mac = new MyAccountPage(driver);
			boolean targetpage = mac.isMyAccountPageExist();

			// Valid Data: Login Success-testpass-logout from application
			// login unsuccessfull-stay on application
			// Invalid data: Login success - test fail - logout
			// Login unsuccessful - test pass - stay on the page
			
			if (expresult.equalsIgnoreCase("valid"))
			{
				if (targetpage == true) 
				{
					mac.clickLogout();
					Assert.assertTrue(true);

				} 
				else
				{
					Assert.assertTrue(false);
				}

			}
			if (expresult.equalsIgnoreCase("invalid")) 
			{
				if (targetpage == true) 
				{
					mac.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		} 
		catch (Exception e) 
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("***Execution Ended for TC002_LoginTestingDDT***");
	}
}
