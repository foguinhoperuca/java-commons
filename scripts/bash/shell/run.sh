# Copyright (C) 2012 - Jefferson Campos - foguinho [dot] peruca [at] gmail [dot] com

# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

# This program manage all tasks during coding.

#!/bin/sh

DATE=$(date +%Y-%m-%d_%Hh%Mm%Ss)
AWKNET_PATH=/home/jecampos/universal/Dropbox/projects/awknet/commons/scm/trunk/awknet-commons
MICASA_PATH=/home/jecampos/universal/Dropbox/projects/pms/MINHA-CASA-VIDA/scm/trunk/minha-casa-vida
ACTION=$1
BKP_CONFLICT_DROPBOX=~/Desktop/bkp_conflict_dropbox/

if [ "$ACTION" = "" ]
then
    echo ""
    echo "Using default parameter: TEST"
    echo ""
    ACTION="TEST"
fi

show_usage()
{
    echo ""
    echo "./run.sh [TEST_MICASA|TEST_COMMONS|COMMIT|RESOLVE_DROPBOX_CONFLICT] [TEST]"
    echo ""
}

testCommons()
{
	cd $AWKNET_PATH
	svn up .
	svn ci .
	mvn clean deploy
}

testMICASA()
{
	cd $MICASA_PATH
	mysql -h localhost -u root -pA12345678a MICASA < $MICASA_PATH/micasa/src/main/database/MICASA.sql
#	mysql -h localhost -u root -pA12345678a MICASA < $AWKNET_PATH/src/main/database/DDL_awknet-commons.sql
#	mysql -h localhost -u root -pA12345678a MICASA < $MICASA_PATH/micasa/src/main/database/DDL_MICASA.sql
	echo ""
	echo "================================================================================"
	echo "TEST: mvn clean test -Dtest=$1"
	echo "================================================================================"
	echo ""
	mvn clean test -Dtest=$1
}

commit()
{
    cd $AWKNET_PATH
    svn up .
    svn ci .
    mvn clean deploy

    cd $MICASA_PATH
    mysql -h localhost -u root -pA12345678a MICASA < $MICASA_PATH/micasa/src/main/database/MICASA.sql
    mysql -h 172.16.1.110 -u root -pA12345678a MICASA < $MICASA_PATH/micasa/src/main/database/MICASA.sql
    svn up .
    svn ci .
    mvn clean tomcat:redeploy -Dtest=AppTest

}

resolve_dropbox_conflict()
{
	if [ ! -e $BKP_CONFLICT_DROPBOX ]
	then
		mkdir $BKP_CONFLICT_DROPBOX
	fi

	mkdir $BKP_CONFLICT_DROPBOX/$DATE
	BKP=$BKP_CONFLICT_DROPBOX/$DATE
	cd $MICASA_PATH
	export IFS=";"
	for i in $(svn st . | grep \(*\) | awk '{for (i = 2 ; i <= NF ; i++){ printf "%s ", $i; if (i == NF) printf ";"}}' | sed -e 's/ /\\ /g' | sed -e 's/(/\\(/g' | sed -e "s/'s/\\\'s/g" | sed -e 's/)/\\)/g' | sed -e 's/\\ ;/;/g')
	do
		echo $i | xargs -I CONFLICT cp CONFLICT $BKP
		echo $i | xargs rm
	done
}

case $ACTION in
    "TEST_MICASA")
		testMICASA $2;
		;;
	"TEST_COMMONS")
		testCommons;
		;;
    "COMMIT")
		commit;
		;;
    "RESOLVE_DROPBOX_CONFLICT")
		resolve_dropbox_conflict;
		;;
    *)
		show_usage;
		;;
esac
