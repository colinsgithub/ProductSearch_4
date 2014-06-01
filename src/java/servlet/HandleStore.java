/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Merchandise;
import bean.Mobile;
import bean.Store;
import bean.StoreMerchandise;
import bean.Tag;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author poonkaho
 */
@WebServlet(name = "HandleStore", urlPatterns = {"/HandleStore"})
public class HandleStore extends HttpServlet {

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
        String dbUrl = "jdbc:mysql://localhost:3306/product";
        String dbUser = "root";
        String dbPassword = "root";


    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        if ("list".equalsIgnoreCase(action)) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Store.findAll", Store.class);
            List<Store> stores = q.getResultList();

            ArrayList storeList = new ArrayList(stores);
            request.setAttribute("stores", storeList);
            //Store result = em.find(Store.class, Integer.parseInt(storeId));
            em.getTransaction().commit();
            em.close();
            factory.close();

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/index-2.jsp");
            rd.forward(request, response);
        } else if ("list2".equalsIgnoreCase(action)) {
            //String storeId = request.getParameter("storeId");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Store.findAll", Store.class);
            List<Store> stores = q.getResultList();

            ArrayList x = new ArrayList(stores);
            request.setAttribute("stores", x);
            //Store result = em.find(Store.class, Integer.parseInt(storeId));
            em.getTransaction().commit();
            em.close();
            factory.close();
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);

        } else if ("getAStoreProductList".equalsIgnoreCase(action)) {
            String storeId = request.getParameter("storeId");
            try {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
                EntityManager em = factory.createEntityManager();
                em.getTransaction().begin();
                Store store = em.find(Store.class, Integer.parseInt(storeId));
                ArrayList<StoreMerchandise> merchandises = new ArrayList<StoreMerchandise>(store.getStoreMerchandiseCollection());

                JSONObject jsonObject = new JSONObject();
                final GsonBuilder builder = new GsonBuilder();
                builder.excludeFieldsWithoutExposeAnnotation();
                final Gson gson = builder.create();
                JSONArray array = new JSONArray();
                for (int x = 0; x < merchandises.size(); x++) {
                    JSONObject merchandise = new JSONObject();
                    merchandise.put("merchandiseColor", merchandises.get(x).getColor());
                    merchandise.put("merchandisePrice", merchandises.get(x).getPrice());
                    merchandise.put("merchandiseName", merchandises.get(x).getMerchandise().getMerchandiseName());
                    merchandise.put("merchandiseDesc", merchandises.get(x).getMerchandise().getMerchandiseDesc());
                    merchandise.put("merchandiseImage", merchandises.get(x).getMerchandise().getMerchandiseImage());
                    merchandise.put("merchandiseImage1", merchandises.get(x).getMerchandiseImage1());
                    merchandise.put("merchandiseImage2", merchandises.get(x).getMerchandiseImage2());
                    merchandise.put("merchandiseImage3", merchandises.get(x).getMerchandiseImage3());
                    merchandise.put("merchandiseBrandName", merchandises.get(x).getMerchandise().getBrandID().getBrandName());
                    merchandise.put("listingYear", merchandises.get(x).getMerchandise().getListingYear().getYear() + 1900);
                    if (merchandises.get(x).getMerchandise().getCloth() != null) {
                        JsonElement features = gson.toJsonTree(merchandises.get(x).getMerchandise().getCloth());
                        merchandise.put("features", features.getAsJsonObject());
                    } else if (!merchandises.get(x).getMerchandise().getMobileCollection().isEmpty()) {
                        ArrayList<Mobile> mobileFeatures = new ArrayList(merchandises.get(x).getMerchandise().getMobileCollection());
                        JsonArray jsonArray = new JsonArray();
                        for (int y = 0; y < mobileFeatures.size(); y++) {
                            JsonElement features = gson.toJsonTree(mobileFeatures.get(y));
                            jsonArray.add(features);
                        }
                        merchandise.put("features", jsonArray);
                    } else {
                        merchandise.put("features", "null");
                    }
                    array.put(merchandise);
                }
                em.getTransaction().commit();
                em.close();
                factory.close();
                jsonObject.put("data", array);
                out.println(jsonObject);

            } catch (Exception e) {
                Logger.getLogger(HandleTag.class.getName()).log(Level.SEVERE, null);
            } finally {
                out.close();
            }
        } else if ("getAStore".equalsIgnoreCase(action)) {
            String storeId = request.getParameter("storeId");
            try {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
                EntityManager em = factory.createEntityManager();
                em.getTransaction().begin();
                Store store = em.find(Store.class, Integer.parseInt(storeId));
                JSONObject jsonStore = new JSONObject();
                jsonStore.put("storeName", store.getStoreName());
                jsonStore.put("phoneNumber", store.getPhoneNumber());
                jsonStore.put("rank", store.getRank());
                jsonStore.put("storeAvatar", store.getStoreAvatar());
                jsonStore.put("addedCount", store.getTagCollection().size());
                jsonStore.put("createTime", store.getStoreCreateTime().toGMTString());
                jsonStore.put("storeDesc", store.getStoreDesc());
                jsonStore.put("storeAddress", store.getAddress());
                em.getTransaction().commit();
                em.close();
                factory.close();
                out.println(jsonStore);
            } catch (Exception e) {
                Logger.getLogger(HandleTag.class.getName()).log(Level.SEVERE, null);
            } finally {
                out.close();
            }
        }
    }
}
