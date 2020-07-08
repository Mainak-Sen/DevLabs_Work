package learnTestNG;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Driving_Excel_data {
    @DataProvider(name="get_data")
	public static Object[][] read_data() {
		// TODO Auto-generated method stub
		//get the workbook
		XSSFWorkbook wb = null;
		DataFormatter dft=new DataFormatter();
		try {
			wb= new XSSFWorkbook("./Data/Role_data.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		}
		//get the sheet 
		XSSFSheet sheet =wb.getSheet("Role_sheet");
		//get the row-count 
		int rows=sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		//System.out.println(rows);
		//get the column count
		short columns=(short)(sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum());
		Object[][] obj_arr=new Object[rows][columns];
		//System.out.println(columns);
		
		//iterate through rows and columns to get the cell values and print them.
		int k=0;
		for(int i=1;i<=rows;i++)
		{
			for(short j=0;j<columns;j++)
			{
				String cell_value= dft.formatCellValue(sheet.getRow(i).getCell(j));
				obj_arr[k][j]=cell_value;
			}
			//System.out.println();
			k++;
		}
		
        return obj_arr;
		//using iterator concept 
	     
		/*int sheet_count=wb.getNumberOfSheets();
		for(int i=0;i<sheet_count;i++)
		{
			if(wb.getSheetName(i).equals("Role_sheet"))
			{
				Iterator<Row> ri=wb.getSheetAt(i).iterator();
				ri.next();//pointing cursor to the first row i.e header so that we can skip them in next iteration
			    while(ri.hasNext())
			    {
				Row r=ri.next();
				Iterator<Cell> ci=r.cellIterator();
				while(ci.hasNext())
				{
					System.out.print(dft.formatCellValue(ci.next())+" ");
				}
				System.out.println();
			}
			   
		}
		}*/
		
		

	}

}
