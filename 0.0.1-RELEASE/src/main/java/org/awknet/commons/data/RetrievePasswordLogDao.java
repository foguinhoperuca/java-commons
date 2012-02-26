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

import java.util.List;

import org.awknet.commons.model.entity.RetrievePasswordLog;
import org.awknet.commons.model.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;

// TODO must implement all tests!
public class RetrievePasswordLogDao extends Dao<RetrievePasswordLog> {

    public RetrievePasswordLogDao(Session session) {
	super(session, RetrievePasswordLog.class);
    }

    /**
     * Find a retrieve code already in DB.
     * 
     * @param rpLog
     * @return
     */
    public RetrievePasswordLog findRetrieveCode(String retrieveCode) {
	String hql = "SELECT r FROM RetrievePasswordLog AS r "
		+ "WHERE r.retrieveCode = :retrieveCode";
	Query query = getSession().createQuery(hql);
	query.setParameter("retrieveCode", retrieveCode);

	return (RetrievePasswordLog) query.uniqueResult();
    }

    // TODO must implement it UserBOImpl.updatePassword
    public User getUserByRetrieveCode(String retrieveCode) {
	String hql = "SELECT u FROM User AS u, RetrievePasswordLog AS r "
		+ "WHERE u.ID = r.userId AND r.retrieveCode = :retrieveCode";
	Query query = getSession().createQuery(hql);
	query.setParameter("retrieveCode", retrieveCode);

	return (User) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsersThaCanUseRetrieveCode() {
	String hql = "SELECT u FROM User AS u, RetrievePasswordLog AS r "
		+ "WHERE u.ID = r.userId AND r.updated = FALSE GROUP BY u.login";
	Query query = getSession().createQuery(hql);

	return (List<User>) query.list();
    }

    // FIXME implement all tests!
    public int updateRetrieveCodeUnused(Long userID) {
	String hql = "UPDATE RetrievePasswordLog AS r SET updated = TRUE "
		+ "WHERE userID = :userID AND updated = FALSE";

	Query query = getSession().createQuery(hql);
	query.setParameter("userID", userID);
	return query.executeUpdate();

    }
}
