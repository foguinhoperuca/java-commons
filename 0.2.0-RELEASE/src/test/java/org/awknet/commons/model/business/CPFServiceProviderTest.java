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

package org.awknet.commons.model.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.awknet.commons.exception.CPFException;
import org.junit.Before;
import org.junit.Test;

public class CPFServiceProviderTest {

    // private static final Log LOG = LogFactory
    // .getLog(CPFServiceProviderTest.class);
    private String validCPFComplete = "";

    @Before
    public void setUp() throws Exception {

    }

    // FIXME Rewrite it!
    // @Test(expected=Exception.class)
    // public void testValidateInternalCPF() throws Exception {
    // clean.validateInternalCPF();
    // }
    //
    // @Test
    // public void testValidate() throws Exception {
    // assertTrue(clean.validate("346104938", 7, 0));
    // assertFalse(invalid.validateInternalCPF());
    // assertFalse(sameNumber.validateInternalCPF());
    // assertTrue(valid.validateInternalCPF());
    // }

    @Test
    public void testGetDigits() {
	CPFServiceProvider.getDigits(null);
    }

    // @Test
    // public void testValidate() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testValidateDocumentBody() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testCalculateFirstDigit() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testCalculateSecondDigit() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testGetCPFBody() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testGetFirstDigit() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testGetSecondDigit() {
    // fail("Not yet implemented");
    // }

    @Test
    public void testGenerateValidCPF() throws CPFException {
	validCPFComplete = "";
	for (int i = 0; i < 10; i++) {
	    validCPFComplete = CPFServiceProvider.generateValidCPF();
	    assertNotNull(validCPFComplete);
	    System.out.println("Re-validation of CPF: " + validCPFComplete);
	    assertTrue(CPFServiceProvider.validate(validCPFComplete));
	    System.out.println("CPF IS VALID: " + validCPFComplete + " ######");
	}
    }
    // @Test
    // public void testMask() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testUnmask() {
    // fail("Not yet implemented");
    // }

}
