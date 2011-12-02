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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CPFBOImplTest {

    private CPFBOImpl sameNumber;
    private CPFBOImpl invalid;
    private CPFBOImpl valid;
    private CPFBOImpl clean;
    
    @Before
    public void setUp() throws Exception {
	sameNumber = new CPFBOImpl("111111111", 1, 1);
	invalid = new CPFBOImpl("156753479", 6, 9);
	valid = new CPFBOImpl("346104938", 7, 0);
	clean = new CPFBOImpl();
    }

    @Test(expected=Exception.class)
    public void testValidateInternalCPF() throws Exception {
	clean.validateInternalCPF();
    }
    
    @Test
    public void testValidate() throws Exception {
	assertTrue(clean.validate("346104938", 7, 0));
	assertFalse(invalid.validateInternalCPF());
	assertFalse(sameNumber.validateInternalCPF());
	assertTrue(valid.validateInternalCPF());
    }

    /*@Test
    public void testIsValid() {
	fail("Not yet implemented");
    }

    @Test
    public void testCalculateFirstDigit() {
	fail("Not yet implemented");
    }

    @Test
    public void testCalculateSecondDigit() {
	fail("Not yet implemented");
    }*/

}
