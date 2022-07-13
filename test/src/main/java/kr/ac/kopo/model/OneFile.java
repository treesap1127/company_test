package kr.ac.kopo.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class OneFile {
	private int code;
	private int filecode;
	private String filename;
	private String UUID;
	private List<OneExcel> oneExcel;
	private List<MultipartFile> files;
	public int getCode() {
		return code;
	}
	public int getFilecode() {
		return filecode;
	}
	public void setFilecode(int filecode) {
		this.filecode = filecode;
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
	public List<OneExcel> getOneExcel() {
		return oneExcel;
	}
	public void setOneExcel(List<OneExcel> oneExcel) {
		this.oneExcel = oneExcel;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
