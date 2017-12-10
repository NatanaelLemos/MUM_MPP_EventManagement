package edu.mum.eventmanagement.services;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptographyService {
	public static String encrypt(String text) {
		try {
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			Base64.Encoder encoder = Base64.getEncoder();
			String encryptedString = encoder.encodeToString(encrypted);
			return encryptedString;
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}

	public static String decrypt(String encryptedString) {
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(decoder.decode(encryptedString)));
			return decrypted;
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
}
