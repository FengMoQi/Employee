package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateMD5 {
		public static String getMD5(String plaintext) {
			try {
				MessageDigest md5=MessageDigest.getInstance("MD5");
				return new BigInteger(md5.digest(plaintext.getBytes())).toString(16);
		} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

}