package student_management.util.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorkbookUtil {
    public static Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    public static Workbook loadWorkbook(FileInputStream fileIn) throws IOException {
        return new XSSFWorkbook(fileIn);
    }

    public static void saveWorkbook(Workbook workbook, FileOutputStream fileOut) throws IOException {
        workbook.write(fileOut);
    }
}