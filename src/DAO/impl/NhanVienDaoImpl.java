package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import DAO.NhanVienDao;
import DBConnection.DBConnect;
import Model.NhanVienModel;
import java.sql.SQLException;

public class NhanVienDaoImpl implements NhanVienDao {

    Connection cnn;

    public NhanVienDaoImpl() {
        cnn = new DBConnect().getConnection();
    }

    public NhanVienModel login(String phone, String password) {

        String sql = "Select * from nhanvien where SDT = ? and password = ?";
        NhanVienModel nhanvien = null;
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nhanvien = new NhanVienModel();
                nhanvien.setName(rs.getString("tenNV"));
                nhanvien.setEmail(rs.getString("email"));
                nhanvien.setAddress(rs.getString("diaChi"));
                nhanvien.setAvatar(rs.getBlob("avatar"));
                nhanvien.setDob(rs.getDate("ngaySinh"));
                nhanvien.setGender(rs.getString("gioiTinh"));
                nhanvien.setId(rs.getInt("idNV"));
                nhanvien.setPassword(rs.getString("password"));
                nhanvien.setPhone(rs.getString("SDT"));
                nhanvien.setRole(rs.getInt("role"));
                nhanvien.setStatus(rs.getInt("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nhanvien;
    }

    public List<NhanVienModel> getAll() {
        String sql = "Select * from nhanvien";
        List<NhanVienModel> list = new ArrayList<NhanVienModel>();
        try {
            Statement ps = cnn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                NhanVienModel nhanvien = new NhanVienModel();
                nhanvien = new NhanVienModel();
                nhanvien.setId(rs.getInt("idNV"));
                nhanvien.setName(rs.getString("tenNV"));
                nhanvien.setEmail(rs.getString("email"));
                nhanvien.setAddress(rs.getString("diaChi"));
                nhanvien.setAvatar(rs.getBlob("avatar"));
                nhanvien.setDob(rs.getDate("ngaySinh"));
                nhanvien.setGender(rs.getString("gioiTinh"));
                nhanvien.setPassword(rs.getString("password"));
                nhanvien.setPhone(rs.getString("SDT"));
                nhanvien.setRole(rs.getInt("role"));
                nhanvien.setStatus(rs.getInt("status"));
                list.add(nhanvien);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public NhanVienModel getById(int id) {
        String sql = "Select * from nhanvien where idNV=?";
        NhanVienModel nhanvien = null;
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nhanvien = new NhanVienModel();
                nhanvien.setId(rs.getInt("idNV"));
                nhanvien.setName(rs.getString("tenNV"));
                nhanvien.setEmail(rs.getString("email"));
                nhanvien.setAddress(rs.getString("diaChi"));
                nhanvien.setAvatar(rs.getBlob("avatar"));
                nhanvien.setDob(rs.getDate("ngaySinh"));
                nhanvien.setGender(rs.getString("gioiTinh"));
                nhanvien.setPassword(rs.getString("password"));
                nhanvien.setPhone(rs.getString("SDT"));
                nhanvien.setRole(rs.getInt("role"));
                nhanvien.setStatus(rs.getInt("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nhanvien;
    }

    public boolean insert(NhanVienModel nhanvien) {
        try {
            String sql
                    = "insert into nhanvien(tenNV, gioiTinh, ngaySinh, SDT, diaChi, email, password, avatar, role, status) "
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, nhanvien.getName());
            st.setString(2, nhanvien.getGender());
            st.setDate(3, nhanvien.getDob());
            st.setString(4, nhanvien.getPhone());
            st.setString(5, nhanvien.getAddress());
            st.setString(6, nhanvien.getEmail());
            st.setString(7, nhanvien.getPassword());

            if (nhanvien.getAvatar().length > 0) {
                st.setBlob(8, new SerialBlob(nhanvien.getAvatar()));
            } else {
                st.setNull(8, java.sql.Types.BLOB);
            }

            st.setInt(9, nhanvien.getRole());
            st.setInt(10, nhanvien.getStatus());
            st.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean update(NhanVienModel nhanvien) {
        String sql;
        if (nhanvien.getAvatar().length == 0) {
            sql = "update nhanvien set tenNV=? , gioiTinh=?, ngaySinh=?, SDT=?, diaChi=?, email=?, password=?, role=?, status=?"
                    + " where idNV=?";
        } else {
            sql = "update nhanvien set tenNV=? , gioiTinh=?, ngaySinh=?, SDT=?, diaChi=?, email=?, password=?, role=?, status=?, avatar=?"
                    + " where idNV=?";
        }
        try {

            PreparedStatement st = cnn.prepareStatement(sql);
            st.setString(1, nhanvien.getName());
            st.setString(2, nhanvien.getGender());
            st.setDate(3, nhanvien.getDob());
            st.setString(4, nhanvien.getPhone());
            st.setString(5, nhanvien.getAddress());
            st.setString(6, nhanvien.getEmail());
            st.setString(7, nhanvien.getPassword());
            st.setInt(8, nhanvien.getRole());
            st.setInt(9, nhanvien.getStatus());

            if (nhanvien.getAvatar().length > 0) {
                st.setBlob(10, new SerialBlob(nhanvien.getAvatar()));
                st.setInt(11, nhanvien.getId());
            } else {
                st.setInt(10, nhanvien.getId());
            }

            st.execute();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "delete from nhanvien where idNV=?";
        try {
            PreparedStatement st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<NhanVienModel> getByName(String name) {
        String sql = "Select * from nhanvien where tenNV like ?";
        List<NhanVienModel> list = new ArrayList<NhanVienModel>();
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienModel nhanvien = new NhanVienModel();
                nhanvien = new NhanVienModel();
                nhanvien.setId(rs.getInt("idNV"));
                nhanvien.setName(rs.getString("tenNV"));
                nhanvien.setEmail(rs.getString("email"));
                nhanvien.setAddress(rs.getString("diaChi"));
                nhanvien.setAvatar(rs.getBlob("avatar"));
                nhanvien.setDob(rs.getDate("ngaySinh"));
                nhanvien.setGender(rs.getString("gioiTinh"));
                nhanvien.setPassword(rs.getString("password"));
                nhanvien.setPhone(rs.getString("SDT"));
                nhanvien.setRole(rs.getInt("role"));
                nhanvien.setStatus(rs.getInt("status"));
                list.add(nhanvien);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isEmployee(String phone) {
        String sql = "Select * from nhanvien where SDT=?";
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
