package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage
{
   //Constructor
	public RegisterAccountPage(WebDriver driver)
	{
		super(driver);
	
	}
	//Locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement chxboxPolicy;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	//Action Methods
	public void enterFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	public void enterLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	public void enterEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	public void enterPhoneNumber(String pnumber)
	{
		txtTelephone.sendKeys(pnumber);
	}
	public void enterPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void enterConfirmPassword(String cpwd)
	{
		txtConfirmPassword.sendKeys(cpwd);
	}
	public void clickPolicy()
	{
		chxboxPolicy.click();
	}
	public void clickContinuebutton()
	{
		btnContinue.click();
	}
	public String getCofirmationMessage()
	{
		try
		{
			return(msgconfirmation.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}

}
