# spring-boot-file-uploader

- Make sure the JAVA_HOME is set to 1.8
- Open the application.properties file from the folder location spring-boot-file-uploader\src\main\resources. 
- Change the below parameters accordingly.
  - **spring.datasource.url**=`jdbc:mysql://localhost:3306/databasename?autoReconnect=true&useSSL=false&rewriteBatchedStatements=true`
  - **spring.datasource.username**=`username`
  - **spring.datasource.password**=`password`
- Open command prompt and move to spring-boot-file-uploader folder
- Run the command `mvnw package`  .Wait for the dependencies to download and for the build.
- Once build is success move to the folder target from command prompt and run the below command `java -jar bloomberg-1.0.0.jar`
- Application has started on the port 8090 . Access it using the url http://localhost:8090/file/upload
- For stopping the application.Press ctrl+shift+c
- For Sample files please refer fxfile13.csv and fxfile4.csv in the root folder.
- Run the queries for fetching the data `SELECT * FROM FILE_VALID_DATA ` , `SELECT * FROM FILE_INVALID_DATA`

------------------------------------------------------------------
If application is not starting please import the project as a maven project into spring STS.Run it as spring boot app
