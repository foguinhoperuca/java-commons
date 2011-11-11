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

import java.util.List;

import org.awknet.commons.data.DaoFactory;
import org.awknet.commons.model.entity.BaseEntity;

// FIXME error passing generic to getRegisterDao
public class RegisterBOImpl<T extends BaseEntity> implements Register<T> {

    private DaoFactory daoFactory;

    public RegisterBOImpl(DaoFactory _daoFactory) {
	daoFactory = _daoFactory;
    }

    @Override
    public T save(T entity) {
	daoFactory.beginTransaction();
	daoFactory.getRegisterDao(entity.getClass()).save(entity);
	daoFactory.commit();
	return null;
    }

    @Override
    public T update(T entity) {
	daoFactory.beginTransaction();
	daoFactory.getRegisterDao(entity.getClass()).update(entity);
	daoFactory.commit();
	return null;
    }

    @Override
    public void delete(T entity) {
	daoFactory.beginTransaction();
	daoFactory.getRegisterDao(entity.getClass()).delete(entity);
	daoFactory.commit();
    }

    // FIXME Oh hell! need receive some var of type T - otherwise I need receive the class in sign.
    @Override
    public List<T> listAll() {
	daoFactory.beginTransaction();
	daoFactory.getRegisterDao(((T) new Object()).getClass()).list(); // only Chuck Norris can do this!!
	daoFactory.commit();
	return null;
    }

    @Override
    public T load(long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public T loadByExemple(long id) {
	// TODO Auto-generated method stub
	return null;
    }

}
