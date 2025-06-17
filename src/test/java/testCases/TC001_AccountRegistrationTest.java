package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		try
		{
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("*** Clicked on my account link ***");
		
			hp.clickRegister();
			logger.info("*** Clicked on Registration Link ***");
		
			RegisterAccountPage rap=new RegisterAccountPage(driver);
			rap.enterFirstName(randomeString().toUpperCase());
			rap.enterLastName(randomeString().toUpperCase());
			rap.enterEmail(randomeString().toUpperCase()+"@gmail.com");	
			rap.enterPhoneNumber(randomeNumber());
			String password=randomeAlphaNumeric();
			rap.enterPassword(password);
			rap.enterConfirmPassword(password);
			rap.clickPolicy();
			rap.clickContinuebutton();
			String getconfmessage=rap.getCofirmationMessage();
			if(getconfmessage.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			
			}
			else
			{
				logger.error("***TC001_Account Registration Test has failed");
				logger.debug("***Debug Logs***");
				Assert.fail();
			}			
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***TC001_Account Registration Test completed***");
	}	
	
}
