package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		//Take data from Excel that is created under testData folder using ExcelUtility class
		
		//Step:1 Provide the path of Excel
		String path="D:\\Workspace\\tutorials-ninja\\testData\\Login_TestData.xlsx";
		
		//Step:2 Create an object for ExcelUtility
		ExcelUtility exu=new ExcelUtility(path);
		
		//Step:3 Get the total row count and column count
		int totalrowcount=exu.getRowCount("sheet1");
		int totalcolcount=exu.getCellCount("sheet1", 1);
		System.out.println(" Total Row Count:"+totalrowcount);
		System.out.println(" Total Cell Count:"+totalcolcount);
		
		
		//Step:4 Extract Data from Excel and store it in 2 dimensional array
		//Note: before writing this logic we need to know how many rows and columns are available in Excel
		
		String[][] logindata=new String[totalrowcount][totalcolcount];
		for(int i=1;i<=totalrowcount;i++)// ignoring the header i.e. the reason we are passing the i value as 1
		{			
			for(int j=0;j<totalcolcount;j++)
			{
				logindata[i-1][j]=exu.getCellData("sheet1", i, j);//0,0=	
			}
		}
		return logindata;		
	}
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4

}
