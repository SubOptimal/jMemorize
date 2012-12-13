#!/bin/sh

#
# script to convert existing native files into properties fileformat and vice versa
#

# check if the binary is in the path
binary=`which native2ascii`
if [ $? -ne 0 ]
then
  echo "executable native2ascii not found in path"
  exit 1
fi

echo "using executable: ${binary}"

# convert native -> properties
printf "###\n### convert files with native encoding into properties files (ASCII)\n###\n"
for native_file in *.native
do
  printf "native: ${native_file}"
  prop_file="${native_file%.*native}.properties"
  if [ ! -f "${prop_file}" ]
  then
    echo " --> ${prop_file}"
    native2ascii ${native_file} ${prop_file}
  else
    echo " --> skipped..."
  fi
done

# convert properties -> native
printf "###\n### convert properties files back into files with native encoding (UTF8)\n###\n"
for prop_file in *.properties
do
  printf "properties: ${prop_file}"
  native_file="${prop_file%.*properties}.native"
  if [ ! -f "$native_file" ]
  then
    echo " --> ${native_file}"
    native2ascii -reverse ${prop_file} ${native_file}
  else
    echo " --> skipped..."
  fi
done
