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

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MajorityEntityValidator {
	
	private static final Log LOG = LogFactory.getLog(MajorityEntityValidator.class);

	public static boolean validate(MajorityEntity entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		try {
			Set<ConstraintViolation<MajorityEntity>> constraintViolations = validator
					.validate(entity);
			if (constraintViolations.size() != 0) {
				LOG.debug("[VALIDATE MAJORITY ENTITY] Errors "
						+ constraintViolations.size());
				for (ConstraintViolation<MajorityEntity> cvMajorityEntity : constraintViolations) {
					LOG.debug("[CONSTRAINT VIOLATION] "
							+ cvMajorityEntity.getMessage() + " [ERROR AT] "
							+ cvMajorityEntity.getPropertyPath()
							+ " [VALUE INVALID] "
							+ cvMajorityEntity.getInvalidValue());
				}
				return false;
			}
			return true;
		} catch (ValidationException e) {
			LOG.error(
					"[MAJORITY ENTITY VALIDATE] An Error was raised - MajorityEntity is invalid!",
					e);
			return false;
		}
	}
}
