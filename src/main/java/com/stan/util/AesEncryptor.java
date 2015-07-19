package com.stan.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AesEncryptor {
	
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

	private static final byte[] INIT_PARM =
	{
		(byte)0xcd, (byte)0x91, (byte)0xa7, (byte)0xc5,
		(byte)0x27, (byte)0x8b, (byte)0x39, (byte)0xe0,
		(byte)0xfa, (byte)0x72, (byte)0xd0, (byte)0x29,
		(byte)0x83, (byte)0x65, (byte)0x9d, (byte)0x74
	};

	private static final byte[] DEFAULT_KEY =
	{
		(byte)0xf2, (byte)0x46, (byte)0x5d, (byte)0x2a,
		(byte)0xd1, (byte)0x73, (byte)0x0b, (byte)0x18,
		(byte)0xcb, (byte)0x86, (byte)0x95, (byte)0xa3,
		(byte)0xb1, (byte)0xe5, (byte)0x89, (byte)0x27
	};

	private byte[] cipherKey = null;

    public AesEncryptor() { }

    public AesEncryptor(String key) { 
    	setKey(key);
    }

	public String encrypt(String value)
	{
		if (value == null) { return null; }
		byte [] bytes = null;
		try { bytes = value.getBytes("UTF-8"); }
		catch (UnsupportedEncodingException uee) { bytes = value.getBytes(); }
		return Base64.encodeBytes( cipher(bytes, getKey(), Cipher.ENCRYPT_MODE) );
	}

	public String decrypt(String value)
	{
		if (value == null) { return null; }
		byte [] bytes = cipher(Base64.decode(value), getKey(), Cipher.DECRYPT_MODE);
		if (bytes == null) { return null; }
		String result = null;
		try { result = new String(bytes,"UTF-8"); }
		catch (UnsupportedEncodingException uee) { result = new String(bytes); }
		return result;
	}

	private byte [] cipher(byte [] attribute, byte [] key, int mode)
	{
		byte [] result = null;
		try
		{
		    Key aesKey = new SecretKeySpec(key, "AES");

		    Cipher aesCipher = Cipher.getInstance(ALGORITHM);

		    aesCipher.init(mode, aesKey, new IvParameterSpec(INIT_PARM));
		    result = aesCipher.doFinal(attribute);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private byte [] getKey()
	{
		return cipherKey == null ? DEFAULT_KEY : cipherKey;
	}

	private void setKey(byte [] key)
	{
		cipherKey = editKey(key);
	}

	public void setKey(String key)
	{
		if (key == null) { 
			cipherKey = null; 
			return;
		}
		byte [] bytes = null;
		try { bytes = key.getBytes("UTF-8"); }
		catch (UnsupportedEncodingException uee) { bytes = key.getBytes(); }
		setKey(editKey(bytes));
	}

	private byte [] editKey(byte [] key)
	{
		if (key == null) { return null; }
		byte [] result = new byte [DEFAULT_KEY.length];
		for (int x=0; x<DEFAULT_KEY.length; x++)
		{
			result[x] = x < key.length ? key[x] : DEFAULT_KEY[x];
		}
		return result;
	}

}
