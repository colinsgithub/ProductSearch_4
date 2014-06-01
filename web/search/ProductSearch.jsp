<%-- 
    Document   : ProductSearch
    Created on : Mar 11, 2014, 1:27:13 PM
    Author     : user
--%>

<%@page import="string.StringManager"%>
<%@page import="bean.Category"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="bean.Brand"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbEngine.QueryResult"%>
<%@page import="java.util.HashMap"%>
<%@page import="bean.Mobile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">

<script type="text/javascript" src="../js/jquery/jquery.js">
</script>
<script type="text/javascript" src="../js/jqueryui/jquery-ui.js">
</script>
<script type="text/javascript" src="../js/js-product-search.js">
</script>
<link rel="stylesheet" href="../js/jqueryui/css/jquery-ui.css">
<link rel="stylesheet" href="../css/css-merchandise-search.css">
<link rel="stylesheet" href="../css/font-awesome.css">
<link rel="stylesheet" href="http://fortawesome.github.io/Font-Awesome/assets/css/site.css"/>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.css">
<script src="../bootstrap/js/bootstrap.js"></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Search</title>
    </head>
    <body>
        <div id="wrapper-margin">
            <div id="wrapper-main">
                <div id="wrapper-header">
                    <jsp:include page="./layout/init-header.jsp"/>
                </div>
                <div id="wrapper-body">
                    <div class='carousel-wrapper'>
                        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
                        <div id="MCarousel" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#MCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#MCarousel" data-slide-to="1"></li>
                                <li data-target="#MCarousel" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img data-src="../system/image/shop/home-mobile-ca-example-1.jpg" alt="First slide" src="../system/image/shop/home-mobile-ca-example-1.jpg">
                                    <div class="carousel-caption">
                                        <h3>4G Mobile</h3>
                                    </div>
                                </div>
                                <div class="item">
                                    <img data-src="../system/image/shop/home-mobile-ca-example-2.jpg" alt="Second slide" src="../system/image/shop/home-mobile-ca-example-2.jpg">
                                    <div class="carousel-caption">
                                        <h3>New Mobile</h3>
                                    </div>
                                </div>
                                <div class="item">
                                    <img data-src="../system/image/shop/home-mobile-ca-example-3.jpg" alt="Third slide" src="../system/image/shop/home-mobile-ca-example-3.jpg">
                                    <div class="carousel-caption">
                                        <h3>Smart Mobile</h3>
                                    </div>
                                </div>
                            </div>
                            <a class="left carousel-control ah" href="#MCarousel" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control ah" href="#MCarousel" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
