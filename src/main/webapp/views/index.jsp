<%-- 
    Document   : index
    Created on : 16.05.2013, 22:58:42
    Author     : Madness
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Web-Shop</title>

        <link href="<c:url value="resources/css/templatemo_style.css"/>" rel="stylesheet" type="text/css" />



        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/ddsmoothmenu.css" />" />

        <script type="text/javascript" src="<c:url value="resources/js/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="resources/js/ddsmoothmenu.js"/>" >

            /***********************************************
             * Smooth Navigational Menu- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
             * This notice MUST stay intact for legal use
             * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
             ***********************************************/

        </script>

        <script type="text/javascript">
            ddsmoothmenu.init({
                mainmenuid: "top_nav", //menu DIV id
                orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
                classname: 'ddsmoothmenu', //class added to menu's outer DIV
                //customtheme: ["#1c5a80", "#18374a"],
                contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
            })

        </script>

    </head>

    <body>

        <div id="templatemo_body_wrapper">
            <div id="templatemo_wrapper">

                <div id="templatemo_header">
                    <div id="site_title"><h1><a href="#">Онлайн магазин</a></h1></div>
                    <div class="cleaner"></div>
                </div> <!-- END of templatemo_header -->

                <div id="templatemo_menubar">
                    <div id="top_nav" class="ddsmoothmenu">
                        <ul>
                            <li><a href="index.html" class="selected">Главная</a></li>
                            <li><a href="#">Производители</a>
                                <ul>
                                    <c:if test="${not empty makers}">
                                        <c:forEach items="${makers}" var="mak">
                                            <li><a href="#">${mak.makName}</a></li>
                                            </c:forEach>
                                        </c:if>                                            
                                </ul>
                            </li>
                            <li><a href="#">О нас</a></li>
                            <li><a href="#">Контакты</a></li>
                        </ul>
                        <br style="clear: left" />
                    </div> <!-- end of ddsmoothmenu -->
                    <div id="templatemo_search">
                        <form action="#" method="get">
                            <input type="text" value=" " name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                            <input type="submit" name="Search" value=" " alt="Search" id="searchbutton" title="Search" class="sub_btn"  />
                        </form>
                    </div>
                </div> <!-- END of templatemo_menubar -->

                <div id="templatemo_main">
                    <div id="sidebar" class="float_l">
                        <div class="sidebar_box"><span class="bottom"></span>
                            <h3>Категории</h3>   
                            <div class="content"> 
                                <ul class="sidebar_list">
                                    <c:if test="${not empty categories}">
                                        <c:forEach items="${categories}" var="cat">
                                            <li><a href="#">${cat.catName}</a></li>
                                            </c:forEach>
                                        </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div id="content" class="float_r">
                        <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>                        
                        <h1>New Products</h1>
                        <c:if test="${not empty products}">
                            <c:forEach items="${products}" var="product">
                                <div class="product_box">
                                    <h3>${product.prodName}</h3>
                                    <img src= "<c:url value="/admin/image/${product.prodImg}"/>" />
                                    <p>${product.prodDescription}</p>
                                    <c:if test="${product.prodExist}" >
                                        <p class="product_exist"> Есть на складе </p> 
                                    </c:if>
                                    <c:if test="${not product.prodExist}" >
                                        <p class="product_exist"> Нет на складе </p> 
                                    </c:if>
                                    <p class="product_price">${product.prodPrice} грн</p>                           
                                </div>
                            </c:forEach>
                        </c:if>     
                        
                        <div class="product_box">
                            <h3>Titel</h3>
                            <a href="productdetail.html"><img src="images/product/02.jpg" alt="Shoes 2" /></a>
                            <p>Sed congue, erat id congue vehicula. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow">XHTML</a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow">CSS</a>.</p>
                            <p class="product_price">$ 80</p>
                            <a href="shoppingcart.html" class="addtocart"></a>
                            <a href="productdetail.html" class="detail"></a>
                        </div>        	    	
                        <div class="product_box no_margin_right">
                            <h3>Nulla luctus urna</h3>
                            <a href="productdetail.html"><img src="images/product/06.jpg" alt="Shoes 6" /></a>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            <p class="product_price">$ 160</p>
                            <a href="shoppingcart.html" class="addtocart"></a>
                            <a href="productdetail.html" class="detail"></a>
                        </div>        	
                    </div> 
                    <div class="cleaner"></div>
                </div> <!-- END of templatemo_main -->

                <div id="templatemo_footer">
                    <p><a href="#">Главная</a> | <a href="#">О нас</a> | <a href="#">Контакты</a>
                    </p>
                </div> <!-- END of templatemo_footer -->

            </div> <!-- END of templatemo_wrapper -->
        </div> <!-- END of templatemo_body_wrapper -->

    </body>
</html>








<!--

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Makers List</title>
            <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
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
<jsp:include page="tableJSP.jsp" />
<h1>Products</h1>
<c:if test="${not empty products}">
    <table>
        <tbody>
    <c:forEach items="${products}" var="prod">
        <tr>
            <td>
                <img src="<c:url value="/admin/image/${prod.prodImg}"/>" 
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
-->
