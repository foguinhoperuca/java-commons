#!/bin/bash

# Copyright(C) 2011 - Jefferson Campos - foguinho.peruca@gmail.com
# This program is free software licencied under terms of GPLv2 or higher.
# This program configure my personal development environment. It's very useful and maybe it can help you... ;)
# For more information, see README file.

PROJECTS_MODEL_URL="http://awknet.googlecode.com/svn/trunk/default_project"
PROJECTS_PATH="$HOME/universal/projects"
LIB_FOLDER="$PROJECTS_PATH/lib"
IDE_PATH="$PROJECTS_PATH/ide"
LIBRARY_SOURCE="$PROJECTS_PATH/awknet/commons/scm/trunk/LIBRARY.DAT"

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

fnDebug()
{
    echo ""
    echo "Debugging... =]"
    echo "PROJECTS_MODEL_URL - " $PROJECTS_MODEL_URL
    echo "PROJECTS_PATH      - " $PROJECTS_PATH
    echo "LIB_FOLDER         - " $LIB_FOLDER
    echo "IDE_PATH           - " $IDE_PATH
    echo "LIBRARY_SOURCE     - " $LIBRARY_SOURCE
    echo ""

    fnCreateLibFolderForce

    tree $LIB_FOLDER | more
}

# Create all folders of lib - brutal force
fnCreateLibFolderForce()
{
    if [ -e $LIB_FOLDER ]
    then
	echo "exist!! Is here! $LIB_FOLDER"
	rm -rf $LIB_FOLDER
    fi
    mkdir $LIB_FOLDER

    export IFS=","
    cat $LIBRARY_SOURCE | while read LOCAL_PATH VERSION URL_BIN URL_SRC URL_DOC;
    do
	fnCreateDefaultLibraryFolders $LOCAL_PATH $VERSION
#	echo "tmp path of $LOCAL_PATH is: " $(ls -l $LIB_FOLDER/$LOCAL_PATH/$VERSION/)
	cd $LIB_FOLDER/$LOCAL_PATH/$VERSION/
	pwd
    done
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
	mkdir $LIB_FOLDER/$1/$2/extracted
}

# create a library and configure it!
# Usage: <LOCAL_PATH>,<VERSION>,<URL_BIN>[,<URL_SRC>,<URL_DOC>]
fnCreateLibraryPath()
{
    # echo "Configuring library path"

    if [ -e $LIB_FOLDER ]
    then
	rm -rf $LIB_FOLDER
    fi
    mkdir $LIB_FOLDER

    export IFS=","

    cat $LIBRARY_SOURCE | while read LOCAL_PATH VERSION URL_BIN URL_SRC URL_DOC;
    do

# create default layout
	fnCreateDefaultLibraryFolders $LOCAL_PATH $VERSION
	cd $LIB_FOLDER/$LOCAL_PATH/$VERSION/tmp

# get all files and extract it!
	wget $URL_BIN
	for FILE_DOWNLOADED in $(ls | awk '{print $1}')
	do
	    fnInstallLib $FILE_DOWNLOADED bin
	    # tar xvf $FILE_DOWNLOADED -C ../extracted/    
	    # EXTRACTED=$(ls ../extracted)
	    # mv ../extracted/$EXTRACTED/* ../bin/
	    # mv $FILE_DOWNLOADED ../download
	done
	# fnCleanFolder recreate

	# if [ -n "$URL_SRC" ]
	# then
	#     echo "Download SRC: $URL_SRC"
	#     wget $URL_SRC
	#     for FILE_DOWNLOADED in $(ls | awk '{print $1}')
        #     do
	#     	# tar xvf $FILE_DOWNLOADED -C ../src/
	#     	# mv $FILE_DOWNLOADED ../download
	# 	fnInstallLib $FILE_DOWNLOADED src
        #     done
	# fi
# 	fnCleanFolder recreate

# 	if [ -n "$URL_DOC" ]
# 	then
# 	    wget $URL_DOC
# 	    for FILE_DOWNLOADED in $(ls | awk '{print $1}')
# 	    do
# 	    	# tar xvf $FILE_DOWNLOADED -C ../doc/
# 	    	# mv $FILE_DOWNLOADED ../download
# 		fnInstallLib $FILE_DOWNLOADED doc
# 	    done
# 	fi

# #	cd ../
 	fnCleanFolder
    done
}

# $2 is the type: [bin|doc|src]
fnInstallLib()
{
    FILE_DOWNLOADED=$1
    TYPE=$2

    tar xvf $FILE_DOWNLOADED -C ../extracted/    
    EXTRACTED=$(ls ../extracted)
    mv ../extracted/$EXTRACTED/* ../$TYPE
    mv $FILE_DOWNLOADED ../download
}

fnCleanFolder()
{
    rm -rf ../tmp/
    rm -rf ../extracted/
    if [ "$1" == "recreate" ]
    then
	mkdir ../tmp
	mkdir ../extracted
    fi
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
	fnInstallJava
	fnInstalIDE
	fnCreateLibraryPath;
	;;
    debug)
	fnDebug;
	;;
    *)
	fnHelp;
	;;
esac

echo "Done! Please, check if it is all right!"
echo "Thanks for all fishes..."
echo "Bye!!"