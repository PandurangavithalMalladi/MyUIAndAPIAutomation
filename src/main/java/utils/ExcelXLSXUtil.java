package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelXLSXUtil {

    // 🔹 Fully parameterized method
    public static Object[][] getTestData(String excelPath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet '" + sheetName + "' not found in Excel file"
                );
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rowCount - 1][colCount];
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] =
                            formatter.formatCellValue(
                                    sheet.getRow(i).getCell(j)
                            );
                }
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to read Excel data from: " + excelPath, e
            );
        }
    }

    // 🔹 Optional convenience overload (SAFE DEFAULT)
    public static Object[][] getTestData(String sheetName) {
        String defaultPath =
                System.getProperty("user.dir")
                + "/src/test/resources/testdata/TestData.xlsx";

        return getTestData(defaultPath, sheetName);
    }
}
