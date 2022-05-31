package bustransport.admin.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name = "busadmin")
public class Admin {
	@Id
	@Column(name="adminId",nullable = false,unique = true)
	private String adminId;

	@Size(min = 8, message = "Password Minimum Should be 8.")
	private String password;
	
	@Size(min = 3, max = 25, message = "Admin Name Length Should be Length of 3-25")
	private String name;
	
	@Email
	private String email;

	@Size(min = 10, max = 10, message = "Length of Phone number should be 10.")
	@Pattern(regexp = "(^[0-9]{1,11})", message = "Phone Number Should Contains Only Digits.")
	private String phoneNumber;


	public Admin() {
		super();
	}
	
	public Admin(String adminId, String password, String name, String email, String phoneNumber) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

