package GUI.Controller;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ProfileController {
	private int userId;
	private JTextField txfID;
	private JTextField txfName;
	private JTextField txfPhone;
	private JTextField txfAddress;
	private JTextField txfEmail;
	private JDateChooser txdate;
	private JComboBox cbRole;
	private JComboBox cbGender;
	private JButton btnUpload;
	private JButton btnEdit;
	private JButton btnCancel;
	private JButton btnSave;
	private JComboBox cbStatus;
	private JPasswordField psfConfirmPw;
	private JPasswordField psfNewPw;
	private JPasswordField psfOldPw;
	private JLabel lblAvt;
	private JButton btnChangePw;
	private JButton btnCancelPw;
	private JButton btnSavePw;
        private String focusItem = "";
    private NhanVienDao dao;
    private byte[] avtImg = null;

	public ProfileController(int userId, JTextField txfID, JTextField txfName, JTextField txfPhone,
			JTextField txfAddress, JTextField txfEmail, JDateChooser txdate, JComboBox cbRole, JComboBox cbGender,
			JButton btnUpload, JButton btnEdit, JButton btnCancel, JButton btnSave, JComboBox cbStatus,
			JPasswordField psfConfirmPw, JPasswordField psfNewPw, JPasswordField psfOldPw, JLabel lblAvt,
			JButton btnChangePw, JButton btnCancelPw, JButton btnSavePw, byte[] avtImg) {
		super();
		this.userId = userId;
		this.txfID = txfID;
		this.txfName = txfName;
		this.txfPhone = txfPhone;
		this.txfAddress = txfAddress;
		this.txfEmail = txfEmail;
		this.txdate = txdate;
		this.cbRole = cbRole;
		this.cbGender = cbGender;
		this.btnUpload = btnUpload;
		this.btnEdit = btnEdit;
		this.btnCancel = btnCancel;
		this.btnSave = btnSave;
		this.cbStatus = cbStatus;
		this.psfConfirmPw = psfConfirmPw;
		this.psfNewPw = psfNewPw;
		this.psfOldPw = psfOldPw;
		this.lblAvt = lblAvt;
		this.btnChangePw = btnChangePw;
		this.btnCancelPw = btnCancelPw;
		this.btnSavePw = btnSavePw;
		this.avtImg = avtImg;
		
		txfID.setEditable(false);
		cbRole.setEnabled(false);
		enableChangePw(0);
		dao = new NhanVienDaoImpl();
		setEvent();
		loadEmployee();
		buttonChangeStats(1);
                focusInput();
                resetInputColor();
	}

	private void setEvent() {		
		btnUpload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "Images", "jpg", "gif", "png");
				chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(null);
		        
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	try {
                                    avtImg = Files.readAllBytes(Paths.get(chooser.getSelectedFile().getAbsolutePath()));
                                    lblAvt.setIcon(
                                    new ImageIcon(
                                        ImageIO.read(new ByteArrayInputStream(avtImg)).getScaledInstance(lblAvt.getWidth(), lblAvt.getHeight(), Image.SCALE_SMOOTH))
                                    );
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
		        }
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonChangeStats(2);
				enableChangePw(0);
                                resetInputColor();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadEmployee();
				buttonChangeStats(1);
                                resetInputColor();
			}
		});
		
		btnCancelPw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				psfOldPw.setText("");
				psfNewPw.setText("");
				psfConfirmPw.setText("");
				enableChangePw(0);
                                resetInputColor();
			}
		});
		
		btnChangePw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableChangePw(1);
				buttonChangeStats(1);
                                resetInputColor();
                                focusItem = "psfOldPw";
                                focusInput();
                                focusItem = "";
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                                resetInputColor();
//				if (txfName.getText().equals("") || txfPhone.getText().equals("") || txfEmail.getText().equals("")) {
//					MyUtils.showErrorMessage("Error" , "Please fill the employee information properly!");
//					return;
//				}

                                String name;
                                String phone;
                                String address;
                                String email;

                                 if (checkName().isBlank()) {
                                    name = txfName.getText().trim();
                                } else {
                                    MyUtils.addErrorMessage(checkName());

                                    focusItem = focusItem.isBlank() ? "txfName" : focusItem;
                                    txfName.setBackground(Color.pink);
                                }
                                 
                                if (checkEmail().isBlank()) {
                                    email = txfEmail.getText().trim();
                                } else {
                                    MyUtils.addErrorMessage(checkEmail());
                                    focusItem = focusItem.isBlank() ? "txfEmail" : focusItem;
                                    txfEmail.setBackground(Color.pink);
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
				
				int input = JOptionPane.showConfirmDialog(null, "Do you want to update your information?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (input == 0) {
					NhanVienModel nhanvien = dao.getById(userId);
					if(!dao.update(new NhanVienModel(Integer.parseInt(txfID.getText()),
                                        txfName.getText(),
                                        cbGender.getSelectedIndex() == 0? "Male": "Female",
                                        new Date(txdate.getDate().getTime()),// dob
                                        txfPhone.getText(),
                                        txfAddress.getText(),
                                        txfEmail.getText(),
                                        nhanvien.getPassword(),
                                        avtImg == null ? nhanvien.getAvatar() : avtImg,
                                        cbRole.getSelectedIndex()+1,
                                        cbStatus.getSelectedIndex() == 0? 1: 0
                                        ))) {
                                            MyUtils.showErrorMessage("Error" , "Something Wrong! Please try again!");
					}
					else {
                                            MyUtils.showInfoMessage("Info", "Update profile success!");
                                            buttonChangeStats(1);
					}
				}
			}	
		});
		
		btnSavePw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                                resetInputColor();
                                
                                String currentPW = "";
                                String newPW = "";
                                String confirmPW = "";
