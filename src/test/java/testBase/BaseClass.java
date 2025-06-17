package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
//Step:2 Import the Log4j Packages
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger;//Step:1 Create an object of Logger
	public Properties prop;//To call Properties file
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException
	{
		//Logging Information
		logger = LogManager.getLogger(this.getClass());//Step:3 load the log4j2.xml from resources
		
		//Loading config.properties file
		try 
		{
		   FileReader file=new FileReader("D://Workspace//tutorials-ninja//src//test//resources//config.properties");
		   prop = new Properties();
		   prop.load(file);
					
		} 
		catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
		
		
		//To Execute the project remotely or locally, below is the code to set the operating system
		//OS from xml
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities dcp=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				dcp.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				dcp.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			//browser from testng.xml file
			switch(br.toLowerCase())
			{
				case "chrome": dcp.setBrowserName("chrome");
				break;
				case "edge":dcp.setBrowserName("MicrosoftEdge");
				break;
				default: System.out.println("No Browser");
				return;
			}
			//This will automatically trigger the driver
			driver = new RemoteWebDriver(new URL("http://192.168.1.8.4444/WD/hub"),dcp);
		}
		
		//If the execution is local
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			//Choosing the Browser based on the input provided from testng.xml
			switch(br.toLowerCase())
			{
				case "chrome":driver = new ChromeDriver();
				break;
				case "edge" : driver = new EdgeDriver();
				break;
				case "firefox" : driver = new FirefoxDriver();
				break;
				default : System.out.println("Invalid Browser name");
				return;
			}
			
		}
		
		
		//Loading Info
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Accessing the app URL from Config Properties file
		driver.get(prop.getProperty("appURL"));
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	
	//Reusable Methods
	public String randomeString()
	{		
		 RandomStringGenerator rsg=new RandomStringGenerator.Builder().withinRange('a', 'z').get();
		 String randomString = rsg.generate(10);		 
		 return randomString;		
	}
	public String randomeNumber()
	{
		RandomStringGenerator numericGenerator=new RandomStringGenerator.Builder().withinRange('0', '9').get();
		String randomNumber=numericGenerator.generate(10);
		return randomNumber;
		
	}
	public String randomeAlphaNumeric()
	{
		RandomStringGenerator alphanumericGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(Character::isLetterOrDigit).get();
		String randomAlphaNumeric=alphanumericGenerator.generate(12);
		return randomAlphaNumeric;
	}
	//Screenshot Method
	public String captureScreen(String tname)
	{
		//Step:1-Giving the timestamp to differentiate the screenshots
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot tss=(TakesScreenshot)driver;
		File sourcefile = tss.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
		File targetFile = new File(targetFilePath);
		sourcefile.renameTo(targetFile);
		return targetFilePath;
		
	}



}
