package org.apache.jsp.search;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import string.StringManager;
import bean.Category;
import java.util.ArrayList;
import bean.Store;
import bean.User;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class StoreManagement_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
 String requestURL = request.getRequestURL() + "";
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print( requestURL);
      out.write("/../../js/jquery/jquery.js\">\n");
      out.write("</script>\n");
      out.write("<script type=\"text/javascript\" src=\"//cdn.datatables.net/1.10.0/js/jquery.dataTables.js\">\n");
      out.write("</script>\n");
      out.write("<script type=\"text/javascript\" src=\"//cdn.datatables.net/plug-ins/28e7751dbec/integration/jqueryui/dataTables.jqueryui.js\">\n");
      out.write("</script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print( requestURL);
      out.write("/../../js/js-store-list.js\">\n");
      out.write("</script>\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print( requestURL);
      out.write("/../../css/font-awesome.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"//cdn.datatables.net/plug-ins/28e7751dbec/integration/jqueryui/dataTables.jqueryui.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print( requestURL);
      out.write("/../../css/css-store-list.css\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>FYP Product Search -- MY STORE</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        <div id=\"store-wrapper\" class=\"page\">\n");
      out.write("            <table id=\"example\" class=\"display\" cellspacing=\"0\" width=\"100%\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Store ID</th>\n");
      out.write("                        <th>Store Name</th>\n");
      out.write("                        <th>Store Description</th>\n");
      out.write("                        <th>Address</th>\n");
      out.write("                        <th>Rank</th>\n");
      out.write("                        <th>Created Date</th>\n");
      out.write("                        <th>Category</th>\n");
      out.write("                        <th>Manage Merchandise</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tfoot> \n");
      out.write("                    <tr>\n");
      out.write("                        <th>Store ID</th>\n");
      out.write("                        <th>Store Name</th>\n");
      out.write("                        <th>Store Description</th>\n");
      out.write("                        <th>Address</th>\n");
      out.write("                        <th>Rank</th>\n");
      out.write("                        <th>Created Date</th>\n");
      out.write("                        <th>Category</th>\n");
      out.write("                        <th>Manage Merchandise</th>\n");
      out.write("                    </tr>\n");
      out.write("                </tfoot>\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        for (Store s : stores) {
                            Category cat = s.getCategoryID();
                            String catName = StringManager.wordsFirstAlphabetUpper(cat.getCategoryName());

                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");
      out.print( s.getStoreID() + "");
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <image src=\"");
      out.print( requestURL + "/../../" + s.getStoreAvatar());
      out.write("\" />\n");
      out.write("                            ");
      out.print( s.getStoreName());
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        <td>");
      out.print( s.getStoreDesc());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( s.getAddress());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( s.getRank());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( s.getStoreCreateTime());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print( catName);
      out.write("</td>\n");
      out.write("                        <td><a onclick=\"manageMerchandise('");
      out.print( s.getStoreID());
      out.write("');\" class=\"fa fa-fw btn btn-default\">Manage Merchandise</a></td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("        ");
        } else {
        
      out.write("\n");
      out.write("        YOU HAVE NO STORE!\n");
      out.write("        ");

                }
            } else {
                out.println("Please Login!");
            }
        
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
