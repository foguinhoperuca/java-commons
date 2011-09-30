#!/bin/bash

# Copyright(C) 2011 - Jefferson Campos - foguinho.peruca@gmail.com
# This program is free software licencied under terms of GPLv2 or higher.
# This program configure my personal development environment. It's very useful and maybe it can help you... ;)
# For more information, see README file.

# URL for all resources
PROJECTS_MODEL_URL="http://awknet.googlecode.com/svn/trunk/default_project"
PROJECTS_PATH="$HOME/universal/projects"
LIB_FOLDER="$PROJECTS_PATH/lib"
IDE_PATH="$PROJECTS_PATH/ide"
LIBRARY_SOURCE="$PROJECTS_PATH/awknet/commons/scm/trunk/LIBRARY.DAT"

# Folder's layout
BIN_FOLDER=bin
SRC_FOLDER=src
DOC_FOLDER=doc
DOWNLOAD_FOLDER=download
TMP_FOLDER=tmp
EXTRACTED_FOLDER=extracted

NAME_FILE_DOWNLOADED=""

# All data files will be separated by comma.
export IFS=","

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
    echo "PROJECTS_MODEL_URL   - " $PROJECTS_MODEL_URL
    echo "PROJECTS_PATH        - " $PROJECTS_PATH
    echo "LIB_FOLDER           - " $LIB_FOLDER
    echo "IDE_PATH             - " $IDE_PATH
    echo "LIBRARY_SOURCE       - " $LIBRARY_SOURCE
    echo "BIN_FOLDER           - " $BIN_FOLDER
    echo "SRC_FOLDER           - " $SRC_FOLDER
    echo "DOC_FOLDER           - " $DOC_FOLDER
    echo "DOWNLOAD_FOLDER      - " $DOWNLOAD_FOLDER
    echo "TMP_FOLDER           - " $TMP_FOLDER
    echo "EXTRACTED_FOLDER     - " $EXTRACTED_FOLDER
    echo "NAME_FILE_DOWNLOADED - " $NAME_FILE_DOWNLOADED
    echo ""

    fnCreateLibFolderForce
    tree $LIB_FOLDER | more
}

# Create all folders of lib - brutal force
fnCreateLibFolderForce()
{
    if [ -e $LIB_FOLDER ]
    then
	rm -rf $LIB_FOLDER
    fi
    mkdir $LIB_FOLDER

    export IFS=","
    cat $LIBRARY_SOURCE | while read LANG LOCAL_PATH VERSION URL_BIN URL_SRC URL_DOC;
    do
	fnCreateDefaultLibraryFolders $LANG $LOCAL_PATH $VERSION
	fnGetFileNameToDownload $URL_SRC
	# echo $NAME_FILE_DOWNLOADED
    done
}

