package Model;

import java.sql.Date;

public abstract class PersonModel {
	private int id;
	private String name;
	private String gender;
	private Date dob;
	private String phone;
	private String address;
	
	public PersonModel() {}
	
	public PersonModel(int id, String name, String gender, Date dob, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
	}
	
	public PersonModel(String name, String gender, Date dob, String phone, String address) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
