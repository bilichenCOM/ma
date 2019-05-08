package model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String surname;
	private String gender;
	private int age;
	private String email;
	private String password;
	private int roleId;
	private double balance;

	public User(Long id, String name, String surname, String gender, int age, String email, String password, int roleId,
			double balance) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.balance = balance;
	}

	public User(String name, String surname, String gender, int age, String email, String password, int roleId, double balance) {
		this(null, name, surname, gender, age, email, password, roleId, balance);
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void purchase(Good good) {
		balance = balance - good.getPrice();
		System.out.println("purchased for money!");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", age=" + age
				+ ", email=" + email + ", password=" + password + ", roleId=" + roleId + "]";
	}
}