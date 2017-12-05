package com.zylsite.symmetry;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * PBE算法
 * 
 * @author zhaoyl
 * @createdate 2017年12月5日
 */
public class EPbe {

	private final static String str = "immoc security pbe";

	public static void main(String[] args) throws Exception {
//		jdkPbe();
		bouncyCastlePbe();
	}

	public static void jdkPbe() throws Exception {
		// 初始化盐
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);

		// 口令与秘钥
		String password = "immoc";
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
		Key convertSecretKey = factory.generateSecret(pbeKeySpec);

		// 加密
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey, parameterSpec);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Base64.encodeBase64String(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey, parameterSpec);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}
	
	public static void bouncyCastlePbe() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		
		// 初始化盐
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);

		// 口令与秘钥
		String password = "immoc";
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
		Key convertSecretKey = factory.generateSecret(pbeKeySpec);

		// 加密
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey, parameterSpec);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Base64.encodeBase64String(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey, parameterSpec);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

}
