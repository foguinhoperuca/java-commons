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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.exception.UserException;
import org.awknet.commons.exception.UserExceptionType;
import org.awknet.commons.mail.Mail;
import org.awknet.commons.mail.RecipientType;
import org.awknet.commons.model.entity.RetrievePasswordLog;
import org.awknet.commons.model.entity.User;
import org.awknet.commons.security.Encryption;
import org.hibernate.exception.ConstraintViolationException;

// TODO implement a "validator" for user
// TODO create link to send in email. (use ip and what to create link?)
public class UserBOImpl {
    private static final String DEFAULT_PROPERTIES_FILE = "/awknet-commons.properties";
    private User user;
    private DaoFactory daoFactory;
    private static final Log LOG = LogFactory.getLog(UserBOImpl.class);

    public UserBOImpl(DaoFactory daoFactory) {
	this.daoFactory = daoFactory;
    }

    public UserBOImpl(DaoFactory _daoFactory, User user) {
	this.daoFactory = _daoFactory;
	this.user = user;
    }

    /**
     * This method remove all signals that wasn't in English language.
     * 
     * @param login
     *            A login to be rewrite.
     * @return correct login.
     */
    protected String rewriteLogin(String login) {
	String newLogin = login.toLowerCase();

	newLogin = newLogin.replace("ç", "c");
	newLogin = newLogin.replace("ñ", "n");
	newLogin = newLogin.replace("á", "a");
	newLogin = newLogin.replace("à", "a");
	newLogin = newLogin.replace("ã", "a");
	newLogin = newLogin.replace("â", "a");
	newLogin = newLogin.replace("ä", "a");
	newLogin = newLogin.replace("é", "e");
	newLogin = newLogin.replace("è", "e");
	newLogin = newLogin.replace("ẽ", "e");
	newLogin = newLogin.replace("ê", "e");
	newLogin = newLogin.replace("ë", "e");
	newLogin = newLogin.replace("í", "i");
	newLogin = newLogin.replace("ì", "i");
	newLogin = newLogin.replace("ĩ", "i");
	newLogin = newLogin.replace("î", "i");
	newLogin = newLogin.replace("ï", "i");
	newLogin = newLogin.replace("ó", "o");
	newLogin = newLogin.replace("ò", "o");
	newLogin = newLogin.replace("õ", "o");
	newLogin = newLogin.replace("ô", "o");
	newLogin = newLogin.replace("ö", "o");
	newLogin = newLogin.replace("ú", "u");
	newLogin = newLogin.replace("ù", "u");
	newLogin = newLogin.replace("ũ", "u");
	newLogin = newLogin.replace("û", "u");
	newLogin = newLogin.replace("ü", "u");

	return newLogin;
    }

