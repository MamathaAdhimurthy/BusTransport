package bustransport.user.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bustransport.busbook.model.BusBook;

@Entity
@Table(name="bususer")
public class User {

	@Id
	@Column(name="UserId",nullable = false,unique = true)
	private String userId;

	@Size(min = 8,message = "Enter Valid Password and Minimum length Should be 8.")
	private String password;

	@Size(min=2,max=20,message = "User Name Should be of length of 2-20.")
	private String name;

	//	@Min(value = 10,message ="Minimum Age should be 10" )
	//	@Max(value = 80,message = "Maximum Age should be 80")		
	private int age;

	@Size(min=10,max=10,message = "Length of mobile number should be 10.")
	@Pattern(regexp = "(^[0-9]{1,11})",message = "you can enter only digits.")
	private String phoneNumber;

	@Email
	private String email;
	private String address;


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(@Email String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public User(String userId, String name, String password, int age, String phoneNumber, String string,
			String address) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = string;
		this.address = address;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user",orphanRemoval = true)
	private List<BusBook> bookingId;
	public List<BusBook> getBookingId() {
		return bookingId;
	}
	public void setBookingId(List<BusBook> bookingId) {
		this.bookingId = bookingId;
	}
}

