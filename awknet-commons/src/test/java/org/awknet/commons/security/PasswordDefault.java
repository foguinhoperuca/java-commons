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

import java.util.ArrayList;
import java.util.List;

public final class PasswordDefault {
	public static final String pass_A12345678a_encrypted = "229c3f7e7b9c1be5bfa2f46d90c4ab00";
	public static final String pass_somePass_encrypted = "7800be194fe996624b31dbf1b40ebccf";
	public static final String pass_adminadmin_encrypted = "f6fdffe48c908deb0f4c3bd36c032e72";
	public static final String pass_masterkey_encrypted = "abe6db4c9f5484fae8d79f2e868a673c";
	public static final String pass_xpto_encrypted = "3851b1ae73ca0ca6e3c24a0256a80ace";
	public static final String pass_securePassword_encrypted = "bda1c3cb732175a19d94520cd477cd7e";
	public static final String pass_lammer_encrypted = "775ec15b5e82ae0cd4b6df1f8b8cec67";
	public static final String pass_hoax_booter_encrypted = "8ec7273d9892e7e61987e8d3b0511db9";
	public static final String pass_cracker_encrypted = "f9c750645482711942bd928e27336288";
	public static final String pass_123456_encrypted = "e10adc3949ba59abbe56e057f20f883e";

	public static final String pass_A12345678a = "A12345678a";
	public static final String pass_somePass = "someP@ss";
	public static final String pass_adminadmin = "adminadmin";
	public static final String pass_masterkey = "masterkey";
	public static final String pass_xpto = "xpto";
	public static final String pass_securePassword = "S3cur3P@ssw0rd";
	public static final String pass_lammer = "L4MM3R";
	public static final String pass_hoax_booter = "H0ax_B00t3r";
	public static final String pass_cracker = "CR4CK3R";
	public static final String pass_123456 = "123456";

	public static List<String> getDefaultPasswordEncrypted() {
		List<String> passwordsEncrypted = new ArrayList<String>();

		passwordsEncrypted.add(pass_A12345678a);
		passwordsEncrypted.add(pass_somePass);
		passwordsEncrypted.add(pass_adminadmin);
		passwordsEncrypted.add(pass_masterkey);
		passwordsEncrypted.add(pass_xpto);
		passwordsEncrypted.add(pass_securePassword);
		passwordsEncrypted.add(pass_lammer);
		passwordsEncrypted.add(pass_hoax_booter);
		passwordsEncrypted.add(pass_cracker);
		passwordsEncrypted.add(pass_123456);

		return passwordsEncrypted;
	}

	public static List<String> getDefaultPassword() {
		List<String> passwords = new ArrayList<String>();

		passwords.add(pass_A12345678a);
		passwords.add(pass_somePass);
		passwords.add(pass_adminadmin);
		passwords.add(pass_masterkey);
		passwords.add(pass_xpto);
		passwords.add(pass_securePassword);
		passwords.add(pass_lammer);
		passwords.add(pass_hoax_booter);
		passwords.add(pass_cracker);
		passwords.add(pass_123456);

		return passwords;
	}
}
