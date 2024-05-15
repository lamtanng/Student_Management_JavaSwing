package DAO;

import java.util.List;

import Model.NhanVienModel;

public interface NhanVienDao{
	public NhanVienModel login(String phone, String password);
	public List<NhanVienModel> getAll();
	public List<NhanVienModel> getByName(String name);
	public NhanVienModel getById(int id);
	public boolean insert(NhanVienModel nhanvien);
	public boolean update(NhanVienModel nhanvien);
	public boolean delete(int id);
        public boolean isEmployee(String phone);
}
