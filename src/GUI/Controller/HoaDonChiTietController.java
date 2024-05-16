package GUI.Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.HoaDonChiTietDao;
import DAO.HoaDonDao;
import DAO.KhachHangDao;
import DAO.SanPhamDao;
import DAO.impl.HoaDonChiTietDaoImpl;
import DAO.impl.HoaDonDaoImpl;
import DAO.impl.KhachHangDaoImpl;
import DAO.impl.SanPhamDaoImpl;
import GUI.formBillPrinter;
import Model.HoaDonChiTietModel;
import Model.HoaDonModel;
import Model.KhachHangModel;
import Model.SanPhamModel;
import Ultils.MyUtils;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class HoaDonChiTietController {
	private Integer userId;
	private JTextField txfPro;
	private JTextField txfPrice;
	private JTextField txfQuantity;
	private JTextField txfCus;
	private JTextField txfPhone;
	private JTextField txfAddress;
	private JTextField txfTotal;
	private JTable table;
	private JComboBox cbCus;
	private JComboBox cbPro;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnPay;
	private JButton btnDel;
	private SanPhamDao pDao;
	private KhachHangDao cDao;
	private HoaDonDao bDao;
	private HoaDonChiTietDao bdDao;
        private String focusItem= "";
    
        private JButton btnExport;
	private boolean isLoading = true;
    
	public HoaDonChiTietController(Integer userId, JTextField txfPro, JTextField txfPrice, JTextField txfQuantity, JTextField txfCus,
			JTextField txfPhone, JTextField txfAddress, JTextField txfTotal, JTable table, JComboBox cbCus,
			JComboBox cbPro, JButton btnAdd, JButton btnCancel, JButton btnPay, JButton btnDel, JButton btnExport) {
		this.userId = userId;
		this.txfPro = txfPro;
		this.txfPrice = txfPrice;
		this.txfQuantity = txfQuantity;
		this.txfCus = txfCus;
		this.txfPhone = txfPhone;
		this.txfAddress = txfAddress;
		this.txfTotal = txfTotal;
		this.table = table;
		this.cbCus = cbCus;
		this.cbPro = cbPro;
		this.btnAdd = btnAdd;
		this.btnCancel = btnCancel;
		this.btnPay = btnPay;
		this.btnDel = btnDel;
                this.btnExport = btnExport;
		pDao = new SanPhamDaoImpl();
		cDao = new KhachHangDaoImpl();
		bDao = new HoaDonDaoImpl();
		bdDao = new HoaDonChiTietDaoImpl();
		setEvent();
		
		buttonChangeStats(1);
		txfPrice.setEditable(false);
		txfPhone.setEditable(false);
		txfAddress.setEditable(false);
		txfTotal.setEditable(false);
                resetInputColor();
                focusInput();
	}
	
	public void loadData() {
		txfQuantity.setText("");
		txfTotal.setText("0");
		clearTable();
		loadCmbCustomer();
		loadCmbProduct();
	}

	private void setEvent() {
		cbCus.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent arg0) {
                        if (isLoading)
                                return;
                        loadCustomerInfo();
                    }
		});
		
		cbPro.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent arg0) {
                        if (isLoading)
                                return;
                        loadProductInfo();
                    }
		});
		
		table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                            btnDel.setEnabled(true);
                    }
		});
		
		btnDel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            int row = table.getSelectedRow();
                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                            tableModel.removeRow(row);
                            table.setModel(tableModel);
                            showTotalPrice();
                            
                            isLoading = true;
                            cbPro.removeAllItems();
                            List<SanPhamModel> list = pDao.getAll();
                            for (SanPhamModel sanpham : list) {
                                boolean isExistAndMax = false;
                                for (int i =0; i< tableModel.getRowCount(); i++) {
                                    if(tableModel.getValueAt(i, 0).equals(sanpham.getId())){
                                        if ((int) tableModel.getValueAt(i, 3) >= sanpham.getQuantity() || sanpham.getQuantity() <= 0){
                                            isExistAndMax = true;
                                        }
                                    }
                                }
                                if(!isExistAndMax){
                                    cbPro.addItem(sanpham);
                                }
                            }
                            cbPro.setSelectedIndex(-1);
                            loadProductInfo();
                            isLoading = false;
                    }
		});
		
		btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            loadData();
                    }
		});
		
		btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            resetInputColor();
                            addToTable();
                    }
		});
		
		btnPay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            createBill();
                    }
		});
                btnExport.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(table.getRowCount() == 0) {
                                MyUtils.showInfoMessage("Info", "No data to export!");
                       }
                       else{
                            String[] colNames = {"ID", "Name", "Price", "Quantity"};
                            Object[][] data = convertTableData(table);
                            DefaultTableModel model = new DefaultTableModel();
                            for(String colName : colNames) {
                                model.addColumn(colName);
                            }
                            for (Object[] row : data) {
                                model.addRow(row);
                            }
                            MyUtils.exportToExcel(model);
                       }
                      
                   }
               });
    }

	private void loadCmbCustomer() {
            isLoading = true;
            cbCus.removeAllItems();
            List<KhachHangModel> list = cDao.getAll();
            for (KhachHangModel khachhang : list) {
                    cbCus.addItem(khachhang);
            }
            txfPhone.setText("");
            txfAddress.setText("");
            cbCus.setSelectedIndex(-1);
            isLoading = false;
	}
	
	private void loadCmbProduct() {
            isLoading = true;
            cbPro.removeAllItems();
            List<SanPhamModel> list = pDao.getAll();
            for (SanPhamModel sanpham : list) {
                if (sanpham.getQuantity() > 0) {
                    cbPro.addItem(sanpham);
                }
            }
            txfPrice.setText("");
            cbPro.setSelectedIndex(-1);
            isLoading = false;
	}
	
	private void loadCustomerInfo() {
            KhachHangModel khachhang = (KhachHangModel)cbCus.getSelectedItem();
            txfPhone.setText(khachhang.getPhone());
            txfAddress.setText(khachhang.getAddress());
	}
	
	private void loadProductInfo() {
            SanPhamModel sanpham = (SanPhamModel)cbPro.getSelectedItem();
            if(sanpham == null)   {
                txfPrice.setText("");
            } else{
                txfPrice.setText(String.format("%.0f", sanpham.getPrice()));
            }
	}
	
	private void addToTable() {
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();

            if (cbPro.getSelectedItem() == null) {
                    MyUtils.showErrorMessage("Error", "Please choose product first!");
                    return;
            }

            try {
                SanPhamModel sanpham = ((SanPhamModel)cbPro.getSelectedItem());
                String name = sanpham.getName();
                int pID = sanpham.getId();
                
                int quantity = Integer.parseInt(txfQuantity.getText());
                if (quantity < 1) {
                        txfQuantity.setBackground(Color.pink);
                        focusItem = "txfQuantity";
                        focusInput();
                        MyUtils.showErrorMessage("Error", "Invalid quantity!");
                        return;
                }

                boolean exist = false;
                int currentInBill = 0 ;
                for (int i =0; i< tableModel.getRowCount(); i++) {
                    if(tableModel.getValueAt(i, 0).equals(pID)) {
                        int oldValue = (int) tableModel.getValueAt(i, 3);
                        currentInBill = oldValue;
                        
                        if (quantity > sanpham.getQuantity() - oldValue) {
                            txfQuantity.setBackground(Color.pink);
                            focusItem = "txfQuantity";
                            focusInput();
                            MyUtils.showErrorMessage("Error", "Not enough quantity! Maximum quantity is " + String.valueOf(sanpham.getQuantity() - oldValue));
                            return;
                        }
                          
                        int choose = JOptionPane.showConfirmDialog(null, "The product has been selected. Do you want to update its quantity?", "Information", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (choose == 0) {
                                tableModel.setValueAt(oldValue + quantity, i, 3);
                                currentInBill += quantity;
                        }
                        exist = true;
                    }
                }
                if(!exist) {
                    if (quantity > sanpham.getQuantity()) {
                        txfQuantity.setBackground(Color.pink);
                        focusItem = "txfQuantity";
                        focusInput();
                        MyUtils.showErrorMessage("Error", "Not enough quantity! Maximum quantity is " + String.valueOf(sanpham.getQuantity()));
                        return;
                    }
                    
                    Object[] row = {((SanPhamModel)cbPro.getSelectedItem()).getId(), name, txfPrice.getText(), quantity};
                    currentInBill = quantity;
                    tableModel.addRow(row);
                }
                
                // remove item that has quantity <= quantity current in bill
                if(currentInBill >= sanpham.getQuantity()){
                    isLoading = true;
                    cbPro.removeAllItems();
                    List<SanPhamModel> list = pDao.getAll();
                    for (SanPhamModel item : list) {
                        if (item.getQuantity() > 0 && sanpham.getId() != item.getId()) {
                            cbPro.addItem(item);
                        }
                    }
                    isLoading = false;
                    cbPro.setSelectedItem(null);
                    loadProductInfo();
                }
                
                this.table.setModel(tableModel);
                txfQuantity.setText("");
                showTotalPrice();
            }
            catch (NumberFormatException numex) {
                 txfQuantity.setBackground(Color.pink);
                 focusItem = "txfQuantity";
                 focusInput();
                 MyUtils.showErrorMessage("Error", "Input quantity is invalid!");
            }
            catch(Exception ex) {
                    ex.printStackTrace();
                    MyUtils.showErrorMessage("Error", ex.getMessage());
            }
	}
	
	private void showTotalPrice() {
            try {
                DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
                double total = 0;
                for (int i =0; i< tableModel.getRowCount(); i++) {
                    total += Double.parseDouble(tableModel.getValueAt(i, 2).toString()) 
                            * Double.parseDouble(tableModel.getValueAt(i, 3).toString());
                }

                txfTotal.setText(String.valueOf(String.format("%.0f", total)));
            }catch(Exception ex) {
                ex.printStackTrace();
                MyUtils.showErrorMessage("Error", ex.getMessage());
            }
		
	}
	
	private void createBill() {
            if (cbCus.getSelectedItem() == null) {
                
                MyUtils.showErrorMessage("Error", "Please choose customer first!");
                return;
            }

            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();

            if (tableModel.getRowCount() == 0) {
                
                MyUtils.showErrorMessage("Error", "At least one product is required to create bill!");
                return;
            }

            try {
                int id = bDao.insert(new HoaDonModel(new Date(new java.util.Date().getTime()),
                                Double.parseDouble(txfTotal.getText()),
                                userId,
                                ((KhachHangModel)cbCus.getSelectedItem()).getId()
                                ));
                if (id == -1) {
                        MyUtils.showErrorMessage("Error", "Something went wrong, please try again!");
                        return;
                }
                for (int i =0; i< tableModel.getRowCount(); i++) {
                        int idSp = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
                        double price = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
                        int quantity = Integer.parseInt(tableModel.getValueAt(i, 3).toString());
                        bdDao.insert(new HoaDonChiTietModel(id, idSp, price, quantity));
                        pDao.changeQuantity(idSp, quantity);
                }
                loadCmbProduct();
                clearTable();
                txfQuantity.setText("");
                txfTotal.setText("0");
                //MyUtils.showInfoMessage("Information", "Create bill successfully!");
                
                formBillPrinter f = new formBillPrinter(id);
                f.setVisible(true);
            }
            catch(Exception ex) {
                ex.printStackTrace();
                MyUtils.showErrorMessage("Error", ex.getMessage());
            }
	}
	
	private void buttonChangeStats(int stat) {
            if (stat == 1) {
                btnAdd.setEnabled(true);
                btnDel.setEnabled(false);
            }
	}
	
	public void clearTable() {
            table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Product name", "Price", "Quantity"}) {
                @Override
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
            });
	}
        
        private void resetInputColor() {
            txfQuantity.setBackground(Color.white);
        }

    private void focusInput() {
        switch (focusItem) {
            case "":
            case "cbCus":
                cbCus.requestFocus();
                break;
            case "txfQuantity":
                txfQuantity.requestFocus();
                break;
            default:
                cbCus.requestFocus();
        }
    }
    
    // return the error message
    private String checkQuantity() {
        Integer quantity = null;
        String errorStr = "";

        if (txfQuantity.getText().trim().equals("")) {
            return "Quantity is required!";
        }

        try {
            quantity = Integer.valueOf(txfQuantity.getText().trim());

            if (quantity <= 0) {
                return "Quantity must be greater than ";
            }
        } catch (NumberFormatException err) {
            return "Quantity must be a float!";
        }

        return errorStr;
    }
        private Object[][] convertTableData(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int colCount = model.getColumnCount();
            Object[][] data = new Object[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i][j] = model.getValueAt(i, j); 
                }
            }
            return data;
        }
}
