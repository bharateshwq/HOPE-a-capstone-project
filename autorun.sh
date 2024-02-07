#/bin/bash

spring_dir=backend
react_dir=frontend

echo "running spring"
(
cd $spring_dir || exit
./mvnw spring-boot:run
) &
echo "running react"
(
cd $react_dir || exit
npm install || npm audit --fix
npm start


) &
