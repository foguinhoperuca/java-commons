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

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

public class EncryptionTest {

    private Encryption instance;
    
    @Before
    public void setUp() throws Exception {
	instance = new Encryption();
    }

    /**
     * Strings used:<br/>
     * <ul>
     * <li>A12345678a</li>
     * <li>someP@ss</li>
     * <li>adminadmin</li>
     * <li>masterkey</li>
     * <li>xpto</li>
     * <li>S3cur3P@ssw0rd</li>
     * <li>L4MM3R</li>
     * <li>H0ax_B00t3r</li>
     * <li>CR4CK3R</li>
     * <li>123456</li>
     * </ul>
     * 
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGenericgenericEncrypt() throws NoSuchAlgorithmException {
	String pass_A12345678a = "229c3f7e7b9c1be5bfa2f46d90c4ab00";
	String pass_somePass = "7800be194fe996624b31dbf1b40ebccf";
	String pass_adminadmin = "f6fdffe48c908deb0f4c3bd36c032e72";
	String pass_masterkey = "abe6db4c9f5484fae8d79f2e868a673c";
	String pass_xpto = "3851b1ae73ca0ca6e3c24a0256a80ace";
	String pass_securePassword = "bda1c3cb732175a19d94520cd477cd7e";
	String pass_lammer = "775ec15b5e82ae0cd4b6df1f8b8cec67";
	String pass_hoax_booter = "8ec7273d9892e7e61987e8d3b0511db9";
	String pass_cracker = "f9c750645482711942bd928e27336288";
	String pass_123456 = "e10adc3949ba59abbe56e057f20f883e";

	// System.out.println(instance.genericEncrypt("A12345678a"));
	// System.out.println(instance.genericEncrypt("someP@ss"));
	// System.out.println(instance.genericEncrypt("adminadmin"));
	// System.out.println(instance.genericEncrypt("masterkey"));
	// System.out.println(instance.genericEncrypt("xpto"));
	// System.out.println(instance.genericEncrypt("S3cur3P@ssw0rd"));
	// System.out.println(instance.genericEncrypt("L4MM3R"));
	// System.out.println(instance.genericEncrypt("H0ax_B00t3r"));
	// System.out.println(instance.genericEncrypt("CR4CK3R"));
	// System.out.println(instance.genericEncrypt("123456"));

	assertEquals(pass_A12345678a, instance.genericEncrypt("A12345678a"));
	assertEquals(pass_somePass, instance.genericEncrypt("someP@ss"));
	assertEquals(pass_adminadmin, instance.genericEncrypt("adminadmin"));
	assertEquals(pass_masterkey, instance.genericEncrypt("masterkey"));
	assertEquals(pass_xpto, instance.genericEncrypt("xpto"));
	assertEquals(pass_securePassword,
		instance.genericEncrypt("S3cur3P@ssw0rd"));
	assertEquals(pass_lammer, instance.genericEncrypt("L4MM3R"));
	assertEquals(pass_hoax_booter, instance.genericEncrypt("H0ax_B00t3r"));
	assertEquals(pass_cracker, instance.genericEncrypt("CR4CK3R"));
	assertEquals(pass_123456, instance.genericEncrypt("123456"));
	System.out.println("Testing Ecnryptation DONE!");
    }

}
