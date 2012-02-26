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

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * This class implement a filter and define a encode to request. By default,
 * it's use UTF-8. Use: <code>new EncodingFilter();</code> Also, you can use
 * ISO-8859-1, just do:
 * <code>new EncodingFilter(EncodingFilter.ISO88591);</code>
 * 
 */
public class EncodingFilter implements Filter {

	public static final String UTF8 = "UTF-8";
	public static final String ISO88591 = "ISO-8859-1";
	private String encoding;

	/**
	 * Default constructor. Use UTF-8 encoding.
	 */
	public EncodingFilter() {
		this.encoding = UTF8;
	}

	/**
	 * A encoding defined by client code.
	 * 
	 * @param _encoding
	 *            a encoding (like ISO-8859-1). Use
	 *            <code>new EncodingFilter(EncodingFilter.ISO88591);</code>
	 */
	public EncodingFilter(String _encoding) {
		this.encoding = _encoding;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		response.setCharacterEncoding(encoding);
		request.setCharacterEncoding(encoding);
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
