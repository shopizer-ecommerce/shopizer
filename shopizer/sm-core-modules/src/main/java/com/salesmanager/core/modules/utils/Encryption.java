package com.salesmanager.core.modules.utils;

/**
 * Can be used to encrypt block or information that has to
 * be maintained secret
 * @author Carl Samson
 *
 */
public interface Encryption {
	

	/**
	 * Encrypts a string value
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String value) throws Exception;
	
	/**
	 * Decrypts a string value
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String value) throws Exception;

}
