#!/bin/bash

# Copyright(C) 2011 - Jefferson Campos - foguinho.peruca@gmail.com
# This program is free software licencied under terms of GPLv2 or higher.
# This program configure my personal development environment. It's very useful and maybe it can help you... ;)

LIBRARY_SOURCE="/home/jefferson/universal/projects/awknet/commons/code/awknet-commons/trunk/LIBRARY.DAT"
PROJECTS_MODEL_URL="http://awknet.googlecode.com/svn/trunk/default_project"
PROJECTS_PATH="$HOME/universal/projects"
LIB_FOLDER="$PROJECTS_PATH/lib"
IDE_PATH="$PROJECTS_PATH/ide"

fnHelp()
{
    echo ""
    echo "Copyright(C) 2011 - Jefferson Campos - foguinho.peruca@gmail.com"
    echo "This program is free software licencied under terms of GPLv2 or higher."
    echo "This program configure my personal development environment."
    echo "Contact me: Jefferson Campos - foguinho.peruca@gmail.com"
    echo "Usage: ./dev_environment.sh [java | ide | library | all | help]"
    echo ""
}

# Create a new project in default projects location.
# Usage: fnCreateProject <PROJECT_NAME>
fnCreateNewProject()
{
    svn co $PROJECTS_MODEL_URL $PROJECTS_PATH/
    mv $PROJECTS_PATH $1    
}

fnCreateDefaultLibraryFolders()
{
	mkdir $LIB_FOLDER/$1/
	mkdir $LIB_FOLDER/$1/$2
	mkdir $LIB_FOLDER/$1/$2/doc
	mkdir $LIB_FOLDER/$1/$2/src
	mkdir $LIB_FOLDER/$1/$2/bin
	mkdir $LIB_FOLDER/$1/$2/download
	mkdir $LIB_FOLDER/$1/$2/tmp
}

# create a library and configure it!
# Usage: <LOCAL_PATH>,<VERSION>,<URL_BIN>[,<URL_SRC>,<URL_DOC>]
fnCreateLibraryPath()
{
    # echo "Configuring library path"

    export IFS=","

    cat $LIBRARY_SOURCE | while read LOCAL_PATH VERSION URL_BIN URL_SRC URL_DOC;
    do

# create default layout
	fnCreateDefaultLibraryFolders $LOCAL_PATH $VERSION
	cd $LOCAL_PATH/$VERSION/tmp

# get all files and extract it!
	wget $URL_BIN
	for FILE_DOWNLOADED in $(ls | awk '{print $1}')
	do
	    tar xvf $FILE_DOWNLOADED -C ../bin/
	    mv $FILE_DOWNLOADED ../download
	done

	if [ -n "$URL_DOC" ]
	then
	    wget $URL_DOC
	    for FILE_DOWNLOADED in $(ls | awk '{print $1}')
	    do
	    	tar xvf $FILE_DOWNLOADED -C ../doc/
	    	mv $FILE_DOWNLOADED ../download
	    done
	fi

	if [ -n "$URL_SRC" ]
	then
	    wget $URL_SRC
	    for FILE_DOWNLOADED in $(ls | awk '{print $1}')
            do
	    	tar xvf $FILE_DOWNLOADED -C ../src/
	    	mv $FILE_DOWNLOADED ../download
            done
	fi

	cd ../
	rm -rf tmp/
    done
}

fnInstallJava()
{
	echo "Installing Java"
	echo "export JAVA_HOME=/usr/local/java" >> /etc/bash.bashrc
	echo "export PATH=$JAVA_HOME/bin:$PATH" >> /etc/bash.bashrc
	echo "export PATH=$JAVA_HOME/bin:$PATH" >> /etc/bash.bashrc
}

fnInstallIDE()
{
	echo "installing IDE"
}

# main program

echo "Configuration of your environment in progress..."
case $1 in
    java)
	fnInstallJava
	;;
    ide)
	fnInstalIDE
	;;
    library)
	fnCreateLibraryPath;
	;;
    all)
	
	;;
    help)
    *)
	fnHelp;
	;;
esac

echo "Done! Please, check if it is all right!"
echo "Thanks for all fishes..."
echo "Bye!!"