package GUI.Controller;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.NhanVienDao;
import DAO.impl.NhanVienDaoImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import GUI.formDangNhap;
import GUI.formNhanVien;
import GUI.formQuanLy;
import Model.NhanVienModel;
import Ultils.MyUtils;
import java.awt.event.WindowAdapter;

public class LoginController {
	private formDangNhap form;
	private JTextField txfUsername;
	private JPasswordField passwordField;
	private JRadioButton rdbtnEmployee;
	private JRadioButton rdbtnManage;
	private JButton btnLogIn;
    private NhanVienDao dao;

    public LoginController(formDangNhap form, JTextField txfUsername, JPasswordField passwordField, JRadioButton rdbtnEmployee,
			JRadioButton rdbtnManage, JButton btnLogIn) {
    	this.form = form;
		this.txfUsername = txfUsername;
		this.passwordField = passwordField;
		this.rdbtnEmployee = rdbtnEmployee;
		this.rdbtnManage = rdbtnManage;
		this.btnLogIn = btnLogIn;
		setEvent();
		dao = new NhanVienDaoImpl();
	}

	public void setEvent() {	
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
    			try {
    				NhanVienModel nhanvien = dao.login(txfUsername.getText(), passwordField.getText());
    				if(nhanvien != null) {
    					if(nhanvien.getStatus() == 1) {
	    					if (rdbtnManage.isSelected() && nhanvien.getRole() == 1) {
//	    						new formQuanLy(nhanvien.getId()).addWindowListener(new java.awt.event.WindowAdapter() {
//	    					        @Override
//	    					        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//	    					        	form.setVisible(true);
//	    					        }
//	    					    });
                                                        formQuanLy fQuanLy = new formQuanLy(nhanvien.getId());
                                                        fQuanLy.setVisible(true);
                                                    
	    						form.setVisible(false);
	    						txfUsername.setText("");
	    						passwordField.setText("");
	    					} else if (rdbtnEmployee.isSelected() && nhanvien.getRole() == 2) {
//	    						new formNhanVien(nhanvien.getId()).addWindowListener(new java.awt.event.WindowAdapter() {
//	    					        @Override
//	    					        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//	    					        	form.setVisible(true);
//	    					        }
//	    					    });
                                                        formNhanVien fNhanVien = new formNhanVien(nhanvien.getId());
                                                        fNhanVien.setVisible(true);
	    						form.setVisible(false);
	    						txfUsername.setText("");
	    						passwordField.setText("");
	    					} else {
	    						MyUtils.showErrorMessage("Login error" , "You have selected wrong account type!");
	    					}
    					}else {
    						MyUtils.showErrorMessage("Login error" , "This account has been disabled!");
    					}
    				}else {
    					MyUtils.showErrorMessage("Login error" , "Login information is invalid!");
    				}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    				MyUtils.showErrorMessage("Login error" , "Something went wrong, please try again!");
    			}
    		}
    	});
    }
}
