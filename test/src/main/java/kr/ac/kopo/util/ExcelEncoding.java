package kr.ac.kopo.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelEncoding {

	public void ExcelEncoding(String filename,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
		
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		
		if (browser.equals("MSIE")) { 
			encodedFilename =URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
			}
		else if (browser.equals("Trident")) {
			encodedFilename =
			URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		}
		else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			encodedFilename =URLDecoder.decode(encodedFilename); 
			} 
		else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} 
				else {
					sb.append(c);
				}
				encodedFilename = sb.toString();
				} 
		}
		else if (browser.equals("Safari")){
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
			encodedFilename =URLDecoder.decode(encodedFilename);
		}
		else {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
		}
		 response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
	}
	
	public String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		
		if (header.indexOf("MSIE") > -1) return "MSIE";
		else if (header.indexOf("Trident") > -1) return "Trident";
		else if (header.indexOf("Chrome") > -1) return "Chrome";
		else if (header.indexOf("Safari") > -1)  return "Safari";
		return "Firefox";
	}	
}
