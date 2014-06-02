<%--
    Document   : productSearch
    Created on : Mar 4, 2014, 12:58:17 AM
    Author     : user
--%>
<%@page import="string.StringManager"%>
<%@page import="bean.Category"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="bean.Cloth"%>
<%@page import="bean.UpperGarment"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="bean.Brand"%>
<%@page import="bean.Mobile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbEngine.QueryResult"%>
<%@page import="java.util.HashMap"%>
<% String requestURL = request.getRequestURL() + ""; %>
<script type="text/javascript" src="<%= requestURL %>/../../js/jquery/jquery.js">
</script>
<script type="text/javascript" src="<%= requestURL %>/../../js/jqueryui/jquery-ui.js">
</script>
<script type="text/javascript" src="<%= requestURL %>/../../js/js-product-search.js">
</script>
<link rel="stylesheet" href="<%= requestURL %>/../../js/jqueryui/css/jquery-ui.css">
<link rel="stylesheet" href="<%= requestURL %>/../../css/css-merchandise-search.css">
<link rel="stylesheet" href="<%= requestURL %>/../../css/font-awesome.css">
<link rel="stylesheet" href="http://fortawesome.github.io/Font-Awesome/assets/css/site.css"/>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.js"></script>
<link rel="stylesheet" href="<%= requestURL %>/../../bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%= requestURL %>/../../bootstrap/css/bootstrap-theme.css">
<script src="<%= requestURL %>/../../bootstrap/js/bootstrap.js"></script>


