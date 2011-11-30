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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;

@MappedSuperclass
public class BaseEntityIDImpl<T> implements BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    public BaseEntityIDImpl() {
    }

    public BaseEntityIDImpl(Long id) {
	this.ID = id;
    }

    public Long getID() {
	return ID;
    }

    public void setID(Long iD) {
	ID = iD;
    }

    // FIXME type safety unchecked!
    public boolean equals(Object obj) {

	if (obj == null)
	    return false;

	if (!(obj instanceof BaseEntityIDImpl))
	    return false;

	BaseEntityIDImpl<T> element = (BaseEntityIDImpl<T>) obj;

	if (this == element)
	    return true;

	if (this.ID == null || element.getID() == null)
	    return false;

	return new EqualsBuilder().append(this.getID(), element.getID())
		.isEquals();
    }

    public int hashCode() {
	return (ID != null ? ID.hashCode() : super.hashCode());
    }

    @Override
    // FIXME type safety unchecked!
    public T retrieveBasicID() {
	return (T) this.getID();
    }

    @Override
    // FIXME type safety unchecked!
    public void defineBasicID(T id) {
	setID((Long) id);
    }

}
