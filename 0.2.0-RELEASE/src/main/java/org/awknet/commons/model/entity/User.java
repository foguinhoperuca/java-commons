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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

// TODO create a different user only with login and password
@Entity
@Table(name = "TUser")
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends BaseEntityIDImpl<Long> implements
		java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 6450793412169953366L;
	private String login;
	private String password;
	private String email;

	public User() {
	}

	public User(Long id, String login, String password, String email) {
		super.setID(id);
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(String login) {
		this.login = login;
	}

	@Size(min = 1, max = 30)
	@Column(name = "login", length = 30)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String _login) {
		this.login = _login;
	}

	@Size(min = 1, max = 40)
	@Column(name = "password", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String _password) {
		this.password = _password;
	}

	@Email
	@Column(name = "email", length = 40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Long retrieveBasicID() {
		return super.getID();
	}

	@Override
	public void defineBasicID(Long id) {
		super.setID(id);
	}

	public User clone() {
		try {
			return (User) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
