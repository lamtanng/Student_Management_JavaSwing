/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.impl.KhachHangDaoImpl;
import DAO.impl.SanPhamDaoImpl;
import Model.KhachHangModel;
import Model.SanPhamModel;
import Ultils.Constant;
import Ultils.MyUtils;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class formImportData extends javax.swing.JFrame {

    KhachHangDaoImpl cusDao = new KhachHangDaoImpl();
    SanPhamDaoImpl productDao = new SanPhamDaoImpl();
    Object[] colsHeader = null;
    String IMPORT_TYPE = "";

    JFileChooser excelFileChooser;
    int excelChooser;
    XSSFWorkbook excelImportToJTable;
    FileInputStream excelFIS = null;
    BufferedInputStream excelBIS = null;
    XSSFSheet excelSheet;
    File excelFile;

    public formImportData(Object[] colsHeader, String typeImport) {
        initComponents();
        this.IMPORT_TYPE = typeImport;
        this.colsHeader = colsHeader;

        btn_Delete.setEnabled(false);
        tbl_data.setModel(new javax.swing.table.DefaultTableModel(
                null,
                colsHeader
        )
        );

        tbl_data.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_data.getSelectedRow();
                if (selectedRow >= 0) {
                    btn_Delete.setEnabled(true);
                } else {
                    btn_Delete.setEnabled(false);
                }

                if (tbl_data.getRowCount() > 0) {
                    btn_Save.setEnabled(true);
                } else {
                    btn_Save.setEnabled(false);
                }
            }
        });

        readExcelData();
    }

    private JFileChooser getDataFile() {
        String defaultCurrentDirectoryPath = "C:\\Users";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        return excelFileChooser;
    }

    private XSSFWorkbook bindData() throws FileNotFoundException, IOException {
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;

        excelFile = excelFileChooser.getSelectedFile();
        //jExcelFilePath.setText(excelFile.toString());
        excelFIS = new FileInputStream(excelFile);
        excelBIS = new BufferedInputStream(excelFIS);
        excelImportToJTable = new XSSFWorkbook(excelBIS);

        return excelImportToJTable;
    }

    public void readExcelData() {
        DefaultTableModel model = (DefaultTableModel) tbl_data.getModel();

        excelFileChooser = getDataFile();

        excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelImportToJTable = bindData();
                XSSFSheet excelSheetTemp = excelImportToJTable.getSheetAt(0);
                excelSheet = excelSheetTemp;

                //customer import
                switch (IMPORT_TYPE) {
                    case Constant.CUSTOMER_IMPORT:
                        for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
                            XSSFRow excelRow = excelSheet.getRow(row);
                            XSSFCell customerName = excelRow.getCell(0);
                            XSSFCell gender = excelRow.getCell(1);
                            XSSFCell dateOfBirth = excelRow.getCell(2);
                            XSSFCell phone = excelRow.getCell(3);
                            XSSFCell address = excelRow.getCell(4);
                            model.addRow(new Object[]{customerName, gender, dateOfBirth.toString(), phone, address});
                        }
                        break;
                    case Constant.PRODUCT_IMPORT:
                        for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
                            XSSFRow excelRow = excelSheet.getRow(row);
                            String name = excelRow.getCell(0).toString().trim();
                            double price = Double.valueOf(excelRow.getCell(1).getNumericCellValue());
                            int quantity = (int) excelRow.getCell(2).getNumericCellValue();
                            model.addRow(new Object[]{name, price, quantity});
                        }
                        break;
                    default:
                        throw new AssertionError();
                }

                JOptionPane.showMessageDialog(null, "Imported Successfully !");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }

    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) tbl_data.getModel();
        mod.addRow(row);
    }

    public void createColumns(Object[] colsHeader, Object[][] dataTable) {
        tbl_data.setModel(new javax.swing.table.DefaultTableModel(
                dataTable,
                colsHeader
        )
        );
    }

    // return the error message
    private String checkPrice(String priceInput) {
        Double price = null;
        String errorStr = "";

        if (priceInput.trim().equals("")) {
            return "Price is required!";
        }

        try {
            price = Double.valueOf(priceInput.trim());

            if (price < 0) {
                return "Price must be greater than or equal 0";
            }
        } catch (NumberFormatException err) {
            return "Price must be a float!";
        }

        return errorStr;
    }

    // return the error message
    private String checkQuantity(String quantityInput) {
        Integer quantity = null;
        String errorStr = "";

        if (quantityInput.trim().equals("")) {
            return "Quantity is required!";
        }

        try {
            quantity = Integer.valueOf(quantityInput.trim());

            if (quantity <= 0) {
                return "Quantity must be greater than or equal 0";
            }
        } catch (NumberFormatException err) {
            return "Quantity must be a float!";
        }

        return errorStr;
    }

    // return the error message
    private String checkName(String name) {
        String errorStr = "";

        if (name.trim().equals("")) {
            return "Name is required!";
        }

        return errorStr;
    }

    private void resetInputColor() {

    }

    private void focusInput() {
//        switch (focusItem) {
//            case "":
//            case "txfName":
//                txfName.requestFocus();
//                break;
//            case "txfPrice":
//                txfPrice.requestFocus();
//                break;
//            case "txfQuantity":
//                txfQuantity.requestFocus();
//                break;
//            default:
//                txfName.requestFocus();
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_data = new javax.swing.JTable();
        btn_Save = new javax.swing.JButton();
        btn_ReChoose = new javax.swing.JButton();
        btn_Cancel = new javax.swing.JButton();
        btn_AddNew = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_data);

        btn_Save.setText("Save");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_ReChoose.setText("File");
        btn_ReChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReChooseActionPerformed(evt);
            }
        });

        btn_Cancel.setText("Cancel");
        btn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelActionPerformed(evt);
            }
        });

        btn_AddNew.setText("Add new");
        btn_AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddNewActionPerformed(evt);
            }
        });

        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ReChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_AddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ReChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_AddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed

        int confirmSave = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmSave == JOptionPane.YES_OPTION) {
            try {
                switch (IMPORT_TYPE) {
                    case Constant.CUSTOMER_IMPORT:
                        for (int i = 0; i < tbl_data.getRowCount(); i++) {
                            String name = tbl_data.getValueAt(i, 0).toString().trim();
                            String gender = tbl_data.getValueAt(i, 1).toString().trim();
                            Date dOB = Date.valueOf(tbl_data.getValueAt(i, 2).toString().trim());
                            String phone = tbl_data.getValueAt(i, 3).toString().trim();
                            String address = tbl_data.getValueAt(i, 4).toString().trim();

                            KhachHangModel model = new KhachHangModel(name, gender, dOB, phone, address);

                            //validate...
                            cusDao.insert(model);
                        }
                        MyUtils.showInfoMessage("Success", "Success");
                        break;
                    case Constant.PRODUCT_IMPORT:
                        for (int i = tbl_data.getRowCount() - 1; i >= 0; i--) {
                            String name = tbl_data.getValueAt(i, 0).toString().trim();
                            double price = Double.parseDouble(String.valueOf(tbl_data.getValueAt(i, 1)).trim());
                            int quantity = Integer.parseInt(String.valueOf(tbl_data.getValueAt(i, 2)).trim());

                            //validate...
                            if (checkName(name).isBlank()) {
                                name = name.trim();
                            } else {
                                MyUtils.addErrorMessage(checkName(name));

                                //focusItem = focusItem.isBlank() ? "txfName" : focusItem;
                                //txfName.setBackground(Color.pink);
                            }

                            if (checkPrice(String.valueOf(price)).isBlank()) {
                                price = price;
                            } else {
                                MyUtils.addErrorMessage(checkPrice(String.valueOf(price)));

                                //focusItem = focusItem.isBlank() ? "txfPrice" : focusItem;
                                //txfPrice.setBackground(Color.pink);
                            }

                            if (checkQuantity(String.valueOf(quantity)).isBlank()) {
                                quantity = quantity;
                            } else {
                                MyUtils.addErrorMessage(checkQuantity(String.valueOf(quantity)));
//                            focusItem = focusItem.isBlank() ? "txfQuantity" : focusItem;
//                            txfQuantity.setBackground(Color.pink);
                            }

                            if (!MyUtils.isErrorListEmpty()) {
                                MyUtils.showErrorList();
                                focusInput();
//                            focusItem = "";
                                return;
                            }
                            SanPhamModel model = new SanPhamModel(name, price, quantity);
                            productDao.insert(model);
                            DefaultTableModel modelTable = (DefaultTableModel) tbl_data.getModel();
                            modelTable.removeRow(i);
                        }
                        MyUtils.showInfoMessage("Success", "Success");
                        break;
                    default:
                        MyUtils.showErrorMessage("Error", "Fail !");
                }
            } catch (NumberFormatException e) {
                MyUtils.showErrorMessage("Error", e.getMessage());
            }

        }

    }//GEN-LAST:event_btn_SaveActionPerformed

    private void btn_ReChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReChooseActionPerformed
        readExcelData();
    }//GEN-LAST:event_btn_ReChooseActionPerformed

    private void btn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_CancelActionPerformed

    private void btn_AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddNewActionPerformed
        addRow(null);

    }//GEN-LAST:event_btn_AddNewActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // Lấy chỉ số của dòng được chọn
        int selectedRow = tbl_data.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbl_data.getModel();

        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            btn_Delete.setEnabled(false);
        }
