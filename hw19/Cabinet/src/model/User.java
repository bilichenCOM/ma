package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String name;
	private String surname;
	private String gender;
	private Integer age;
	private String email;
	private String password;

	@Column(name = "role_id")
	private Integer roleId;
	private Double balance;
	private String salt;

	public User() {}
	public User(Long id, String name, String surname, String gender, int age, String email, String password, int roleId, double balance, String salt) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.balance = balance;
		this.salt = salt;
	}

	public User(String name, String surname, String gender, int age, String email, String password, int roleId, double balance, String salt) {
		this(null, name, surname, gender, age, email, password, roleId, balance, salt);
	}

	public User(String name, String surname, String gender, int age, String email, String password, int roleId, double balance) {
		this(null, name, surname, gender, age, email, password, roleId, balance, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void purchase(Good good) {
		balance = balance - good.getPrice();
		System.out.println("purchased for money!");
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", age=" + age
		        + ", email=" + email + ", password=" + password + ", roleId=" + roleId + "]";
	}
}