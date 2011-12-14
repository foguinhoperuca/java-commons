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
    private static final int LAST_DIGIT_CPF_NUMBER = 9;
    private static final int FIRST_DIGIT_POSITION = 10;
    private static final int SECOND_DIGIT_POSITION = 11;

    public static String getDigits(String documentBody) {
	try {
	    if (documentBody == null || documentBody.equals(""))
		throw new CPFException(CPFExceptionType.CPFBodyEmpty);

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
    public static boolean validate(String documentComplete) throws CPFException {
	if (documentComplete == null || documentComplete.length() != 11)
	    throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	String documentBody = getCPFBody(documentComplete);
	LOG.debug("Document body is: " + documentBody);
	int firstDigit = getFirstDigit(documentComplete);
	LOG.debug("FIRST digit is: " + firstDigit);
	int secondDigit = getSecondDigit(documentComplete);
	LOG.debug("SECOND digit is: " + secondDigit);

	try {
	    if (!validateDocumentBody(documentBody))
		throw new CPFException(CPFExceptionType.CPFBodyValidation);

	    if (calculateFirstDigit(documentBody) != firstDigit)
		throw new CPFException(CPFExceptionType.CPFFirstDigit);

	    if (calculateSecondDigit(documentBody) != secondDigit)
		throw new CPFException(CPFExceptionType.CPFSecondDigit);

	    return true;

	} catch (CPFException e) {
	    LOG.error("[CPF VALIDATION] ERROR during validation!", e);
	    return false;
	}
    }

    /**
     * Checks the validity of the CPF, indicating as invalid all those who have
     * repeated digits. Eg.: 111111111-11 is invalid.
     * 
     * @return true if CPF is valid.
     */
    public static boolean validateDocumentBody(String documentBody) {
	int i;
	String initialDigit = documentBody.substring(0, 1);
	// boolean valid = false;

	try {
	    if (documentBody == null || documentBody.length() != 9)
		throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	    for (i = 1; i < 9; i++) {
		// FIXME must break if CPF is valid?
		if (!initialDigit.equals(documentBody.substring(i, i + 1))) {
		    // valid = true;
		    // break;
		    return true;
		}
	    }
	    // return valid;
	    return false;
	} catch (CPFException e) {
	    LOG.error("[CPF VALIDATION BODY] CPF BODY is invalid!", e);
	    return false;
	}
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

    public static String getCPFBody(String documentComplete) {
	try {
	    if (documentComplete == null || documentComplete.equals(""))
		throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	    return documentComplete.substring(0, LAST_DIGIT_CPF_NUMBER);
	} catch (CPFException e) {
	    LOG.error("[getCPFBody] Error with CPF: ", e);
	}
	return null;
    }

    public static Integer getFirstDigit(String documentComplete) {
	try {
	    if (documentComplete == null || documentComplete.equals(""))
		throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	    return Integer.parseInt(documentComplete.substring(
		    LAST_DIGIT_CPF_NUMBER, FIRST_DIGIT_POSITION));
	} catch (CPFException e) {
	    LOG.error("[getFirstDigit] Error with 1st digit: ", e);
	}
	return null;
    }

    public static Integer getSecondDigit(String documentComplete) {
	try {
	    if (documentComplete == null || documentComplete.equals(""))
		throw new CPFException(CPFExceptionType.CPFBodyEmpty);

	    return Integer.parseInt(documentComplete.substring(
		    FIRST_DIGIT_POSITION, SECOND_DIGIT_POSITION));
	} catch (CPFException e) {
	    LOG.error("[getSecondDigit] Error with 2nd digit: ", e);
	}
	return null;
    }

    public static String generateValidCPF() {
	String cpfBody = new String(), cpfComplete = "";
	Double rndDigit;
	Integer digit;

	try {
	    for (int i = 1; i < 10; i++) {
		rndDigit = (Math.random() * 10);
		digit = rndDigit.intValue();
		cpfBody += digit.toString();
	    }

	    if (!validateDocumentBody(cpfBody))
		throw new CPFException(CPFExceptionType.CPFGeneration);

	    cpfComplete = cpfBody + getDigits(cpfBody);
	    LOG.debug("value of CPF complete: " + cpfComplete);
	    if (!validate(cpfComplete))
		// CPFServiceProvider.generateValidCPF(); // FIXME loop infinite
		throw new CPFException(CPFExceptionType.CPFCompleteValidation);
	} catch (CPFException e) {
	    LOG.error("[genereteValidCPF] error during creation of CPF.", e);
	    return null;
	}

	return cpfComplete;
    }

    // FIXME [CPFServiceProvider.mask] must implement it!
    public static String mask(String documentComplete) {
	if (documentComplete == null || documentComplete.equals(""))
	    return null;

	return "";
    }

    // FIXME [CPFServiceProvider.unmask] must implement it!
    public static String unmask(String documentComplete) {
	if (documentComplete == null || documentComplete.equals(""))
	    return null;

	return "";
    }
}
