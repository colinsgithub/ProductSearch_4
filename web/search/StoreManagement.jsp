
<%@page import="string.StringManager"%>
<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Store"%>
<%@page import="bean.User"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% String requestURL = request.getRequestURL() + "";%>
<script type="text/javascript" src="<%= requestURL%>/../../js/jquery/jquery.js">
</script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js">
</script>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/28e7751dbec/integration/jqueryui/dataTables.jqueryui.js">
</script>
<script type="text/javascript" src="<%= requestURL%>/../../js/js-store-list.js">
</script>
<link rel="stylesheet" href="<%= requestURL%>/../../css/font-awesome.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="//cdn.datatables.net/plug-ins/28e7751dbec/integration/jqueryui/dataTables.jqueryui.css">
<link rel="stylesheet" href="<%= requestURL%>/../../css/css-store-list.css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FYP Product Search -- MY STORE</title>
    </head>
    <body>
        <%
            String PU = getServletContext().getInitParameter("PU");
            EntityManager em = Persistence.createEntityManagerFactory(PU)
                    .createEntityManager();
            User user = (User) session.getAttribute("user");

//            test
            if (user == null) {
                user = em.find(User.class, "bossini");
                session.setAttribute("user", user);
            }
//            test end

            if (user != null) {
                ArrayList<Store> stores = new ArrayList(user.getStoreCollection());
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
                        <th>Manage Merchandise</th>
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
                        <th>Manage Merchandise</th>
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
                        <td><a onclick="manageMerchandise('<%= s.getStoreID()%>');" class="fa fa-fw btn btn-default">Manage Merchandise</a></td>
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
            } else {
                out.println("Please Login!");
            }
        %>

    </div>
</body>
</html>
