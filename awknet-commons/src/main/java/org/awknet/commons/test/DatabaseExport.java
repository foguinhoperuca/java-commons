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

package org.awknet.commons.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.util.search.SearchException;

public class DatabaseExport {

	private Class driverClass;
	private Connection jdbcConnection;
	private IDatabaseConnection connection;

	public void setup() throws ClassNotFoundException, SQLException,
			DatabaseUnitException {
		driverClass = Class.forName(DataGenerator.DRIVER);
		jdbcConnection = DriverManager.getConnection(
				DataGenerator.URL_CONNECTION, DataGenerator.USER,
				DataGenerator.PASSWORD);
		connection = new DatabaseConnection(jdbcConnection);
	}

	public void fullExport() throws SQLException, DataSetException,
			FileNotFoundException, IOException {
		ITableFilter filter = new DatabaseSequenceFilter(connection);
		// IDataSet fullDataSet = connection.createDataSet();
		IDataSet fullDataSet = new FilteredDataSet(filter,
				connection.createDataSet());

		FlatXmlDataSet.write(fullDataSet, new FileOutputStream(DataGenerator.FULL_INITIAL_DATASET));
	}

	// TODO implement using parameter
	public void partialExport(Map<String, String> table)
			throws DataSetException, FileNotFoundException, IOException {
		
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
		partialDataSet.addTable("BAR");
		FlatXmlDataSet.write(partialDataSet,
				new FileOutputStream(DataGenerator.PARTIAL_INITIAL_DATASET));

	}

	// TODO implement using parameter
	public void dependentTablesExport(Map<String, String> table) throws SearchException,
			DataSetException, SQLException, FileNotFoundException, IOException {
		// dependent tables export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(
				connection, "X");
		IDataSet depDataset = connection.createDataSet(depTableNames);
		FlatXmlDataSet
				.write(depDataset, new FileOutputStream(DataGenerator.DEPENDENT_INITIAL_DATASET));
	}

	public void createDTD() throws DataSetException, FileNotFoundException,
			IOException, SQLException {
		FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream(
				DataGenerator.AWKNET_COMMONS_DTD));
	}

	public static void main(String[] args) throws Exception {
		DatabaseExport exporter = new DatabaseExport();
		exporter.setup();
		exporter.fullExport();
	}
}
