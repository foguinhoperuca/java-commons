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
import javax.persistence.Id;
/*import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;*/
import javax.persistence.Table;

// FIXME rename properties strLoginUsuario and strSenhaUsuario
@Entity
@Table(name = "TUsuario", catalog = "DBSigerar")
public class User extends BaseEntityIDImpl implements java.io.Serializable {

    private static final long serialVersionUID = 6450793412169953366L;
    private Long intIdUsuario;
    private String strLoginUsuario;
    private String strSenhaUsuario;

    public User() {
    }

    public User(Long intIdUsuario) {
	this.intIdUsuario = intIdUsuario;
    }

    public User(Long intIdUsuario, String strLoginUsuario,
	    String strSenhaUsuario) {
	this.intIdUsuario = intIdUsuario;
	this.strLoginUsuario = strLoginUsuario;
	this.strSenhaUsuario = strSenhaUsuario;

    }

    @Id
    @Column(name = "INT_idUSUARIO", unique = true, nullable = false)
    public Long getIntIdUsuario() {
	return this.intIdUsuario;
    }

    public void setIntIdUsuario(Long intIdUsuario) {
	this.intIdUsuario = intIdUsuario;
    }

    @Column(name = "STR_Login_USUARIO", length = 30)
    public String getStrLoginUsuario() {
	return this.strLoginUsuario;
    }

    public void setStrLoginUsuario(String strLoginUsuario) {
	this.strLoginUsuario = strLoginUsuario;
    }

    @Column(name = "STR_Senha_USUARIO", length = 40)
    public String getStrSenhaUsuario() {
	return this.strSenhaUsuario;
    }

    public void setStrSenhaUsuario(String strSenhaUsuario) {
	this.strSenhaUsuario = strSenhaUsuario;
    }

    @Override
    public Long retriveBasicID() {
	return getIntIdUsuario();
    }

    @Override
    public void defineBasicID(Long id) {
	setIntIdUsuario(id);
    }
}
