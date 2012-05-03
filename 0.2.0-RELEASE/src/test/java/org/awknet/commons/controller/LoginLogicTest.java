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

package org.awknet.commons.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.model.entity.User;
import org.junit.Before;
import org.junit.Test;

public class LoginLogicTest {
    private static String DEFAULT_PASSWORD_ENCRYPT = "229c3f7e7b9c1be5bfa2f46d90c4ab00";
    private static String DEFAULT_PASSWORD = "A12345678a";
    private LoginLogic instance;
    private DaoFactory daoFactory;
    private User user;
    private User user_invalid;
    private List<String> retrieveCodes;

    @Before
    public void setUp() throws Exception {
	daoFactory = new DaoFactory();
	instance = new LoginLogic(daoFactory);
	user = new User("95310731393", DEFAULT_PASSWORD);
	user_invalid = new User("1597536485", DEFAULT_PASSWORD);
	// for (RetrievePasswordLog rpLog :
	// daoFactory.getRetrievePasswordLogDao()
	// .list())
	// retrieveCodes.add(rpLog.getRetrieveCode());
    }

    @Test
    public void testDoLogin() {
	System.out.println("========== TEST DO LOGIN ==========");
	assertEquals("ok", instance.doLogin(user));
	assertEquals("invalid", instance.doLogin(user_invalid));
    }

    // @Test
    // public void testLoginLogic() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testLogin() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testLogout() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testRetrievePassword() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testRetrieveCodeValidation() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testUpdatePassword() {
    // for (String rc : retrieveCodes)
    // assertEquals("ok", instance.updatePassword("newPassword", rc));
    //
    // assertEquals("error",
    // instance.updatePassword("newPassword", "invalid_retrieveCode"));
    // }
    //
    // @Test
    // public void testGetLogin() {
    // fail("Not yet implemented");
    // }

}
