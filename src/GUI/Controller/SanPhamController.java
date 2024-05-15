package GUI.Controller;

import DAO.NhanVienDao;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.SanPhamDao;
import DAO.impl.NhanVienDaoImpl;
import DAO.impl.SanPhamDaoImpl;
import Model.NhanVienModel;
import Model.SanPhamModel;
import Ultils.MyUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SanPhamController {
	private JTextField txfID;
	private JTextField txfName;
	private JTextField txfPrice;
	private JTextField txfQuantity;
	private JTextField txfFind;
	private JTable table;
	private JButton btnFind;
	private JComboBox cbFilter;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnEdit;
	private JButton btnAdd;
        private SanPhamDao dao;
        private NhanVienDao nvDao;
        private JButton btnExport;
        private int mode;

	public SanPhamController(Integer userId, JTextField txfID, JTextField txfName, JTextField txfPrice, JTextField txfQuantity,
			JTextField txfFind, JTable table, JButton btnFind, JComboBox cbFilter, JButton btnSave,
			JButton btnCancel, JButton btnEdit, JButton btnAdd, JButton btnExport) {
		this.txfID = txfID;
		this.txfName = txfName;
		this.txfPrice = txfPrice;
		this.txfQuantity = txfQuantity;
		this.txfFind = txfFind;
		this.table = table;
		this.btnFind = btnFind;
		this.cbFilter = cbFilter;
		this.btnSave = btnSave;
		this.btnCancel = btnCancel;
		this.btnEdit = btnEdit;
		this.btnAdd = btnAdd;
                this.btnExport = btnExport;
		dao = new SanPhamDaoImpl();
                nvDao = new NhanVienDaoImpl();
                NhanVienModel nhanvien = nvDao.getById(userId);
		setEvent();
		buttonChangeStats(nhanvien.getRole());
		txfID.setEditable(false);
	}
	
	public void loadData() {
		loadTable(dao.getAll());
		
		txfID.setText("");
		txfName.setText("");
		txfPrice.setText("");
		txfQuantity.setText("");
		
		loadRow();
	}

	private void setEvent() {	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadRow();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 1;
				buttonChangeStats(2);
				
				txfID.setText("");
				txfName.setText("");
				txfPrice.setText("");
				txfQuantity.setText("");
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (table.getSelectedRow() == -1) {
					MyUtils.showErrorMessage("Error", "Please select a product to edit first!");
					return;
				}
				
				mode = 2;
				buttonChangeStats(2);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonChangeStats(1);
				loadRow();
			}
		});
		
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable(find());
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txfPrice.getText().equals("") || txfQuantity.getText().equals("") || txfName.getText().equals("")) {
					MyUtils.showErrorMessage("Error", "Please fill the information properly!");
					return;
				}
				
				if (mode == 1) {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to create new product?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (input == 0) {
						if (!dao.insert(new SanPhamModel(
								txfName.getText(),
								Double.parseDouble(txfPrice.getText()),
								Integer.parseInt(txfQuantity.getText())
								))) {
							MyUtils.showErrorMessage("Error" , "Something Wrong! Please try again!");
						}
						else {
							loadTable(dao.getAll());
							buttonChangeStats(1);
							MyUtils.showInfoMessage("Info", "Update product success!");
						}
						
					}

				}else if (mode == 2) {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to update this product's information?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (input == 0) {
						if (!dao.update(new SanPhamModel(Integer.parseInt(txfID.getText()),
								txfName.getText(),
								Double.parseDouble(txfPrice.getText()),
								Integer.parseInt(txfQuantity.getText())
								))) {
							MyUtils.showErrorMessage("Error" , "Something Wrong! Please try again!");
						}
						else {
							loadTable(dao.getAll());
							buttonChangeStats(1);
							MyUtils.showInfoMessage("Info", "Update product success!");
						}
					}
				}	
			}
		});
                btnExport.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(table.getRowCount() == 0) {
                            MyUtils.showInfoMessage("Info", "No data to export!");
                        }
                        else {
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

	private void loadTable(List<SanPhamModel> list) {
		String[] labels= {"ID", "Product name ", "Price", "Quantity"};
		DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
			@Override
			public boolean isCellEditable(int row, int column) { 
				return false;
			};
		};
		try {
			for (SanPhamModel sanpham : list) {
				Object[] row = {sanpham.getId(), sanpham.getName(), String.format("%.0f", sanpham.getPrice()), sanpham.getQuantity()};
				tableModel.addRow(row);
			}
			this.table.setModel(tableModel);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			MyUtils.showErrorMessage("Error", ex.getMessage());
		}
	}
	
	private void loadRow() {
		if (btnSave.isEnabled())
			return;
		
		int row = table.getSelectedRow();
		
		if (row == -1) {
			if (table.getRowCount() > 0) {
				table.setRowSelectionInterval(0, 0);
				row = table.getSelectedRow();
			}
			else {
				return;
			}
		}
		
		txfID.setText(table.getValueAt(row, 0).toString());
		txfName.setText(table.getValueAt(row, 1).toString());
		txfPrice.setText(table.getValueAt(row, 2).toString());
		txfQuantity.setText(table.getValueAt(row, 3).toString());
	}
	
	private void buttonChangeStats(int stat) {
		if (stat == 1) {
			btnAdd.setEnabled(true);
			btnEdit.setEnabled(true);
			btnSave.setEnabled(false);
			btnCancel.setEnabled(false);
			
			txfName.setEditable(false);
			txfPrice.setEditable(false);
			txfQuantity.setEditable(false);
		}
		else {
			btnAdd.setEnabled(false);
			btnEdit.setEnabled(false);
			btnSave.setEnabled(true);
			btnCancel.setEnabled(true);
			
			txfName.setEditable(true);
			txfPrice.setEditable(true);
			txfQuantity.setEditable(true);
		}
	}
	
	private List<SanPhamModel> find() {
		String kw = txfFind.getText();
		if (kw.equals("")) {
			return dao.getAll();
		}
		
		List<SanPhamModel> list = new ArrayList<SanPhamModel>();

		if (cbFilter.getSelectedIndex() == 0) {
			SanPhamModel sanpham = dao.getById(Integer.parseInt(kw));
			if ( sanpham != null) {
				list.add(sanpham);
			}
		}
		else if (cbFilter.getSelectedIndex() == 1) {
			list = dao.getByName(kw);
		}
		return list;
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
