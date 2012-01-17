/*
 * Copyright (C) 2012 Jefferson Campos <jecampos@sorocaba.sp.gov.br>
 * This file is part of MINHA-CASA-VIDA
 * see at http://172.16.1.110/redmine/projects/minha-casa-vida
 *
 * MINHA-CASA-VIDA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MINHA-CASA-VIDA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MINHA-CASA-VIDA. If not, see <http://www.gnu.org/licenses/>.
 */

package org.awknet.commons.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataGenerator {

	public static String CONTROLLER_EXEC_SUCCESS = "ok";
	public static String CONTROLLER_EXEC_ERROR = "error";

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/MICASA";
	public static final String FOREIGN_KEY_DOWN = "?sessionVariables=FOREIGN_KEY_CHECKS=0";
	public static final String URL_FOREIGN_KEY_DOWN = URL_CONNECTION
			+ FOREIGN_KEY_DOWN;
	public static final String USER = "root";
	public static final String PASSWORD = "A12345678a";

	public static final String FULL_INITIAL_DATASET = "/full.xml";
	public static final String PARTIAL_INITIAL_DATASET = "/partial.xml";
	public static final String DEPENDENT_INITIAL_DATASET = "/dependents.xml";
	public static final String AWKNET_COMMONS_DTD = "awknet-commons.dtd";

	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
	public static Date DATE_FUTURE;
	public static final String CPF_OK_PERSON = "20443711380";
	public static final String CPF_OK_SPOUSE = "98806677004";
	public static final String CPF_OK_PERSON_2 = "11169911285";
	public static final String CPF_OK_SPOUSE_2 = "97688628237";
	
}
