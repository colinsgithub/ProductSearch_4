<%--
    Document   : productSearch
    Created on : Mar 4, 2014, 12:58:17 AM
    Author     : user
--%>
<%@page import="bean.Merchandise"%>
<%@page import="bean.LowerGarment"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="bean.Brand"%>
<%@page import="bean.Mobile"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbEngine.QueryResult"%>
<%@page import="java.util.HashMap"%>
<script type="text/javascript" src="js/jquery/jquery.js">
</script>
<script type="text/javascript" src="js/js-product-search.js">
</script>
<link rel="stylesheet" href="css/css-product-search.css">
<div>
    <div id="spage-wrapper-a">
        <div id="spage-wrapper-header">
            <%@include file="MerchandiseSearch/frame/searchBar.jsp" %>
        </div>
        <div id="spage-wrapper-body">
            <form id="sb_output" method="GET" action="SearchProduct">
                <div>
                    <ul>
                        <div class=''>
                        <%
                            String qs = request.getQueryString();
                            if (qs != null && !qs.equals("")) {
                                String[] qss = qs.split("&");
                                for (String qsson : qss) {
                                    String[] qsa = qsson.split("=");
                                    String href = "";
                                    if (qss.length > 1) {
                                        href = qs;
                                        if (href.contains("&" + qsson)) {
                                            href.replace("&" + qsson, "");
                                        } else if (href.contains(qsson + "&")) {
                                            href.replace(qsson + "&", "");
                                        } else {
                                            href.replace(qsson, "");
                                        }
                                    }
                                    if (qsa != null && qsa.length == 2 && !qsa[0].equals("categoryID")) {
                                        out.println("<a class='condition-tag' href=?" + href + ">" + qsa[0] + ":" + qsa[1] + "</a>");
                                    }
                                }
                            }
                        %>
                        <li>
                            <input type="search" name="keywords" />
                            <input type="submit" value="Search" />
                        </li>
                    </ul>
                    <%
                        HashMap<String, QueryResult> results = (HashMap<String, QueryResult>) request.getAttribute("results");
                        if (results != null) {
                            QueryResult qr = results.get("LowerGarment");
                            ArrayList<LowerGarment> ls = qr.getResult();
                            ArrayList<Brand> brandFilter = qr.getBrandFilter();
                            HashMap<String, ArrayList<String>> filters = qr.getFilters();
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

                            if (ls != null) {
                                String divResult = "";
                                String divMDetail = "";
                                String divM = "<div id='searchResult'>";
                                for (LowerGarment l : ls) {
                                    Merchandise m = l.getCloth().getMerchandise();
                                    divM += "<div class='merchandise'>";
                                    divM += "<div class='m_title'>" + m.getBrandID().getBrandName() + "</div>";
                                    divM += "<div><img src='" + m.getMerchandiseImage() + "' /></div>";
                                    divM += "<div class='m_price'>$" + m.getStoreMerchandiseList().get(0).getPrice() + "</div>";
                                    divM += "<div class='m_name'>" + m.getMerchandiseName() + "</div>";
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
