package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import DAO.ThongKeDao;
import DBConnection.DBConnect;

public class ThongKeDaoImpl implements ThongKeDao {
	Connection cnn;
	
	public ThongKeDaoImpl() {
		cnn = new DBConnect().getConnection();
	}
	
	public Map<Integer, Integer> reportAllYear() {
		String sql = "SELECT YEAR(ngayTao), SUM(thanhTien)\r\n"
					+ "FROM qlchdt.hoadon\r\n"
					+ "group by YEAR(ngayTao)\r\n"
					+ "order by YEAR(ngayTao) ASC";
		
		Map<Integer, Integer> m = new LinkedHashMap<Integer, Integer>();
		
		try {
			Statement ps =  cnn.createStatement();
	        ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				m.put(rs.getInt(1), rs.getInt(2));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return m;
	}
	
	public Map<Integer, Integer> reportByMonthYear(int year) {
		String sql = "SELECT MONTH(ngayTao), SUM(thanhTien)\r\n"
					+ "FROM qlchdt.hoadon\r\n"
					+ "WHERE YEAR(ngayTao) = ?\r\n"
					+ "group by MONTH(ngayTao)\r\n"
					+ "order by MONTH(ngayTao) ASC";
		
		Map<Integer, Integer> m = new LinkedHashMap<Integer, Integer>();
		
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, year);
	        ResultSet rs = ps.executeQuery();
			while(rs.next()){
				m.put(rs.getInt(1), rs.getInt(2));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return m;
	}
	
	public Map<Integer, Integer> reportByQuaterYear(int year) {
		String sql = "SELECT QUARTER(ngayTao), SUM(thanhTien)\r\n"
					+ "FROM qlchdt.hoadon\r\n"
					+ "WHERE YEAR(ngayTao) = ?\r\n"
					+ "group by QUARTER(ngayTao)\r\n"
					+ "order by QUARTER(ngayTao) ASC";
		
		Map<Integer, Integer> m = new LinkedHashMap<Integer, Integer>();
		
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, year);
	        ResultSet rs = ps.executeQuery();
			while(rs.next()){
				m.put(rs.getInt(1), rs.getInt(2));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return m;
	}
	
	public int reportByTime(Date dateStart, Date dateEnd) {
		String sql = "SELECT SUM(thanhTien)\r\n"
					+ "FROM qlchdt.hoadon\r\n"
					+ "WHERE ngayTao between ? and ?";
		
		Map<Integer, Integer> m = new LinkedHashMap<Integer, Integer>();
		
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(dateStart.getTime()));
			ps.setDate(2, new java.sql.Date(dateEnd.getTime()));
	        ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return rs.getInt(1);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
