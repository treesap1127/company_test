package kr.ac.kopo.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

public class User {
	@Pattern(regexp="[A-za-z0-9]{5,15}",
            message = "영문자로 시작하는 영문자 또는 숫자 6~20자로 입력해주세요")
	public String id;
	private String passwd;
	@Pattern(regexp="[가-힣a-zA-Z]{1,32}",
            message = "닉네임은 한글/영문 32자만 입력 가능합니다.")
	private String name;
	@Pattern(regexp="d{10,11}|\\d{3}-\\d{3,4}-\\d{4}",
            message = "전화번호 입력이 올바르지 않습니다.")
	private String tel;
	private char logincheck;
	@AssertTrue(message="아이디 중복확인을 해주세요")//true만
	private boolean idcheck;
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
	public boolean isIdcheck() {
		return idcheck;
	}
	public void setIdcheck(boolean idcheck) {
		this.idcheck = idcheck;
	}
	
}
