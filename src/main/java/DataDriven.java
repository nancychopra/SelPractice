

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven
{

    @Test(dataProvider = "getData")
    public void testExcel(String greeting,String name,String id)
    {
        System.out.println(greeting + " " + id + " " + name + " ,how are you?");

    }

    @DataProvider
    public Object[][] getData() throws IOException {

            FileInputStream fis=new FileInputStream(System.getProperty("user.dir") +"/test1.xlsx");
            XSSFWorkbook workbook=new XSSFWorkbook(fis);
            XSSFSheet sheet=workbook.getSheetAt(0);
            int rows=sheet.getPhysicalNumberOfRows();
            XSSFRow row=sheet.getRow(0);
            int colCount=row.getLastCellNum();
            Object data[][] = new Object[rows-1][colCount];
            DataFormatter formatter = new DataFormatter();
            for (int i = 0; i <rows-1 ; i++)
            {
                row= sheet.getRow(i+1);
                for (int j = 0; j <colCount ; j++)
                {
                  XSSFCell cell= row.getCell(j);
                  data[i][j]= formatter.formatCellValue(cell);
                }
            }
           return data;
    }


}
