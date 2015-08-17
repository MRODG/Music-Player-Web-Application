# Music-Player-Web-Application

The application is web based with a user friendly client side frontend and also the supporting server side management system. Its main function is to allow user to view a list of available artists, display each artist’s available song and finally play those songs subject to login constraints. Essentially there will be two types of client side actors; a registered user and a guest. The guest has access to every part of the web app except from the ability of playing or downloading private songs. In order to be able to perform those restricted actions the actor has to be a registered user and login into the system to authenticate that claim. Details of the registered user are stored in the SQL database, so when a user attempts to login the details (username and password) he provides are checked to see if they match any data in the database. Once logged in the actor is labelled as a registered user by the server side management system and then obtains credentials to access the specified restricted areas of the app.

The system has been implemented by developing a Web application in the Netbeans  8.02 development platform with Java 6 EE, the local client connection  is done by  Apache Tomcat and finally the connection to the SQL database is done by WAMPSERVER 2.5 allowing access to user: badnoise and password: secret. 

The below Have to be included in the Library Folder:
*MySQL JDBC Driver - mysql-connector-java-5.1.23-bin.jar, 
*gson-2.3.jar, 
*JDK 1.8, 
*Apache Tomcat.

Also the SQL Databse named; BadNoise.sql must be stored Locally.

Finally, in order for each JSP page to play a selected song the actual mp3 files need to be included to the publir or private folder accordingly.