<div id="wrapper-margin">
    <div id="wrapper-main">
        <div id="wrapper-header">
            <jsp:include page="layout/init-header.jsp" />
        </div>
        <div id="wrapper-body">
            <form id="sb_output" method="GET" action="SearchProduct">
                <div>
                    <div class='q-contions'>
                        <div class='q-conditions-value'>Conditions: 
                            <%
                                String qs = request.getQueryString();
                                if (qs != null && !qs.equals("")) {
                                    String[] qss = qs.split("&");
                                    for (String qsson : qss) {
                                        String[] qsa = qsson.split("=");
                                        String href = "";
                                        for (String qss2 : qss) {
                                            if (qss2.equals(qsson) || qss2.equals("categoryID")) {
                                                continue;
                                            }
                                            if (!href.equals("")) {
                                                href += "&";
                                            }
                                            href += qss2;
                                        }
                                        if (qsa != null && qsa.length == 2 && !qsa[0].equals("categoryID")) {
                                            out.println("<a class='condition-tag' href=?" + href + ">" + qsa[0] + ":" + qsa[1] + "<i class='fa-cross-circle'>X</i></a>");
                                        }
                                    }
                                }

                            %>
                        </div>
                        <ul>
                            <li>
                                <a id='btn-filter' class="btn btn-primary">
                                    <i class="fa fa-filter fa-lg fa-fw fa-cust"></i>
                                </a>
                                <a class="btn btn-primary">
                                    <i class="fa fa-refresh fa-lg fa-fw fa-cust"></i></a>
                                <input type="search" class="search" name="keywords" />
                                <input type="button" value="Search" onclick="onSubmit()"/>
                                <input type="hidden" name="categoryID" value="43"/>
                            </li>
                        </ul>
                    </div>
                    <%                        HashMap<String, QueryResult> results = (HashMap<String, QueryResult>) request.getAttribute("results");
                        if (results != null) {
                            QueryResult qr = results.get("UpperGarment");
                            ArrayList<UpperGarment> upperGarments = qr.getResult();
                            ArrayList<Brand> brandFilter = qr.getBrandFilter();
                            HashMap<String, ArrayList<String>> filters = qr.getFilters();
                            out.println("<div style=''>Matched Results: " + upperGarments.size() + "</div>");
                            String divBrandFilter = "";
                            if (brandFilter != null) {
                                divBrandFilter += "<div class='filterBar'>";
                                divBrandFilter += "<div class='attrKey'>Brand</div>";
                                divBrandFilter += "<div class='attrValues'>";
                                divBrandFilter += "<ul class='attrValue_ul'>";
                                String brandIcon = "";
                                for (Brand b : brandFilter) {
                                    divBrandFilter += String.format("<li><a href='%s'>", qs + "&brandName=" + b.getBrandName());
                                    brandIcon = b.getBrandIcon();
                                    if (brandIcon != null) {
                                        divBrandFilter += "<img src='" + brandIcon + "' />";
                                    } else {
                                        divBrandFilter += b.getBrandName();
                                    }
                                    divBrandFilter += "</a></li>";
                                }
                                divBrandFilter += "</ul></div>";
                                divBrandFilter += "</div>";
                            }
                            String divFilters = "";
                            if (filters != null) {
                                for (Entry<String, ArrayList<String>> entry : filters.entrySet()) {
                                    String filterName = entry.getKey();
                                    ArrayList<String> filterValues = entry.getValue();
                                    divFilters += "<div class='filterBar'>";
                                    divFilters += "<div class='attrKey'>"
                                            + filterName + "</div>";
                                    divFilters += "<div class='attrValues'>";
                                    divFilters += "<ul class='attrValue_ul'>";

                                    qs = request.getQueryString();
                                    for (String filterValue : filterValues) {
                                        if (qs == null) {
                                            qs = "";
                                        } else {
                                            qs += "&";
                                        }
                                        divFilters += "<li><input type='checkbox'"
                                                + " name='"
                                                + filterName + "'"
                                                + " value='"
                                                + filterValue + "'/> " + String.valueOf(filterValue) + "</li>";
                                    }
                                    divFilters += "</ul>";
                                    divFilters += "</div>";
                                    divFilters += "</div>";
                                }
                            }
                            out.println("<div id='filterNav'>");
                            out.println(divBrandFilter);
                            out.println(divFilters);
                            out.println("</div>");

                            if (upperGarments != null) {
                                String divResult = "";
                                String divMDetail = "";
                                String divM = "<div id='searchResult'>";
                                for (UpperGarment u : upperGarments) {
                                    Cloth m = u.getCloth();
                                    divM += "<div class='merchandise'>";
                                    divM += "<div class='m_title'>" + m.getMerchandise().getBrandID().getBrandName() + "</div>";
                                    divM += "<div><img src='" + m.getMerchandise().getMerchandiseImage() + "' /></div>";
                                    divM += "<div class='m_price'>$" + m.getMerchandise().getStoreMerchandiseCollection().iterator().next().getPrice() + "</div>";
                                    divM += "<div class='m_name'>" + m.getMerchandise().getMerchandiseName() + "</div>";
//                                    divM += "<div><a class='m_other' href='" + m.getMerchandise().getReferenceLink() + ">Other</a></div>";
                                    divM += "</div>";

                                    //                    divMDetail = "<table class='m_detail'>";
                                    //                        String columnName = rm.getColumnName(i);
                                    //                        if (!columnName.contains("brandName")
                                    //                                && !columnName.contains("merchandise")
                                    //                                && !columnName.contains("price")) {
                                    //                            divMDetail += "<tr>";
                                    //                        }
                                    //                        divMDetail += "<td>" + rm.getColumnName(i) + "</td>";
                                    //                        divMDetail += "<td>" + rm.getString(i) + "</td>";
                                    ////                        divMDetail += "</tr>";
                                    //                    divMDetail += "</table>";
                                    divM += divMDetail;
                                }
                                divM += "</div>";
                                divResult += divM;
                                out.println(divResult);
                            } else if (upperGarments.isEmpty()) {
                                out.println("<div class='error'>NO RELATED RECORD!!</div>");
                            }

                            Map<String, String[]> params = request.getParameterMap();
                            String key, value;
                            for (Map.Entry<String, String[]> param : params.entrySet()) {
                                key = param.getKey();
                                value = param.getValue()[0];
                                if (!key.equals("keywords")) {
                                    out.println("<input type='hidden' name='" + key + "' value='" + value + "'/>");
                                }
                            }
                        }
                    %>
                </div>
            </form>
        </div>
    </div>
</div>
