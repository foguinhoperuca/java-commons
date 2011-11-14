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

import org.awknet.commons.data.DaoFactory;
import org.vraptor.Interceptor;
import org.vraptor.LogicException;
import org.vraptor.LogicFlow;
import org.vraptor.view.ViewException;
import org.vraptor.annotations.Out;

/**
 * 
 * This "interceptor" is aimed to VRaptor 2.6.
 *
 */
public class DaoInterceptor implements Interceptor {

	private final DaoFactory factory = new DaoFactory();

	public void intercept(LogicFlow flow) throws LogicException, ViewException {
		flow.execute();
		if (factory.hasTransaction()) {
			factory.rollback();
		}
		factory.close();
	}

	@Out(key = "org.awknet.commons.data.DaoFactory")
	public DaoFactory getFactory() {
		return factory;
	}
}