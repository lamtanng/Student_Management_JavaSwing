package DAO;

import java.util.List;

import Model.HoaDonChiTietModel;

public interface HoaDonChiTietDao{
	public List<HoaDonChiTietModel> getAll();
	public boolean insert(HoaDonChiTietModel hdct);
	public boolean update(HoaDonChiTietModel hdct);
	public List<HoaDonChiTietModel> getByIdBill(int idBill);
}
