<%-- 
    Document   : errorHandler
    Created on : 18-Jan-2015, 15:42:40
    Author     : Mr marios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Exception Handler Page</title>
    </head>
    <body align="center" bgcolour="#FFFFFF">
        <p>
            <font color="#FF0000" size="+1" face="Arial">
            An unrecoverable error has occurred.<br/>
            </font>
        </p>
        <p>
            <font style="font-family:arial;" >
            We apologise for any inconvenience resulting
            from this problem. <br/>Should the condition
            persists, then please report it by clicking
            </font>
            <a href="mailto:1115302@brunel.ac.uk">here</a>.
        </p>
        <% application.log(exception.getMessage(), exception); %>
    </body>
</html>
