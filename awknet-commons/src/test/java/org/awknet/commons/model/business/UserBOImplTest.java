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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.exception.RetrieveCodeException;
import org.awknet.commons.exception.UserException;
import org.awknet.commons.model.entity.RetrievePasswordLog;
import org.awknet.commons.model.entity.User;
import org.awknet.commons.security.PasswordDefault;
import org.junit.Before;
import org.junit.Test;

// FIXME must run clean up DB script before execute this test! DBUnit!!
public class UserBOImplTest {

    private static final String DEFAULT_PROPERTIES_FILE = "/awknet-commons.properties";
    private static final String IP = "172.16.1.110";
    private DaoFactory daoFactory;
    private UserBOImpl instance;
    private User root;
    private User somebody;
    private User simple;
    private User someone;
    private User jcampos6669;
    private String subject, mailText;

    @Before
    public void setUp() throws Exception {
	daoFactory = new DaoFactory();
	instance = new UserBOImpl(daoFactory);
	subject = "[TEST] UserBOImpl email subject.";
	mailText = "[TEST] UserBOImpl email mailText.";
	root = daoFactory.getUserDao().load(new Long(1));
	somebody = daoFactory.getUserDao().load(new Long(2));
	simple = daoFactory.getUserDao().load(new Long(3));
	someone = daoFactory.getUserDao().load(new Long(4));
	jcampos6669 = new User(new Long(6669), "jcampos6669",
		"229c3f7e7b9c1be5bfa2f46d90c4ab00", "jcampos6669@awknet.org");
    }

    @Test
    public void testRewriteLogin() {
	assertEquals("c", instance.rewriteLogin("ç"));
	assertEquals("n", instance.rewriteLogin("ñ"));
	assertEquals("a", instance.rewriteLogin("á"));
	assertEquals("a", instance.rewriteLogin("à"));
	assertEquals("a", instance.rewriteLogin("ã"));
	assertEquals("a", instance.rewriteLogin("â"));
	assertEquals("a", instance.rewriteLogin("ä"));
	assertEquals("e", instance.rewriteLogin("é"));
	assertEquals("e", instance.rewriteLogin("è"));
	assertEquals("e", instance.rewriteLogin("ẽ"));
	assertEquals("e", instance.rewriteLogin("ê"));
	assertEquals("e", instance.rewriteLogin("ë"));
	assertEquals("i", instance.rewriteLogin("í"));
	assertEquals("i", instance.rewriteLogin("ì"));
	assertEquals("i", instance.rewriteLogin("ĩ"));
	assertEquals("i", instance.rewriteLogin("î"));
	assertEquals("i", instance.rewriteLogin("ï"));
	assertEquals("o", instance.rewriteLogin("ó"));
	assertEquals("o", instance.rewriteLogin("ò"));
	assertEquals("o", instance.rewriteLogin("õ"));
	assertEquals("o", instance.rewriteLogin("ô"));
	assertEquals("o", instance.rewriteLogin("ö"));
	assertEquals("u", instance.rewriteLogin("ú"));
	assertEquals("u", instance.rewriteLogin("ù"));
	assertEquals("u", instance.rewriteLogin("ũ"));
	assertEquals("u", instance.rewriteLogin("û"));
	assertEquals("u", instance.rewriteLogin("ü"));
    }

    /**
     * A simple test of encryption. The encryption itself is tested by
     * EncryptionTest.testGenericgenericEncrypt()
     */
    @Test
    public void testEncryptPassword() throws NoSuchAlgorithmException {
	String pass_A12345678a = "229c3f7e7b9c1be5bfa2f46d90c4ab00";
	assertEquals(pass_A12345678a, instance.encryptPassword("A12345678a"));
    }

    // @Test
    // public void testCreateUser() {
    // assertEquals("jcampos6669", instance.createUser("Jefferson Campos"));
    // }

    // @Test
    // public void testVerifyUser() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testResetPassword() {
    // fail("Not yet implemented");
    // }

    // @Test
    // public void testSendLinkToRetrievePassword() throws Exception {
    // User inexistent_user = new User();
    // User login_someone = new User();
    // User email_user = new User();
    //
    // inexistent_user.setLogin("fake");
    // login_someone.setLogin("02598649435");
    // email_user.setEmail(simple.getEmail());
    //
    // assertFalse(instance.sendLinkToRetrievePassword(inexistent_user,
    // subject, mailText, DEFAULT_PROPERTIES_FILE));
    // assertTrue(instance.sendLinkToRetrievePassword(login_someone, subject,
    // mailText, DEFAULT_PROPERTIES_FILE));
    // assertTrue(instance.sendLinkToRetrievePassword(email_user, subject,
    // mailText, DEFAULT_PROPERTIES_FILE));
    // }

