#/bin/bash

spring_dir=backend
react_dir=frontend

(
cd $spring_dir || exit
./mvnw clean
echo  "------------------------------------------------spring process completed"
) &
(
cd $react_dir || exit
rm -rf node_modules
echo "------------------------------------------------react process completed"
) &
