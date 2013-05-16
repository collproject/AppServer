<%-- 
    Document   : index
    Created on : 16.05.2013, 22:58:42
    Author     : Madness
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Makers List</title>
    </head>
    <body>
        <h1>Makers</h1>
        <c:if test="${not empty makers}">
            <table>
                <tbody>
                    <c:forEach items="${makers}" var="maker">
                        <tr>
                            <td>${maker.makName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <h1>Products</h1>
        <c:if test="${not empty products}">
            <table>
                <tbody>
                    <c:forEach items="${products}" var="prod">
                        <tr>
                            <td>
                                <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/shop-app-server/admin/image/${prod.prodImg}"
                                     width="60" height="60">
                            </td>
                            <td> 
                                ${prod.prodName}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
    </body>
</html>
