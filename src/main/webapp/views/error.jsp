<%-- 
    Document   : error
    Created on : May 18, 2013, 8:53:13 PM
    Author     : Дмитрий
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/error.css" />" />
    </head>
    <body>
        <div id="center">
            <h1>   Страница не доступна</h1>
            <c:if test="${not empty error}">
                <p>${error.message}</p>
            </c:if>
            
        </div>
    </body>
</html>