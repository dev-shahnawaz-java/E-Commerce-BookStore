package com.nareshit.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nareshit.entity.BooksExcelFile;

public class Helper {

	public static boolean checkExcelFile(MultipartFile file) {

		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	}

	public static List<BooksExcelFile> excelFileInsertDatabase(InputStream io) throws IOException {

		// Create empty list to store final book data
		List<BooksExcelFile> list = new ArrayList<>();

		// Use Set to store unique product names(avoid duplicates)
		Set<String> uniqueProducts = new HashSet<>();

		// Create workbook object from input stream
		XSSFWorkbook work = new XSSFWorkbook(io);

		// Get first sheet from workbook (sheet 0)
		XSSFSheet sheetAt = work.getSheetAt(0);

		// create roe iterator to loop through Excel rows
		Iterator<Row> iteratorRows = sheetAt.iterator();

		// skip the header row (usually first row)

		if (iteratorRows.hasNext()) {
			iteratorRows.next();
		}

		// Loop through remaining rows (actual data)

		while (iteratorRows.hasNext()) {
			// Get the current row
			Row iteratorRow = iteratorRows.next();

			// get all cells in the row
			Iterator<Cell> cells = iteratorRow.iterator();

			// Create a new Book object to hold current row data
			BooksExcelFile excelFile = new BooksExcelFile();

			// Loop through each cell in the current row
			while (cells.hasNext()) {

				// Get current cell
				Cell cell = cells.next();

				// Decide based on column index
				switch (cell.getColumnIndex()) {

				// column 0:Product name
				case 0:
					excelFile.setProductName(cell.getStringCellValue());
					break;

				// Column 1: Description
				case 1:
					excelFile.setDescription(cell.getStringCellValue());
					break;

				// Column 2: Price (numeric value)
				case 2:
					excelFile.setPrice(cell.getNumericCellValue());
					break;

				// For any other column: Do nothing
				default:
					break;
				}

				// only add the book if the product name is unique
				if (uniqueProducts.add(excelFile.getProductName())) {
					list.add(excelFile);
				}

			}
		}
		// return the final list of book objects
		return list;

	}
}
