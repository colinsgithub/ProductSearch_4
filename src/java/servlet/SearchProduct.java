/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dbEngine.DatabaseEngine;
import dbEngine.QueryResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "SearchProduct", urlPatterns = {"/SearchProduct"})
public class SearchProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
//    protected String PU = this.getInitParameter("PU");
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String PU = getServletContext().getInitParameter("PU");
            Map<String, String[]> tmp = request.getParameterMap();
            Map<String, String[]> params = new HashMap(tmp);
            String[] keywordss = params.remove("keywords");
            String keywords = null;
            if (keywordss != null && keywordss.length != 0) {
                keywords = keywordss[0];
            }
            if(params.isEmpty() || params.get("categoryID") == null){
                params.put("categoryID", new String[]{"56"});
            }
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
            DatabaseEngine dbe = new DatabaseEngine(emf);
            HashMap<String, QueryResult> results = dbe.doSearch(params, keywords);
            if (results != null) {
                String tableName = "Product";
                if (results.size() == 1) {
                    int i = 0;
                    for (Map.Entry<String, QueryResult> entry : results.entrySet()) {
                        System.out.println("_______LOADING______");
                        tableName = entry.getKey();
                        System.out.println(tableName);
                        System.out.println("size: " + entry.getValue().getResult().size());
                    }
                }
                System.out.println("search/" + tableName + "Search.jsp");
                RequestDispatcher rd = request.getRequestDispatcher("search/" + tableName + "Search.jsp");
                request.setAttribute("results", results);
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
