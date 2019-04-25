package model;

import java.util.Map;

public class User {
	private String email;
	private String passwd;

	private String name;
	private String surname;
	private String gender;
	private int age;

	public User(String email, String passwd) {
		this.email = email;
		this.passwd = passwd;
	}

	public User(Map<String, String> userInfo) {
		email = userInfo.get("email");
		passwd = userInfo.get("passwd");
		name = userInfo.get("user_name");
		surname = userInfo.get("user_surname");
		age = Integer.parseInt(userInfo.get("user_age"));
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}