/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Store;
import bean.Tag;
import bean.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.eclipse.persistence.sessions.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author poonkaho
 */
@WebServlet(name = "HandleUser", urlPatterns = {"/HandleUser"})
public class HandleUser extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        /*
         String dbUser = this.getServletContext().getInitParameter("dbUser");
         String dbPassword = this.getServletContext().getInitParameter("dbPassword");
         String dbUrl = this.getServletContext().getInitParameter("dbUrl");
         */



    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equalsIgnoreCase(action)) {
            //db.createCustTable();
            String userId = request.getParameter("userId");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();


            User user = em.find(User.class, userId);
            em.getTransaction().commit();
            em.close();
            factory.close();
            try {
                if (user.getPassword().equals(request.getParameter("password"))) {
                    HttpSession httpSession = request.getSession(true);
                    httpSession.setAttribute("user", user);


                    response.getWriter().print("true");
                } else {
                    response.getWriter().print("false");
                }
            } catch (NullPointerException e) {
                response.getWriter().print("false");
            }
        } else if ("getInfo".equalsIgnoreCase(action)) {
            HttpSession httpSession = request.getSession(false);
            User user = (User) httpSession.getAttribute("user");
            //Gson gson = new Gson();
            //String gUser = gson.toJson(user, User.class);

            Map map = new HashMap();
            map.put("userID", user.getUserID());
            map.put("userName", user.getUserName());
            map.put("sex", user.getSex());
            map.put("age", user.getAge());
            map.put("email", user.getEmail());
            map.put("avatar", user.getAvatar());
            map.put("credit", user.getCredit());
            map.put("role", user.getRole());


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("user", map);
            } catch (JSONException ex) {
                Logger.getLogger(HandleUser.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.getWriter().print(jsonObject);

        } else if ("logout".equalsIgnoreCase(action)) {
            HttpSession httpSession = request.getSession(false);
            httpSession.invalidate();

            response.getWriter().print("true");
        } else if ("getEditCustomer".equalsIgnoreCase(action)) { // call the query db to get retrieve for all customer
            String id = request.getParameter("id");
            if (id != null) {



                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editCustomer.jsp");
                rd.forward(request, response);
            }
        } else if ("getUserInfo".equalsIgnoreCase(action)) {
            HttpSession httpSession = request.getSession();
            User user = (User) httpSession.getAttribute("user");
            

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            em.getTransaction().commit();
            em.close();
            factory.close();

            request.setAttribute("user", user);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/personalInfo.jsp");
            rd.forward(request, response);
        } else if ("updateUser".equalsIgnoreCase(action)) {
            String colum = request.getParameter("name");
            String value = request.getParameter("value");
            
            HttpSession httpSession = request.getSession(false);
            User user = (User) httpSession.getAttribute("user");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            Query updateQuery = em.createQuery("UPDATE User s SET s." + colum + " = :value WHERE s.userID = :userID");
            updateQuery.setParameter("value", value);
            updateQuery.setParameter("userID", user.getUserID());
            int updated = updateQuery.executeUpdate();
            /*
            User newUser = em.find(User.class, user.getUserID());
            newUser.setUserName(value);
            em.merge(newUser);
            */
            
            User newUser = em.find(User.class, user.getUserID());
            
            em.getTransaction().commit();
            em.close();
            factory.close();
            
            httpSession.setAttribute("user", newUser);

        } else if("viewOtherUser".equalsIgnoreCase(action)) {} 
            
        
        
        else {
            PrintWriter out = response.getWriter();
            out.println("HandleUser:No such action!!!");
        }
    }
}
