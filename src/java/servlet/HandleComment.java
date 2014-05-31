/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Comment;
import bean.CommentPK;
import bean.Store;
import bean.Tag;
import bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author poonkaho
 */
@WebServlet(name = "HandleComment", urlPatterns = {"/HandleComment"})
public class HandleComment extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        if ("getComment".equalsIgnoreCase(action)) {
            int storeId = Integer.parseInt(request.getParameter("storeId"));
            int startIndex = Integer.parseInt(request.getParameter("startIndex"));
            int endIndex = Integer.parseInt(request.getParameter("endIndex"));
            boolean isLastIndex = false;

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            //Store store = em.find(Store.class, storeId);
            Query query = em.createQuery("select c from Comment c where c.commentPK.storeID = :storeID order by c.commentPK.postTime DESC");
            query.setParameter("storeID", storeId);
            //get a sorted comment list
            List<Comment> comments = query.getResultList();

            em.getTransaction().commit();
            em.close();
            factory.close();

            //ArrayList<Comment> comments = new ArrayList<Comment>(store.getCommentCollection());

            JSONObject jsonObject = new JSONObject();

            JSONArray commentJsonArray = new JSONArray();

            JSONObject commentObject;

            try {
                if (endIndex >= comments.size()) {
                    endIndex = comments.size() - 1;
                    isLastIndex = true;
                }

                for (int x = startIndex; x < (endIndex + 1); x++) {
                    commentObject = new JSONObject();

                    commentObject.put("userId", comments.get(x).getUser().getUserID());
                    commentObject.put("userName", comments.get(x).getUser().getUserName());
                    commentObject.put("feedback", comments.get(x).getFeedback());
                    commentObject.put("score", comments.get(x).getRank());
                    String postedDate = comments.get(x).getCommentPK().getPostTime().toGMTString().replaceAll("GMT", "");
                    String[] dateArray = postedDate.split(" ");
                    
                    commentObject.put("postedDate", dateArray[0] + " " + dateArray[1] + " " + dateArray[2]);

                    commentJsonArray.put(commentObject);
                }

                jsonObject.put("comments", commentJsonArray);

                jsonObject.put("startIndex", startIndex += 10);
                jsonObject.put("endIndex", endIndex += 10);
                jsonObject.put("storeId", storeId);
                jsonObject.put("isLastIndex", isLastIndex);
                //next set of comment list

                out.println(jsonObject);
            } catch (JSONException ex) {
                Logger.getLogger(HandleComment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("commentStore".equalsIgnoreCase(action)) {
            HttpSession httpSession = request.getSession(false);
            User user = (User) httpSession.getAttribute("user");
            //get user account from session

            int storeId = Integer.parseInt(request.getParameter("storeId"));
            String comment = request.getParameter("comment");
            Short score = Short.parseShort(request.getParameter("score"));

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            
            Comment newComment = new Comment();
            newComment.setCommentPK(new CommentPK(user.getUserID(),storeId, new Date()));
            newComment.setFeedback(comment);
            newComment.setRank(score);
            
            em.persist(newComment);

            em.getTransaction().commit();
            em.close();
            factory.close();

            out.println("From Servlet");
        }



    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
