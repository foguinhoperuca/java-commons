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

package org.awknet.commons.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesAwknetCommons {

    public static final String DEFAULT_PROPERTIES_FILE = "/awknet-commons.properties";
    private static final Log LOG = LogFactory
	    .getLog(PropertiesAwknetCommons.class);

    public static String resolvePropertiesFile() {
	Properties javaMailProperties = new Properties();
	try {
	    javaMailProperties
		    .load(PropertiesAwknetCommons.class
			    .getResourceAsStream(PropertiesAwknetCommons.DEFAULT_PROPERTIES_FILE));
	} catch (IOException e) {
	    LOG.error("[resolvePropertiesFile] Error opening the file "
		    + PropertiesAwknetCommons.DEFAULT_PROPERTIES_FILE);
	    return null;
	}

	LOG.debug("FILENAME IS: "
		+ javaMailProperties.getProperty("config.filename"));
	return javaMailProperties.getProperty("config.filename");
    }

    public static String getProperty(String property, String fileName) {
	Properties javaMailProperties;

	if (property == null || property.equals(""))
	    return null;

	javaMailProperties = new Properties();
	try {
	    if (fileName == null | fileName.equals(""))
		javaMailProperties.load(PropertiesAwknetCommons.class
			.getResourceAsStream(DEFAULT_PROPERTIES_FILE));
	    else
		javaMailProperties.load(PropertiesAwknetCommons.class
			.getResourceAsStream(fileName));
	} catch (IOException e) {
	    LOG.error("[resolvePropertiesFile] Error opening the file "
		    + DEFAULT_PROPERTIES_FILE);
	    return null;
	}

	return javaMailProperties.getProperty(property);
    }

}
