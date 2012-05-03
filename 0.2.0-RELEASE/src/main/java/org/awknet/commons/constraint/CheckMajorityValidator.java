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

package org.awknet.commons.constraint;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CheckMajorityValidator implements
		ConstraintValidator<CheckMajority, Date> {

	private Majority majority;
	private static final Log LOG = LogFactory
			.getLog(CheckMajorityValidator.class);

	public void initialize(CheckMajority constraintAnnotation) {
		this.majority = constraintAnnotation.value();
	}

	public boolean isValid(Date birthday,
			ConstraintValidatorContext constraintContext) {

		boolean result;

		LOG.debug("[CHECK MAJORITY VALIDATOR] birthday: " + birthday);

		/*
		 * Respect Bean Validation specification: If null is not a valid value
		 * for an element, it should be annotated with @NotNull explicitly.
		 */
		if (birthday == null)
			return true;

		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE);
		LOG.debug("[CHECK MAJORITY VALIDATOR] day: " + day);
		int month = calendar.get(Calendar.MONTH) + 1;
		LOG.debug("[CHECK MAJORITY VALIDATOR] month: " + month);
		int year = calendar.get(Calendar.YEAR);
		LOG.debug("[CHECK MAJORITY VALIDATOR] year: " + year);

		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);
		LOG.debug("[CHECK MAJORITY VALIDATOR] birth: " + birth);
		int dayBirth = birth.get(Calendar.DATE);
		LOG.debug("[CHECK MAJORITY VALIDATOR] dayBirth: " + dayBirth);
		int monthBirth = birth.get(Calendar.MONTH) + 1;
		LOG.debug("[CHECK MAJORITY VALIDATOR] monthBirth: " + monthBirth);
		int yearBirth = birth.get(Calendar.YEAR);
		LOG.debug("[CHECK MAJORITY VALIDATOR] yearBirth: " + yearBirth);

		Integer age = year - yearBirth;
		LOG.debug("[CHECK MAJORITY VALIDATOR] age: " + age);

		if (monthBirth > month || (monthBirth == month && dayBirth > day))
			age -= 1;

		LOG.debug("[CHECK MAJORITY VALIDATOR] age: " + age);

		if (majority == Majority.MAJOR)
			result = age >= 18 ? true : false;
		else
			result = age < 18 ? true : false;

		LOG.debug("[CHECK MAJORITY VALIDATOR] age: " + age + " result: "
				+ result);

		if (!result) {
			constraintContext.disableDefaultConstraintViolation();
			constraintContext
					.buildConstraintViolationWithTemplate(
							"[CHECK MAJORITY] {org.awknet.commons.constraint.checkmajority.message}")
					.addConstraintViolation();
		}
		return result;
	}
}