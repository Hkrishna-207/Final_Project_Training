package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToXL {
	
	static File file;
	static FileOutputStream fos;
	static XSSFWorkbook book;
	static XSSFSheet sheet;
	static Row row;
	static Cell cell;
	
	public static void writeHondaData(Object[][] data) throws IOException {
		file=new File("resultdata/HondaBikesList.xlsx");
		fos=new FileOutputStream(file);
		book=new XSSFWorkbook();
		sheet=book.createSheet("Honda_Bikes_Data");
		for(int i=0;i<data.length;i++) {
			row=sheet.createRow(i);
			for(int j=0;j<data[i].length;j++) {
				cell=row.createCell(j);
				cell.setCellValue((String)data[i][j]);
			}
		}
		book.write(fos);
	}

	public static void writeCarsData(Object[][] data) throws IOException {
		file=new File("resultdata/UsedCarsList.xlsx");
		fos=new FileOutputStream(file);
		book=new XSSFWorkbook();
		sheet=book.createSheet("Used_Cars_Data");
		
		for(int i=0;i<data.length;i++) {
			row=sheet.createRow(i);
			for(int j=0;j<data[i].length;j++) {
				cell=row.createCell(j);
				cell.setCellValue((String)data[i][j]);
			}
		}
		book.write(fos);
	}
}
