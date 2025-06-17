package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver)
	{
		super(driver);		
	}
	
	//Locators
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgheading;
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccountLoc;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btnlogout;
	
	//Action Methods
	public boolean isMyAccountPageExist()
	{
		try
		{
			return msgheading.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
			
	}
	public void clickLogout()
	{
		btnlogout.click();
	}
	

}
