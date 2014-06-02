<%-- 
    Document   : ProductSearch
    Created on : Mar 11, 2014, 1:27:13 PM
    Author     : user
--%>

<%@page import="bean.Store"%>
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

<% String requestURL = request.getRequestURL() + "";%>
<script type="text/javascript" src="<%= requestURL%>/../../js/jquery/jquery.js">
</script>
<script type="text/javascript" src="<%= requestURL%>/../../js/jqueryui/jquery-ui.js">
</script>
<script type="text/javascript" src="<%= requestURL%>/../../js/js-product-search.js">
</script>
<link rel="stylesheet" href="<%= requestURL%>/../../js/jqueryui/css/jquery-ui.css">
<link rel="stylesheet" href="<%= requestURL%>/../../css/css-merchandise-search.css">
<link rel="stylesheet" href="<%= requestURL%>/../../css/font-awesome.css">
<link rel="stylesheet" href="http://fortawesome.github.io/Font-Awesome/assets/css/site.css"/>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
<link rel="stylesheet" href="<%= requestURL%>/../../bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%= requestURL%>/../../bootstrap/css/bootstrap-theme.css">
<script src="<%= requestURL%>/../../bootstrap/js/bootstrap.js"></script>

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


                <%
                    String PU = getServletContext().getInitParameter("PU");
                    EntityManager em = Persistence.createEntityManagerFactory(PU)
                            .createEntityManager();
                    ArrayList<Store> stores = new ArrayList(
                            em.createNamedQuery("Store.findAll", Store.class)
                            .getResultList()
                    );
                    if (!stores.isEmpty()) {
                %>
                <div id="store-wrapper" class="page">
                    <table id="example" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Store ID</th>
                                <th>Store Name</th>
                                <th>Store Description</th>
                                <th>Address</th>
                                <th>Rank</th>
                                <th>Created Date</th>
                                <th>Category</th>
                            </tr>
                        </thead>
                        <tfoot> 
                            <tr>
                                <th>Store ID</th>
                                <th>Store Name</th>
                                <th>Store Description</th>
                                <th>Address</th>
                                <th>Rank</th>
                                <th>Created Date</th>
                                <th>Category</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <%
                                for (Store s : stores) {
                                    Category cat = s.getCategoryID();
                                    String catName = StringManager.wordsFirstAlphabetUpper(cat.getCategoryName());

                            %>
                            <tr>
                                <td><%= s.getStoreID() + ""%></td>
                                <td>
                        <image src="<%= requestURL + "/../../" + s.getStoreAvatar()%>" />
                        <%= s.getStoreName()%>
                        </td>
                        <td><%= s.getStoreDesc()%></td>
                        <td><%= s.getAddress()%></td>
                        <td><%= s.getRank()%></td>
                        <td><%= s.getStoreCreateTime()%></td>
                        <td><%= catName%></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <%        } else {
                %>
                YOU HAVE NO STORE!
                <%
                    }
                %>
                </body> 
            </div>
        </div>
    </div>
</html>
