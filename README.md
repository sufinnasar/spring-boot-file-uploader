# spring-boot-file-uploader
1.Make sure the JAVA_HOME is set to 1.8
2.Open the application.properties file from the folder location bloomberg\src\main\resources . Change the below parameters accordingly.
spring.datasource.url=jdbc:mysql://localhost:3306/databasename?autoReconnect=true&useSSL=false&rewriteBatchedStatements=true
spring.datasource.username=username
spring.datasource.password=password
3.Open command prompt and move to spring-boot-file-uploader folder
4.Run the command mvnw package  .Wait for the dependencies to download and for the build.
5.Once build is success move to the folder target from command prompt and run the below command
java -jar bloomberg-1.0.0.jar
6.Application has started on the port 8090 . Access it using the url http://localhost:8090/file/upload
7.For stopping the application.Press ctrl+shift+c
8.For Sample files please refer fxfile13.csv and fxfile4.csv in the root folder.
------------------------------------------------------------------
If application is not starting please import the project bloomberg as a maven project.Run it as spring boot app
