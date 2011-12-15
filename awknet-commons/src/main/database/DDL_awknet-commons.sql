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

CREATE TABLE TUser (
    ID INT(10) NOT NULL,
    login VARCHAR(30),
    password VARCHAR(40),
    email VARCHAR(100),
    PRIMARY KEY (ID)
);

-- FIXME retrieve code not updated must be unique per user!
CREATE TABLE TRetrieve_Password_LOG (
    retrieveCode VARCHAR(32),
    userID INT(10) NOT NULL,
    IP VARCHAR(15),
    request DATETIME NOT NULL,
    updated BOOLEAN,
    PRIMARY KEY (retrieveCode),
    FOREIGN KEY (userID) REFERENCES TUser(ID)
);
