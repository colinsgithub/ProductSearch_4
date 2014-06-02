/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEngine;

import bean.Brand;
import bean.Merchandise;
import bean.Store;
import bean.StoreMerchandise;
import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class QueryResult {

    private String tableName;
    private Class U;
    private ArrayList result;
    private HashMap<String, ArrayList<String>> filters;
    private ArrayList<Brand> brandFilter;
    private int catID;

    public QueryResult(String tableName, String catID) throws ClassNotFoundException {
        System.out.println(tableName);
        U = Class.forName("bean." + tableName);
        this.catID = Integer.parseInt(catID);
    }

    public int getCatID() {
        return catID;
    }

    public <T> ArrayList<T> getResult() {
        Class T = this.U;
        return (ArrayList<T>) result;
    }

    public void setResult(ArrayList result) {
        this.result = result;
    }

    public Class getEntityClass() {
        return U;
    }

    public String getTableName() {
        return tableName;
    }

    public HashMap<String, ArrayList<String>> getFilters() {
        return filters;
    }

    public void setFilters(HashMap<String, ArrayList<String>> filters) {
        this.filters = filters;
    }

    public ArrayList<Brand> getBrandFilter() {
        return brandFilter;
    }

    public void setBrandFilter(ArrayList<Brand> brandFilter) {
        this.brandFilter = brandFilter;
    }

    public ArrayList<Merchandise> getMerchandises() {
        EntityManager em = Persistence.createEntityManagerFactory("ProductSearch_3PU")
                .createEntityManager();
        ArrayList<Merchandise> merchandises = new ArrayList();
        for (Object obj : result) {
            String id = this.getPrimaryKey(obj.toString());
            String sqlGetMerchandise = "SELECT m.* FROM merchandise m "
                    + "WHERE m.merchandiseID = ? AND categoryID = ?";
            Query q = em.createNativeQuery(sqlGetMerchandise, Merchandise.class);
            q.setParameter(1, id);
            q.setParameter(2, catID);
            ArrayList<Merchandise> tmp = new ArrayList(q.getResultList());
            if (!tmp.isEmpty()) {
                merchandises.add(tmp.get(0));
            }
        }
        if (merchandises.isEmpty()) {
            return null;
        }
        return merchandises;
    }

    public ArrayList<Store> getStores() {
        ArrayList<Merchandise> ms = this.getMerchandises();
        boolean hasStore = false;
        if (ms == null || ms.isEmpty()) {
            return null;
        }
        ArrayList<Store> stores = new ArrayList();
        for (Merchandise m : ms) {
            ArrayList<StoreMerchandise> sms = new ArrayList(m.getStoreMerchandiseCollection());
            if (sms == null) {
                continue;
            }
            for (StoreMerchandise sm : sms) {
                Store s = sm.getStore();
                hasStore = false;
                for (Store st : stores) {
                    if (st.getStoreID() == s.getStoreID()) {
                        hasStore = true;
                    }
                }
                if(!hasStore){
                    stores.add(s);
                }
            }
        }
        return stores;
    }

    protected String getPrimaryKey(String str) {
        String key = "";
        boolean isBegin = false;
        for (char c : str.toCharArray()) {
            if (isBegin) {
                if (c != ' ' || c != ',') {
                    key += c;
                }
            }
            if (c == '=') {
                isBegin = true;
            }
        }
        return key;
    }

}
