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

import org.awknet.commons.model.entity.RetrievePasswordLog;
import org.hibernate.Query;
import org.hibernate.Session;

public class RetrievePasswordLogDao extends Dao<RetrievePasswordLog> {

    public RetrievePasswordLogDao(Session session) {
	super(session, RetrievePasswordLog.class);
    }

    /**
     * Find a retrieve code already in DB.
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
}
