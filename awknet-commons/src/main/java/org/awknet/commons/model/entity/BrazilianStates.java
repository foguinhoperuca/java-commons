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
	    11), MT(12), PA(14), PB(15), PE(16), PI(17), PR(18), RJ(19), RN(20), RO(
	    21), RR(22), RS(23), SC(24), SE(25), SP(26), TO(27);

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
