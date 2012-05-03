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

package org.awknet.commons.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

// FIXME verify doc - new use of HibernateUtil
@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static final Log LOG = LogFactory.getLog(HibernateUtil.class);
    private static final SessionFactory FACTORY;

    static {
	try {
	    Configuration conf = new AnnotationConfiguration();
	    conf.configure();
	    FACTORY = conf.buildSessionFactory();

	} catch (Exception ex) {
	    LOG.error("Ow CRAP! Hibernate Session Factory creation !!", ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    public static Session getSession() {
	return FACTORY.openSession();
    }

    public static SessionFactory getSessionFactory() {
	return FACTORY;
    }

}