    // @Test(expected = UserException.class)
    // // FIXME return hibernate not uniquye exception!!
    // public void testSendLinkRetrivePasswordUsingPassword() throws
    // UserException {
    // User password_user = new User();
    // password_user.setPassword(simple.getPassword());
    //
    // instance.sendLinkToRetrievePassword(password_user, subject, mailText,
    // DEFAULT_PROPERTIES_FILE);
    // }

    // @Test(expected = UserException.class)
    // public void testSendLinkRetrivePasswordUsingID() throws UserException {
    // User id_user = new User();
    // id_user.setID(new Long(1));
    //
    // instance.sendLinkToRetrievePassword(id_user, subject, mailText,
    // DEFAULT_PROPERTIES_FILE);
    // }

    @Test
    public void testGenerateCodeToRetrievePassword() throws UserException,
	    RetrieveCodeException {
	List<String> retrieveCodeGenerated = new ArrayList<String>();

	System.out.println("Retrieve code generated:");

	retrieveCodeGenerated.add(instance.generateCodeToRetrievePassword(
		root.getID(), IP));
	retrieveCodeGenerated.add(instance.generateCodeToRetrievePassword(
		somebody.getID(), IP));
	retrieveCodeGenerated.add(instance.generateCodeToRetrievePassword(
		simple.getID(), IP));
	retrieveCodeGenerated.add(instance.generateCodeToRetrievePassword(
		someone.getID(), IP));

	for (String rc : retrieveCodeGenerated) {
	    assertFalse(rc.equals(""));
	    assertFalse(daoFactory.getRetrievePasswordLogDao()
		    .findRetrieveCode(rc).getUpdated());
	    System.out.println("For code: "
		    + rc
		    + " updated field is: "
		    + daoFactory.getRetrievePasswordLogDao()
			    .findRetrieveCode(rc).getUpdated());
	}
    }

    @Test
    public void testIsValidRequest() throws RetrieveCodeException {
	List<RetrievePasswordLog> codes = daoFactory
		.getRetrievePasswordLogDao().list();

	for (RetrievePasswordLog rpLog : codes)
	    assertTrue(instance.isValidRequest(new Date(),
		    rpLog.getRetrieveCode()));
    }

    /**************************************************************************/
    // @Test
    // FIXME need clean DB before use! need use DBunit here!!
    public void testUpdatePassword() {
	// // List<String> retrieveCodes = new ArrayList<String>();
	//
	// List<RetrievePasswordLog> rpLogInDB = daoFactory
	// .getRetrievePasswordLogDao().list();
	// List<User> users = daoFactory.getRetrievePasswordLogDao()
	// .getUsersThaCanUseRetrieveCode();
	//
	// for (RetrievePasswordLog rpLog : rpLogInDB) {
	// // retrieveCodes.add(rpLog.getRetrieveCode());
	// assertTrue(instance.updatePassword(PasswordDefault.pass_somePass,
	// rpLog.getRetrieveCode()));
	// }
	//
	// // for (String rc : retrieveCodes) {
	// // assertTrue(instance.updatePassword(PasswordDefault.pass_somePass,
	// // rc));
	// // }
	//
	// for (User user : users)
	// assertEquals(PasswordDefault.pass_somePass_encrypted,
	// user.getPassword());
    }

    // @Test(expected = RetrieveCodeException.class)
    @Test
    public void testUpdatePasswordInvalidRequest() {
	// try {
	// if (!isValidRequest(new Date(), retrieveCode))
	// return false;

	assertFalse(instance.updatePassword(PasswordDefault.pass_somePass,
		"invalid_retrieveCode"));
    }

    @Test
    public void testUpdatePasswordVoidPassword() throws UserException,
	    RetrieveCodeException {
	// if (entity.getPassword().equals(""))
	// return false;

	String validRetrieveCode = instance.generateCodeToRetrievePassword(
		new Long(1), "172.16.1.112");

	// RetrievePasswordLog rpLog = daoFactory.getRetrievePasswordLogDao()
	// .findRetrieveCode(validRetrieveCode);
	User user = daoFactory.getRetrievePasswordLogDao()
		.getUserByRetrieveCode(validRetrieveCode);

	instance.updatePasswordToDefault(validRetrieveCode);
	assertEquals(PasswordDefault.pass_A12345678a_encrypted,
		user.getPassword());
    }
    /**************************************************************************/
}
