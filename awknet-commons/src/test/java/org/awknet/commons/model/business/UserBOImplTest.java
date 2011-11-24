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

import junit.framework.TestCase;

import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.exception.UserException;
import org.awknet.commons.model.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserBOImplTest extends TestCase {

    private DaoFactory daoFactory;
    private UserBOImpl instance;
    private User simple;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    protected void setUp() throws Exception {
	daoFactory = new DaoFactory();
	instance = new UserBOImpl(daoFactory);
	simple = daoFactory.getUserDao().load(new Long(3));
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

    // @Test
    // public void testEncryptPassword() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testCreateUser() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testVerifyUser() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testResetPassword() {
    // fail("Not yet implemented");
    // }

    @Test(expected = UserException.class)
    public void testSendLinkToRetrievePassword() throws UserException {
	User inexistent_user = new User();
	User login_someone = new User();
	User id_user = new User();
	User password_user = new User();
	User email_user = new User();

	inexistent_user.setLogin("fake");
	login_someone.setLogin("someone");
	id_user.setID(new Long(1));
	password_user.setPassword(simple.getPassword());
	email_user.setEmail(simple.getEmail());

	assertFalse(instance.sendLinkToRetrievePassword(inexistent_user));
	assertTrue(instance.sendLinkToRetrievePassword(login_someone));
	try {
	    assertFalse(instance.sendLinkToRetrievePassword(password_user));
	    assertFalse(instance.sendLinkToRetrievePassword(id_user));
	} catch (UserException ue) {
	}

	assertTrue(instance.sendLinkToRetrievePassword(email_user));
	//assertTrue(instance.sendLinkToRetrievePassword(simple));
    }
}
