# EmployeePortal

1. Environment and software dependencies 
	A. java 8+
	B. Maven
	C. Eclipse
	
2. Instructions to launch/run the application
	A. Import application in eclipse as a Existing maven project.
	B. Application uses in memory h2 data base to make that persistence change the value of property from (spring.datasource.url=jdbc:h2:mem:testdb)
	to (spring.datasource.url=jdbc:h2:file:D:/data/demo) in application.properties file so that It will be saved data on disc. Note that 'jdbc:h2:file:D:/data/demo' is a location of folder
	 on application running computer/machine.
	C. Right click on com.emp.portal.assignment.EmployeePortalAppLaunch and run as Java program. 

3. After stating the application as per above steps(#2), Connect to DB with url-  http://localhost:8080/h2-console 
	A. Enter the required details :
			JDBC URL: jdbc:h2:file:D:/data/EmployPortalDev   (It is the value of spring.datasource.url property in application.properties file)
			User Name: sa
			Password: password
			
	Note All the values are available in application.properties.
		
4. API endpoint documentations are available on url-  http://localhost:8080/swagger-ui.html for dev env
	Login to swagger by using 
	username=Ajay_Chauhan
	password=password

	Note: Environment specific credentials are mentioned in property file of each environment.
