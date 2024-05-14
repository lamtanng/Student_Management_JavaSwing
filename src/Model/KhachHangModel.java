package Model;

import java.sql.Date;

public class KhachHangModel extends PersonModel {
	public KhachHangModel() {}
	
	public KhachHangModel(int id, String name, String gender, Date dob, String phone, String address) {
		super(id, name, gender, dob, phone, address);
	}
	
	public KhachHangModel(String name, String gender, Date dob, String phone, String address) {
		super(name, gender, dob, phone, address);
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
