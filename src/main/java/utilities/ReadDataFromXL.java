package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromXL {

	public Object[] getLoginData() throws IOException {
		Object[] loginData;
		File file=new File("resources/loginData.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook book=new XSSFWorkbook(fis);
		XSSFSheet sheet=book.getSheetAt(0);
		
		loginData=new Object[sheet.getLastRowNum()+1];
		
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			Cell cell=sheet.getRow(i).getCell(0);
			loginData[i]=cell.getStringCellValue();
			
		}
		return loginData;
	}
	
}
