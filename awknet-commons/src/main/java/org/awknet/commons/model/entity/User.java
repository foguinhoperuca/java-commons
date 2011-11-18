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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "TUser")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User extends BaseEntityIDImpl implements java.io.Serializable {

    private static final long serialVersionUID = 6450793412169953366L;
    private String login;
    private String password;

    public User() {
    }

    public User(String _login, String _password) {
	this.login = _login;
	this.password = _password;

    }

    @Column(name = "STR_Login_USUARIO", length = 30)
    public String getLogin() {
	return this.login;
    }

    public void setLogin(String _login) {
	this.login = _login;
    }

    @Column(name = "STR_Senha_USUARIO", length = 40)
    public String getPassword() {
	return this.password;
    }

    public void setPassword(String _password) {
	this.password = _password;
    }

    @Override
    public Long retriveBasicID() {
	return getID();
    }

    @Override
    public void defineBasicID(Long id) {
	setID(id);
    }
}
