package Model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

public class NhanVienModel extends PersonModel{
	private String email;
	private String password;
	private byte[] avatar;
	private int role;
	private int status;
	
	public NhanVienModel() {}
	
	public NhanVienModel(int id, String name, String gender, Date dob, String phone, String address, String email,
			String password, byte[] avatar, int role, int status) {
		super(id, name, gender, dob, phone, address);
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
		this.status = status;
	}
	
	public NhanVienModel(String name, String gender, Date dob, String phone, String address, String email,
			String password, byte[] avatar, int role, int status) {
		super(name, gender, dob, phone, address);
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(Blob blob) {
		if (blob == null) {
			this.avatar = new byte[0];
		}
		else {
			try {
				int blobLength = (int)blob.length();
				if (blobLength > 0) {
					this.avatar = blob.getBytes(1, blobLength);
				}
				else {
					this.avatar = new byte[0];
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
