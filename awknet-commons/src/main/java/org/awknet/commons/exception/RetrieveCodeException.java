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

// TODO internationalization of messages!
public class RetrieveCodeException extends Exception {
	private static final long serialVersionUID = 6196598941109275246L;
	private static final Log LOG = LogFactory.getLog(UserException.class);
	public static String MSG_RETRIEVE_CODE_ERROR = "[RETRIEVE CODE EXCEPTION] a user with ID already in use!";
	public static String MSG_LOGIN_ERROR = "[RETRIEVE CODE EXCEPTION] error with login of user!";
	public static String MSG_EMAIL_ERROR = "[RETRIEVE CODE EXCEPTION] error with email of user!";
	public static String MSG_IP_ERROR = "[RETRIEVE CODE EXCEPTION] IP was null but is MANDATORY!";
	public static String MSG_DATE_ERROR = "[RETRIEVE CODE EXCEPTION] error with date!";
	public static String MSG_GENERIC_ERROR = "[RETRIEVE CODE EXCEPTION] IT WAS THROWN A GENERIC ERROR!";
	public static int RETRIEVE_CODE_ERROR = 0;
	public static int PASSWORD_ERROR = 1;
	public static int MAIL_NULL = 2;
	public static int IP_ERROR = 3;
	public static int DATE_ERROR = 4;

	private int code;

	public RetrieveCodeException(int error) {
		this.code = error;
	}

	public RetrieveCodeException(RetrieveCodeExceptionType error) {
		this.code = error.getType();
	}

	@Override
	public String getMessage() {
		switch (code) {
		case 0:
			LOG.error(RetrieveCodeException.MSG_RETRIEVE_CODE_ERROR);
			return RetrieveCodeException.MSG_RETRIEVE_CODE_ERROR;
		case 1:
			LOG.error(RetrieveCodeException.MSG_LOGIN_ERROR);
			return RetrieveCodeException.MSG_LOGIN_ERROR;
		case 2:
			LOG.error(RetrieveCodeException.MSG_EMAIL_ERROR);
			return RetrieveCodeException.MSG_EMAIL_ERROR;
		case 3:
			LOG.error(RetrieveCodeException.MSG_IP_ERROR);
			return RetrieveCodeException.MSG_IP_ERROR;
		case 4:
			LOG.error(RetrieveCodeException.MSG_DATE_ERROR);
			return RetrieveCodeException.MSG_DATE_ERROR;
		default:
			LOG.error(MSG_GENERIC_ERROR);
			return MSG_GENERIC_ERROR;
		}
	}
}
