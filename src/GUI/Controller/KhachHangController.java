package GUI.Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.KhachHangDao;
import DAO.impl.KhachHangDaoImpl;
import Model.KhachHangModel;
import Ultils.Constant;
import Ultils.MyUtils;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class KhachHangController {

    private JTextField txfName;
    private JTextField txfPhone;
    private JTextField txfAddress;
    private JTextField txfFind;
    private JDateChooser txdate;
    private JTextField txfId;
    private JComboBox cbGender;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnCancel;
    private JButton btnSave;
    private JComboBox cbFilter;
    private JButton btnFind;
    private JTable table;
    private KhachHangDao dao;
    private int mode;
    String focusItem = "";

    public KhachHangController(JTextField txfName, JTextField txfPhone, JTextField txfAddress, JTextField txfFind,
            JDateChooser txdate, JTextField txfId, JComboBox cbGender, JButton btnAdd, JButton btnEdit,
            JButton btnCancel, JButton btnSave, JComboBox cbFilter, JButton btnFind, JTable table) {
        this.txfName = txfName;
        this.txfPhone = txfPhone;
        this.txfAddress = txfAddress;
        this.txfFind = txfFind;
        this.txdate = txdate;
        this.txfId = txfId;
        this.cbGender = cbGender;
        this.btnAdd = btnAdd;
        this.btnEdit = btnEdit;
        this.btnCancel = btnCancel;
        this.btnSave = btnSave;
        this.cbFilter = cbFilter;
        this.btnFind = btnFind;
        this.table = table;
        dao = new KhachHangDaoImpl();
        setEvent();
        buttonChangeStats(1);
        txfId.setEditable(false);
        focusInput();
    }

    public void loadData() {
        loadTable(dao.getAll());

        txfId.setText("");
        txfName.setText("");
        txfPhone.setText("");
        txfAddress.setText("");
        txdate.setDate(null);

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
                resetInputColor();
                buttonChangeStats(2);

                txfName.setText("");
                txfPhone.setText("");
                txfAddress.setText("");
                txfId.setText("");
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    MyUtils.showErrorMessage("Error", "Please select a customer to edit first!");
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
                
//                if (txfName.getText().equals("") || txfPhone.getText().equals("") || txfAddress.getText().equals("")) {
//                    MyUtils.showErrorMessage("Error", "Please fill the customer information properly!");
//                    return;
//                }

                String name;
                String phone;
                String address;
                
                 if (checkName().isBlank()) {
                    name = txfName.getText().trim();
                } else {
                    MyUtils.addErrorMessage(checkName());

                    focusItem = focusItem.isBlank() ? "txfName" : focusItem;
                    txfName.setBackground(Color.pink);
                }

                if (checkPhone().isBlank()) {
                    phone = txfPhone.getText().trim();
                } else {
                    MyUtils.addErrorMessage(checkPhone());

                    focusItem = focusItem.isBlank() ? "txfPhone" : focusItem;
                    txfPhone.setBackground(Color.pink);
                }

                if (checkAddress().isBlank()) {
                    name = txfAddress.getText().trim();
                } else {
                    MyUtils.addErrorMessage(checkAddress());
                    focusItem = focusItem.isBlank() ? "txfAddress" : focusItem;
                    txfAddress.setBackground(Color.pink);
                }

                if (!MyUtils.isErrorListEmpty()) {
                    MyUtils.showErrorList();
                    focusInput();
                    focusItem = "";
                    return;
                }

                if (mode == 1) {
                    int input = JOptionPane.showConfirmDialog(null, "Do you want to create new customer?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (input == 0) {
                        if (!dao.insert(new KhachHangModel(
                                txfName.getText(),
                                cbGender.getSelectedIndex() == 0 ? "Male" : "Female",
                                txdate.getDate() == null ? null : new Date(txdate.getDate().getTime()),
                                txfPhone.getText(),
                                txfAddress.getText()
                        ))) {
                            MyUtils.showErrorMessage("Error", "Something Wrong! Please try again!");
                        } else {
                            loadTable(dao.getAll());
                            buttonChangeStats(1);
                            MyUtils.showInfoMessage("Info", "Create customer success!");
                        }
                    }

                } else if (mode == 2) {
                    int input = JOptionPane.showConfirmDialog(null, "Do you want to update this customer infomation?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (input == 0) {
                        if (!dao.update(new KhachHangModel(Integer.parseInt(txfId.getText()),
                                txfName.getText(),
                                cbGender.getSelectedIndex() == 0 ? "Male" : "Female",
                                txdate.getDate() == null ? null : new Date(txdate.getDate().getTime()),
                                txfPhone.getText(),
                                txfAddress.getText()
                        ))) {
                            MyUtils.showErrorMessage("Error", "Something Wrong! Please try again!");
                        } else {
                            loadTable(dao.getAll());
                            buttonChangeStats(1);
                            MyUtils.showInfoMessage("Info", "Update customer success!");
                        }
                    }
                }
            }
        });
    }

    private void loadTable(List<KhachHangModel> list) {
        String[] labels = {"ID", "Customer name", "Gener", "Birthday", "Phone", "Address"};
        DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
		
		try {
            for (KhachHangModel khachhang : list) {
                Object[] row = {khachhang.getId(), khachhang.getName(), khachhang.getGender(), khachhang.getDob(),
                    khachhang.getPhone(), khachhang.getAddress()};
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
            }
        }

        txfId.setText(table.getValueAt(row, 0).toString());
        txfName.setText(table.getValueAt(row, 1).toString());
        cbGender.setSelectedIndex(table.getValueAt(row, 2).equals("Male") ? 0 : 1);
        cbGender.setForeground(Color.black);
        txdate.setDate((java.util.Date) table.getValueAt(row, 3));
        txfPhone.setText(table.getValueAt(row, 4).toString());
        txfAddress.setText(table.getValueAt(row, 5).toString());
    }

    private void buttonChangeStats(int stat) {
        if (stat == 1) {
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(true);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);

            txfName.setEditable(false);
            txfPhone.setEditable(false);
            txfAddress.setEditable(false);

            cbGender.setEnabled(false);
            txdate.setEnabled(false);
        } else {
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(false);
            btnSave.setEnabled(true);
            btnCancel.setEnabled(true);

            txfName.setEditable(true);
            txfPhone.setEditable(true);
            txfAddress.setEditable(true);

            cbGender.setEnabled(true);
            txdate.setEnabled(true);
        }
    }

    private List<KhachHangModel> find() {
        String kw = txfFind.getText().trim();
        if (kw.equals("")) {
            return dao.getAll();
        }

        List<KhachHangModel> list = new ArrayList<KhachHangModel>();

        if (cbFilter.getSelectedIndex() == 0) {
            KhachHangModel khachhang;
            try {
                khachhang = dao.getById(Integer.parseInt(kw));
            } catch (NumberFormatException err) {
                MyUtils.showErrorMessage("Error", "ID must be number!");
                return dao.getAll();
            }
            if (khachhang != null) {
                list.add(khachhang);
            }
        } else if (cbFilter.getSelectedIndex()
                == 1) {
            list = dao.getByName(kw);
        }

        if (list.size() == 0) {
            MyUtils.showInfoMessage("Info", "0 result found!");
        }

        return list;
    }

    // return the error message
    private String checkName() {
        String data = txfName.getText().trim();
        String errorStr = "";

        if (data.equals("")) {
            return "Name is required!";
        }

        if (!MyUtils.isValid(data, Constant.TEXT_TYPE)) {
            return "Name cannot contain number!";
        }

        return errorStr;
    }

    // return the error message
    private String checkPhone() {
        String data = txfPhone.getText().trim();
        String errorStr = "";

        if (data.equals("")) {
            return "Phone is required!";
        }

        if (!MyUtils.isValid(data, Constant.PHONE_TYPE)) {
            return "Phone is invalid!";
        }

        if (dao.isExist(data)) {
            return "Phone is already exist!";
        }

        return errorStr;
    }

    // return the error message
    private String checkAddress() {
        String data = txfAddress.getText().trim();
        String errorStr = "";

        if (data.equals("")) {
            return "Address is required!";
        }

        return errorStr;
    }

    private void resetInputColor() {
        txfName.setBackground(Color.white);
        txfPhone.setBackground(Color.white);
        txfAddress.setBackground(Color.white);
    }

    private void focusInput() {
        switch (focusItem) {
            case "":
            case "txfName":
                txfName.requestFocus();
                break;
            case "txfPhone":
                txfPhone.requestFocus();
                break;
            case "txfAddress":
                txfAddress.requestFocus();
                break;
            default:
                txfName.requestFocus();
        }
    }

}
