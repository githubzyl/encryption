package com.zylsite.symmetry;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class E3Des {

	private final static String str = "immoc security 3des";

	public static void main(String[] args) throws Exception {
//		jdk3Des();
		bouncyCastle3Des();
	}

	public static void jdk3Des() throws Exception {
		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		// keyGenerator.init(168);
		keyGenerator.init(new SecureRandom());
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();

		// key转换
		DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		Key convertSecretKey = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

	public static void bouncyCastle3Des() throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		// keyGenerator.init(168);
		keyGenerator.init(new SecureRandom());
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();

		// key转换
		DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		Key convertSecretKey = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

}
