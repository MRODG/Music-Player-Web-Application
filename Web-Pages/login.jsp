<%-- 
    Document   : login
    Created on : 18-Jan-2015, 23:21:51
    Author     : Mr marios
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page errorPage="errorHandler.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body style="background-color:#FFFDDC;">
        <h1>
            <font color="#7F794B" face="Arial"> 
                Please Login
            </font>
        </h1>
        <form action="loginservlet" method="post">
            Username:<br>
            <input type="text" name="username"><br>
            Password:<br>
            <input type="password" name="password"><br>
            <input style="background-color:#CCBB3A;" type="submit" value="LogIn"/>
        </form>               
    </body>
</html>
