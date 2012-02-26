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

package org.awknet.commons.mail;

/**
 * Return the recipient type (email logic). The types ares: <br/>
 * <ul>
 * <li>RECIPIENT_TYPE_TO(0);</li>
 * <li>RECIPIENT_TYPE_CC(1);</li>
 * <li>RECIPIENT_TYPE_BCC(2);</li>
 * </ul>
 */
public enum RecipientType {

    RECIPIENT_TYPE_TO(0), RECIPIENT_TYPE_CC(1), RECIPIENT_TYPE_BCC(2);

    private int type;

    RecipientType(int type) {
	this.type = type;
    }

    public int getType() {
	return type;
    }

    public String getName() {
	return name();
    }
}
