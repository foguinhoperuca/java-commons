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
     * @param login
     *            A login to be rewrite.
     * @return correct login.
     */
    protected String rewriteLogin(String login) {
	login = login.toLowerCase();
	login = login.replace("ç", "c");
	login = login.replace("ñ", "n");
	login = login.replace("á", "a");
	login = login.replace("à", "a");
	login = login.replace("ã", "a");
	login = login.replace("â", "a");
	login = login.replace("ä", "a");
	login = login.replace("é", "e");
	login = login.replace("è", "e");
	login = login.replace("ẽ", "e");
	login = login.replace("ê", "e");
	login = login.replace("ë", "e");
	login = login.replace("í", "i");
	login = login.replace("ì", "i");
	login = login.replace("ĩ", "i");
	login = login.replace("î", "i");
	login = login.replace("ï", "i");
	login = login.replace("ó", "o");
	login = login.replace("ò", "o");
	login = login.replace("õ", "o");
	login = login.replace("ô", "o");
	login = login.replace("ö", "o");
	login = login.replace("ú", "u");
	login = login.replace("ù", "u");
	login = login.replace("ũ", "u");
	login = login.replace("û", "u");
	login = login.replace("ü", "u");

	return login;
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
    protected String encryptPassword(String password)
	    throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("MD5");
	BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
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

    public boolean verifyUser(User entity) {
	boolean equal = false;
	try {
	    entity.setPassword(encryptPassword(entity.getPassword()));
	    user = daoFactory.getUserDao().onlyOne(entity);
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
    public User resetPassword(User entity) {
	try {
	    entity.setPassword(encryptPassword("A12345678a"));
	    daoFactory.beginTransaction();
	    daoFactory.getUserDao().update(entity);
	    daoFactory.commit();
	} catch (NoSuchAlgorithmException ex) {
	    LOG.error("[RESET] Error during the encryptation of password!", ex);
	}
	return entity;
    }

    public User getUser() {
	return user;
    }
}
