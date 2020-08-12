package com.warmsheep.des;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * DES���ܹ�����
 * 
 * @author Warmsheep
 * 
 */
public class DesMain
{
	private static final String model = "DESede/ECB/PKCS5Padding";//���ģʽ(PKCS5Padding)   ѡ���Զ�Ӧ���κ����(NoPadding)
	/**
	 * DES����
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String desDecrypt(String message, String key) throws Exception {
		try {
			byte[] keyBytes = null;
			if(key.length() == 16){
				keyBytes = newInstance8Key(ByteUtil.convertHexString(key));
			} else if(key.length() == 32){
				keyBytes = newInstance16Key(ByteUtil.convertHexString(key));
			} else if(key.length() == 48){
				keyBytes = newInstance24Key(ByteUtil.convertHexString(key));
			}
			SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
			Cipher c1 = Cipher.getInstance(model);
			c1.init(2, deskey);
			byte[] retByte = c1.doFinal(ByteUtil.convertHexString(message));
			
			return new String(retByte);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	/**
	 * DES����
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String desDecryptToHex(String message, String key) throws Exception {
		try {
			byte[] keyBytes = null;
			if(key.length() == 16){
				keyBytes = newInstance8Key(ByteUtil.convertHexString(key));
			} else if(key.length() == 32){
				keyBytes = newInstance16Key(ByteUtil.convertHexString(key));
			} else if(key.length() == 48){
				keyBytes = newInstance24Key(ByteUtil.convertHexString(key));
			}
			SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
			Cipher c1 = Cipher.getInstance(model);
			c1.init(2, deskey);
			byte[] retByte = c1.doFinal(ByteUtil.convertHexString(message));
			
			return ByteUtil.toHexString(retByte);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	/**
	 * DES����
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String desEncrypt(String message, String key) throws Exception {
		byte[] keyBytes = null;
		if(key.length() == 16){
			keyBytes = newInstance8Key(ByteUtil.convertHexString(key));
		} else if(key.length() == 32){
			keyBytes = newInstance16Key(ByteUtil.convertHexString(key));
		} else if(key.length() == 48){
			keyBytes = newInstance24Key(ByteUtil.convertHexString(key));
		}
		
		SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance(model);
		cipher.init(1, deskey);
		return ByteUtil.toHexString(cipher.doFinal(message.getBytes("UTF-8")));
	}
	
	public static String desEncryptHexString(String message,String key) throws Exception {
		byte[] keyBytes = null;
		if(key.length() == 16){
			keyBytes = newInstance8Key(ByteUtil.convertHexString(key));
		} else if(key.length() == 32){
			keyBytes = newInstance16Key(ByteUtil.convertHexString(key));
		} else if(key.length() == 48){
			keyBytes = newInstance24Key(ByteUtil.convertHexString(key));
		}
		
		SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
		Cipher cipher = Cipher.getInstance(model);
		cipher.init(1, deskey);
		return ByteUtil.toHexString(cipher.doFinal(ByteUtil.convertHexString(message)));
	}
	
	/*** 
	* MD5���� ����32λmd5�� 
	*/
	public static String md5Encrypt(String message) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] md5Bytes = md5.digest(message.getBytes());
		String hexValue = ByteUtil.toHexString(md5Bytes);
		return hexValue;
	}
	
	private static byte[] newInstance24Key(byte[] key) {
		if ((key != null) && (key.length == 24)) {
			return key;
		}
		System.err.println("��Կ��������,����ֵ[24]");
		return null;
	}
	private static byte[] newInstance16Key(byte[] key) {
		if ((key != null) && (key.length == 16)) {
			byte[] b = new byte[24];
			System.arraycopy(key, 0, b, 0, 16);
			System.arraycopy(key, 0, b, 16, 8);
			key = (byte[]) null;
			return b;
		}
		System.err.println("��Կ��������,����ֵ[16]");
		return null;
	}
	private static byte[] newInstance8Key(byte[] key) {
		if ((key != null) && (key.length == 8)) {
			byte[] b = new byte[24];
			System.arraycopy(key, 0, b, 0, 8);
			System.arraycopy(key, 0, b, 8, 8);
			System.arraycopy(key, 0, b, 16, 8);
			key = (byte[]) null;
			return b;
		}
		System.err.println("��Կ��������,����ֵ[8]");
		return null;
	}
	public static void main(String[] args) throws Exception {
		// �����������DEMO
//		System.out.println(md5Encrypt("����"));
		String key = "0000000000000000";
		String password = "��������";
		
		System.out.println("��Կ:"+key);
		System.out.println("����:" + password);
		
		try {
			System.out.println("ʹ��des���ܺ������:" + password);
			System.out.println("ʹ��des���ܺ������:" + desDecrypt(password, key));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}