package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.HoaDonDao;
import DBConnection.DBConnect;
import Model.HoaDonModel;

public class HoaDonDaoImpl implements HoaDonDao {
	Connection cnn;
	public HoaDonDaoImpl() {
		cnn = new DBConnect().getConnection();
	}
	
	public List<HoaDonModel> getAll(Integer userId) {
		String sql;
		if (userId == null) {
			sql = "Select * from hoadon";
		}
		else {
			sql = "Select * from hoadon where idNV=?";
		}
		
		List<HoaDonModel> list = new ArrayList<HoaDonModel>();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			if (userId != null) {
				ps.setInt(1, userId);
			}
	        ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HoaDonModel hoadon = new HoaDonModel();
				hoadon.setId(rs.getInt("idHD"));
				hoadon.setCreateDate(rs.getDate("ngayTao"));
				hoadon.setTotalPrice(rs.getDouble("thanhTien"));
				hoadon.setIdKH(rs.getInt("idKH"));
				hoadon.setIdNV(rs.getInt("idNV"));
				list.add(hoadon);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public int insert(HoaDonModel hoadon) {	
		int key = -1;
		try {
			String sql = "insert into hoadon(ngayTao, thanhTien, idKH, idNV) values(?,?,?,?)";
			PreparedStatement st = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setDate(1, hoadon.getCreateDate());
			st.setDouble(2, hoadon.getTotalPrice());
			st.setInt(3, hoadon.getIdKH());
			st.setInt(4, hoadon.getIdNV());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return key;
	}
	
	public boolean update(HoaDonModel hoadon) {	
		try {
			String sql = "update sanpham set ngayTao=?, thanhTien=?, idKH=?, idNV=? where idHD=?";
			PreparedStatement st = cnn.prepareStatement(sql);
			st.setDate(1, hoadon.getCreateDate());
			st.setDouble(2, hoadon.getTotalPrice());
			st.setInt(3, hoadon.getIdKH());
			st.setInt(4, hoadon.getIdNV());
			st.setInt(5, hoadon.getId());
			st.execute();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int id) {
		String sql = "delete from hoadon where idHD=?";
		try {
			PreparedStatement st = cnn.prepareStatement(sql);
			st.setInt(1, id);
			st.execute();
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public HoaDonModel getById(int id, Integer userId) {
		String sql;
		if (userId == null) {
			sql = "Select * from hoadon where idHD=?";
		}
		else {
			sql = "Select * from hoadon where idHD=? and idNV=?";
		}
		
		HoaDonModel hoadon = null;
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, id);
            if (userId != null) {
            	ps.setInt(2, userId);
            }
	        ResultSet rs = ps.executeQuery();
			if(rs.next()){
				hoadon = new HoaDonModel();
				hoadon.setId(rs.getInt("idHD"));
				hoadon.setCreateDate(rs.getDate("ngayTao"));
				hoadon.setTotalPrice(rs.getDouble("thanhTien"));
				hoadon.setIdKH(rs.getInt("idKH"));
				hoadon.setIdNV(rs.getInt("idNV"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return hoadon;
	}
	
	public List<HoaDonModel> getByIdEmployee(int id) {
		String sql = "Select * from hoadon where idNV = ?";
		List<HoaDonModel> list = new ArrayList<HoaDonModel>();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HoaDonModel hoadon = new HoaDonModel();
				hoadon.setId(rs.getInt("idHD"));
				hoadon.setCreateDate(rs.getDate("ngayTao"));
				hoadon.setTotalPrice(rs.getDouble("thanhTien"));
				hoadon.setIdKH(rs.getInt("idKH"));
				hoadon.setIdNV(rs.getInt("idNV"));
				list.add(hoadon);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
