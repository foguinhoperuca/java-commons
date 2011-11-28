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
    public static String MSG_ID_ERROR = "NOSENSE a user with ID!";
    public static String MSG_PASSWORD_ERROR = "NOSENSE a user with PASSWORD!";
    public static int ID_ERROR = 0;
    public static int PASSWORD_ERROR = 1;
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
	    LOG.error(UserException.MSG_ID_ERROR);
	    return UserException.MSG_ID_ERROR;
	case 1:
	    LOG.error(UserException.MSG_PASSWORD_ERROR);
	    return UserException.MSG_PASSWORD_ERROR;
	default:
	     LOG.error("NOSENSE!!! - USER WITH PASSWORD AND ID!");
	    return "NOSENSE!!!";
	}
    }

}
