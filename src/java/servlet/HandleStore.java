/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Category;
import bean.Merchandise;
import bean.Mobile;
import bean.Store;
import bean.StoreMerchandise;
import bean.Tag;
import bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author poonkaho
 */
@WebServlet(name = "HandleStore", urlPatterns = {"/HandleStore"})
@MultipartConfig
public class HandleStore extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(HandleStore.class.getCanonicalName());

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
        } else if ("requestAStoreRedirectToMap".equalsIgnoreCase(action)) {
            //String storeId = request.getParameter("storeId");
            String storeId = request.getParameter("storeId");
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            Store store = em.find(Store.class, Integer.parseInt(storeId));
            ArrayList<Store> stores = new ArrayList();
            stores.add(store);
            request.setAttribute("stores", stores);
            //Store result = em.find(Store.class, Integer.parseInt(storeId));
            em.getTransaction().commit();
            em.close();
            factory.close();
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/index-2.jsp");
            rd.forward(request, response);

        } else if ("getAStoreWhole".equalsIgnoreCase(action)) {
            String storeId = request.getParameter("storeId");
            try {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
                EntityManager em = factory.createEntityManager();
                em.getTransaction().begin();
                Store store = em.find(Store.class, Integer.parseInt(storeId));
                em.getTransaction().commit();
                em.close();
                factory.close();

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/storeInfo.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                
            } finally {
                out.close();
            }

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
               
            } finally {
                out.close();
            }
        } else if ("newStore".equalsIgnoreCase(action)) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();
            Query q = em.createNamedQuery("Category.findAll", Category.class);
            List<Category> x = q.getResultList();
            ArrayList<Category> categories = new ArrayList(x);
            request.setAttribute("categories", categories);
            em.getTransaction().commit();
            em.close();
            factory.close();
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/newStore.jsp");
            rd.forward(request, response);
        } else if ("newStoreWithValue".equalsIgnoreCase(action)) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
            EntityManager em = factory.createEntityManager();
            em.getTransaction().begin();

            Store store = new Store();

            store.setStoreName(request.getParameter("storeName"));
            store.setAddress(request.getParameter("address"));
            String category = request.getParameter("category");
            store.setCategoryID(em.find(Category.class, Integer.parseInt(category)));

            store.setPhoneNumber(Integer.parseInt(request.getParameter("phoneNumber")));
            store.setRank(60);
            store.setStoreDesc(request.getParameter("storeDesc"));
            Date date = new Date();
            date.setDate(Integer.parseInt(request.getParameter("storeCreateTime").replace("-", "")));
            store.setStoreCreateTime(new Date());
            store.setLatitude(Double.parseDouble(request.getParameter("lat")));
            store.setLongitude(Double.parseDouble(request.getParameter("lng")));

            // Create path components to save the file
            String path = getServletContext().getRealPath("/") + "avatar";
            //String path = "fileUpload"; 
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);

            OutputStream out2 = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                out2 = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out2.write(bytes, 0, read);
                }
                writer.println("New file " + fileName + " created at " + path);
                LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                        new Object[]{fileName, path});
            } catch (FileNotFoundException fne) {
                writer.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());

                LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                        new Object[]{fne.getMessage()});
            }
            HttpSession httpSession = request.getSession(false);
            User user = (User) httpSession.getAttribute("user");
            store.setUserID(user);
            String imgPath = ("avatar" + File.separator + fileName);
            store.setStoreAvatar(imgPath);
            em.persist(store);
            User newUser = em.find(User.class, user.getUserID());
            httpSession.setAttribute("user", newUser);
            //renew the bean.user in user

            //User user = em.find(User.class, userId);
            em.getTransaction().commit();
            em.close();
            factory.close();

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/HandleUser?action=getUserInfo");
            rd.forward(request, response);
        } else {
            out.println("no such action");
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
