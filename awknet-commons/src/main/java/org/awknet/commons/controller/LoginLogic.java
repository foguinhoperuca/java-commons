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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.exception.RetrieveCodeException;
import org.awknet.commons.interceptor.DaoInterceptor;
import org.awknet.commons.model.business.UserBOImpl;
import org.awknet.commons.model.entity.RetrievePasswordLog;
import org.awknet.commons.model.entity.User;
import org.vraptor.annotations.Component;
import org.vraptor.annotations.InterceptedBy;
import org.vraptor.annotations.Logic;
import org.vraptor.annotations.Out;
import org.vraptor.scope.ScopeType;

// TODO pre-compile a .jsp with form (without css or using a default). Maybe use gwt
@Component
@InterceptedBy(DaoInterceptor.class)
public class LoginLogic {

	private final DaoFactory daoFactory;
	private UserBOImpl userBO;
	private User login;
	private static final Log LOG = LogFactory.getLog(LoginLogic.class);

	// FIXME must use new userBO in constructor or method? See Memory usage....
	public LoginLogic(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		userBO = new UserBOImpl(daoFactory);
	}

	public void login() {
	}

	public String doLogin(User _entity) {
		if (userBO.verifyUser(_entity)) {
			login = userBO.getUser();
			return "ok";
		} else {
			return "invalid";
		}
	}

	public void logout() {
		this.login = null;
	}

	public void retrievePassword() {
	}

	@Logic(parameters = "retrieveCode")
	public String retrieveCodeValidation(String retrieveCode) {
		if (retrieveCode == null)
			return "error";

		LOG.debug("RETRIEVE CODE IS: " + retrieveCode);

		try {
			if (userBO.isValidRequest(new Date(), retrieveCode))
				return "ok";
		} catch (RetrieveCodeException e) {
			LOG.error("[RETRIEVE CODE] retrieve code #:" + retrieveCode, e);
			return "error";
		}

		LOG.info("[RETRIEVE CODE] Retrieve code is invalid!!");
		return "error";
	}

	@Logic(parameters = { "newPassword", "retrieveCode" })
	public String updatePassword(String newPassword, String retrieveCode) {
		if (!userBO.updatePassword(newPassword, retrieveCode))
			return "error";

		return "ok";
	}

	@Out(scope = ScopeType.SESSION)
	public User getLogin() {
		return login;
	}
}
