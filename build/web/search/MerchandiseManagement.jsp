
<%@page import="bean.Merchandise"%>
<%@page import="bean.StoreMerchandise"%>
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
            Store mStore = (Store) session.getAttribute("selectedStore");

//               test
            if (user == null) {
                user = em.find(User.class, "bossini");
            }
            if (mStore == null) {
                mStore = user.getStoreCollection().iterator().next();
            }

//            test end

            if (mStore != null) {
        %>
        <div class="singleStore">
            <div class="store-img">
                <img src="<%= requestURL + "/../../" + mStore.getStoreAvatar()%>"/>
            </div>
            <div class="store-details">
                <div class="store-title"><%= mStore.getStoreName()%></div>
                <br>
                <div class="store-desc"><param-title>Description: </param-title><br/><%= mStore.getStoreDesc()%></div>
                <div class="store-addr"><param-title>Address: </param-title>(<%= mStore.getLatitude()%>, <%= mStore.getLongitude()%>)
                    <br/>
                    <%= mStore.getAddress()%>
                </div>
            </div>
        </div>
        <%
            ArrayList<StoreMerchandise> sms = new ArrayList(mStore.getStoreMerchandiseCollection());
            ArrayList<Merchandise> ms = new ArrayList();
            if (!sms.isEmpty()) {
                for (StoreMerchandise sm : sms) {
                    Merchandise m1 = sm.getMerchandise();
                    boolean hasM = false;
                    for (Merchandise m : ms) {
                        if (m.getMerchandisePK().getCategoryID()
                                == m1.getMerchandisePK().getCategoryID()) {
                            hasM = true;
                        }
                    }
                    if(!hasM){
                        ms.add(m1);
                    }
                }
            }
            if(!ms.isEmpty()){
                String divMerchandise = "<div class='page'>";
                divMerchandise += "<div></div>";
                divMerchandise += "<div></div>";
                divMerchandise += "</div>";
            }
        %>
        <%
            }
        %>
    </body>
</html>
