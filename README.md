# Shopaholic
Name of Website: Shopaholic

Database Application Type: E-Commerce Website

Purpose of Application: Our e-commerce website will be a place for users to shop and for merchants to sell their products. This website and its activities will be overseen by the admin. 

Application Functionalities: Our website will have three main components: users, merchants/small businesses, and admin. These three components will have different functionalities and have access to different parts of the website. With these different views, only necessary data will be presented and details of the underlying implementation will be hidden. 

# How to Setup Shopaholic Application

Technologies/Software used: 
- Eclipse IDE
- Tomcat Server
- Java
- Java Servlet
- JSP

1. Download Eclipse IDE for Enterprise Java and Web Developers and Tomcat server.
2. Download the following jar files from Google:
- jsp-api-2.2.jar
- jstl-1.2.jar
- mysql-connector-j-8.1.0.jar
- servletapi-2.3.jar
3. In Eclipse IDE, under Help, click “Install New Software” and install the following software.
- Eclipse Java Development Tools
- Eclipse Java EE Developer Tools
- Eclipse Java Web Developer Tools
- Eclipse Plug-in Development Environment
- JSP Server Adapters Extensions (Apache Tomcat)
4. In Eclipse IDE, under Help, click “Eclipse Marketplace” and install the following software.
- Eclipse JST Server Adapters (Apache Tomcat, JOnAS, J2EE) Luna
5. Tomcat Configuration: After switching to Java EE perspective, define a new server.
- Under the folder “Apache,” choose the corresponding Tomcat Server version (usually 9.0 or 10.1)
6. Under File, create a new Dynamic Web Project. Choose Apache Tomcat for the target runtime.
7. Under src/main/webapp/WEB-INF, create a folder named “lib” and copy and paste the four jar files (from STEP 2) into this folder.
8. Clone the files from the GitHub repository “Shopaholic.”
9. Run the LoginServlet to start the web application.

# Division of Work
- Jasmine: README.md, Login, Admin, integration of all files, presentation slides, ER Diagram, final report
- Phuong: User and Review page, ER Diagram, final report
- Gael: merchant view page, user cart and user order history page (edit,delete, update), database connection, final report
