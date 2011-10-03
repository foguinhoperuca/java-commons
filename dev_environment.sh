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
USER_LIBRARY_ECLIPSE=$(pwd)/test/test.userlibraries
TMP_USERLIB=$(pwd)/test/tmp_userlib.tmp

# All data files will be separated by comma.
export IFS=","

fnHelp()
{
    echo ""
    echo "Copyright(C) 2011 - Jefferson Campos - foguinho.peruca@gmail.com"
    echo "This program is free software licencied under terms of GPLv2 or higher."
    echo "This program configure my personal development environment."
    echo "Contact me: Jefferson Campos - foguinho.peruca@gmail.com"
    echo "Usage: ./dev_environment.sh [java | ide | library | java_lib | user_library | all | help]"
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

    USER_LIBRARY_ECLIPSE=$(pwd)/test/test.userlibraries
    fnCreateUserLibraryEclipse

    LIBRARY_SOURCE="$PROJECTS_PATH/awknet/commons/scm/trunk/test/LIBRARY.DAT"

    cat $LIBRARY_SOURCE | while read LANG LOCAL_PATH VERSION URL_BIN URL_SRC URL_DOC MAIN_JAR;
    do

# Create default layout
	fnCreateDefaultLibraryFolders $LANG $LOCAL_PATH $VERSION
	cd $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER

	if [ "$LANG" = "java" ]
	then
	    LINES=$(wc -l $USER_LIBRARY_ECLIPSE | awk '{print $1}')
	    SHOW=$(expr $LINES - 1)
	    head -n$SHOW $USER_LIBRARY_ECLIPSE > $TMP_USERLIB
	    echo "<library name=\"$LOCAL_PATH-$VERSION\" systemlibrary=\"false\">" >> $TMP_USERLIB
	    cat $TMP_USERLIB > $USER_LIBRARY_ECLIPSE	    
	fi

# Get and extract all files.
	wget $URL_BIN
	for FILE_DOWNLOADED in $(ls | awk '{print $1}')
	do
	    fnInstallLib $FILE_DOWNLOADED bin
	done
	fnCleanFolder recreate
	for FILE_DOWNLOADED in $(ls ../$BIN_FOLDER | grep .jar | awk '{print $1}')
	do
	    if [ -n FILE_DOWNLOADED ]
	    then
		fnAddLibraryEclipse $FILE_DOWNLOADED $URL_DOC $URL_SRC
	    fi
	done

	if [ -n "$URL_SRC" ]
	then
	    fnGetFileNameToDownload $URL_SRC
	    touch $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER/$NAME_FILE_DOWNLOADED
	    wget -O $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_FOLDER/$NAME_FILE_DOWNLOADED $URL_SRC

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
 	fi

	echo "COMPLET_MAIN_JAR IS: $COMPLET_MAIN_JAR"
	COMPLET_MAIN_JAR=$LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_BIN/$MAIN_JAR
	fnAddLibraryEclipse $COMPLET_MAIN_JAR $$LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_DOC $LIB_FOLDER/$LANG/$LOCAL_PATH/$VERSION/$TMP_SRC
	cat $TMP_USERLIB > $USER_LIBRARY_ECLIPSE

	fnCleanFolder
    done
}

# Extract and save files.
# Usage: fnInstallLib <PATH_FILE_DOWNLOADED> <TYPE: [bin|doc|src]>
# TODO: Verify all type of download: [.tar.bz2 | tar.gz | .zip]
fnInstallLib()
{
    FILE_DOWNLODED=$1
    TYPE=$2

    tar xvf $FILE_DOWNLOADED -C ../$EXTRACTED_FOLDER/    
    FILE_EXTRACTED=$(ls ../$EXTRACTED_FOLDER)
    mv ../$EXTRACTED_FOLDER/$FILE_EXTRACTED/* ../$TYPE
    mv $FILE_DOWNLOADED ../$DOWNLOAD_FOLDER
}

# Remove temp folders.
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

fnCreateUserLibrary()
{
    fnCreateUserLibraryEclipse
#    cat $USER_LIBRARY_ECLIPSE
#    fnAddLibraryEclipse documentation binary source_code
#    fnAddLibraryEclipse DOC BIN SOURCE
#    fnAddLibraryEclipse BOZO MAFALDA CARECA
    fnAddLibraryEclipse "/home/jecampos/BIN" "/home/jecampos/DOC" "/home/jecampos/SOURCE"
    fnAddLibraryEclipse "/home/jecampos/BIN" "/home/jecampos/DOC" ""
    fnAddLibraryEclipse "/home/jecampos/BIN" "" "/home/jecampos/SOURCE"
    fnAddLibraryEclipse "/home/jecampos/BIN" "" ""
}

# Create a lib on file.
# Usage: fnAddLibraryEclipse <BIN_FILE> [<DOC_FILE>|""] [SRC_FILE>|""]
# TODO: review doc and src param (starting smelling...)
fnAddLibraryEclipse()
{
    BIN=$1
    DOC=$2
    SRC=$3
    DOC_STR=""
    SRC_STR=""

    USER_LIBRARY_ECLIPSE=$(pwd)/test/test.userlibraries

    LINES=$(wc -l $USER_LIBRARY_ECLIPSE | awk '{print $1}')
    SHOW=$(expr $LINES - 2)
    head -n$SHOW $USER_LIBRARY_ECLIPSE > $TMP_USERLIB
    if [ "$DOC" != "" ]
    then
	DOC_STR="javadoc=\"jar:file:$DOC!\""
    fi
    if [ "$SRC" != "" ]
    then
	SRC_STR="source=\"$SRC\""
    fi

    echo "<archive $DOC_STR path=\"$BIN\" $SRC_STR/>" >> $TMP_USERLIB
    echo "</library>" >> $TMP_USERLIB
    echo "</eclipse-userlibraries>" >> $TMP_USERLIB
    cat $TMP_USERLIB > $USER_LIBRARY_ECLIPSE
}

# Create user library to eclipse.
fnCreateUserLibraryEclipse()
{
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" > $USER_LIBRARY_ECLIPSE
    echo "<eclipse-userlibraries version=\"2\">" >> $USER_LIBRARY_ECLIPSE
    echo "</eclipse-userlibraries>" >> $USER_LIBRARY_ECLIPSE
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
echo ""
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
    user_library)
	fnCreateUserLibrary;
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

echo ""
echo "Done! Please, check if it is all right!"
echo "Thanks for all fishes..."
echo "Bye!!"