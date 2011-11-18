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

import org.awknet.commons.model.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDao extends Dao<User> {

    public UserDao(Session session) {
	super(session, User.class);
    }

    /**
     * Search for a unique user with login/password;
     */
    public User onlyOne(User _user) {
	String hql = "SELECT u FROM User AS u WHERE u.login = :login AND "
		+ "u.password = :password";
	Query query = getSession().createQuery(hql);
	query.setParameter("login", _user.getLogin());
	query.setParameter("password", _user.getPassword());

	return (User) query.uniqueResult();
    }
}
