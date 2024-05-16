package GUI.Controller;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.NhanVienDao;
import DAO.impl.NhanVienDaoImpl;
import Model.NhanVienModel;
import Ultils.Constant;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Ultils.MyUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhanVienController {

    private JTextField txfID;
    private JTextField txfName;
    private JTextField txfPhone;
    private JTextField txfAddress;
    private JTextField txfEmail;
    private JTextField txfPasswd;
    private JTextField txfFind;
    private JDateChooser txdate;
    private JTable table;
    private JComboBox cbRole;
    private JComboBox cbGender;
    private JLabel lblAvt;
    private JButton btnUpload;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnCancel;
    private JButton btnSave;
    private JComboBox cbFilter;
    private JButton btnFind;
    private JComboBox cbStatus;
    private NhanVienDao nhanVienDao;
    private int mode;
    private byte[] avtImg = null;

    public NhanVienController(JTextField txfID, JTextField txfName, JTextField txfPhone, JTextField txfAddress,
            JTextField txfEmail, JTextField txfPasswd, JTextField txfFind, JDateChooser txdate, JTable table,
            JComboBox cbRole, JComboBox cbGender, JLabel lblAvt, JButton btnUpload, JButton btnAdd, JButton btnEdit,
            JButton btnCancel, JButton btnSave, JComboBox cbFilter, JButton btnFind, JComboBox cbStatus) {
        this.txfID = txfID;
        this.txfName = txfName;
        this.txfPhone = txfPhone;
        this.txfAddress = txfAddress;
        this.txfEmail = txfEmail;
        this.txfPasswd = txfPasswd;
        this.txfFind = txfFind;
        this.txdate = txdate;
        this.table = table;
        this.cbRole = cbRole;
        this.cbGender = cbGender;
        this.lblAvt = lblAvt;
        this.btnUpload = btnUpload;
        this.btnAdd = btnAdd;
        this.btnEdit = btnEdit;
        this.btnCancel = btnCancel;
        this.btnSave = btnSave;
        this.cbFilter = cbFilter;
        this.btnFind = btnFind;
        this.cbStatus = cbStatus;
        nhanVienDao = new NhanVienDaoImpl();
        setEvent();
        buttonChangeStats(1);
        txfID.setEditable(false);
    }

    public void loadData() {
        loadTable(nhanVienDao.getAll());

        txfID.setText("");
        txfName.setText("");
        txfPhone.setText("");
        txfAddress.setText("");
        txfEmail.setText("");
        txfPasswd.setText("");
        txfFind.setText("");
        txfID.setText("");
        txdate.setDate(null);

        lblAvt.setIcon(null);

        loadRow();
    }

    private void setEvent() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadRow();
            }
        });

        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Images", "jpg", "gif", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        avtImg = Files.readAllBytes(Paths.get(chooser.getSelectedFile().getAbsolutePath()));
                        lblAvt.setIcon(
                                new ImageIcon(
                                        ImageIO.read(
                                                new ByteArrayInputStream(avtImg)).getScaledInstance(lblAvt.getWidth(), lblAvt.getHeight(), Image.SCALE_SMOOTH)
                                )
                        );
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = 1;
                buttonChangeStats(2);
                txfID.setText("");
                txfPhone.setText("");
                txfAddress.setText("");
                txfEmail.setText("");
                txfPasswd.setText("");
                txfName.setText("");
                txdate.setDate(null);
                lblAvt.setIcon(null);;
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    MyUtils.showErrorMessage("Error", "Please select an employee to edit first!");
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
                    if (mode == 1) {
                        if (!isValidEmployee(1)) return;
                        int input = JOptionPane.showConfirmDialog(null, "Do you want to create new employee?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (input == 0) {
                            if (!nhanVienDao.insert(new NhanVienModel(
                                    txfName.getText(),
                                    cbGender.getSelectedIndex() == 0 ? "Male" : "Female",
                                    txdate.getDate() == null ? null : new Date(txdate.getDate().getTime()),
                                    txfPhone.getText(),
                                    txfAddress.getText(),
                                    txfEmail.getText(),
                                    txfPasswd.getText(),
                                    avtImg == null ? new byte[0] : avtImg,
                                    cbRole.getSelectedIndex() + 1,
                                    cbStatus.getSelectedIndex() == 0 ? 1 : 0
                            ))) {
                                MyUtils.showErrorMessage("Error", "Something Wrong! Please try again!");
                            } else {
                                MyUtils.showInfoMessage("Info", "Create employee success!");
                                loadTable(nhanVienDao.getAll());
                                buttonChangeStats(1);
                            }
                        }

                    } else if (mode == 2) {
                        if (!isValidEmployee(2)) return;
                        int input = JOptionPane.showConfirmDialog(null, "Do you want to update this employee's information?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (input == 0) {
                            if (!nhanVienDao.update(new NhanVienModel(Integer.parseInt(txfID.getText()),
                                    txfName.getText(),
                                    cbGender.getSelectedIndex() == 0 ? "Male" : "Female",
                                    txdate.getDate() == null ? null : new Date(txdate.getDate().getTime()),
                                    txfPhone.getText(),
                                    txfAddress.getText(),
                                    txfEmail.getText(),
                                    txfPasswd.getText(),
                                    avtImg == null ? new byte[0] : avtImg,
                                    cbRole.getSelectedIndex() + 1,
                                    cbStatus.getSelectedIndex() == 0 ? 1 : 0
                            ))) {
                                MyUtils.showErrorMessage("Error", "Something Wrong! Please try again!");
                            } else {
                                MyUtils.showInfoMessage("Info", "Update employee success!");
                                loadTable(nhanVienDao.getAll());
                                buttonChangeStats(1);
                            }
                        }
                    }
            }
        });
    }

    private void loadTable(List<NhanVienModel> list) {
        String[] labels = {"ID", "Employee name", "Gender", "Birthday", "Phone", "Address", "Email", "Role", "Password", "Status", "avt"};
        DefaultTableModel tableModel = new DefaultTableModel(labels, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
		
		try {
            for (NhanVienModel nhanvien : list) {
                Object[] row = {nhanvien.getId(), nhanvien.getName(), nhanvien.getGender(), nhanvien.getDob(),
                    nhanvien.getPhone(), nhanvien.getAddress(), nhanvien.getEmail(), nhanvien.getRole() == 1 ? "Manager" : "Employee",
                    nhanvien.getPassword(), nhanvien.getStatus() == 1 ? "Enabled" : "Disabled", nhanvien.getAvatar()};
                tableModel.addRow(row);
            }
            this.table.setModel(tableModel);
            this.table.removeColumn(this.table.getColumnModel().getColumn(10));
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

        txfID.setText(table.getValueAt(row, 0).toString());
        txfName.setText(table.getValueAt(row, 1).toString());
        cbGender.setSelectedIndex(table.getValueAt(row, 2).equals("Male") ? 0 : 1);
        txdate.setDate((java.util.Date) table.getValueAt(row, 3));
        txfPhone.setText(table.getValueAt(row, 4).toString());
        txfAddress.setText(table.getValueAt(row, 5).toString());
        txfEmail.setText(table.getValueAt(row, 6).toString());
        cbRole.setSelectedIndex(table.getValueAt(row, 7).equals("Manager") ? 0 : 1);
        txfPasswd.setText(table.getValueAt(row, 8).toString());
        cbStatus.setSelectedIndex(table.getValueAt(row, 9).equals("Enabled") ? 0 : 1);

        avtImg = null;
        if (((byte[]) table.getModel().getValueAt(row, 10)).length > 0) {
            try {
                lblAvt.setIcon(
                        new ImageIcon(
                                ImageIO.read(
                                        new ByteArrayInputStream((byte[]) table.getModel().getValueAt(row, 10))).getScaledInstance(lblAvt.getWidth(), lblAvt.getHeight(), Image.SCALE_SMOOTH)
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblAvt.setIcon(null);
        }
    }

    private void buttonChangeStats(int stat) {
        if (stat == 1) {
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(true);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
            btnUpload.setEnabled(false);

            txfName.setEditable(false);
            txfPhone.setEditable(false);
            txfAddress.setEditable(false);
            txfEmail.setEditable(false);
            txfPasswd.setEditable(false);
            txdate.setEnabled(false);

            cbStatus.setEnabled(false);
            cbRole.setEnabled(false);
            cbGender.setEnabled(false);
        } else {
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(false);
            btnSave.setEnabled(true);
            btnCancel.setEnabled(true);
            btnUpload.setEnabled(true);

            txfName.setEditable(true);
            txfPhone.setEditable(true);
            txfAddress.setEditable(true);
            txfEmail.setEditable(true);
            txfPasswd.setEditable(true);
            txdate.setEnabled(true);

            cbStatus.setEnabled(true);
            cbRole.setEnabled(true);
            cbGender.setEnabled(true);
        }
    }

    private List<NhanVienModel> find() {
        String kw = txfFind.getText().trim();
        if (kw.equals("")) {
            return nhanVienDao.getAll();
        }
        List<NhanVienModel> list = new ArrayList<NhanVienModel>();
        System.out.print(cbFilter.getSelectedIndex());
        if (cbFilter.getSelectedIndex() == 0) {
            NhanVienModel nhanvien;
            try {
                nhanvien = nhanVienDao.getById(Integer.parseInt(kw));
            } catch (NumberFormatException err) {
                MyUtils.showErrorMessage("Error", "ID must be number!");
                return nhanVienDao.getAll();
            }
            if (nhanvien != null) {
                list.add(nhanvien);
            }
        } else if (cbFilter.getSelectedIndex() == 1) {
            list = nhanVienDao.getByName(kw);
        }
        if (list.size() == 0) {
            MyUtils.showInfoMessage("Info", "0 result found!");
        }
        return list;
    }

    private boolean isValidEmployee(int mode) {

        //name
        if (!MyUtils.isValid(txfName.getText(), Constant.TEXT_TYPE)) {
            MyUtils.showErrorMessage("Error", "Name is invalid " + txfName.getText());
            return false;
        }

        //email
        if (!MyUtils.isValid(txfEmail.getText(), Constant.EMAIL_TYPE)) {
            MyUtils.showErrorMessage("Error", "Email is invalid");
            return false;
        }

        //phone
        if (!MyUtils.isValid(txfPhone.getText(), Constant.PHONE_TYPE)) {
            MyUtils.showErrorMessage("Error", "Phone is invalid");
            return false;
        }
        
        if (nhanVienDao.isEmployee(txfPhone.getText().trim() ) && mode ==1) {
            MyUtils.showErrorMessage("Error", "Employee is already exist");
            return false;
        }

        //pass
        if (txfPasswd.getText().trim() == null || txfPasswd.getText().trim().isBlank()) {
            MyUtils.showErrorMessage("Error", "Empty password !");
            return false;
        }

        //address
        if (txfAddress.getText().trim() == null || txfAddress.getText().trim().isBlank()) {
            MyUtils.showErrorMessage("Error", "Empty address !");
            return false;
        }

        //age
        if (txdate.getDate() == null || MyUtils.calculateAge(txdate.getDate()) < 15) {
            MyUtils.showErrorMessage("Error", "Age must be greater than 15");
            return false;
        }
        return true;
    }
}
