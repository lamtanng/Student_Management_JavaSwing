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
import java.awt.Color;

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
    private int mode;
    private String focusItem = "";

    public SanPhamController(Integer userId, JTextField txfID, JTextField txfName, JTextField txfPrice, JTextField txfQuantity,
            JTextField txfFind, JTable table, JButton btnFind, JComboBox cbFilter, JButton btnSave,
            JButton btnCancel, JButton btnEdit, JButton btnAdd) {
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
        dao = new SanPhamDaoImpl();
        nvDao = new NhanVienDaoImpl();
        NhanVienModel nhanvien = nvDao.getById(userId);
        setEvent();
        buttonChangeStats(nhanvien.getRole());
        resetInputColor();
        txfID.setEditable(false);
        focusInput();
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
                resetInputColor();
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

                resetInputColor();

                Double price;
                int quantity;
                String name;

//                if (txfPrice.getText().trim().equals("") || txfQuantity.getText().trim().equals("") || txfName.getText().trim().equals("")) {
//                    MyUtils.showErrorMessage("Error", "Please fill the information properly!");
//                    return;
//                }
                if (checkName().isBlank()) {
                    name = txfName.getText().trim();
                } else {
                    MyUtils.addErrorMessage(checkName());

                    focusItem = focusItem.isBlank() ? "txfName" : focusItem;
                    txfName.setBackground(Color.pink);
                }

                if (checkPrice().isBlank()) {
                    price = Double.valueOf(txfPrice.getText().trim());
                } else {
                    MyUtils.addErrorMessage(checkPrice());

                    focusItem = focusItem.isBlank() ? "txfPrice" : focusItem;
                    txfPrice.setBackground(Color.pink);
                }

                if (checkQuantity().isBlank()) {
                    quantity = Integer.parseInt(txfQuantity.getText().trim());
                } else {
                    MyUtils.addErrorMessage(checkQuantity());
                    focusItem = focusItem.isBlank() ? "txfQuantity" : focusItem;
                    txfQuantity.setBackground(Color.pink);
                }

                if (!MyUtils.isErrorListEmpty()) {
                    MyUtils.showErrorList();
                    focusInput();
                    focusItem = "";
                    return;
                }

                if (mode == 1) {
                    int input = JOptionPane.showConfirmDialog(null, "Do you want to create new product?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (input == 0) {
                        if (!dao.insert(new SanPhamModel(
                                txfName.getText().trim(),
                                Double.parseDouble(txfPrice.getText().trim()),
                                Integer.parseInt(txfQuantity.getText().trim())
                        ))) {
                            MyUtils.showErrorMessage("Error", "Something Wrong! Please try again!");
                        } else {
                            loadTable(dao.getAll());
                            buttonChangeStats(1);
                            MyUtils.showInfoMessage("Info", "Update product success!");
                        }

                    }

                } else if (mode == 2) {
                    int input = JOptionPane.showConfirmDialog(null, "Do you want to update this product's information?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (input == 0) {
                        if (!dao.update(new SanPhamModel(Integer.parseInt(txfID.getText().trim()),
                                txfName.getText().trim(),
                                Double.parseDouble(txfPrice.getText().trim()),
                                Integer.parseInt(txfQuantity.getText().trim())
                        ))) {
                            MyUtils.showErrorMessage("Error", "Something Wrong! Please try again!");
                        } else {
                            loadTable(dao.getAll());
                            buttonChangeStats(1);
                            MyUtils.showInfoMessage("Info", "Update product success!");
                        }
                    }
                }
            }
        });
    }

    private void loadTable(List<SanPhamModel> list) {
        String[] labels = {"ID", "Product name ", "Price", "Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
		try {
            for (SanPhamModel sanpham : list) {
                Object[] row = {sanpham.getId(), sanpham.getName(), String.format("%.0f", sanpham.getPrice()), sanpham.getQuantity()};
                tableModel.addRow(row);
            }
            this.table.setModel(tableModel);
        } catch (Exception ex) {
            ex.printStackTrace();
            MyUtils.showErrorMessage("Error", ex.getMessage());
        }
    }

    private void loadRow() {
        if (btnSave.isEnabled()) {
            return;
        }

        int row = table.getSelectedRow();

        if (row == -1) {
            if (table.getRowCount() > 0) {
                table.setRowSelectionInterval(0, 0);
                row = table.getSelectedRow();
            } else {
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
        } else {
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
        String kw = txfFind.getText().trim();
        if (kw.equals("")) {
            return dao.getAll();
        }

        List<SanPhamModel> list = new ArrayList<SanPhamModel>();

        if (cbFilter.getSelectedIndex() == 0) {
            SanPhamModel sanpham;
            try {
                sanpham = dao.getById(Integer.parseInt(kw));
            } catch (NumberFormatException err) {
                MyUtils.showErrorMessage("Error", "ID must be number!");
                return dao.getAll();
            }
            if (sanpham != null) {
                list.add(sanpham);
            }
        } else if (cbFilter.getSelectedIndex() == 1) {
            list = dao.getByName(kw);
        }

        if (list.size() == 0) {
            MyUtils.showInfoMessage("Info", "0 result found!");
        }
        return list;
    }

    // return the error message
    private String checkPrice() {
        Double price = null;
        String errorStr = "";

        if (txfPrice.getText().trim().equals("")) {
            return "Price is required!";
        }

        try {
            price = Double.valueOf(txfPrice.getText().trim());

            if (price < 0) {
                return "Price must be greater than or equal 0";
            }
        } catch (NumberFormatException err) {
            return "Price must be a float!";
        }

        return errorStr;
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
                return "Quantity must be greater than or equal 0";
            }
        } catch (NumberFormatException err) {
            return "Quantity must be a float!";
        }

        return errorStr;
    }

    // return the error message
    private String checkName() {
        String name = "";
        String errorStr = "";

        if (txfName.getText().trim().equals("")) {
            return "Name is required!";
        }

        return errorStr;
    }

    private void resetInputColor() {
        txfName.setBackground(Color.white);
        txfPrice.setBackground(Color.white);
        txfQuantity.setBackground(Color.white);
    }

    private void focusInput() {
        switch (focusItem) {
            case "":
            case "txfName":
                txfName.requestFocus();
                break;
            case "txfPrice":
                txfPrice.requestFocus();
                break;
            case "txfQuantity":
                txfQuantity.requestFocus();
                break;
            default:
                txfName.requestFocus();
        }
    }
}
