package com.zylsite.symmetry;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * DES的替代者，更加高级
 * 
 * @author zhaoyl
 * @createdate 2017年12月5日
 */
public class EAes {

	private final static String str = "immoc security aes";

	public static void main(String[] args) throws Exception {
//		jdkAes();
		bouncyCastleAes();
	}

	public static void jdkAes() throws Exception {
		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		// keyGenerator.init(new SecureRandom());
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();

		// key转换
		Key convertSecretKey = new SecretKeySpec(byteKey, "AES");

		// 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Base64.encodeBase64String(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

	public static void bouncyCastleAes() throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		// keyGenerator.init(new SecureRandom());
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();

		// key转换
		Key convertSecretKey = new SecretKeySpec(byteKey, "AES");

		// 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Base64.encodeBase64String(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

}
