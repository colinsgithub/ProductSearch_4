<%-- 
    Document   : personalInfo
    Created on : Mar 18, 2014, 1:00:41 PM
    Author     : poonkaho
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="bean.Tag"%>
<%@page import="bean.Store"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>

        <!-- Fonts -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Oswald:400,700" />
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,400,700" />
        <link href='http://fonts.googleapis.com/css?family=Petit+Formal+Script' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Muli:400,400italic,300italic,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="fonts/MyFontsWebfontsKit.css">

        <!-- Stylesheets -->
        <link rel='stylesheet' href='css/flexslider.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/jquery.fancybox-1.3.4.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/epicslider.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/style.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/udt_shortcodes.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/skin.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/udt_media_queries.css' type='text/css' media='all' />
        <link rel='stylesheet' href='css/metro-bootstrap.css' type='text/css' media='all' />
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="js/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
        <!-- Scripts -->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script type='text/javascript' src='js/jquery-1.11.0.min.js'></script>
        <script type='text/javascript' src='js/jquery-migrate-1.2.1.min.js'></script>

        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="js/bootstrap3-editable/js/bootstrap-editable.js"></script>

    </head>
    <body>
        <div id="header-wrapper">

            <div id="header-inner">
                <header>
                    <!-- Logo -->
                    <div id="logo">
                        <a>

                        </a>
                    </div>


                    <!-- Mobile Menu Toggle -->
                    <div class="mobile-menu-toggle"><a href="#"></a></div>

                    <!-- Navigation -->
                    <nav id="primary-nav">
                        <div class="menu-container">
                            <ul id="menu-1" class="menu">
                                <li class="menu-item"><a href="javascript:changeSlide(0)">Profile</a></li>
                                <li class="menu-item"><a href="javascript:changeSlide(1)">Store</a></li>
                                <li class="menu-item"><a href="javascript:changeSlide(2)">Preference</a></li>

                                <li class="menu-item"><a href="contact.html">Contact</a></li>
                                <script>
                                    function changeSlide(page) {
                                        var height = $(window).height();
                                        window.scroll(0, page*height);
                                        }
                                </script>
                            </ul>
                        </div>
                    </nav>

                </header>
                <div style="clear:both;"></div>
            </div>

            <div style="clear:both;"></div>

            <div class="header-widget-box">
                <div class="header-outer-widget-wrapper">
                    <div style="clear:both;"></div>

                    <div class="mobile-widget-box-toggle-wrapper">
                        <a href="#">Toggle</a>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
        <!-- Header End -->
        <!-- Content Area -->
        <div id="content-wrapper" class="front-page front-page-full-width-media">
            <!-- Epic Slider -->
            <section class="slider-full-width slider-homepage clearfix">
                <div id="epicSlider1" class="epic-slider slider-wrapper">
                    <ul id="slides">
                        <li class="metro">
                            <div class="es-caption" style="" data-caption="impact" data-caption-position="center" data-caption-width="550">
                                <legend><h2 style=" color:  white;">Personal Profile</h2></legend>
                            </div>
                        </li>
                        <%
                            User user = (User) request.getAttribute("user");

                            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
                            EntityManager em = factory.createEntityManager();

                            User user2 = em.find(User.class, user.getUserID());

                            em.getTransaction().begin();
                            em.getTransaction().commit();
                            em.close();
                            factory.close();
                        %>
                        <li class="metro">
                            <div class="es-caption" style="" data-caption="impact" data-caption-position="center" data-caption-width="550">
                                <img src="data:image/png;base64,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">
                                <dl class="horizontal" id="info">
                                    <dt>User ID</dt>
                                    <dd style="text-align: left;"><%=user.getUserID()%></dd>
                                    <dt>Name</dt>
                                    <dd style="text-align: left;"><a href="#" id="userName" data-type="text" data-title="Enter User Name"><%=user.getUserName().toUpperCase()%></a></dd>
                                    <dt>Gender</dt>
                                    <dd style="text-align: left;"><a href="#" id="sex" data-type="text" data-title="Enter Sex"><%=user.getSex()%></a></dd>
                                    <dt>Age</dt>
                                    <dd style="text-align: left;"><a href="#" id="age" data-type="text" data-title="Enter Age"><%=user.getAge()%></a></dd>
                                    <dt>Credit</dt>
                                    <dd style="text-align: left;"><%=user.getCredit()%></dd>
                                    <dt>Role</dt>
                                    <dd style="text-align: left;"><%=user.getRole()%></dd>
                                    <dt>Email</dt>
                                    <dd style="text-align: left;"><a href="#" id="email" data-type="text" data-title="Enter Email"><%=user.getEmail()%></a></dd>
                                    <dt>Description</dt>
                                    <dd style="text-align: left;"><a href="#" id="userDesc" data-type="textarea" data-title="Enter Description"><%=user.getUserDesc()%></a></dd>
                                </dl>
                                <button onclick="editDate();">Edit Data</button>
                                <script>
                                        function editDate() {
                                            $.fn.editable.defaults.mode = 'inline';
                                            $('#info a').editable({
                                                disabled: true,
                                                pk: '1',
                                                url: 'HandleUser?action=updateUser',
                                                success: function(response, newValue) {
                                                    if (response.status == 'error')
                                                        return response.msg; //msg will be shown in editable form
                                                }
                                            });
                                            $('#info a').editable('toggleDisabled');
                                        }
                                </script>
                            </div>
                        </li>
                        <li style=" background-color: #00aff0;"></li>
                        <li data-image="img/start-slider-04.jpg"></li>
                    </ul>
                </div>
            </section>
            <!-- Epic Slider End -->

            <!-- Portfolio Index Grid -->
            <section class="portfolio-full-width-grid" style="height:100vh;">
                <div class="sub-section-title">
                    <h2>Store</h2>
                </div>
                <div id="grid" class="clearfix">  
                    <%
                        ArrayList<Tag> tags;
                        ArrayList<Store> stores = new ArrayList(user2.getStoreCollection());;
                        if (stores.isEmpty()) {
                            tags = new ArrayList(user2.getTagCollection());
                        } else {
                            stores = new ArrayList(user2.getStoreCollection());
                            for (int x = 0; x < stores.size(); x++) {
                                String src = stores.get(x).getStoreAvatar();
                                out.print("<div class='thumb' data-project-categories='personal'>");
                                out.print("<a href='#' title='Concrete Cross' data-caption='" + stores.get(x).getStoreName() + "<span></span'>");
                                out.print("<img src='" + src + "' alt='Concrete Cross'>");
                                out.print("</a>");
                                out.print("</div>");
                            }
                            tags = new ArrayList(user2.getTagCollection());
                        }
                    %>
                </div>
                <div class="portfolio-button"><a href="HandleStore?action=newStore" title="New Store">New Store &rarr;</a></div>
            </section>
            <!-- Portfolio Index Grid End -->

            <!-- Portfolio Index Grid -->
            <section class="portfolio-full-width-grid" style="height:100vh;">
                <div class="sub-section-title">
                    <h2>Preference</h2>
                </div>
                <div id="grid" class="clearfix">  
                    <%
                        for (int x = 0; x < tags.size(); x++) {
                            String src = tags.get(x).getStoreID().getStoreAvatar();
                            out.print("<div class='thumb' data-project-categories='personal'>");
                            out.print("<a href='HandleStore?action=requestAStoreRedirectToMap&storeId=" + tags.get(x).getStoreID().getStoreID() + "' title='Concrete Cross' data-caption='" + tags.get(x).getStoreID().getStoreName()+ "<span></span'>");
                            out.print("<img src='" + src + "' alt='Concrete Cross'>");
                            out.print("</a>");
                            out.print("</div>");
                        }
                    %>
                </div>
                <div class="portfolio-button"><a href="#" title="View All Stores Followed">View all stores followed &rarr;</a></div>
            </section>
            <!-- Portfolio Index Grid End -->
        </div>
        <!-- Content Area End -->

        <!-- Footer -->
        <div id="footer-wrapper">
            <div id="footer-top" class="clearfix">
                <footer>

                    <div class="column_one_half column-footer-widget">
                        <ul class="footer-widget">
                            <li class="widget widget_text">
                                <h4 class="widget-title">Product Search</h4>
                                <div class="textwidget">
                                    <p>a platform, simplifies the step of buying a product. We will point out that this system is valuable to what kind of user. The function and design of this system how to solve those troubles and requests.
                                    </p>
                                </div>
                            </li>
                        </ul>
                        </li>
                        </ul>
                    </div>

                    <div class="column_one_half column-footer-widget">
                        <ul class="footer-widget">
                            <h4 class="widget-title">Target</h4>
                            <ul class="pinkArrow1" style="margin-bottom:0px;">
                                <li>Find A Product You Want</li>

                                <li>Promote A Product You Want</li>
                            </ul>
                        </ul>

                    </div>
                    <div class="clear"></div>
                </footer>
            </div>

            <div id="footer-bottom">
                <div id="footer-bottom-inner-wrapper">
                    <footer>
                        <!-- Copyright info -->
                    </footer>
                    <!-- "Back to Top" link -->
                    <a class="back-to-top" title="Back to top" href="#">Back to top</a>
                </div>
            </div>

        </div>
        <!-- Footer End -->

        <!-- Scripts -->
        <script type='text/javascript' src='js/jquery.ui.core.min.js'></script>
        <script type='text/javascript' src='js/jquery.easing.1.3.js'></script>
        <script type='text/javascript' src='js/jquery.fancybox-1.3.4.pack.js'></script>
        <script type='text/javascript' src='js/jquery.epicHover-fadeZoom.js'></script>
        <script type='text/javascript' src='js/jquery.epicslider.js'></script>
        <script type='text/javascript' src='js/jquery.flexslider-min-edited.js'></script>
        <script type='text/javascript' src='js/jquery.mobile-touch-swipe-1.0.js'></script>
        <script type='text/javascript' src='js/settings.js'></script>
        <script type='text/javascript' src='js/common.js'></script>
        <script type='text/javascript' src='js/udt_shortcodes.js'></script>
        <script type='text/javascript' src='http://maps.googleapis.com/maps/api/js?sensor=false'></script>
        <script type='text/javascript' src='js/contact.js'></script>
</html>
