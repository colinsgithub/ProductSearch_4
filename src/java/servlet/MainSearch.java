/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Store;
import dbEngine.DatabaseEngine;
import dbEngine.QueryResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "MainSearch", urlPatterns = {"/MainSearch"})
public class MainSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected String PU;
    
    public void init(){
        PU = getServletContext().getInitParameter("PU");
    }
    protected void doGeneralSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String keywords = request.getParameter("keywords");
        if (keywords != null) {
            System.out.println("--start--");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
            DatabaseEngine dbe = new DatabaseEngine(emf);
            Map<String, String[]> params = new HashMap();
            ArrayList<ArrayList<String>> res = new ArrayList();
            ArrayList<String> tmp = null;
            params.put("categoryID", new String[]{"2"});
            System.out.println("-- Main Search --");
            HashMap<String, QueryResult> results = dbe.doSearch(params, keywords);
            if (results != null && !results.isEmpty()) {
                for (Map.Entry<String, QueryResult> entry : results.entrySet()) {
                    String tableName = entry.getKey();
                    QueryResult qs = entry.getValue();
                    ArrayList al = qs.getResult();
                    if (al.size() > 0) {
                        tmp = new ArrayList();
                        tmp.add(tableName);
                        tmp.add(al.size() + "");
                        tmp.add(qs.getCatID() + "");
                        res.add(tmp);
                        System.out.println(tableName);
                        System.out.println(al.size());
                    }
                }
            }
            System.out.println(res.size());
            Collections.sort(res, new Comparator() {
                public int compare(Object arr1, Object arr2) {
                    return ((ArrayList<String>) arr2).get(1)
                            .compareTo(((ArrayList<String>) arr1).get(1));
                }
            });
            System.out.println(res.size());

            String json = "[";
            if (res != null && !res.isEmpty()) {
                for (ArrayList<String> altmp : res) {
                    if (!json.equals("[")) {
                        json += ", ";
                    }

                    json += "{";
                    json += "\"name\":\"" + altmp.get(0) + "\", ";
                    json += "\"count\":" + altmp.get(1) + ", ";
                    json += "\"categoryID\":" + altmp.get(2);
                    json += "}";
                }
            }
            json += "]";

            PrintWriter out = response.getWriter();
            out.print(json);
            System.out.println(json);
        }
    }

    protected void doSelect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String keywords = request.getParameter("keywords");
        Map<String, String[]> params = new HashMap();
        params.put("categoryID", request.getParameterValues("categoryID"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DatabaseEngine dbe = new DatabaseEngine(emf);

        params.put("categoryID", new String[]{"2"});
        HashMap<String, QueryResult> results = dbe.doSearch(params, keywords);
    }

    protected void doGetResult(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String keywords = request.getParameter("keywords");
        System.out.println("--start--");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DatabaseEngine dbe = new DatabaseEngine(emf);
        Map<String, String[]> params = new HashMap();
        ArrayList<ArrayList<String>> res = new ArrayList();
        ArrayList<String> tmp = null;
        params.put("categoryID", new String[]{"2"});
        String storeID = request.getParameter("storeID");
        if (storeID != null && !storeID.equals("")) {
            params.put("storeID", new String[]{storeID});
        }
        HashMap<String, QueryResult> results = dbe.doSearch(params, keywords);
        ArrayList<Store> stores = new ArrayList();
        boolean hasStore = false;
        for (Map.Entry<String, QueryResult> entry : results.entrySet()) {
            String tableName = entry.getKey();
            QueryResult qs = entry.getValue();
            ArrayList al = qs.getResult();
            ArrayList<Store> sts = qs.getStores();
            if (sts != null && !sts.isEmpty()) {
                for (Store st : sts) {
                    hasStore = false;
                    for (Store s : stores) {
                        if (st.getStoreID() == s.getStoreID()) {
                            hasStore = true;
                        }
                    }
                    if (!hasStore) {
                        stores.add(st);
                    }
                }
            }
        }

        System.out.println("Stores COUNT: " + stores.size());
        request.setAttribute("stores", stores);
        HttpSession session = request.getSession();
        session.setAttribute("search_result", results);
        RequestDispatcher rd = request.getRequestDispatcher("search/ResultManagement.jsp");
        rd.forward(request, response);
    }

    protected void doGetResultA(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String keywords = request.getParameter("keywords");
        System.out.println("--start--");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        DatabaseEngine dbe = new DatabaseEngine(emf);
        Map<String, String[]> params = new HashMap();
        ArrayList<ArrayList<String>> res = new ArrayList();
        ArrayList<String> tmp = null;
        params.put("categoryID", new String[]{"2"});
        String storeID = request.getParameter("storeID");
        if (storeID != null && !storeID.equals("")) {
            params.put("storeID", new String[]{storeID});
        }
        HashMap<String, QueryResult> results = dbe.doSearch(params, keywords);
        ArrayList<Store> stores = new ArrayList();
        boolean hasStore = false;
        for (Map.Entry<String, QueryResult> entry : results.entrySet()) {
            String tableName = entry.getKey();
            QueryResult qs = entry.getValue();
            ArrayList al = qs.getResult();
            ArrayList<Store> sts = qs.getStores();
            if (sts != null && !sts.isEmpty()) {
                for (Store st : sts) {
                    hasStore = false;
                    for (Store s : stores) {
                        if (st.getStoreID() == s.getStoreID()) {
                            hasStore = true;
                        }
                    }
                    if (!hasStore) {
                        stores.add(st);
                    }
                }
            }
        }

        System.out.println("Stores COUNT: " + stores.size());
        request.setAttribute("stores", stores);
        HttpSession session = request.getSession();
        session.setAttribute("search_result", results);
        RequestDispatcher rd = request.getRequestDispatcher("index-2.jsp");
        rd.forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action.equals("search")) {
                this.doGeneralSearch(request, response);
            } else if (action.equals("select")) {
                this.doSelect(request, response);
            } else if (action.equals("getResult")) {
                this.doGetResult(request, response);
            } else if (action.equals("getResultA")) {
                this.doGetResultA(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainSearch.class.getName()).log(Level.SEVERE, null, ex);
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
