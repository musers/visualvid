#/bin/sh

file="./.env"

if [ -f "$file" ]
then
  echo "$file found."

  while IFS='=' read -r key value
  do
    export $key=$value
  done < "$file"

else
  echo "$file not found."
fi