//				if (psfNewPw.getText().equals("")) {
//                                    MyUtils.showErrorMessage("Error", "Please enter new password!");
//                                    return;
//				}

                                if (checkCurrentPassword().isBlank()) {
                                    currentPW = psfOldPw.getText().trim();
                                } else {
                                    MyUtils.addErrorMessage(checkCurrentPassword());
                                    focusItem = focusItem.isBlank() ? "psfOldPw" : focusItem;
                                    psfOldPw.setBackground(Color.pink);
                                }
                                
                                 if (checkNewPassword().isBlank()) {
                                    newPW = psfNewPw.getText().trim();
                                } else {
                                    MyUtils.addErrorMessage(checkNewPassword());
                                    focusItem = focusItem.isBlank() ? "psfNewPw" : focusItem;
                                    psfNewPw.setBackground(Color.pink);
                                }
                                 
                                if (checkConfirmPW().isBlank()) {
                                    confirmPW = psfConfirmPw.getText().trim();
                                } else {
                                    MyUtils.addErrorMessage(checkConfirmPW());
                                    focusItem = focusItem.isBlank() ? "psfConfirmPw" : focusItem;
                                    psfConfirmPw.setBackground(Color.pink);
                                }
                                
                                
                                
                                if (!newPW.isBlank() && !confirmPW.isBlank() && !checkMatchNewPW().isBlank()) {
                                    MyUtils.addErrorMessage(checkMatchNewPW());
                                    focusItem = focusItem.isBlank() ? "psfNewPw" : focusItem;
                                    psfNewPw.setBackground(Color.pink);
                                    psfConfirmPw.setBackground(Color.pink);
                                }
                                
                                if (!MyUtils.isErrorListEmpty()) {
                                    MyUtils.showErrorList();
                                    focusInput();
                                    focusItem = "";
                                    return;
                                }

				if (psfNewPw.getText().equals(psfConfirmPw.getText())){
					NhanVienModel nhanvien = dao.getById(userId);
					if(nhanvien.getPassword().equals(psfOldPw.getText())) {
                                        nhanvien.setPassword(psfNewPw.getText());
                                        dao.update(nhanvien);
                                        MyUtils.showInfoMessage("Info", "Change password success!");
                                        psfOldPw.setText("");
                                        psfNewPw.setText("");
                                        psfConfirmPw.setText("");
                                        enableChangePw(0);
					}
					else {
                                            MyUtils.showErrorMessage("Error" , "Wrong Password!");
                                            focusItem = focusItem.isBlank() ? "psfOldPw" : focusItem;
                                            focusInput();
                                            focusItem = "";
					}
				}
				else {
					MyUtils.showErrorMessage("Confirm Error" , "Confirm password do not match!");
				}
			}	
		});
	}
	
	private void loadEmployee() {
		NhanVienModel nhanvien = dao.getById(userId);
		
		txfID.setText(String.valueOf(nhanvien.getId()));
		txfName.setText(nhanvien.getName());
		cbGender.setSelectedIndex(nhanvien.getGender().equals("Male") ? 0: 1);
		txdate.setDate((java.util.Date)nhanvien.getDob());
		txfPhone.setText(nhanvien.getPhone());
		txfAddress.setText(nhanvien.getAddress());
		txfEmail.setText(nhanvien.getEmail());
		cbRole.setSelectedIndex(nhanvien.getRole() - 1);
		cbStatus.setSelectedIndex(nhanvien.getStatus() == 1 ? 0: 1);

		avtImg = null;
		if (nhanvien.getAvatar().length > 0) {
                try {
                    BufferedImage avaImage = ImageIO.read(new ByteArrayInputStream(nhanvien.getAvatar()));
                    Image scaledImage = avaImage.getScaledInstance(lblAvt.getWidth(), lblAvt.getHeight(), Image.SCALE_SMOOTH);
                    lblAvt.setIcon(new ImageIcon(scaledImage));
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
		else {
			lblAvt.setIcon(null);
		}
	}
	
	private void buttonChangeStats(int stat) {
		if (stat == 1) {
			btnEdit.setEnabled(true);
			btnSave.setEnabled(false);
			btnCancel.setEnabled(false);
			btnUpload.setEnabled(false);
			
			txfName.setEditable(false);
			txfPhone.setEditable(false);
			txfAddress.setEditable(false);
			txfEmail.setEditable(false);
			txdate.setEnabled(false);
			
			cbStatus.setEnabled(false);
			cbGender.setEnabled(false);
		}
		else {
			btnEdit.setEnabled(false);
			btnSave.setEnabled(true);
			btnCancel.setEnabled(true);
			btnUpload.setEnabled(true);
			
			txfName.setEditable(true);
			txfPhone.setEditable(true);
			txfAddress.setEditable(true);
			txfEmail.setEditable(true);
			txdate.setEnabled(true);
			
			cbStatus.setEnabled(true);
			cbGender.setEnabled(true);
		}
	}
	
	public void enableChangePw(int mode) {
		if (mode == 1) {
			psfOldPw.setEditable(true);
			psfNewPw.setEditable(true);
			psfConfirmPw.setEditable(true);
			btnSavePw.setEnabled(true);
			btnCancelPw.setEnabled(true);
			btnChangePw.setEnabled(false);
		}else {
			psfOldPw.setEditable(false);
			psfNewPw.setEditable(false);
			psfConfirmPw.setEditable(false);
			btnSavePw.setEnabled(false);
			btnCancelPw.setEnabled(false);
			btnChangePw.setEnabled(true);
		}
	}
        
    private void resetInputColor() {
        txfName.setBackground(Color.white);
        txfPhone.setBackground(Color.white);
        txfAddress.setBackground(Color.white);
        txfEmail.setBackground(Color.white);
        txdate.setBackground(Color.white);
        psfOldPw.setBackground(Color.white);
        psfNewPw.setBackground(Color.white);
        psfConfirmPw.setBackground(Color.white);
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
            case "txfEmail":
                txfEmail.requestFocus();
                break;
            case "txdate":
                txdate.requestFocus();
                break;
            case "psfOldPw":
                psfOldPw.requestFocus();
                break;
            case "psfNewPw":
                psfNewPw.requestFocus();
                break;
            case "psfConfirmPw":
                psfConfirmPw.requestFocus();
                break;
            default:
                txfName.requestFocus();
        }
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

        if (dao.isEmployee(data)) {
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
    
    // return the error message
    private String checkEmail() {
        String data = txfEmail.getText().trim();
        String errorStr = "";

        if (data.equals("")) {
            return "Email is required!";
        }
        
        if(!MyUtils.isValid(txfEmail.getText(), Constant.EMAIL_TYPE)){
            return "Email is invalid!";
        }

        return errorStr;
    }
    
    // return the error message
    private String checkCurrentPassword() {
        String data = psfOldPw.getText().trim();
        String errorStr = "";

        if (data.equals("")) {
            return "Current is required!";
        }

        return errorStr;
    }
    
    // return the error message
    private String checkNewPassword() {
        String newPass = psfNewPw.getText().trim();
        String confirmPass = psfConfirmPw.getText().trim();
        String errorStr = "";

        if (newPass.equals("")) {
            return "New password is required!";
        }

        return errorStr;
    }
    
    // return the error message
    private String checkConfirmPW() {
        String newPass = psfNewPw.getText().trim();
        String confirmPass = psfConfirmPw.getText().trim();
        String errorStr = "";

        if (confirmPass.equals("")) {
            return "Confirm password is required!";
        }

        return errorStr;
    }
    
    // return the error message
    private String checkMatchNewPW() {
        String newPass = psfNewPw.getText().trim();
        String confirmPass = psfConfirmPw.getText().trim();
        String errorStr = "";

        if (!newPass.equals(confirmPass)) {
            return "New password and confirm password is not match!";
        }

        return errorStr;
    }
}
