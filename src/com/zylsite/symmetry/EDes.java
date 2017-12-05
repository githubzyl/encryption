package com.zylsite.symmetry;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EDes {

	private final static String str = "immoc security des";

	public static void main(String[] args) throws Exception {
//		jdkDes();
		bouncyCastleDes();
	}
	
	public static void jdkDes() throws Exception {
		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();

		// key转换
		DESKeySpec desKeySpec = new DESKeySpec(byteKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		Key convertSecretKey = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

	public static void bouncyCastleDes() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		
		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
		keyGenerator.getProvider();
		keyGenerator.init(56);
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] byteKey = secretKey.getEncoded();

		// key转换
		DESKeySpec desKeySpec = new DESKeySpec(byteKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		Key convertSecretKey = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
		byte[] bytes = cipher.doFinal(str.getBytes());
		System.out.println("encode:" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
		bytes = cipher.doFinal(bytes);
		System.out.println("decode:" + new String(bytes));
	}

}
