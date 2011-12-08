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

;

/**
 * This software is dealing with CPF (brazilian's income tax document). That
 * CPF's routine is very common in softwares that are developed aimed to
 * brazilian's market.
 */
public class CPFBOImpl implements Document {

    private String cpfBody;
    private int firstDigit;
    private int secondDigit;
    private static final Log LOG = LogFactory.getLog(CPFBOImpl.class);
    // FIXME remove all magic numbers!
    private static final int LAST_DIGIT_CPF_NUMBER = 9;
    private static final int FIRST_DIGIT_POSITION = 10;
    private static final int SECOND_DIGIT_POSITION = 11;

    public CPFBOImpl() {
	firstDigit = 0;
	secondDigit = 0;
    }

    public CPFBOImpl(String cpfBody, int digitOne, int digitTwo) {
	this.cpfBody = cpfBody;
	this.firstDigit = digitOne;
	this.secondDigit = digitTwo;
    }

    /**
     * Given a CPF without digits, Calculates "all" 2 digits and returns them.
     * 
     * @return a string with 2 digits.
     */
    @Override
    public String digits(String cpfBody) {
	return new String(Integer.toString(calculateFirstDigit(cpfBody)))
		.concat(Integer.toString(calculateSecondDigit(cpfBody)));
    }

    /**
     * Validates the internal CPF created at instantiation time.
     * 
     * @return Given a CPF, without digits, return true if it is valid.
     */
    public boolean validateInternalCPF() throws Exception {
	try {
	    if (cpfBody == "") {
		LOG.error("CPF unfilled.");
		throw new Exception("CPF unfilled. Returning false.");
	    }
	} catch (Exception ex) {
	    LOG.info("CPF unfilled - Returning false");
	    return false;
	}

	return validate(this.cpfBody, this.firstDigit, this.secondDigit);
    }

    // FIXME better use a static method!
    public static boolean validate(String cpfComplete) {
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
	return false;
    }

    /**
     * Validates a CPF, without digits.
     * 
     * @return Given a CPF, without digits, return true if it is valid.
     */
    // FIXME _cpf argument is useless!!
    // FIXME refactor all returns (specially "return false").
    @Override
    public boolean validate(String cpfBody, int firstDigit, int secondDigit) {
	if (isValid(cpfBody)) {
	    if (calculateFirstDigit(cpfBody) == firstDigit) {
		LOG.info("First digit is fine!");
		if (this.calculateSecondDigit(cpfBody) == secondDigit) {
		    LOG.info("Second digit is fine!");
		    return true;
		} else {
		    LOG.info("Error with SECOND digit!");
		    /* return false; */
		}
	    } else {
		LOG.info("Error with FIRST digit!");
		/* return false; */
	    }
	} else {
	    LOG.info("Error with FORMAT!");
	    /* return false; */
	}
	return false;
    }

    /**
     * Checks the validity of the CPF, indicating as invalid all those who have
     * repeated digits. Eg.: 111111111-11 is invalid.
     * 
     * @return true if CPF is valid.
     */
    // FIXME verify the length
    public Boolean isValid(String cpfBody) {
	int i;
	String initialDigit = cpfBody.substring(0, 1);
	Boolean valid = false;

	for (i = 1; i < 9; i++) {
	    // FIXME must break if CPF is valid?
	    if (!initialDigit.equals(cpfBody.substring(i, i + 1))) {
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
    public int calculateFirstDigit(String cpf) {
	int i, rest, dig, sum = 0;

	for (i = 0; i < 9; i++) {
	    sum += ((Integer.parseInt(cpf.substring(i, i + 1))) * (i + 1));
	}
	rest = sum % 11;
	if (rest == 10) {
	    dig = 0;
	} else {
	    dig = rest;
	}

	return dig;
    }

    /**
     * Calculate the second digit.
     * 
     * @return the number of second digit.
     */
    public int calculateSecondDigit(String cpf) {
	int i, rest, dig, sum = 0;

	for (i = 0; i < 9; i++) {
	    sum += ((Integer.parseInt(cpf.substring(i, i + 1))) * (12 - (i + 1)));
	}
	sum += calculateFirstDigit(cpf) * 2;
	sum *= 10;
	rest = sum % 11;
	if (rest == 10) {
	    dig = 0;
	} else {
	    dig = rest;
	}

	return dig;
    }

    public String getCpf() {
	return cpfBody;
    }

    public void setCpf(String cpf) {
	this.cpfBody = cpf;
    }
}
