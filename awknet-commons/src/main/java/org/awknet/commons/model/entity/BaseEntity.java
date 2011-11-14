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

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;

// TODO impĺement ID field as default
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	//
	// private static final long serialVersionUID = 1L;
	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "ID")
	// private Long id;
	//
	// public BaseEntity() {
	// }
	//
	// public BaseEntity(Long id) {
	// this.id = id;
	// }
	//
	// public void setId(Serializable id) {
	// this.id = (Long) id;
	// }
	//
	// public Long getId() {
	// return id;
	// }
	//
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
	// if (this.id == null || element.getId() == null)
	// return false;
	//
	// return new EqualsBuilder().append(this.getId(), element.getId())
	// .isEquals();
	// }
	//
	// public int hashCode() {
	// return (id != null ? id.hashCode() : super.hashCode());
	// }
}
