<%@page import="string.StringManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Category"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%
    String contextPath = request.getContextPath();
    String PU = getServletContext().getInitParameter("PU");
    String requestURL = request.getRequestURL() + "";
%>
<link rel="stylesheet" href="<%= requestURL%>/../../../css/css-merchandise-search.css">
<div id="header-content">
    <a id="page-logo" href="<%= contextPath %>/search/ProductSearch.jsp">
        <font class="font-logo">FYP Product Search</font>
    </a>
    <jsp:include page="search-bar.jsp" />
    <div id="header-bar">
        <ul class='nav'>
            <li class='nav-item' id='navMerchandise'>
                <a>
                    Merchandise
                    <i class='nav-item-arrow fa fa-caret-down'></i>
                </a>
                <div class='nav-list'>
                    <%
                        EntityManager em = Persistence
                                .createEntityManagerFactory(PU)
                                .createEntityManager();
                        Category cat = em.find(Category.class, 2);
                        ArrayList<Category> cats = new ArrayList(
                                cat.getCategoryCollection()
                        );
                        for (Category c : cats) {
                            ArrayList<Category> cs = new ArrayList(
                                    c.getCategoryCollection()
                            );
                    %>
                    <div>
                        <a href='SearchProduct?categoryID=<%= (c.getCategoryID() == 43 || c.getCategoryID() == 56) ? c.getCategoryID() + "" : ""%>'>
                            <%= StringManager.wordsFirstAlphabetUpper(c.getCategoryName())%>
                        </a>
                        <%
                            if (cs != null && !cs.isEmpty()) {
                                String st = "<div class='nav-list-2'>";
                                for (Category csc : cs) {
                                    st += "<a href='" + contextPath + "/SearchProduct?categoryID="
                                            + csc.getCategoryID()
                                            + "'>"
                                            + StringManager.wordsFirstAlphabetUpper(csc.getCategoryName())
                                            + "</a>";
                                    st += "</a>";
                                }
                                st += "</div>";
                                out.print(st);
                            }
                        %>
                    </div>
                    <%
                        }
                    %>
                </div>
            </li>
            <li class='nav-item'>
                <a href='<%= contextPath %>/search/ProductSearch.jsp'>Home</a>
            </li>
            <li class='nav-item'>
                <a>Store</a>
            </li>
            <li class='nav-item'> 
                <a>Brand</a>
            </li>
        </ul>
    </div>
</div> 
