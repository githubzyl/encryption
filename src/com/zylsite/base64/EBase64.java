package com.zylsite.base64;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EBase64 {

	private final static String str = "immoc security base64";//"zylsite encryption base64";

	public static void main(String[] args) throws IOException {
//		jdkBase64();
//		commonsCodecBase64();
		bouncyCastleBase64();
	}

	public static void jdkBase64() throws IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(str.getBytes());
		System.out.println("encode:" + encode);

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(encode);
		System.out.println("decode:" + new String(bytes));
	}
	
	public static void commonsCodecBase64(){
		byte[] bytes = Base64.encodeBase64(str.getBytes());
		System.out.println("encode:" + new String(bytes));
		
		bytes = Base64.decodeBase64(bytes);
		System.out.println("decode:" + new String(bytes));
	}
	
	public static void bouncyCastleBase64(){
		byte[] bytes = org.bouncycastle.util.encoders.Base64.encode(str.getBytes());
		System.out.println("encode:" + new String(bytes));
		
		bytes = org.bouncycastle.util.encoders.Base64.decode(bytes);
		System.out.println("decode:" + new String(bytes));
	}
	
}
