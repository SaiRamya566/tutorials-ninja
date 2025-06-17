package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	
	//Constructor-Driver is initiated

	public HomePage(WebDriver driver)
	{
		super(driver);
		
	}
	
	//Locators
	
	@FindBy(xpath="/html[1]/body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]")
	WebElement myAccountloc;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement registerloc;
	
	@FindBy(linkText="Login")
	WebElement loginloc;
	
	//ActionMethods for the locators
	
	public void clickMyAccount() 
	{		
		myAccountloc.click();
	}
	public void clickRegister()
	{
		registerloc.click();
	}
	
	public void clickLogin()
	{
		loginloc.click();
	}
	

}
