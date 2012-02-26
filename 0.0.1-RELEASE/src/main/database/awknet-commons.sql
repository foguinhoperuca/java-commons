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

-- Don't forget: Modify the path for all lines! To use shell type:
-- $ export AWKNET_COMMONS_SRC=/home/jefferson/universal/projects/awknet/commons/code
-- $ mysql --default-character-set UTF8 -uawknet-commons -pA12345678a -e"Source ${AWKNET_COMMONS_SRC}/trunk/awknet-commons/src/main/database/awknet-commons.sql"
-- Or if you want to make it more sweet use this:
-- $ alias awknet-commons_db='mysql --default-character-set UTF8 -usigerar -pA12345678a -e"Source ${AWKNET_COMMONS_SRC}/trunk/awknet-commons/src/main/database/awknet-commons.sql"'
-- $ awknet-commons_db
-- Add it to the end of your ~/.bashrc !
-- Have fun! - Jeff - foguinho [dot] peruca [at] gmail [dot] com
source ${AWKNET_COMMONS_SRC}/trunk/awknet-commons/src/main/database/DDL_awknet-commons.sql
source ${AWKNET_COMMONS_SRC}/trunk/awknet-commons/src/main/database/DML_awknet-commons.sql
