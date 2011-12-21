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

public class CPFException extends Exception {

    private static final long serialVersionUID = 8288239246941573999L;
    private static final Log LOG = LogFactory.getLog(UserException.class);
    public static String MSG_BODY_EMPTY_ERROR = "[CPF EXCEPTION] CPF was null. Please, fill it!";
    public static String MSG_FIRST_DIGIT_VALIDATION_ERROR = "[CPF EXCEPTION] FIRST digit is not valid!";
    public static String MSG_SECOND_DIGIT_VALIDATION_ERROR = "[CPF EXCEPTION] SECOND digit is not valid!";
    public static String MSG_BODY_VALIDATION_ERROR = "[CPF EXCEPTION] CPF BODY is invalid!";
    public static String MSG_COMPLETE_VALIDATION_ERROR = "[CPF EXCEPTION] CPF COMPLETE is invalid!";
    public static String MSG_CPF_GENERATION_ERROR = "[CPF EXCEPTION] CPF COMPLETE is INVALID!";
    public static String MSG_COMPLETE_EMPTY_ERROR = "[CPF EXCEPTION] CPF COMPLETE is EMPTY!";
    public static String MSG_GENERIC_ERROR = "[CPF EXCEPTION] IT WAS THROWN A GENERIC ERROR!";
    public static int BODY_EMPTY_ERROR = 0;
    public static int FIRST_DIGIT_VALIDATION_ERROR = 1;
    public static int SECOND_DIGIT_VALIDATION_ERROR = 2;
    public static int BODY_VALIDATION_ERROR = 3;
    public static int COMPLETE_VALIDATION_ERROR = 4;
    public static int CPF_GENERATION_ERROR = 5;
    public static int CPF_COMPLETE_EMPTY_ERROR = 6;

    private int code;

    public CPFException(int error) {
	this.code = error;
    }

    public CPFException(CPFExceptionType error) {
	this.code = error.getType();
    }

    @Override
    public String getMessage() {
	switch (code) {
	case 0:
	    LOG.error(MSG_BODY_EMPTY_ERROR);
	    return MSG_BODY_EMPTY_ERROR;
	case 1:
	    LOG.error(MSG_FIRST_DIGIT_VALIDATION_ERROR);
	    return MSG_FIRST_DIGIT_VALIDATION_ERROR;
	case 2:
	    LOG.error(MSG_SECOND_DIGIT_VALIDATION_ERROR);
	    return MSG_SECOND_DIGIT_VALIDATION_ERROR;
	case 3:
	    LOG.error(MSG_BODY_VALIDATION_ERROR);
	    return MSG_BODY_VALIDATION_ERROR;
	case 4:
	    LOG.error(MSG_COMPLETE_VALIDATION_ERROR);
	    return MSG_COMPLETE_VALIDATION_ERROR;
	case 5:
	    LOG.error(MSG_CPF_GENERATION_ERROR);
	    return MSG_CPF_GENERATION_ERROR;
	case 6:
	    LOG.error(MSG_COMPLETE_EMPTY_ERROR);
	    return MSG_COMPLETE_EMPTY_ERROR;
	default:
	    LOG.error(MSG_GENERIC_ERROR);
	    return MSG_GENERIC_ERROR;
	}
    }
}
