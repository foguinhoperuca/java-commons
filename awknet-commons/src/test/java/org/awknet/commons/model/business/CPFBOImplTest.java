package org.awknet.commons.model.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CPFBOImplTest {

    private CPFBOImpl sameNumber;
    private CPFBOImpl internal;
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
