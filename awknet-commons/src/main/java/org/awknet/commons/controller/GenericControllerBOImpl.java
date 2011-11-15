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

package org.awknet.commons.controller;

import java.util.List;

import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.model.business.RegisterBOImpl;
import org.awknet.commons.model.entity.BaseEntity;

public class GenericControllerBOImpl<T extends BaseEntity> implements
		GenericController<T> {

	protected final RegisterBOImpl<T> register;
	// FIXME this getter must be enough! No need of specific getter!
	private T entity;
	private List<T> entities;

	public GenericControllerBOImpl(DaoFactory _daoFactory, Class _clazz) {
		register = new RegisterBOImpl<T>(_daoFactory, _clazz);
	}

	@Override
	public void form(T _entity) {
		entities = register.listAll();
		if (_entity.retriveBasicID() != null) {
			entity = register.load(_entity.retriveBasicID());
		}
	}

	@Override
	public void save(T _entity) {
		register.update(_entity);
		this.entity = _entity;
	}

	@Override
	public void delete(T _entity) {
		register.delete(_entity);
	}

	@Override
	public List<T> list() {
		return register.listAll();
	}

	@Override
	public T load(T _entity) {
		return register.load(_entity.retriveBasicID());
	}

	@Override
	public T getEntity() {
		return entity;
	}

	@Override
	public List<T> getEntities() {
		return entities;
	}
}
