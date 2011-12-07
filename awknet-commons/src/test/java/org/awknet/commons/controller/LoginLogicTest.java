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
import org.awknet.commons.model.entity.RetrievePasswordLog;
import org.awknet.commons.model.entity.User;
import org.junit.Before;
import org.junit.Test;

public class LoginLogicTest {

	private LoginLogic instance;
	private DaoFactory daoFactory;
	private User user;
	private List<String> retrieveCodes;

	@Before
	public void setUp() throws Exception {
		daoFactory = new DaoFactory();
		instance = new LoginLogic(daoFactory);
		user = new User();
		for (RetrievePasswordLog rpLog : daoFactory.getRetrievePasswordLogDao()
				.list())
			retrieveCodes.add(rpLog.getRetrieveCode());
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
	// public void testDoLogin() {
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
