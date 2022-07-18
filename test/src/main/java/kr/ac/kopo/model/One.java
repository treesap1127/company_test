package kr.ac.kopo.model;

import java.util.List;

import javax.validation.constraints.NotBlank;


public class One {
	private int code;
	@NotBlank(message = "내용은 필수 입력 값입니다.")
	private String info;
	@NotBlank(message = "제목은 필수 입력 값입니다.")
	private String name;
	private List<OneFile> onefile;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OneFile> getOnefile() {
		return onefile;
	}
	public void setOnefile(List<OneFile> onefile) {
		this.onefile = onefile;
	}
	
}
