package DAO;

import java.util.List;

import Model.SanPhamModel;

public interface SanPhamDao{
	public List<SanPhamModel> getAll();
	public List<SanPhamModel> getByName(String name);
	public SanPhamModel getById(int id);
	public boolean insert(SanPhamModel sanpham);
	public boolean update(SanPhamModel sanpham);
	public boolean changeQuantity(int id, int quantity);
	public boolean delete(int id);
}
