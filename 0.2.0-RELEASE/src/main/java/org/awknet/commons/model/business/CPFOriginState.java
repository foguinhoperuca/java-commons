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

package org.awknet.commons.model.business;

/**
 * 0 Rio Grande do Sul<br/>
 * 1 Distrito Federal, Goiás, Mato Grosso, Mato Grosso do Sul e Tocantins<br/>
 * 2 Amazonas, Pará, Roraima, Amapá, Acre e Rondônia<br/>
 * 3 Ceará, Maranhão e Piauí<br/>
 * 4 Paraíba, Pernambuco, Alagoas e Rio Grande do Norte<br/>
 * 5 Bahia e Sergipe<br/>
 * 6 Minas Gerais<br/>
 * 7 Rio de Janeiro e Espírito Santo<br/>
 * 8 São Paulo<br/>
 * 9 Paraná e Santa Catarina<br/>
 */
public enum CPFOriginState {
    RS(0), DF(1), GO(1), MT(1), MS(1), TO(1), AM(2), PA(2), RR(2), AP(2), AC(2), RO(
	    2), CE(3), MA(3), PI(3), PB(4), PE(4), AL(4), RN(4), BA(5), SE(5), MG(
	    6), RJ(7), ES(7), SP(8), PR(9), SC(9);

    private int type;

    CPFOriginState(int type) {
	this.type = type;
    }

    public int getType() {
	return type;
    }
}

