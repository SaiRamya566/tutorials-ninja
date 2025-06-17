package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
		public ExtentSparkReporter sparkreporter;// This class helps us to design an UI for creating a report
		public ExtentReports extentreports;// This class helps us to populate the common info such who is the reporter, on which browser and on which operating system the tc is getting executed
		public ExtentTest test;//This class helps us to 
		String repName;
		
		public void onStart(ITestContext testContext)
		{
			/*
			 * SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=new
			 * Date(); String currentdatetimestamp=df.format(dt);
			 */
			
			
			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report-" + timestamp + ".html";
			
			
			sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);// To generate a report and store it we need to provide a location
			sparkreporter.config().setDocumentTitle("Tutorials Ninja Automation Report"); // We are specifying the Title of the Report
			sparkreporter.config().setReportName("Tutorials Ninja Automation");
			sparkreporter.config().setTheme(Theme.STANDARD); 
			
			extentreports = new ExtentReports();
			extentreports.attachReporter(sparkreporter);
			extentreports.setSystemInfo("Application", "Tutorials Ninja");
			extentreports.setSystemInfo("Module", "Admin");
			extentreports.setSystemInfo("Sub Module", "Customers");
			extentreports.setSystemInfo("User Name", System.getProperty("user.name"));
			extentreports.setSystemInfo("Environment", "QA");
			
			//Dynamically getting the os details from <Parameters> from testng.xml file
			String os = testContext.getCurrentXmlTest().getParameter("os");
			extentreports.setSystemInfo("Operating System",os);
			
			//Dynamically getting the browser details from <Parameters> from testng.xml file
			String browser = testContext.getCurrentXmlTest().getParameter("browser");
			extentreports.setSystemInfo("Browser", browser);
			
			//Dynamically getting the groups details from <Parameters> from testng.xml file
			List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty())
			{
				extentreports.setSystemInfo("Groups",includedGroups.toString());
			}	   
		}

		public void onTestSuccess(ITestResult result) 
		{
			
			test = extentreports.createTest(result.getTestClass().getName());//This will create a new Entry in the Report// from result we will get which class got exectued and the Name of class
			test.assignCategory(result.getMethod().getGroups());//display groups in report		
			test.log(Status.PASS, result.getName()+"got successfully executed");//update the status
			
	    }
		public void onTestFailure(ITestResult result)
		{
			test = extentreports.createTest(result.getName());//This will create a new Entry in the Report
			test.assignCategory(result.getMethod().getGroups());		
			test.log(Status.FAIL, result.getName()+"got failed");//update the status
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			//Screenshot of the failure- attach the screenshot for failure
			try
			{
				String imgPath=new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);		
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			
		}
		public void onTestSkipped(ITestResult result)
		{
			test = extentreports.createTest(result.getName());//This will create a new Entry in the Report
			test.assignCategory(result.getMethod().getGroups());//from result it is getting the groups		
			test.log(Status.SKIP, result.getName()+"got skipped");//update the status
			test.log(Status.INFO, result.getThrowable().getMessage());
			
		}
		public void onFinish(ITestContext context) 
		{
			extentreports.flush();
			
			//Open the Report Automatically
			String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport = new File(pathOfExtentReport);
			try
			{
				Desktop.getDesktop().browse(extentReport.toURI());//This will automatically open the report on the browser automatically
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//Code to send the emails automatically
			/*
			 * try { URL url=new
			 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			 * //Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
			 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 * email.setHostName("smtp.googlemail.com"); email.setSmtpPort(465);
			 * email.setAuthenticator(new
			 * DefaultAuthenticator("ramyasaiachari@gmail.com","BeautifulLife@566"));
			 * email.setSSLOnConnect(true);
			 * email.setFrom("ramyasaiachari@gmail.com");//sender
			 * email.setSubject("Test Results");
			 * email.setMsg("Please find the attached report...");
			 * email.addTo("ramyasaiachari@gmail.com");//receiver
			 * email.attach(url,"extent report","please check report..."); email.send(); }
			 * catch(Exception e) { e.printStackTrace(); }
			 */
			
			
		}


}
