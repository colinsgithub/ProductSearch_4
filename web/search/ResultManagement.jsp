
<%@page import="java.util.Map"%>
<%@page import="dbEngine.QueryResult"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Store"%>
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
<%
    ArrayList<Store> stores = (ArrayList<Store>) request.getAttribute("stores");
    HashMap<String, QueryResult> results = (HashMap<String, QueryResult>) session.getAttribute("search_result");
    int amount = 0;
%>
<div id="wrapper-margin">
    <div id="wrapper-main">
        <div id="wrapper-header">
            <jsp:include page="./layout/init-header.jsp"/>
        </div>
        <div id="wrapper-body">
            <div id="wrapper-body-choice">
                <%
                    String step = request.getParameter("step");

                    if (!(stores == null || results == null)) {
                        for (Map.Entry<String, QueryResult> entry : results.entrySet()) {
                            String tableName = entry.getKey();
                            QueryResult qr = entry.getValue();
                            ArrayList rs = qr.getResult();
                            amount += rs.size();
                        }
                        if (step == null || step.equals("") || step.equals("1")) {
                            if (amount != 0) {
                                if (results.size() > 1) {
                %>
                <div class="choice-merchandise"><a class="a-tag">Merchandise  <span class="badge a-badge"><%= results.size()%></span></a></div>
                <div class="choice-store"><a class="a-tag">Store  <span class="badge"><%= stores.size()%></span></a></div>
                <div class="wrapper-show-result">
                    <ul class="nav nav-pills nav-justified">
                        <li><a href="#merchandise" class="active nav-ii" data-toggle="tab">Merchandise<span class="badge"><%= results.size()%></span></a></a></li>
                        
                        <li><a href="#store" class="nav-ii" data-toggle="tab">Store<span class="badge"><%= stores.size()%></span></a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="merchandise">
                            <%
                                String divR = "";
                            %>
                        </div>
                        <div class="tab-pane" id="store">Store</div>
                    </div>
                </div>
                <%
                                }
                            }
                        } else if (step.equals("2")) {

                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
