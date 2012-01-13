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

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

// TODO how reuse this class efficiently?
// FIXME DB URL CONNECTION OVERRIDE!
public class InsertCleanFullTest extends DBTestCase {

	private String driver;
	private String url;
	private String user;
	private String password;

	public InsertCleanFullTest() {
		setupDefaultDBConnection();
		configureDBConnection();
	}

	public InsertCleanFullTest(String name) {
		super(name);
		setupDefaultDBConnection();
		configureDBConnection();
	}

	public void setupDefaultDBConnection() {
		driver = DataGenerator.DRIVER;
		url = DataGenerator.URL_FOREIGN_KEY_DOWN;
		user = DataGenerator.USER;
		password = DataGenerator.PASSWORD;
	}

	public void configureDBConnection() {
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				user);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				password);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(DataGenerator.class
				.getResourceAsStream(DataGenerator.FULL_INITIAL_DATASET));
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	// @Override
	// protected DatabaseOperation getTearDownOperation() throws Exception {
	// return DatabaseOperation.DELETE_ALL;
	// }
}