    /**
     * <p>
     * This function encrypt the password with MD5 method.
     * </p>
     * 
     * @param password
     *            : a password to be encrypt.
     * @return A encrypted password.
     * @throws NoSuchAlgorithmException
     */
    // TODO test it!
    protected String encryptPassword(String password)
	    throws NoSuchAlgorithmException {
	// MessageDigest md = MessageDigest.getInstance("MD5");
	// BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
	// String encryptedPassword = hash.toString(16);
	// if (encryptedPassword.length() % 2 != 0) {
	// encryptedPassword = "0" + encryptedPassword;
	// }
	// return encryptedPassword;
	return Encryption.encrypt(password);
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
     * @param entity
     *            : A user to be filled.
     * @since SIGERAR v1.1 - Apr/2008.
     */
    public User createUser(User entity, String name) {
	try {
	    int i = 1;
	    // String _name = _entity.getStrNomeUsuario();
	    String firstLetter = name.substring(0, 1);
	    String lastName = "";

	    while (!(name.charAt(name.length() - i) == " ".charAt(0))) {
		i++;
	    }
	    lastName = name.substring(name.length() - (i - 1));

	    // u.setLogin(rewriteLogin(firstLetter.concat(lastName)) +
	    // u.getIntIdUsuario());
	    entity.setLogin(rewriteLogin(firstLetter.concat(lastName)));
	    if (entity.getPassword() == null) {
		entity.setPassword("A12345678a");
	    }
	    entity.setPassword(encryptPassword(entity.getPassword()));
	} catch (Exception ex) {
	    LOG.error("Error during the creation of user!", ex);
	    entity = null;
	}
	return entity;
    }

    public boolean verifyUser(User entity) {
	boolean equal = false;
	try {
	    entity.setPassword(encryptPassword(entity.getPassword()));
	    user = daoFactory.getUserDao().onlyOne(entity);
	    if (user != null)
		equal = true;

	} catch (NoSuchAlgorithmException ex) {
	    LOG.error("[CRYPT] Error during the encryptation of password!", ex);
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

    /**
     * Send a link to retrieve a password. Don't implement it self, but call
     * sendLinkToRetrievePassword(User entity, String subject, String mailText)
     * and load properties from file. <br/>
     * If file name is null, use default file: /awknet-commons.properties
     * 
     * @param entity
     *            a user without password or ID.
     * @param fileName
     *            if null, use default file: /awknet-commons.properties
     * @return call sendLinkToRetrievePassword(User entity, String subject,
     *         String mailText) and return a boolean
     * @throws UserException
     */
    // FIXME must implement http://sourcemaking.com/design_patterns/null_object
    // FIXME merge it with sendLinkToRetrievePassword
    public boolean sendLinkToRetrievePassword(User entity, String fileName)
	    throws UserException {
	String subject, mailText;
	Properties mailProperties = new Properties();

	if (fileName.equals("") || fileName == null)
	    fileName = DEFAULT_PROPERTIES_FILE;

	try {
	    mailProperties.load(getClass().getResourceAsStream(fileName));
	    subject = mailProperties
		    .getProperty("mail.retrievePassword.subject");
	    mailText = mailProperties
		    .getProperty("mail.retrievePassword.mailText");
	    return sendLinkToRetrievePassword(entity, subject, mailText,
		    fileName);
	} catch (IOException e) {
	    LOG.error("Error handling with properties of app.", e);
	}
	return false;
    }

    /**
     * Send a link to retrieve a password.
     * 
     * @param entity
     *            a user without password or ID.
     * @param subject
     *            defined by user.
     * @param mailText
     *            body of e-mail defined by user.
     * @return true if find and send a email to retrieve password.
     * @throws UserException
     */
    // FIXME email is mandatory!
    public boolean sendLinkToRetrievePassword(User entity, String subject,
	    String mailText, String fileName) throws UserException {
	boolean success = false;
	Mail mail;
	if (entity.getPassword() != null)
	    throw new UserException(UserExceptionType.PASSWORD);
	else if (entity.getID() != null)
	    throw new UserException(UserExceptionType.ID);

	user = daoFactory.getUserDao().loadByExample(entity);
	if (user != null) {
	    try {
		// TODO email validation.
		if (user.getEmail().equals("") || user.getEmail() == null)
		    throw new UserException(UserExceptionType.EMAIL_NULL);

		mail = new Mail(subject, mailText, fileName);
		mail.addMailRecipient(RecipientType.RECIPIENT_TYPE_TO,
			user.getEmail());
		mail.send();
		success = true;
		LOG.info("Link to retrive password send to user "
			+ user.getLogin() + " - email " + user.getEmail());
	    } catch (IOException e) {
		LOG.error("Error handling with properties of app.", e);
	    } catch (MessagingException e) {
		LOG.error("Error sending e-mail.", e);
	    }
	} else {
	    LOG.info("User " + entity.getLogin() + " - email "
		    + entity.getEmail() + " -- NOT FOUND!");
	}
	return success;
    }

    /**
     * Create a code basead in:<br/>
     * retrieveCode = id_user + login + email + actual date<br/>
     * If the code is already in DB, will throw a exception (the code must be
     * unique!)!
     * 
     * @param userID
     * @return a retrieve code to password
     * @throws UserException
     */
    // FIXME getTime: must have millisecond precision! Use JODA TIME!
    public String generateCodeToRetrievePassword(Long userID)
	    throws UserException {

	String retrieveCode;
	Date dateRequestedNewPassword = Calendar.getInstance().getTime();
	RetrievePasswordLog rpLog;

	if (userID == null)
	    throw new UserException(UserExceptionType.ID);

	User entity = daoFactory.getUserDao().load(userID);
	if (entity == null)
	    throw new UserException(UserExceptionType.ID);

	retrieveCode = Encryption.encrypt(entity.getID() + entity.getLogin()
		+ entity.getEmail() + dateRequestedNewPassword.toString());

	try {
	    daoFactory.beginTransaction();
	    rpLog = new RetrievePasswordLog(retrieveCode, userID,
		    "172.16.1.110", dateRequestedNewPassword, false);
	    daoFactory.getRegisterDao(RetrievePasswordLog.class).save(rpLog);
	    daoFactory.commit();
	} catch (ConstraintViolationException e) {
	    LOG.error("[RETRIEVE PASSWORD] code not saved in DB.", e);
	    return null;
	} catch (Exception e) {
	    LOG.error("[RETRIEVE PASSWORD] generic error in DB.", e);
	    return null;
	}

	return retrieveCode;
    }
    /******************************************************************************/

    // public void createUserProspectRequest(User user, String requestIp) {
    // user.setCreationDate(new Date());
    // String profileName = SystemMessageAcessor
    // .getSystemMessage("user.creation.profile");
    //
    // if (StringUtils.hasText(profileName)) {
    // UserProfile profile = userSecurityRepository
    // .getProfileByName(profileName);
    //
    // if (profile != null)
    // user.addProfile(profile);
    // }
    //
    // this.saveUser(user);
    //
    // RequestActivationForm raf = new RequestActivationForm(requestIp,
    // new Date(), user.getId());
    // userSecurityRepository.saveBaseElement(raf);
    //
    // String templateName = "confirm_creation_user_mail";
    // Map<String, Object> params = new HashMap<String, Object>();
    // params.put("requestId", raf.getId());
    // params.put("user", user);
    // params.put("homepage",
    // SystemMessageAcessor.getSystemMessage("site.url"));
    //
    // MailMessage mail = new MailMessage();
    // // mail.setHomePage(homepage);
    // mail.setFrom(SystemMessageAcessor.getSystemMessage("mail.from"));
    // mail.setSubject(SystemMessageAcessor
    // .getSystemMessage("request.user.creation.activation.mail.subject"));
    // mail.setTemplate(templateName);
    //
    // mail.setTo(user.getEmail());
    // mail.setHtmlPlain(" ");
    // mail.setTextPlain(" ");
    // mail.setParams(params);
    //
    // messageService.sendThreadMail(mail);
    // }
    //
    // /**************************************************************************/
    //
    // private void saveUser(User user) {
    // String encodePassword = Md5Encoder.getInstance().encodePassword(
    // user.getPassword());
    // user.setPassword(encodePassword);
    //
    // if (this.existUserByLogin(user.getLogin()))
    // throw new DuplicateLoginError(
    // SystemMessageAcessor
    // .getSystemMessage("error.duplicated.user.login"));
    //
    // userSecurityRepository.save(user);
    // }
    //
    // /**************************************************************************/
    //
    // protected boolean isValidRequest(Date requestDate) {
    // int days = SystemMessageAcessor
    // .getPropertyAsInteger("request.activation.form.valid.until.days");
    //
    // GregorianCalendar dateGenerateLink = new GregorianCalendar();
    // dateGenerateLink.setTime(requestDate);
    // dateGenerateLink.add(Calendar.DAY_OF_YEAR, days);
    //
    // GregorianCalendar currentDate = new GregorianCalendar();
    // return currentDate.before(dateGenerateLink);
    // }
    //
    // /**************************************************************************/
    //
    // public void activationUser(Long requestId, Long userId, String ip)
    // throws ActivationUserError {
    // RequestActivationForm raf = userSecurityRepository
    // .getRequestActivationForm(requestId);
    //
    // if (raf == null)
    // throw new ActivationUserError(
    // SystemMessageAcessor
    // .getSystemMessage("active.user.errors.invalid.requisition"));
    //
    // if (!raf.getRequestIp().equalsIgnoreCase(ip)
    // || !raf.getObjectId().equals(userId))
    // throw new ActivationUserError(
    // SystemMessageAcessor
    // .getSystemMessage("active.user.errors.different.ip"));
    //
    // if (!raf.isValid())
    // throw new ActivationUserError(
    // SystemMessageAcessor
    // .getSystemMessage("active.user.errors.invalid.requisition"));
    //
    // if (!isValidRequest(raf.getRequestDate()))
    // throw new ActivationUserError(
    // SystemMessageAcessor
    // .getSystemMessage("active.user.errors.requisition.expired"));
    //
    // User user = userSecurityRepository.get(userId);
    // user.setActive(true);
    //
    // raf.setValid(false);
    // }
}
