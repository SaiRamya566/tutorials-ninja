package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("*** TC002_LoginTest Execution started ***");
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("email"));
			lp.setPassword(prop.getProperty("pwd"));
			lp.clicklogin();
			
			MyAccountPage macp = new MyAccountPage(driver);
			boolean target_page=macp.isMyAccountPageExist();
			
		    Assert.assertEquals(target_page, true, "LoginFailed");
		    		    
		}
		catch(Exception e)
		{
			Assert.fail();
			logger.info("*** TC002_LoginTest Execution Failed ***");
		}
		logger.info("*** TC002_LoginTest Execution Completed ***");
		
	}
	
}
