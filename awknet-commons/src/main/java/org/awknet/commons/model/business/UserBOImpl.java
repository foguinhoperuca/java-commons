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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.exception.UserException;
import org.awknet.commons.exception.UserExceptionType;
import org.awknet.commons.model.entity.User;

public class UserBOImpl {

    private User user;
    private DaoFactory daoFactory;
    private static final Log LOG = LogFactory.getLog(UserBOImpl.class);

    public UserBOImpl(DaoFactory _daoFactory) {
	this.daoFactory = _daoFactory;
    }

    public UserBOImpl(DaoFactory _daoFactory, User _user) {
	this.daoFactory = _daoFactory;
	this.user = _user;
    }

    /**
     * This method remove all signals that wasn't in English language.
     * 
     * @param _login
     *            A login to be rewrite.
     * @return correct login.
     */
    protected String rewriteLogin(String _login) {
	_login = _login.toLowerCase();
	_login = _login.replace("ç", "c");
	_login = _login.replace("ñ", "n");
	_login = _login.replace("á", "a");
	_login = _login.replace("à", "a");
	_login = _login.replace("ã", "a");
	_login = _login.replace("â", "a");
	_login = _login.replace("ä", "a");
	_login = _login.replace("é", "e");
	_login = _login.replace("è", "e");
	_login = _login.replace("ẽ", "e");
	_login = _login.replace("ê", "e");
	_login = _login.replace("ë", "e");
	_login = _login.replace("í", "i");
	_login = _login.replace("ì", "i");
	_login = _login.replace("ĩ", "i");
	_login = _login.replace("î", "i");
	_login = _login.replace("ï", "i");
	_login = _login.replace("ó", "o");
	_login = _login.replace("ò", "o");
	_login = _login.replace("õ", "o");
	_login = _login.replace("ô", "o");
	_login = _login.replace("ö", "o");
	_login = _login.replace("ú", "u");
	_login = _login.replace("ù", "u");
	_login = _login.replace("ũ", "u");
	_login = _login.replace("û", "u");
	_login = _login.replace("ü", "u");

	return _login;
    }

    /**
     * <p>
     * This function encrypt the password with MD5 method.
     * </p>
     * 
     * @param pwd
     *            : a password to be encrypt.
     * @return A encrypted password.
     * @since SIGERAR v1.1 - Apr/2008.
     * @throws NoSuchAlgorithmException
     */
    protected String encryptPassword(String _password)
	    throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("MD5");
	BigInteger hash = new BigInteger(1, md.digest(_password.getBytes()));
	String encryptedPassword = hash.toString(16);
	if (encryptedPassword.length() % 2 != 0) {
	    encryptedPassword = "0" + encryptedPassword;
	}
	return encryptedPassword;
    }

    /**
     * <p>
     * This method create a user.<br/>
     * To create a login, it's use the first letter of first name and the last
     * name plus the id of user.<br/>
     * And just set the password for &quot;default&quot; password: A12345678a.<br/>
     * ex.: Jefferson Campos id 6669 will be &quot;jcampos6669&quot; with the
     * password &quot;A12345678a&quot;.
     * </p>
     * 
     * @param name
     *            : A name to get login.
     * @param usuario
     *            : A user to be filled.
     * @since SIGERAR v1.1 - Apr/2008.
     */
    public User createUser(User _entity, String _name) {
	try {
	    int i = 1;
	    // String _name = _entity.getStrNomeUsuario();
	    String firstLetter = _name.substring(0, 1);
	    String lastName = "";

	    while (!(_name.charAt(_name.length() - i) == " ".charAt(0))) {
		i++;
	    }
	    lastName = _name.substring(_name.length() - (i - 1));

	    // u.setLogin(rewriteLogin(firstLetter.concat(lastName)) +
	    // u.getIntIdUsuario());
	    _entity.setLogin(rewriteLogin(firstLetter.concat(lastName)));
	    if (_entity.getPassword() == null) {
		_entity.setPassword("A12345678a");
	    }
	    _entity.setPassword(encryptPassword(_entity.getPassword()));
	} catch (Exception ex) {
	    LOG.error("Error during the creation of user!", ex);
	    _entity = null;
	}
	return _entity;
    }

    public boolean verifyUser(User _entity) {
	boolean equal = false;
	try {
	    _entity.setPassword(encryptPassword(_entity.getPassword()));
	    user = daoFactory.getUserDao().onlyOne(_entity);
	    if (user != null)
		equal = true;

	} catch (NoSuchAlgorithmException ex) {
	    LOG.error("Error during the encryptation of password!", ex);
	} catch (Exception ex) {
	    LOG.error("Error during the verification of user!", ex);
	}

	return equal;
    }

    // FIXME send email warning about the reset of password
    public User resetPassword(User _entity) {
	try {
	    _entity.setPassword(encryptPassword("A12345678a"));
	    daoFactory.beginTransaction();
	    daoFactory.getUserDao().update(_entity);
	    daoFactory.commit();
	} catch (NoSuchAlgorithmException ex) {
	    LOG.error("[RESET] Error during the encryptation of password!", ex);
	}
	return _entity;
    }

    public User getUser() {
	return user;
    }

    /**
     * 
     * @param _entity
     *            a user without password or ID.
     * @return true if find and send a email to retrieve password.
     * @throws UserException
     */
    public boolean sendLinkToRetrievePassword(User _entity)
	    throws UserException {
	boolean success = false;
	if (_entity.getPassword() != null)
	    throw new UserException(UserExceptionType.PASSWORD);

	else if (_entity.getID() != null)
	    throw new UserException(UserExceptionType.ID);

	user = daoFactory.getUserDao().loadByExample(_entity);
	if (user != null) {
	    // Email.retrivePassword(user);
	    success = true;
	    LOG.info("Link to retrive password send to user " + user.getLogin()
		    + " - email " + user.getEmail());
	} else {
	    LOG.info("User " + _entity.getLogin() + " - email "
		    + _entity.getEmail() + " -- NOT FOUND!");
	}
	return success;
    }
}
