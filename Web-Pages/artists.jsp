<%-- 
    Document   : artists
    Created on : 13-Jan-2015, 13:43:00
    Author     : Mr marios
--%>

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
        <title>Artists Page</title>
        <script src="scripts/jquery-2.1.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $('a.Song').click(function () {
                    var div = 'div#' + this.name;
                    if ($(div).is(':hidden')) {
                        $(div).empty();
                        $.getJSON(this.href, function (obj) {
                        //$.each(this.href, function(index, obj)){
                        $.each(obj,function(key, val){
                            $('div#' + val.ArtistNumber).append("<a name='"+val.Title+"' href ='"+val.FileLocation+".jsp'><font color='#FF3300' face='Arial'> " + val.Title +" by " +val.Lyricist+ '</font></a><p>');
                        });
                        }).fail(function (jqxhr, status, error) {
                            alert(status + ': ' + error);
                        });
                    }
                    $(div).toggle();
                    return false;
                });
            });
        </script>
    </head>
    <body style="background-color:#FFFDDC;">
       <%
            String fullname; 
            fullname = (String) session.getAttribute("FullName");  
           if(fullname==null){
               fullname=" ";
           }
        %> 
        <div style="background-color:#7F794B;">
            <b align="left">
                <font color="#FFFDDC" face="Arial">
                   <%=fullname %>
                </font>
                <form action="logoutservlet" method="post">
                    <input style="background-color:#CCBB3A;" type="submit" value="LogOut"/>
                </form>
            </b>
        </div>
        <h1 style="background-color:#B0AD67;">
            <font color="#FFFDDC" face="Arial"> 
                BadNoise Records: Artists
            </font>
        </h1>
        <%
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;

            String password = System.getProperty("badnoise.password");
            String url = System.getProperty("badnoise.url");
            String user = System.getProperty("badnoise.user");

            connection = DriverManager.getConnection(url, user, password);
            
            String query = "select * from artist";
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                String firstname = result.getString("FirstName");
                String surname = result.getString("LastName");
                int artistNo = result.getInt("ArtistNumber");

        %>
        <p>
            <a  class="Song" name="<%= artistNo%>" href="Songs?ArtistNumber=<%= artistNo%>">
                <font color="#0000FF" face="Arial"> 
                    <%=firstname %> <%=surname %> 
                </font>
            </a>
        <div class="info" id="<%= artistNo%>" style="display: none"></div>
        <%
            }
        %>
    </body>
</html>
