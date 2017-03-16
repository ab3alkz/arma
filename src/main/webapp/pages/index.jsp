
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
     if (request.isUserInRole("admin_role")){
        %>
             <%@include file='admin/admin.jsp' %>
        <%
    } else {
        %>
            <%@include file='../login.jsp' %>
        <%
    }

%>