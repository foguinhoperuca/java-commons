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

import org.awknet.commons.model.entity.BaseEntity;

public interface Register<T extends BaseEntity> {
    public T save(T _entity);

    public T saveUnit(T _entity);

    public T update(T _entity);

    public T updateUnit(T _entity);

    public T saveOrUpdate(T _entity);

    public T saveOrUpdateUnit(T _entity);

    public void delete(T _entity);

    public void deleteUnit(T _entity);

    public List<T> listAll();

    public T load(long id);

    public T loadByExemple(T _entity);
}
