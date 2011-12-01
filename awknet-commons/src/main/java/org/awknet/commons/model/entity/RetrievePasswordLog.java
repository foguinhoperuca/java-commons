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

package org.awknet.commons.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TRetrieve_Password_LOG")
// FIXME relationship with user must be implemented!
// TODO rename it to RequestActivationForm
public class RetrievePasswordLog extends BaseEntityImpl<String> implements
	java.io.Serializable {

    private static final long serialVersionUID = 2143850686198332077L;
    public static final int DEFAULT_TIME_RETRIEVE_CODE = 2;
    // FIXME use CHAR with CHECK OPTION instead VARCHAR in DB
    private String retrieveCode;
    private Long userId;
    private String ip;
    // FIXME getTime: must have millisecond precision! Use JODA TIME!
    private Date request;
    private Boolean updated;

    public RetrievePasswordLog() {
    }

    public RetrievePasswordLog(String retrieveCode, Long userId, Date request) {
	this.retrieveCode = retrieveCode;
	this.userId = userId;
	this.request = request;
    }

    public RetrievePasswordLog(String retrieveCode, Long userId, String ip,
	    Date request, Boolean updated) {
	this.retrieveCode = retrieveCode;
	this.userId = userId;
	this.ip = ip;
	this.request = request;
	this.updated = updated;
    }

    @Id
    @Column(name = "retrieveCode", unique = true, nullable = false, length = 32)
    public String getRetrieveCode() {
	return this.retrieveCode;
    }

    public void setRetrieveCode(String retrieveCode) {
	this.retrieveCode = retrieveCode;
    }

    @Column(name = "userID", nullable = false)
    public Long getUserId() {
	return this.userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    @Column(name = "IP", length = 15)
    public String getIp() {
	return this.ip;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request", nullable = false, length = 10)
    public Date getRequest() {
	return this.request;
    }

    public void setRequest(Date request) {
	this.request = request;
    }

    @Column(name = "updated")
    public Boolean getUpdated() {
	return this.updated;
    }

    public void setUpdated(Boolean updated) {
	this.updated = updated;
    }

    @Override
    public String retrieveBasicID() {
	return getRetrieveCode();
    }

    @Override
    public void defineBasicID(String id) {
	setRetrieveCode(id);
    }
}
