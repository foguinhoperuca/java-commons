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
 * along with Foobar. If not, see <http://www.gnu.org/licenses/>.
 */

package org.awknet.commons.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public class Dao<T> {

    private final Session session;
    private final Class clazz;

    public Dao(Session session, Class clazz) {
        this.session = session;
        this.clazz = clazz;
    }

    public void save(T u) {
        this.session.save(u);
    }

    public void delete(T u) {
        this.session.delete(u);
    }

    public void update(T u) {
        this.session.merge(u);
    }

    public List<T> list() {
        return this.session.createCriteria(this.clazz).list();
    }

    public T load(Long id) {
        return (T) session.load(this.clazz, id);
    }

    public T loadByExample(T u) {
        Criteria criteria = session.createCriteria(this.clazz)
            .add(Example.create(u));
        return (T) criteria.uniqueResult();
    }

    protected Session getSession() {
        return session;
    }
}
