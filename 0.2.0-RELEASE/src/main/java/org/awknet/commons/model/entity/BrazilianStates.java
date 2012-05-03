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

public enum BrazilianStates {

    AC(0), AL(1), AM(2), AP(3), BA(4), CE(5), DF(6), ES(7), GO(8), MA(9), MG(10), MS(
	    11), MT(12), PA(13), PB(14), PE(15), PI(16), PR(17), RJ(18), RN(19), RO(
	    20), RR(21), RS(22), SC(23), SE(24), SP(25), TO(26);

    private int type;

    BrazilianStates(int type) {
	this.type = type;
    }

    public int getType() {
	return type;
    }

    public String getName() {
	return name();
    }
}
