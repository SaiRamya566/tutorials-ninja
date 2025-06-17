package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet worksheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	
	//Common Constructor
	public ExcelUtility(String path)//Pass the Excel path
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		worksheet = workbook.getSheet(sheetname);
		int rowcount = worksheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
		
	}
	
	public int getCellCount(String sheetname, int rownum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		worksheet = workbook.getSheet(sheetname);
		row=worksheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;		
	}
	
	public String getCellData(String sheetname, int rownum, int colnum) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		worksheet = workbook.getSheet(sheetname);
		row=worksheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try
		{
			data=formatter.formatCellValue(cell);		
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fis.close();
		return data;
	}
	public void setCellData(String sheetname,int rownum,int colnum, String data) throws IOException
	{
		File xlfile=new File(path);
		//If file doesn't exist then create a new file
		if(!xlfile.exists())
		{
			workbook=new XSSFWorkbook();
			fos=new FileOutputStream(path);
			workbook.write(fos);			
		}
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		
		//If sheet doesn't exist then create a new sheet
		if(workbook.getSheetIndex(sheetname)==-1)
		{
			workbook.createSheet(sheetname);
			worksheet=workbook.getSheet(sheetname);
		}
		//If row doesn't exist then create a new row
		if(worksheet.getRow(rownum)==null)
		{
			worksheet.createRow(rownum);
			row=worksheet.getRow(rownum);
		}
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fos=new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();		
	}
	

}
