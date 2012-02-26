/*
 * Copyright (C) 2012 Jefferson Campos <foguinho.peruca@gmail.com>
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

public enum Literacy {

	INCOMPLETE_BASIC_EDUCATION(0), ELEMENTARY_SCHOOL(1), INCOMPLETE_SECONDARY_EDUCATION(
			2), HIGH_SCHOOL(3), INCOMPLETE_HIGHER_EDUCATION(4), COLLEGE_DEGREE(
			5), SPECIALIZATION(6), MASTERS_DEGREE(7), PHD(8);

	private final int type;

	Literacy(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name();
	}

	@Override
	public String toString() {
		return name();
	}
}
