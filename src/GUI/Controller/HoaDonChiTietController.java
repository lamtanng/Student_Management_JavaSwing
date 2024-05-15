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
import Model.HoaDonChiTietModel;
import Model.HoaDonModel;
import Model.KhachHangModel;
import Model.SanPhamModel;
import Ultils.MyUtils;

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
    
	private boolean isLoading = true;
    
	public HoaDonChiTietController(Integer userId, JTextField txfPro, JTextField txfPrice, JTextField txfQuantity, JTextField txfCus,
			JTextField txfPhone, JTextField txfAddress, JTextField txfTotal, JTable table, JComboBox cbCus,
			JComboBox cbPro, JButton btnAdd, JButton btnCancel, JButton btnPay, JButton btnDel) {
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
                    }
		});
		
		btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            clearTable();
                    }
		});
		
		btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            addToTable();
                    }
		});
		
		btnPay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            createBill();
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
            System.out.println("======>list" + list);
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
            txfPrice.setText(String.format("%.0f", sanpham.getPrice()));
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
                int quantity = Integer.parseInt(txfQuantity.getText());
                if (quantity < 1) {
                        MyUtils.showErrorMessage("Error", "Invalid quantity!");
                        return;
                }
                if (quantity > sanpham.getQuantity()) {
                        MyUtils.showErrorMessage("Error", "Not enough quantity! Maximum quantity is " + String.valueOf(sanpham.getQuantity()));
                        return;
                }
                boolean exist = false;
                for (int i =0; i< tableModel.getRowCount(); i++) {
                    if(tableModel.getValueAt(i, 1).equals(name)) {
                        int choose = JOptionPane.showConfirmDialog(null, "The product has been selected. Do you want to update its quantity?", "Information", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (choose == 0) {
                                tableModel.setValueAt(quantity, i, 3);
                        }
                        exist = true;
                    }
                }
                if(!exist) {
                    Object[] row = {((SanPhamModel)cbPro.getSelectedItem()).getId(), name, txfPrice.getText(), quantity};
                    tableModel.addRow(row);
                }
                this.table.setModel(tableModel);
                txfQuantity.setText("");
                showTotalPrice();
            }
            catch (NumberFormatException numex) {
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
                        bdDao.insert(new HoaDonChiTietModel(id,idSp, price, quantity));
                        pDao.changeQuantity(idSp, quantity);
                }
                loadCmbProduct();
                clearTable();
                txfQuantity.setText("");
                txfTotal.setText("0");
                MyUtils.showInfoMessage("Information", "Create bill successfully!");
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
}
