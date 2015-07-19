package com.stan.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AesEncryptorTest {

	@Test
	public void test() {
		
		AesEncryptor aes = new AesEncryptor("TestKey");

		String a = "StanWang52";

		String b = aes.encrypt(a);

		assertEquals(a, aes.decrypt(b));

	}

}
