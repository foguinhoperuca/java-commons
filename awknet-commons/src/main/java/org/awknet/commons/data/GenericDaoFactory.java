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

package org.awknet.commons.data;

import org.awknet.commons.model.entity.BaseEntity;
import org.awknet.commons.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * This class provide basic features to access DB.
 * 
 */
// FIXME use a interface here?
public class GenericDaoFactory {

	private final Session session;
	private Transaction transaction;

	public GenericDaoFactory() {
		session = HibernateUtil.getSession();
	}

	public void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	public void commit() {
		this.transaction.commit();
		this.transaction = null;
	}

	public boolean hasTransaction() {
		return this.transaction != null;
	}

	public void rollback() {
		this.transaction.rollback();
		this.transaction = null;
	}

	public void close() {
		this.session.close();
	}

	public Session getSession() {
		return this.session;
	}

	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> Dao<T> getRegisterDao(Class _clazz) {
		return new Dao<T>(session, _clazz);
	}
}