//        tbl_data.changeSelection(0, 1, false, false);
//        tbl_data.requestFocus();
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        FileOutputStream out;
        try {

            DefaultTableModel model = (DefaultTableModel) tbl_data.getModel();
            int rowCount = model.getRowCount();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // Cập nhật dữ liệu vào file Excel
            for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
                Row excelRow = sheet.createRow(row);
                Cell cell1;
                Cell cell2;
                Cell cell3;
                if (row <= rowCount) {
                    cell1 = excelRow.createCell(0);
                    cell1.setCellValue("");
                    System.out.println(">> " + cell1.getStringCellValue());
                    cell2 = excelRow.createCell(1);
                    cell2.setCellValue(0);

                    cell3 = excelRow.createCell(2);
                    cell3.setCellValue(0);
                } else if (row < rowCount) {
                    cell1 = excelRow.createCell(0);
                    cell1.setCellValue((String) model.getValueAt(row, 0));
                    System.out.println(">> " + cell1.getStringCellValue());
                    cell2 = excelRow.createCell(1);
                    cell2.setCellValue((Double) model.getValueAt(row, 1));

                    cell3 = excelRow.createCell(2);
                    cell3.setCellValue((Integer) model.getValueAt(row, 2));
                }
            }

            // Cập nhật dữ liệu vào file Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row);

                Cell cell1 = excelRow.createCell(0);
                cell1.setCellValue((String) model.getValueAt(row, 0));
                System.out.println(">> " + cell1.getStringCellValue());
                Cell cell2 = excelRow.createCell(1);
                cell2.setCellValue((Double) model.getValueAt(row, 1));

                Cell cell3 = excelRow.createCell(2);
                cell3.setCellValue((Integer) model.getValueAt(row, 2));
            }

            // Lưu file Excel
            FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(formImportData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(formImportData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_UpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AddNew;
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_ReChoose;
    private javax.swing.JButton btn_Save;
    private javax.swing.JButton btn_Update;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_data;
    // End of variables declaration//GEN-END:variables
}
