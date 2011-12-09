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

package org.awknet.commons.model.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.awknet.commons.exception.CPFException;
import org.awknet.commons.exception.CPFExceptionType;

public final class CPFServiceProvider {

    private static final Log LOG = LogFactory.getLog(CPFServiceProvider.class);
    // FIXME remove all magic numbers!
    private static final int LAST_DIGIT_CPF_NUMBER = 9;
    private static final int FIRST_DIGIT_POSITION = 10;
    private static final int SECOND_DIGIT_POSITION = 11;

    public static String getDigits(String documentBody) {
	if (documentBody == null || documentBody.equals(""))
	    return "";

	try {
	    return new String(
		    Integer.toString(calculateFirstDigit(documentBody)))
		    .concat(Integer
			    .toString(calculateSecondDigit(documentBody)));
	} catch (CPFException e) {
	    LOG.error("[GETDIGITS] Error during calculation of digits!", e);
	    return "";
	}
    }

    /**
     * Validates a CPF, without digits.
     * 
     * @return Given a CPF, without digits, return true if it is valid.
     */
    // FIXME better use a static method!
    // FIXME refactor all returns (specially "return false").
    public static boolean validate(String documentComplete) {
	// try {
	// if (cpfComplete == null || cpfComplete.length() != 11)
	// throw new ExceptionInInitializerError();
	//
	// firstDigit = Integer.parseInt(cpfComplete.substring(
	// FIRST_DIGIT_POSITION, 1));
	// secondDigit = Integer.parseInt(cpfComplete.substring(
	// SECOND_DIGIT_POSITION, 1));
	// cpfBody = cpfComplete.substring(0, LAST_DIGIT_CPF_NUMBER);
	//
	// if (!validate(cpfBody, firstDigit, secondDigit))
	// throw new CPFException();
	// } catch (ExceptionInInitializerError e) {
	// LOG.error("[CPF INIT] CPF is INVALID!", e);
	// return;
	// } catch (CPFException e) {
	// LOG.error("[CPF INIT] CPFBOIimpl FAILED to VALIDATE!", e);
	// return;
	// } catch (Exception e) {
	// LOG.error("[CPF INIT] GENERAL ERROR on creation of CPFBOImpl!", e);
	// return;
	// }

	// // FIXME JOIN WITH CODE ABOVE
	// if (isValid(cpfBody)) {
	// if (calculateFirstDigit(cpfBody) == firstDigit) {
	// LOG.info("First digit is fine!");
	// if (this.calculateSecondDigit(cpfBody) == secondDigit) {
	// LOG.info("Second digit is fine!");
	// return true;
	// } else {
	// LOG.info("Error with SECOND digit!");
	// /* return false; */
	// }
	// } else {
	// LOG.info("Error with FIRST digit!");
	// /* return false; */
	// }
	// } else {
	// LOG.info("Error with FORMAT!");
	// /* return false; */
	// }
	// return false;
	return false;
    }

    /**
     * Checks the validity of the CPF, indicating as invalid all those who have
     * repeated digits. Eg.: 111111111-11 is invalid.
     * 
     * @return true if CPF is valid.
     */
    // FIXME verify the length
    public static boolean validateDocumentBody(String documentBody) {
	int i;
	String initialDigit = documentBody.substring(0, 1);
	Boolean valid = false;

	// if (cpfBody == null || cpfBody.length() != 11)
	// return false;

	for (i = 1; i < 9; i++) {
	    // FIXME must break if CPF is valid?
	    if (!initialDigit.equals(documentBody.substring(i, i + 1))) {
		valid = true;
	    }
	}
	return valid;
    }

    /**
     * Calculate the first digit.
     * 
     * @return the number of first digit.
     */
    // FIXME validate it before calculate?
    public static int calculateFirstDigit(String cpf) throws CPFException {
	if (cpf == null || cpf.equals(""))
	    throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	int i, rest, dig, sum = 0;

	for (i = 0; i < 9; i++)
	    sum += ((Integer.parseInt(cpf.substring(i, i + 1))) * (i + 1));

	rest = sum % 11;
	if (rest == 10)
	    dig = 0;
	else
	    dig = rest;

	return dig;
    }

    /**
     * Calculate the second digit.
     * 
     * @return the number of second digit.
     */
    // FIXME validate it before calculate?
    public static int calculateSecondDigit(String cpf) throws CPFException {
	if (cpf == null || cpf.equals(""))
	    throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	int i, rest, dig, sum = 0;

	for (i = 0; i < 9; i++)
	    sum += ((Integer.parseInt(cpf.substring(i, i + 1))) * (12 - (i + 1)));

	sum += calculateFirstDigit(cpf) * 2;
	sum *= 10;
	rest = sum % 11;
	if (rest == 10)
	    dig = 0;
	else
	    dig = rest;

	return dig;
    }

    public static String mask(String documentComplete) {
	if (documentComplete == null || documentComplete.equals(""))
	    return "";

	return "";
    }

    public static String unmask(String documentComplete) {
	if (documentComplete == null || documentComplete.equals(""))
	    return "";

	return "";
    }
}
