package Model;

public class HoaDonChiTietModel {
	private int idHD;
	private int idSP;
	
	private double price;
	private int quantity;

	public HoaDonChiTietModel() {}

	public HoaDonChiTietModel(int idHD, int idSP, double price, int quantity) {
		super();
		this.idHD = idHD;
		this.idSP = idSP;
		this.price = price;
		this.quantity = quantity;
	}

	public int getIdHD() {
		return idHD;
	}

	public void setIdHD(int idHD) {
		this.idHD = idHD;
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
