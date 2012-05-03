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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class MajorityEntityTest {

	private SimpleDateFormat formatter;
	private Date dateMajor, dateMinor;
	private MajorityEntity entityMajor;
	private MajorityEntity entityMinor;

	@Before
	public void setUp() throws Exception {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		entityMajor = new Major(null);
		entityMinor = new Minor(null);
	}

	@Test
	// TODO pegar a data de forma dinamica.
	public void testValidate() throws ParseException {
		 // 0 - year that turns 18:
		 // 0.1 - monthBirth > month
		dateMajor = formatter.parse("1990-12-31");
		dateMinor = formatter.parse("1994-12-31");
		
		entityMajor.setBirthday(dateMajor);
		assertTrue(MajorityEntityValidator.validate(entityMajor));
		entityMajor.setBirthday(dateMinor);
		assertFalse(MajorityEntityValidator.validate(entityMajor));
		
		entityMinor.setBirthday(dateMinor);
		assertTrue(MajorityEntityValidator.validate(entityMinor));
		entityMinor.setBirthday(dateMajor);
		assertFalse(MajorityEntityValidator.validate(entityMinor));

		// 0.2 - monthBirth == month && dayBirth > day
		dateMajor = formatter.parse("1994-05-01");
		dateMinor = formatter.parse("1994-05-30");
		
		entityMajor.setBirthday(dateMajor);
		assertTrue(MajorityEntityValidator.validate(entityMajor));		
		entityMajor.setBirthday(dateMinor);
		assertFalse(MajorityEntityValidator.validate(entityMajor));

		entityMinor.setBirthday(dateMinor);
		assertTrue(MajorityEntityValidator.validate(entityMinor));
		entityMinor.setBirthday(dateMajor);
		assertFalse(MajorityEntityValidator.validate(entityMinor));

		// 2 - age < 18
		dateMajor = formatter.parse("2012-01-01");
		entityMajor.setBirthday(dateMajor);
		assertFalse(MajorityEntityValidator.validate(entityMajor));

		dateMinor = formatter.parse("2000-04-01");
		entityMinor.setBirthday(dateMinor);
		assertTrue(MajorityEntityValidator.validate(entityMinor));

		// 3 -age >= 18
		dateMajor = formatter.parse("1970-01-01");
		entityMajor.setBirthday(dateMajor);
		assertTrue(MajorityEntityValidator.validate(entityMajor));

		dateMinor = formatter.parse("1970-04-01");
		entityMinor.setBirthday(dateMinor);
		assertFalse(MajorityEntityValidator.validate(entityMinor));
	}
}
