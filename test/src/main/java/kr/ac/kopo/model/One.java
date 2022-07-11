package kr.ac.kopo.model;

import java.util.List;

public class One {
	private int code;
	private String info;
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
