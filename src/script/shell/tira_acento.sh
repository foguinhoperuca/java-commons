#!/bin/sh
# Copyright (C) 2011 - Jefferson Campos - foguinho [dot] peruca [at] gmail [dot] com

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

# This program remove all accents

if [ $# -lt 2 ]; then
echo 1>&2 Sintaxe: $0 input_file output_file
exit 1
fi

cat $INPUT_FILE | sed 's/ /_/g' | sed 's/ /_/g' | sed 's/ã/a/g' | sed 's/Ã/A/g' | sed 's/à/a/g' | sed 's/À/A/g' | sed 's/ô/o/g' | sed 's/ô/o/g' | sed 's/Õ/O/g' | sed 's/é/e/g' | sed 's/É/E/g' | sed 's/á/a/g' | sed 's/ó/o/g' | sed 's/Á/A/g' | sed 's/Ó/O/g' | sed 's/ç/c/g' | sed 's/Ç/C/g' | sed 's/ê/e/g' | sed 's/Ê/E/g' | sed 's/ú/u/g' | sed 's/Ú/U/g' | sed 's/â/a/g' | sed 's/Â/A/g' | sed 's/í/i/g' | sed 's/Í/I/g' | sed 's/Ü/U/g' | sed 's/ü/u/g' > $OUTPUT_FILE

# cat subject.txt.original | sed 's/ /_/g' | sed 's/ /_/g' | sed 's/ã/a/g' | sed 's/Ã/A/g' | sed 's/à/a/g' | sed 's/À/A/g' | sed 's/ô/o/g' | sed 's/ô/o/g' | sed 's/Õ/O/g' | sed 's/é/e/g' | sed 's/É/E/g' | sed 's/á/a/g' | sed 's/ó/o/g' | sed 's/Á/A/g' | sed 's/Ó/O/g' | sed 's/ç/c/g' | sed 's/Ç/C/g' | sed 's/ê/e/g' | sed 's/Ê/E/g' | sed 's/ú/u/g' | sed 's/Ú/U/g' | sed 's/â/a/g' | sed 's/Â/A/g' | sed 's/í/i/g' | sed 's/Í/I/g' | sed 's/Ü/U/g' | sed 's/ü/u/g'