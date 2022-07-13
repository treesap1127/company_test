package kr.ac.kopo.model;

import java.util.List;

public class OneFile {
	private int code;
	private int filecode;
	private String filename;
	private String UUID;
	private List<OneExcel> oneExcel;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public int getFilecode() {
		return filecode;
	}
	public void setFilecode(int filecode) {
		this.filecode = filecode;
	}
	public List<OneExcel> getOneExcel() {
		return oneExcel;
	}
	public void setOneExcel(List<OneExcel> oneExcel) {
		this.oneExcel = oneExcel;
	}
}
