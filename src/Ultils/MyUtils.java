package Ultils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyUtils {

    public static void showErrorMessage(String title, String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String title, String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /*
    * value: string to validate
    * type: name of format (phone, mail,...)
     */
    public static boolean isValid(String value, String type) {

        if (value == null || type == null || type.isEmpty()) {
            return false; // Handle null or empty values
        }

        switch (type) {
            case Constant.PHONE_TYPE:
                // Simple phone number validation (US format)
                return value.matches(Regex.PHONE);
            case "email":
                // Improved email validation with stricter checks
                return value.matches(Regex.EMAIL);
            default:
                return false;
//                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    public static int calculateAge(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        LocalDate today = LocalDate.now();
        LocalDate birthday = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return today.getYear() - birthday.getYear() - ((today.getMonthValue() > birthday.getMonthValue()
                || (today.getMonthValue() == birthday.getMonthValue() && today.getDayOfMonth() >= birthday.getDayOfMonth())) ? 0 : 1);
    }
    public static void exportToExcel(TableModel model) {
        try (Workbook workbook = new XSSFWorkbook()){
                JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx");
		chooser.setFileFilter(filter);
		
                int returnVal = chooser.showSaveDialog(null);
        
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath() + ".xlsx";
                    Sheet sheet = workbook.createSheet("Sheet 1");
                    Row headerRow = sheet.createRow(0);
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        headerRow.createCell(col).setCellValue(model.getColumnName(col));
                    }
                    for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                        Row row = sheet.createRow(rowIndex + 1);
                        for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                            Object value = model.getValueAt(rowIndex, colIndex);
                            Cell cell = row.createCell(colIndex);
                            cell.setCellValue(value == null ? "" : value.toString());
                        }
                    }
                    try(FileOutputStream fileOut = new FileOutputStream(path)) {
                        workbook.write(fileOut);
                        MyUtils.showInfoMessage("Info", "Export succesfully!");
                    }
                }
        }
        catch (Exception e) {
            e.printStackTrace();
            MyUtils.showErrorMessage("Error", "Export Failed!");
        }
    }
    
    public Object[][] convertTableData(TableModel model) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for(int j = 0; i < colCount; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        return data;
    }
    
}
