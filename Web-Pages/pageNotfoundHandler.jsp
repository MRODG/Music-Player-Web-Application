<%-- 
    Document   : pageNotfoundHandler
    Created on : 18-Jan-2015, 16:20:02
    Author     : Mr marios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Not Found- 404 Handler Page</title>
    </head>
    <body align="center" bgcolour="#FFFFFF">
        <p>
            <font color="#FF0000" size="+1" face="Arial">
            Oops! We could not find the document.<br/>
            </font>
        </p>
        <p>
            <font style="font-family:arial;" >
            The document you requested could not be found on this server.<br>
            If you provided the URL, then please check that it is correct.
            </font>
        </p>
        <p>
            <font style="font-family:arial;" >
            Should the condition persists then do not hesitate to
            </font>
            <a href="mailto:1115302@brunel.ac.uk">here</a>us.
        </p>
        <%
            String msg = "Requested page: "
            +pageContext.getErrorData().getRequestURI()
            +" Referer page: "+request.getHeader("REFERER");
            application.log(msg);
        %>
        <p>
            <%=msg %> 
        </p>
    </body>
</html>
