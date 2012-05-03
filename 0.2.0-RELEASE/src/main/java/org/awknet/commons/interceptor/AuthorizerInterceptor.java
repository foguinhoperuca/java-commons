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

package org.awknet.commons.interceptor;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.awknet.commons.model.entity.User;
import org.vraptor.Interceptor;
import org.vraptor.LogicException;
import org.vraptor.LogicFlow;
import org.vraptor.annotations.In;
import org.vraptor.scope.ScopeType;
import org.vraptor.view.ViewException;

public class AuthorizerInterceptor implements Interceptor {

	@In(scope = ScopeType.SESSION, required = false, key = "login")
	private User login;
	private static final Log LOG = LogFactory.getLog(AuthorizerInterceptor.class);

	public void intercept(LogicFlow flow) throws LogicException, ViewException {
		if (this.login == null) {
			LOG.info("[AUTHORIZERINTERCEPTOR] Login was NULL!!");
			try {
				flow.getLogicRequest().getResponse()
						.sendRedirect("login.login.logic");
			} catch (IOException e) {
				throw new LogicException(e);
			}
		} else {
			flow.execute();
		}
	}
}
