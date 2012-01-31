/*
 * Copyright (C) 2011 Jefferson Campos <foguinho.peruca@gmail.com>
 * This file is part of awknet-commons - http://awknet-commons.awknet.org
 *
 * Awknet-commons is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Awknet-commons is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with awknet-commons. If not, see <http://www.gnu.org/licenses/>.
 */

package org.awknet.commons.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO use http://www.jasypt.org/
public class Encryption {

	private static final Log LOG = LogFactory.getLog(Encryption.class);

	/**
	 * <p>
	 * This function encrypt the password with MD5 method.
	 * </p>
	 * 
	 * @param value
	 *            : a password to be encrypt.
	 * @return A encrypted password.
	 * @since SIGERAR v1.1 - April/2008.
	 * @throws NoSuchAlgorithmException
	 */
	public String genericEncryptMD5(String value)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
		String encryptedValue = hash.toString(16);
		if (encryptedValue.length() % 2 != 0) {
			encryptedValue = "0" + encryptedValue;
		}
		return encryptedValue;
	}

	public static String encrypt(String value) {
		try {
			return new Encryption().genericEncryptMD5(value);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("[CRYPT] Error during the encryptation of password!", e);
		}
		return null;
	}
}
