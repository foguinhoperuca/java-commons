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

/**
 * This software is dealing with CPF (brazilian's income tax document). That
 * CPF's routine is very common in softwares that are developed aimed to
 * brazilian's market.
 */
// FIXME verify some design pattern - delegate method or something
// TODO implement test (migrate tests from CCPFBOImpl)
public class CPFBOImpl implements Document {
    private static final Log LOG = LogFactory.getLog(CPFBOImpl.class);
    private String cpfBody;
    private int firstDigit;
    private int secondDigit;

    public CPFBOImpl() {
	firstDigit = 0;
	secondDigit = 0;
    }

    public CPFBOImpl(String cpfBody) {
	this.cpfBody = cpfBody;
	try {
	    firstDigit = CPFServiceProvider.calculateFirstDigit(cpfBody);
	    secondDigit = CPFServiceProvider.calculateSecondDigit(cpfBody);
	} catch (CPFException e) {
	    LOG.error("[CONSTR] Error with CPFServiceProvider.getDigits", e);
	    firstDigit = 0;
	    secondDigit = 0;
	}
    }

    public CPFBOImpl(String cpfBody, int digitOne, int digitTwo) {
	this.cpfBody = cpfBody;
	this.firstDigit = digitOne;
	this.secondDigit = digitTwo;
    }

    @Override
    public boolean validateDocumentBody() {
	return CPFServiceProvider.validateDocumentBody(cpfBody);

    }

    @Override
    public boolean validateDigits() {
	if (cpfBody == null || cpfBody.equals(""))
	    return false;
	try {
	    if (CPFServiceProvider.calculateFirstDigit(cpfBody) != firstDigit
		    || CPFServiceProvider.calculateSecondDigit(cpfBody) != secondDigit)
		return false;
	} catch (CPFException e) {
	    LOG.error("[getDigits] Error with CPFServiceProvider.getDigits", e);
	    return false;
	}

	return true;
    }

    /**
     * Validates the internal CPF created at instantiation time.
     * 
     * @return Given a CPF, without digits, return true if it is valid.
     */
    @Override
    public boolean validateDocument() {
	try {
	    return CPFServiceProvider.validate(this.cpfBody);
	} catch (CPFException e) {
	    LOG.error("[CPFBOImpl] error with validation of CPF: ", e);
	}
	return false;
    }

    @Override
    public String maskDocument() {
	return CPFServiceProvider.mask(cpfBody);
    }

    @Override
    public String unmaskDocument() {
	return CPFServiceProvider.unmask(cpfBody);
    }

    /**
     * Given a CPF without digits, Calculates "all" 2 digits and returns them.
     * 
     * @return a string with 2 digits.
     */
    @Override
    public String getDocumentDigits() {
	return CPFServiceProvider.getDigits(cpfBody);
    }

    public String getCpf() {
	return cpfBody;
    }

    public void setCpf(String cpf) {
	this.cpfBody = cpf;
    }
}
