package Model;

import java.sql.Date;

public class HoaDonModel {
	private int id;
	private Date createDate;
	private double totalPrice;
	
	private int idNV;
	private int idKH;

	public HoaDonModel() {}

	public HoaDonModel(int id, Date createDate, double totalPrice, int idNV, int idKH) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.totalPrice = totalPrice;
		this.idNV = idNV;
		this.idKH = idKH;
	}

	public HoaDonModel(Date createDate, double totalPrice, int idNV, int idKH) {
		super();
		this.createDate = createDate;
		this.totalPrice = totalPrice;
		this.idNV = idNV;
		this.idKH = idKH;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIdNV() {
		return idNV;
	}

	public void setIdNV(int idNV) {
		this.idNV = idNV;
	}

	public int getIdKH() {
		return idKH;
	}

	public void setIdKH(int idKH) {
		this.idKH = idKH;
	}
}
