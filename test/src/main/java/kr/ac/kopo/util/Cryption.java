package kr.ac.kopo.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryption {
	public static String padding = "AES/CBC/PKCS5Padding";
	private final String key = "01234567890123456789012345678901";
	private final String iv = key.substring(0, 16);
		// 암호화 모드
	public String encrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance(padding);//padding값 넣고 빈 16바이트 공간 채워넣기
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");// 암호화 방식 설정
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());// 첫번째 암호화를 위한 벡터값
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);// 암호화 할껀지 복호화 할껀지 셋팅

		byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));//UTF-8변환 후 
																//바이트 배열로 변환 후 암호화 실시!

		return Base64.getEncoder().encodeToString(encrypted);// 다시 String 으로 인코딩 후 리턴..
	}
		// 복호화모드 
	  public String decrypt(String cipherText) throws Exception {
	        Cipher cipher = Cipher.getInstance(padding);
	        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
	        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
	        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

	        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
	        byte[] decrypted = cipher.doFinal(decodedBytes);
	        return new String(decrypted, "UTF-8");
	    }

}
