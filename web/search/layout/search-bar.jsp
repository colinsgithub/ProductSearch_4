

<div id="search-page">
    <form class='search-form'>
        <fieldset>
            <div class="search-form-input">
                <div class="s-combobox">
                    <div id='search-bar' class="s-combobox-input-wrap">
                        <%  String keywords = request.getParameter("keywords");
                            keywords = (keywords == null) ? "" : keywords;
                        %>
                        <input type="text" name="keywords" id="keywords" 
                               placeholder='Please enter keywords' class='s-combobox-input' 
                               accesskey="s"  tabindex="9" autofocus="true" 
                               title="please enter searched keywords" 
                               aria-label="please enter searched keywords" value="<%= keywords %>"/>
                    </div>
                    <button type='submit'><i class='fa fa-search'></i></button>
                </div>
            </div>
        </fieldset>
    </form>
    <ul class='hot-q'>
        <%
            String[] hotQs = new String[7];
            hotQs = new String[]{
                "SAMSUNG", "NOKIA", "SONY", "BOSSINI",
                "BOSIDENG", "Coat", "T-SHIRT", "Hoodie"
            };
            for (String hotQ : hotQs) {
        %>
        <li class='hot-q-hl'>
            <a href='?keywords=<%= hotQ%>'><%= hotQ%></a>
        </li>
        <%
            }
        %>
    </ul>
</div>
