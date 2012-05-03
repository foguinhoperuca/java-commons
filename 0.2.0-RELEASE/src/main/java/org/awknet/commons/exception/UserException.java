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
	public static final String MSG_GENERIC = "[USER] NOSENSE!!! - GENERIC ERROR WITH USER!";
	public static final String MSG_ID = "[USER] NOSENSE a user with ID!";
	public static final String MSG_PASSWORD = "[USER] NOSENSE a user with PASSWORD!";
	public static final String MSG_EMAIL_NULL = "[USER] user without PASSWORD in DB!";
	public static final String MSG_LOGIN = "[USER] User with LOGIN INVALID!";
	public static final String MSG_VALIDATION = "[USER] User is INVALID!";
	public static final String MSG_PERSIST = "[USER] User COULD NOT BE SAVED/UPDATED!";
	public static final String MSG_ENCRYPT_PASSWORD = "[USER] Encryption raised an exception!";
	public static final int ID = 0;
	public static final int PASSWORD = 1;
	public static final int MAIL_NULL = 2;
	public static final int LOGIN = 3;
	public static final int VALIDATION = 4;
	public static final int PERSIST = 5;
	public static final int ENCRYPT_PASSWORD = 6;
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
		case ID:
			LOG.error(UserException.MSG_ID);
			return UserException.MSG_ID;
		case PASSWORD:
			LOG.error(UserException.MSG_PASSWORD);
			return UserException.MSG_PASSWORD;
		case MAIL_NULL:
			LOG.error(UserException.MSG_EMAIL_NULL);
			return UserException.MSG_EMAIL_NULL;
		case LOGIN:
			LOG.error(UserException.MSG_LOGIN);
			return UserException.MSG_LOGIN;
		case VALIDATION:
			LOG.error(UserException.MSG_VALIDATION);
			return UserException.MSG_VALIDATION;
		case PERSIST:
			LOG.error(UserException.MSG_PERSIST);
			return UserException.MSG_PERSIST;
		case ENCRYPT_PASSWORD:
			LOG.error(UserException.MSG_PERSIST);
			return UserException.MSG_PERSIST;
		default:
			LOG.error(MSG_GENERIC);
			return MSG_GENERIC;
		}
	}
}
