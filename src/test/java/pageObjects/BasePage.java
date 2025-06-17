package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage
{
	//Resuable component of Base Page
		WebDriver driver;
		public BasePage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}


}
