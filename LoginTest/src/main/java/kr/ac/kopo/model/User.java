package kr.ac.kopo.model;

public class User {
	private String id;
	private String passwd;
	private String name;
	private String tel;
	private char logincheck;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public char getLogincheck() {
		return logincheck;
	}
	public void setLogincheck(char logincheck) {
		this.logincheck = logincheck;
	}
	
}
