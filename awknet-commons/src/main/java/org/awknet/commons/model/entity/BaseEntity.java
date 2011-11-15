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

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;

// TODO impĺement ID field as default
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7242252481157329335L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "ID")
	// private Long ID;

	public BaseEntity() {
	}

	// public BaseEntity(Long id) {
	// this.ID = id;
	// }
	//
	// public Long getID() {
	// return ID;
	// }
	//
	// public void setID(Long iD) {
	// ID = iD;
	// }

	public abstract Long retriveBasicID();

	public abstract void defineBasicID(Long id);

	// public boolean equals(Object obj) {
	//
	// if (obj == null)
	// return false;
	//
	// if (!(obj instanceof BaseEntity))
	// return false;
	//
	// BaseEntity element = (BaseEntity) obj;
	//
	// if (this == element)
	// return true;
	//
	// if (this.ID == null || element.getID() == null)
	// return false;
	//
	// return new EqualsBuilder().append(this.ID(), element.getID())
	// .isEquals();
	// }
	//
	// public int hashCode() {
	// return (ID != null ? ID.hashCode() : super.hashCode());
	// }
}
