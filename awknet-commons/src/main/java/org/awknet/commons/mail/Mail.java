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

package org.awknet.commons.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.awknet.commons.model.entity.User;

public class Mail {
    private static final Log LOG = LogFactory.getLog(Mail.class);
    private String mailSubject;
    private String mailText;
    private List<Address> recipientsTo, recipientsCc, recipientsBcc;

    public Mail(String subject, String mailText) {
	this.mailSubject = subject;
	this.mailText = mailText;
	recipientsTo = new ArrayList<Address>();
	recipientsCc = new ArrayList<Address>();
	recipientsBcc = new ArrayList<Address>();
    }

    public void send() throws AddressException, MessagingException,
	    FileNotFoundException, IOException {
	int count = recipientsTo.size() + recipientsCc.size()
		+ recipientsBcc.size();
	if (count == 0)
	    return;

	deleteDuplicates();

	Properties javaMailProperties = new Properties();
	javaMailProperties.load(getClass().getResourceAsStream(
		"/awknet-commons.properties"));

	final String mailUsername = javaMailProperties
		.getProperty("mail.autentication.username");
	final String mailPassword = javaMailProperties
		.getProperty("mail.autentication.password");
	final String mailFrom = javaMailProperties
		.getProperty("mail.autentication.mail_from");

	Session session = Session.getInstance(javaMailProperties,
		new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(mailUsername,
				mailPassword);
		    }
		});

	final MimeMessage msg = new MimeMessage(session);

	msg.setFrom(new InternetAddress(mailFrom));
	msg.setRecipients(Message.RecipientType.TO, getToRecipientsArray());
	msg.setRecipients(Message.RecipientType.CC, getCcRecipientsArray());
	msg.setRecipients(Message.RecipientType.BCC, getBccRecipientsArray());
	msg.setSentDate(new Date());
	msg.setSubject(mailSubject);
	msg.setText(mailText, "UTF-8", "html");
	// msg.setText(mailText); //OLD WAY
	new Thread(new Runnable() {
	    public void run() {
		try {
		    Transport.send(msg);
		} catch (MessagingException ex) {
		    Logger.getLogger(Mail.class.getName()).log(Level.SEVERE,
			    "Cant send email!", ex);
		}
	    }
	}).start();
    }

    /**
     * Delete duplicates recipients. It is not allowed a same e-mail in 2
     * recipients fields. In this case, all new occurrence is removed.
     */
    private void deleteDuplicates() {
	for (int i = 0; i < recipientsTo.size(); i++) {
	    Address to = recipientsTo.get(i);
	    for (int y = i + 1; y < recipientsTo.size(); y++) {
		if (to.equals(recipientsTo.get(y)))
		    recipientsTo.remove(y--);
	    }
	    for (int y = 0; y < recipientsCc.size(); y++) {
		if (to.equals(recipientsCc.get(y)))
		    recipientsCc.remove(y--);
	    }
	    for (int y = 0; y < recipientsBcc.size(); y++) {
		if (to.equals(recipientsBcc.get(y)))
		    recipientsBcc.remove(y--);
	    }
	}
	for (int i = 0; i < recipientsCc.size(); i++) {
	    Address cc = recipientsCc.get(i);
	    for (int y = i + 1; y < recipientsCc.size(); y++) {
		if (cc.equals(recipientsCc.get(y)))
		    recipientsCc.remove(y--);
	    }
	    for (int y = 0; y < recipientsBcc.size(); y++) {
		if (cc.equals(recipientsBcc.get(y)))
		    recipientsBcc.remove(y--);
	    }
	}
	for (int i = 0; i < recipientsBcc.size(); i++) {
	    Address bcc = recipientsBcc.get(i);
	    for (int y = i + 1; y < recipientsBcc.size(); y++) {
		if (bcc.equals(recipientsBcc.get(y)))
		    recipientsBcc.remove(y--);
	    }
	}
    }

    public void addUsersRecipient(RecipientType recipientType, List<User> users) {
	for (User u : users)
	    if (u != null)
		addMailRecipient(recipientType, u.getEmail());
    }

    public void addMailsRecipient(RecipientType recitientType, String[] mails) {
	for (String s : mails)
	    addMailRecipient(recitientType, s);
    }

    public void addMailsRecipient(RecipientType recipientType,
	    List<String> mails) {
	for (String s : mails)
	    addMailRecipient(recipientType, s);
    }

    public void addMailRecipient(RecipientType recipientType, String mail) {
	if (mail != null && mail.trim().length() == 0)
	    return;
	try {
	    InternetAddress address = new InternetAddress(mail);
	    switch (recipientType) {
	    case RECIPIENT_TYPE_TO:
		recipientsTo.add(address);
	    case RECIPIENT_TYPE_CC:
		recipientsCc.add(address);
	    case RECIPIENT_TYPE_BCC:
		recipientsBcc.add(address);
	    }
	} catch (AddressException ex) {
	    LOG.error("Email address not recognized: " + mail, ex);
	}
    }

    /**
     * Just convert a list to array.
     * 
     * @return a array of recipients
     */
    private Address[] getToRecipientsArray() {
	Address[] array = new Address[recipientsTo.size()];
	for (int i = 0; i < recipientsTo.size(); i++)
	    array[i] = recipientsTo.get(i);
	return array;
    }

    /**
     * Just convert a list to array.
     * 
     * @return a array of recipients
     */
    private Address[] getCcRecipientsArray() {
	Address[] array = new Address[recipientsCc.size()];
	for (int i = 0; i < recipientsCc.size(); i++)
	    array[i] = recipientsCc.get(i);
	return array;
    }

    /**
     * Just convert a list to array.
     * 
     * @return a array of recipients
     */
    private Address[] getBccRecipientsArray() {
	Address[] array = new Address[recipientsBcc.size()];
	for (int i = 0; i < recipientsBcc.size(); i++)
	    array[i] = recipientsBcc.get(i);
	return array;
    }
}
