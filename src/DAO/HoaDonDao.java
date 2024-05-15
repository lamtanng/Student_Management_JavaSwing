package DAO;

import java.util.List;

import Model.HoaDonModel;

public interface HoaDonDao{
	public List<HoaDonModel> getAll(Integer userId);
	public HoaDonModel getById(int id, Integer userId);
	public int insert(HoaDonModel hoadon);
	public boolean update(HoaDonModel hoadon);
	public boolean delete(int id);
	public List<HoaDonModel> getByIdEmployee(int id);
	
}
