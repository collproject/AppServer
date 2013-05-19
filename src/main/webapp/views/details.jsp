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
                                            <li><a href="<c:url value="index.html?mkId=${mak.makID}"/>">${mak.makName}</a></li>
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
                                            <li><a href="<c:url value="index.html?catId=${cat.catID}"/>">${cat.catName}</a></li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <div>
                        <c:if test="${not empty product}">
                            <h1 align="center">${product.prodName}</h1><br>
                                <img id="img_left" src= "<c:url value="/admin/image/${product.prodImg}"/>" width="250" height="250" />
                                <p id="prod_optons">

                                    <dl>
                                        <h3>Характеристики</h3>
                                        <c:if test="${not empty categor}">
                                            <li>Категория ${categor.catName} </li>
                                        </c:if> 

                                        <c:if test="${not empty m}">
                                            <li>Производитель ${m.makName} </li>
                                        </c:if> 

                                        <li>Цена ${product.prodPrice} грн</li>

                                        <c:if test="${product.prodExist}" >
                                            <li>Наличие: Есть на складе </li>
                                        </c:if>
                                        <c:if test="${not product.prodExist}" >
                                            <li>Наличие: Нет на складе </li>
                                        </c:if>

                                    </dl>

                                    <h3>Описание</h3>
                                    ${product.prodDescription}

                                </p>

                            </c:if>
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
