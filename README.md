# Mars Base
![image](https://user-images.githubusercontent.com/101856957/207853845-260335e7-dc1b-483d-8226-8e811af67c85.png)
# The platform supports only English language.

# Description

Mars base. The base has a list of blocks (residential, working, greenhouses).
The administrator forms a Brigade for different types of work (planting
potatoes, check sealing, etc.). Teams report to
performance of work. The administrator puts marks on the quality of implementation
and allocate resources.

# Project stack
 Java EE / PostgreSQL / HTML5 / CSS3 / JavaScript / Command design pattern

# Functional roles
## Admin role:

![image](https://user-images.githubusercontent.com/101856957/207863831-cb01a275-5573-4db4-890b-47f158322a19.png)


1. Sign in;
2. See users;
3. Add user;
4. Delete user;
5. Edit user;
6. See teams;
7. Add team;
8. Delete team;
9. Edit team;
10. Add user to team;
11. Delete User from team;
12. See work;
13. Add work;
14. Delete work;
15. Edit work;
16. See report;
17. Add report;
18. Edit report


## Team lead Role;

![image](https://user-images.githubusercontent.com/101856957/207863672-ae0eebed-02b7-4159-9a86-be806a293986.png)

1. See teams in which he is team leader;
2. Add user to team;
3. Delete User from team;
4. Add user to team;
5. Delete User from team;
6. See work;
7. See report;
8. Add report;
9. Edit report


# Database schema

PostgreSQL database is used to store data.
![databsae -designjpg](https://user-images.githubusercontent.com/101856957/207848904-cdbf7eb5-f21b-47f5-b77e-e1778f635d99.jpg)
                                              database schema


# Installation
1. Clone the project.
2. Create a new PostgreSQL database.
3. Create tables using init-ddl.sql.
4. Change the application.properties file, located in the resources' folder, based on your database configurations.
5. Build the project using maven.
6. Add new Tomcat 9.0.14 configuration to the project. You can download from https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.14/bin/\
7. Run Tomcat and open http://localhost/ on the browser.
8. Log in as admin. Admin default account is email admin@gmail.com, password - root123.
 
 
