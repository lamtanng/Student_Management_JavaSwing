package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.HoaDonChiTietDao;
import DBConnection.DBConnect;
import Model.HoaDonChiTietModel;

public class HoaDonChiTietDaoImpl implements HoaDonChiTietDao{
	Connection cnn;
	public HoaDonChiTietDaoImpl() {
		cnn = new DBConnect().getConnection();
	}
	
	public List<HoaDonChiTietModel> getAll() {
		String sql = "Select * from hoadonchitiet";
		List<HoaDonChiTietModel> list = new ArrayList<HoaDonChiTietModel>();
		try {
			Statement ps = cnn.createStatement();
	        ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				HoaDonChiTietModel hdct = new HoaDonChiTietModel();
				hdct.setIdHD(rs.getInt("idHD"));
				hdct.setIdSP(rs.getInt("idSP"));
				hdct.setPrice(rs.getDouble("donGia"));
				hdct.setQuantity(rs.getInt("soLuong"));
				list.add(hdct);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public boolean insert(HoaDonChiTietModel hdct) {
		try {
			String sql = "insert into hoadonchitiet(idHD, idSP, donGia, soLuong) values(?,?,?,?)";
			PreparedStatement st = cnn.prepareStatement(sql);
			st.setInt(1, hdct.getIdHD());
			st.setInt(2, hdct.getIdSP());
			st.setDouble(3, hdct.getPrice());
			st.setInt(4, hdct.getQuantity());
			st.execute();
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean update(HoaDonChiTietModel hdct) {	
		return false;
	}

	@Override
	public List<HoaDonChiTietModel> getByIdBill(int idBill) {
		String sql = "Select hoadonchitiet.idHD, sanpham.idSP, sanpham.tenSP, hoadonchitiet.soLuong, hoadonchitiet.donGia from hoadonchitiet inner join sanpham on sanpham.idSP = hoadonchitiet.idSP where hoadonchitiet.idHD = ?";
		List<HoaDonChiTietModel> list = new ArrayList<HoaDonChiTietModel>();
		try {
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, idBill);
	        ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HoaDonChiTietModel hdct = new HoaDonChiTietModel();
				hdct.setIdHD(rs.getInt("idHD"));
				hdct.setIdSP(rs.getInt("idSP"));
                                hdct.setTenSP(rs.getString("tenSP"));
				hdct.setPrice(rs.getDouble("donGia"));
				hdct.setQuantity(rs.getInt("soLuong"));
				list.add(hdct);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
