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

package org.awknet.commons.exception;

public enum UserExceptionType {

	ID(0), PASSWORD(1), EMAIL_NULL(2), LOGIN(3), VALIDATION(4), PERSIST(5), ENCRYPT_PASSWORD(
			6);

	private int type;

	UserExceptionType(int _type) {
		this.type = _type;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name();
	}
}
