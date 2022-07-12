package kr.ac.kopo.model;

public class OneFile {
	private int code;
	private int filecode;
	private String filename;
	private String UUID;
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
}
