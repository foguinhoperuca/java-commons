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

package org.awknet.commons.data;

import java.util.List;

import org.awknet.commons.model.entity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public class Dao<T extends BaseEntity> {

    private final Session session;
    @SuppressWarnings("rawtypes")
    private final Class clazz;

    @SuppressWarnings("rawtypes")
    public Dao(Session session, Class clazz) {
        this.session = session;
        this.clazz = clazz;
    }

    public void save(T entity) {
        this.session.save(entity);
    }

    public void delete(T entity) {
        this.session.delete(entity);
    }

    public void update(T entity) {
        this.session.merge(entity);
    }

    public void saveOrUpdate(T entity) {
	this.session.saveOrUpdate(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        return this.session.createCriteria(this.clazz).list();
    }

    @SuppressWarnings("unchecked")
    public T load(Long id) {
        return (T) session.load(this.clazz, id);
    }

    @SuppressWarnings("unchecked")
    public T loadByExample(T entity) {
        Criteria criteria = session.createCriteria(this.clazz)
            .add(Example.create(entity));
        return (T) criteria.uniqueResult();
    }

    protected Session getSession() {
        return session;
    }
}