# Retrieve the original name of download. Need this to fix a bug of wget.
fnGetFileNameToDownload()
{
    export IFS="/"
    URL=$1

    IFS='/' read -ra FILE <<< "$URL"
    tLen=${#FILE[@]}
    NAME_FILE_DOWNLOADED=${FILE[$tLen - 1]}
    export IFS=","
}

# Create the default layout to store lib.
# Usage: fnCreateDefaultLibraryFolders <lang> <lib> <version>
fnCreateDefaultLibraryFolders()
{
    LANG=$1
    LIB=$2
    VERSION=$3

    if [ ! -e $LIB_FOLDER/$LANG/ ]
    then
	mkdir $LIB_FOLDER/$LANG/
    fi

    if [ ! -e $LIB_FOLDER/$LANG/$LIB/ ]
    then
	mkdir $LIB_FOLDER/$LANG/$LIB/
    fi

    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION
    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION/$DOC_FOLDER
    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION/$SRC_FOLDER
    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION/$BIN_FOLDER
    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION/$DOWNLOAD_FOLDER
    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION/$TMP_FOLDER
    mkdir $LIB_FOLDER/$LANG/$LIB/$VERSION/$EXTRACTED_FOLDER
}

# Create and configure a library!
# Usage: <LOCAL_PATH>,<VERSION>,<URL_BIN>[,<URL_SRC>,<URL_DOC>]
# TODO review the extract files.
# TODO review the relative path (src and doc logic)
fnCreateLibraryPath()
{
    # echo "Configuring library path"

    if [ -e $LIB_FOLDER ]
    then
	rm -rf $LIB_FOLDER
    fi
    mkdir $LIB_FOLDER

    cat $LIBRARY_SOURCE | while read LANG LOCAL_PATH VERSION URL_BIN URL_SRC URL_DOC;
    do

# Create default layout
	fnCreateDefaultLibraryFolders $LANG $LOCAL_PATH $VERSION
	cd $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER

# Get and extract all files.
	wget $URL_BIN
	for FILE_DOWNLOADED in $(ls | awk '{print $1}')
	do
	    fnInstallLib $FILE_DOWNLOADED bin
	done
	fnCleanFolder recreate

	if [ -n "$URL_SRC" ]
	then
	    fnGetFileNameToDownload $URL_SRC
	    touch $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER/$NAME_FILE_DOWNLOADED
	    wget -O $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER/$NAME_FILE_DOWNLOADED $URL_SRC

#	    echo "FILE SRC IS: " $(ls $(pwd) | awk '{print $1}')

	    for FILE_DOWNLOADED in $(ls $(pwd) | awk '{print $1}')
            do
		fnInstallLib $(pwd)/$FILE_DOWNLOADED src
            done
	fi
 	fnCleanFolder recreate

 	if [ -n "$URL_DOC" ]
 	then

	    fnGetFileNameToDownload $URL_DOC
	    touch $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER/$NAME_FILE_DOWNLOADED
	    wget -O $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER/$NAME_FILE_DOWNLOADED $URL_DOC

	    echo "FILE DOC IS: " $(ls $(pwd) | awk '{print $1}')

	    for FILE_DOWNLOADED in $(ls $(pwd) | awk '{print $1}')
            do
		fnInstallLib $(pwd)/$FILE_DOWNLOADED src
            done

 	#     wget $URL_DOC
 	#     for FILE_DOWNLOADED in $(ls | awk '{print $1}')
 	#     do
 	#     	# tar xvf $FILE_DOWNLOADED -C ../doc/
 	#     	# mv $FILE_DOWNLOADED ../download
 	# 	fnInstallLib $FILE_DOWNLOADED doc
 	#     done
 	fi

	# cd ../
# 	fnCleanFolder
    done
}

# Extract and save files.
# Usage: fnInstallLib <PATH_FILE_DOWNLOADED> <TYPE: [bin|doc|src]>
# TODO: Verify all type of download: [.tar.bz2 | tar.gz | .zip]
fnInstallLib()
{
    FILE_DOWNLOADED=$1
    TYPE=$2

    tar xvf $FILE_DOWNLOADED -C ../$EXTRACTED_FOLDER/    
    FILE_EXTRACTED=$(ls ../$EXTRACTED_FOLDER)
    mv ../$EXTRACTED_FOLDER/$FILE_EXTRACTED/* ../$TYPE
    mv $FILE_DOWNLOADED ../$DOWNLOAD_FOLDER
}

fnCleanFolder()
{
    rm -rf ../$TMP_FOLDER/
    rm -rf ../$EXTRACTED_FOLDER/
    if [ "$1" == "recreate" ]
    then
	mkdir ../$TMP_FOLDER
	mkdir ../$EXTRACTED_FOLDER
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

# Create a new project in default projects location.
# Usage: fnCreateProject <PROJECT_NAME>
fnCreateNewProject()
{
    svn co $PROJECTS_MODEL_URL $PROJECTS_PATH/
    mv $PROJECTS_PATH $1 
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
    java_lib)
	fnCreateLibraryPath; # must use arg java
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