/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Dao;

import com.stdManage.Models.Account;
import com.stdManage.Utils.U_ModelFields;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class AccountDao{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
//    public Object[][] findAll(){
//        Object[][] listModel = new Object[][]{};
//        List<List<Object>> listcate = new ArrayList<List<Object>>();
//        String sql = "Select * from account";
//
//        try {
//                conn = new DBConnectionDao().getConn();
//                ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
//                rs = ps.executeQuery();// Chuyển dữ liệu ra view
//                while (rs.next()) {
//
//                        account category = new account();
//                        category.setUsername(rs.getString(NameModels.ACCOUNT_USERNAME));
//                        category.setPassword(rs.getString(NameModels.ACCOUNT_PASSWORD));
//                        category.setRole(rs.getString(NameModels.ACCOUNT_ROLE));
//                        category.setCode(rs.getString(NameModels.ACCOUNT_CODE));
//                        category.setStatus(rs.getInt(NameModels.ACCOUNT_STATUS));
//                        
//                        
//                        Object[] obj = category.toModelTable();
//                        listcate.add(Arrays.asList(obj));
//
//                        System.out.println("AccountDAO/ >>>" + Arrays.stream(listcate.get(0).toArray()).toList());
//                } // View nào sẽ nhận dữ liệu
//                listModel = new Object[][] {
//                                            {Arrays.stream(listcate.get(0).toArray()).toList()}
//                                        };
//                
//                conn.close();
//        } catch (Exception e) {
//                e.printStackTrace();
//        }
//
//        return listModel;
//    }
    
    public List<Account> findAll(){
        List<Account> listcate = new ArrayList<Account>();
		String sql = "Select * from account";
                
		try {
			conn = new DBConnectionDao().getConn();
                        ps = conn.prepareStatement(sql);// Truy vấn dữ liệu
			rs = ps.executeQuery();// Chuyển dữ liệu ra view
			while (rs.next()) {

				Account category = new Account();
				category.setUsername(rs.getString(U_ModelFields.ACCOUNT.USERNAME));
				category.setPassword(rs.getString(U_ModelFields.ACCOUNT.PASSWORD));
				category.setRole(rs.getString(U_ModelFields.ACCOUNT.ROLE));
				listcate.add(category);
                                Object[] obj = category.toModelTable();
                                
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listcate;
    }

}


