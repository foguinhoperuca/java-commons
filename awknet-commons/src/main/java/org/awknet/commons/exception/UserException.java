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

package org.awknet.commons.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserException extends Exception {

    private static final long serialVersionUID = 6196598941109275246L;
    private static final Log LOG = LogFactory.getLog(UserException.class);
    public static String MSG_GENERIC = "[USER] NOSENSE!!! - GENERIC ERROR WITH USER!";
    public static String MSG_ID = "[USER] NOSENSE a user with ID!";
    public static String MSG_PASSWORD = "[USER] NOSENSE a user with PASSWORD!";
    public static String MSG_EMAIL_NULL = "[USER] user without PASSWORD in DB!";
    public static String MSG_LOGIN = "[USER] User with LOGIN INVALID!";
    public static String MSG_VALIDATION = "[USER] User is INVALID!";
    public static int ID = 0;
    public static int PASSWORD = 1;
    public static int MAIL_NULL = 2;
    public static int LOGIN = 3;
    private int code;

    public UserException(int error) {
	this.code = error;
    }

    public UserException(UserExceptionType error) {
	this.code = error.getType();
    }

    @Override
    public String getMessage() {
	switch (code) {
	case 0:
	    LOG.error(UserException.MSG_ID);
	    return UserException.MSG_ID;
	case 1:
	    LOG.error(UserException.MSG_PASSWORD);
	    return UserException.MSG_PASSWORD;
	case 2:
	    LOG.error(UserException.MSG_EMAIL_NULL);
	    return UserException.MSG_EMAIL_NULL;
	case 3:
	    LOG.error(UserException.MSG_LOGIN);
	    return UserException.MSG_LOGIN;
	case 4:
	    LOG.error(UserException.MSG_VALIDATION);
	    return UserException.MSG_VALIDATION;
	default:
	    LOG.error(MSG_GENERIC);
	    return MSG_GENERIC;
	}
    }
}
