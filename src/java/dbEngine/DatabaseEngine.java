/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEngine;

import bean.Brand;
import bean.Category;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import org.eclipse.persistence.config.ExclusiveConnectionMode;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 *
 * @author user
 */
public class DatabaseEngine {

//  configure
    protected String paramValueSplitString;

//  tableOptions{tableName, tableOption{
//      filterOptions, searchOptions{
//          case1{ {{No-Table}, {values}} }
//          case2{ Array{ Array{One-Table}, Array{values} } }
//          case3{ Array{ Array{Two-Table}, Array{values} } }
//      }
//  }
    protected HashMap<String, TableConfig> tableOptions;

    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;
    protected ArrayList<String> categories;
    protected ArrayList<String> tableNames;
    protected String tableName;
    protected ArrayList<String> keywords;
    protected HashMap<String, String[]> usefulParams;
    protected String sqlConditions;

    public DatabaseEngine(EntityManagerFactory emf) {
        paramValueSplitString = "";
        this.emf = emf;
        if (emf != null) {
            Map props = new HashMap();
            props.put(PersistenceUnitProperties.EXCLUSIVE_CONNECTION_MODE,
                    ExclusiveConnectionMode.Always);
            em = emf.createEntityManager(props);
            em.setFlushMode(FlushModeType.COMMIT);
            tx = em.getTransaction();
        }
        categories = new ArrayList();
        tableNames = new ArrayList();
        tableName = null;
        keywords = new ArrayList();
        tableOptions = new HashMap();
        usefulParams = new HashMap();
        sqlConditions = "";

        initTableOptions();
    }

    //Maintenance area
    protected void initTableOptions() {
        paramValueSplitString = ",...,";
        String[][][][] optionsSetting;
        // Mobile - Search
        TableConfig tc = new TableConfig("Mobile");
        optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{
                        "brandName", "brandRegion", "merchandiseName",
                        "merchandiseDesc", "screenSize", "resolutionX",
                        "resolutionY", "screenPixelDensity", "mobileFormFactor",
                        "CPUModel", "CPUFrequency", "GPUModel", "SIMNumber",
                        "batteryType", "batteryCapacity", "talkTime", "standByTime",
                        "fontCameraPixel", "rearCameraPixel", "mobileSize", "weight"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"StoreMerchandise"},
                    new String[]{"color"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"TouchScreenType"},
                    new String[]{"touchScreenTypeID"},
                    new String[]{"touchScreenTypeID"},
                    new String[]{"touchScreenTypeName"}
                },
                new String[][]{
                    new String[]{"ScreenMaterial"},
                    new String[]{"screenMaterialID"},
                    new String[]{"screenMaterialID"},
                    new String[]{"screenMaterialName"}
                },
                new String[][]{
                    new String[]{"OperatingSystem"},
                    new String[]{"OSID"},
                    new String[]{"OSID"},
                    new String[]{"OSName"}
                },
                new String[][]{
                    new String[]{"CPUCoreType"},
                    new String[]{"CPUCoreTypeID"},
                    new String[]{"CPUCoreTypeID"},
                    new String[]{"CPUCoreTypeName"}
                },
                new String[][]{
                    new String[]{"CPUManufacturer"},
                    new String[]{"CPUManufacturerID"},
                    new String[]{"CPUManufacturerID"},
                    new String[]{"CPUManufacturerName"}
                },
                new String[][]{
                    new String[]{"FlashMemoryCard"},
                    new String[]{"flashMemoryCardID"},
                    new String[]{"flashMemoryCardID"},
                    new String[]{"flashMemoryCardName"}
                },
                new String[][]{
                    new String[]{"Flash"},
                    new String[]{"flashID"},
                    new String[]{"flashID"},
                    new String[]{"flashName"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"Mobile_MachineInterface", "MachineInterface"},
                    new String[]{"mobileID", "machineInterfaceID"},
                    new String[]{"machineInterfaceName"}
                },
                new String[][]{
                    new String[]{"Mobile_SensorType", "SensorType"},
                    new String[]{"mobileID", "sensorTypeID"},
                    new String[]{"sensorTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_ConnectivityAndSharing", "ConnectivityAndSharing"},
                    new String[]{"mobileID", "connectivityAndSharingID"},
                    new String[]{"connectivityAndSharingName"}
                },
                new String[][]{
                    new String[]{"Mobile_KeyboardType", "KeyboardType"},
                    new String[]{"mobileID", "keyboardTypeID"},
                    new String[]{"keyboardTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_Navigation", "Navigation"},
                    new String[]{"mobileID", "navigationID"},
                    new String[]{"navigationName"}
                },
                new String[][]{
                    new String[]{"Mobile_WLANType", "WLANType"},
                    new String[]{"mobileID", "WLANTypeID"},
                    new String[]{"WLANTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_MobileType", "MobileType"},
                    new String[]{"mobileID", "mobileTypeID"},
                    new String[]{"mobileTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_MobileNetworkStandard", "MobileNetworkStandard"},
                    new String[]{"mobileID", "mobileNetworkStandardID"},
                    new String[]{"mobileNetworkStandardName", "mobileNetworkStandardType"}
                },
                new String[][]{
                    new String[]{"Mobile_SIMCardType", "SIMCardType"},
                    new String[]{"mobileID", "SIMCardTypeID"},
                    new String[]{"SIMCardTypeName"}
                }
            }
        };
        tc.setSearchOptions(optionsSetting);
        // Mobile - Filter
        optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{
                        "brandName", "screenSize", "resolutionX", "resolutionY",
                        "screenPixelDensity", "mobileFormFactor", "CPUFrequency",
                        "SIMNumber", "batteryType", "batteryCapacity", "talkTime",
                        "standByTime", "fontCameraPixel", "rearCameraPixel",
                        "mobileSize", "weight"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"StoreMerchandise"},
                    new String[]{"color"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"TouchScreenType"},
                    new String[]{"touchScreenTypeID"},
                    new String[]{"touchScreenTypeID"},
                    new String[]{"touchScreenTypeName"}
                },
                new String[][]{
                    new String[]{"ScreenMaterial"},
                    new String[]{"screenMaterialID"},
                    new String[]{"screenMaterialID"},
                    new String[]{"screenMaterialName"}
                },
                new String[][]{
                    new String[]{"OperatingSystem"},
                    new String[]{"OSID"},
                    new String[]{"OSID"},
                    new String[]{"OSName"}
                },
                new String[][]{
                    new String[]{"CPUCoreType"},
                    new String[]{"CPUCoreTypeID"},
                    new String[]{"CPUCoreTypeID"},
                    new String[]{"CPUCoreTypeName"}
                },
                new String[][]{
                    new String[]{"CPUManufacturer"},
                    new String[]{"CPUManufacturerID"},
                    new String[]{"CPUManufacturerID"},
                    new String[]{"CPUManufacturerName"}
                },
                new String[][]{
                    new String[]{"FlashMemoryCard"},
                    new String[]{"flashMemoryCardID"},
                    new String[]{"flashMemoryCardID"},
                    new String[]{"flashMemoryCardName"}
                },
                new String[][]{
                    new String[]{"Flash"},
                    new String[]{"flashID"},
                    new String[]{"flashID"},
                    new String[]{"flashName"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"Mobile_MachineInterface", "MachineInterface"},
                    new String[]{"mobileID", "machineInterfaceID"},
                    new String[]{"machineInterfaceName"}
                },
                new String[][]{
                    new String[]{"Mobile_SensorType", "SensorType"},
                    new String[]{"mobileID", "sensorTypeID"},
                    new String[]{"sensorTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_ConnectivityAndSharing", "ConnectivityAndSharing"},
                    new String[]{"mobileID", "connectivityAndSharingID"},
                    new String[]{"connectivityAndSharingName"}
                },
                new String[][]{
                    new String[]{"Mobile_KeyboardType", "KeyboardType"},
                    new String[]{"mobileID", "keyboardTypeID"},
                    new String[]{"keyboardTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_Navigation", "Navigation"},
                    new String[]{"mobileID", "navigationID"},
                    new String[]{"navigationName"}
                },
                new String[][]{
                    new String[]{"Mobile_WLANType", "WLANType"},
                    new String[]{"mobileID", "WLANTypeID"},
                    new String[]{"WLANTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_MobileType", "MobileType"},
                    new String[]{"mobileID", "mobileTypeID"},
                    new String[]{"mobileTypeName"}
                },
                new String[][]{
                    new String[]{"Mobile_MobileNetworkStandard", "MobileNetworkStandard"},
                    new String[]{"mobileID", "mobileNetworkStandardID"},
                    new String[]{"mobileNetworkStandardName", "mobileNetworkStandardType"}
                },
                new String[][]{
                    new String[]{"Mobile_SIMCardType", "SIMCardType"},
                    new String[]{"mobileID", "SIMCardTypeID"},
                    new String[]{"SIMCardTypeName"}
                }
            }
        };
        tc.setFilterOptions(optionsSetting);
        tableOptions.put("Mobile", tc);

        // UpperGarment - Search
        tc = new TableConfig("UpperGarment");
        optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{
                        "brandName", "brandRegion", "merchandiseName",
                        "merchandiseDesc", "listingYear", "upperGarmentType",
                        "sleeveStyle", "sleeveType", "collar"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"StoreMerchandise"},
                    new String[]{"color"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"Cloth"},
                    new String[]{"upperGarmentID", "categoryID"},
                    new String[]{"clothID", "categoryID"},
                    new String[]{
                        "sex", "fabric", "fabricContent", "occasion",
                        "edition", "careLabel", "thickness", "threadThickness",
                        "season", "basicStyle", "subStyle"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
                }
            }
        };
        tc.setSearchOptions(optionsSetting);

        // UpperGarment - Filter
        optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{
                        "listingYear", "upperGarmentType",
                        "sleeveStyle", "sleeveType", "collar"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"StoreMerchandise"},
                    new String[]{"color"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"Cloth"},
                    new String[]{"upperGarmentID", "categoryID"},
                    new String[]{"clothID", "categoryID"},
                    new String[]{
                        "sex", "fabric", "fabricContent", "occasion",
                        "edition", "thickness", "threadThickness",
                        "season", "basicStyle", "subStyle"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
                }
            }
        };
        tc.setFilterOptions(optionsSetting);
        tableOptions.put("UpperGarment", tc);

        tc = new TableConfig("LowerGarment");
        // LowerGarment - Search
        optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{
                        "brandName", "brandRegion", "merchandiseName",
                        "merchandiseDesc", "listingYear", "lowerGarmentType",
                        "outseam", "waistHeight"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"StoreMerchandise"},
                    new String[]{"color"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"Cloth"},
                    new String[]{"lowerGarmentID", "categoryID"},
                    new String[]{"clothID", "categoryID"},
                    new String[]{
                        "sex", "fabric", "fabricContent", "occasion",
                        "edition", "careLabel", "thickness", "threadThickness",
                        "season", "basicStyle", "subStyle"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
                }
            }
        };
        tc.setSearchOptions(optionsSetting);

        // LowerGarment - Filter 
        optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{
                        "brandName", "brandRegion", "merchandiseName",
                        "listingYear", "lowerGarmentType",
                        "outseam", "waistHeight"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"StoreMerchandise"},
                    new String[]{"color"}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{"Cloth"},
                    new String[]{"lowerGarmentID", "categoryID"},
                    new String[]{"clothID", "categoryID"},
                    new String[]{
                        "sex", "fabric", "fabricContent", "occasion",
                        "edition", "thickness", "threadThickness",
                        "season", "basicStyle", "subStyle"
                    }
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
                }
            }
        };
        tc.setFilterOptions(optionsSetting);
        tableOptions.put("LowerGarment", tc);
    }
    // -- END -- //

    private String[][][][] createOptions() {
        String[][][][] optionsSetting = new String[][][][]{
            new String[][][]{
                new String[][]{
                    new String[]{}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
                }
            },
            new String[][][]{
                new String[][]{
                    new String[]{},
                    new String[]{},
                    new String[]{}
                }
            }
        };
        return optionsSetting;
    }

    //gettle
    public ArrayList<String> getTableNames() {
        return tableNames;
    }

    public HashMap<String, TableConfig> getTableOptions() {
        return tableOptions;
    }

    protected String toTableName(String categoryName) {
        String tableName = "";
        char[] chs = categoryName.toCharArray();
        int chsLength = chs.length;
        boolean hasSpace = false;
        for (int i = 0; i < chsLength; i++) {
            if (chs[i] == ' ') {
                hasSpace = true;
                continue;
            }
            if (i == 0 || hasSpace) {
                if (96 < chs[i] && chs[i] < 123) {
                    tableName += (char) (chs[i] - 32);
                }
                hasSpace = false;
            } else {
                tableName += chs[i];
            }

        }
        return tableName;
    }

    //get table names
    protected void retrieveTableNames(int categoryID) {
        if (em == null) {
            return;
        }
        Category cat = em.find(Category.class, categoryID);
        retriveTableNames(cat);
    }

    protected void retriveTableNames(Category cat) {
        ArrayList<Category> cats = new ArrayList(cat.getCategoryCollection());
        if (cats.isEmpty()) {
            String tableName = toTableName(cat.getCategoryName());
            String sqlCheckTable = "SELECT * FROM " + tableName;
            try {
                Query q = em.createNativeQuery(sqlCheckTable);
                q.getResultList();
                tableNames.add(tableName);
                categories.add(cat.getCategoryID() + "");
            } catch (Exception e) {
            }
        } else {
            for (Category tmp : cats) {
                retriveTableNames(tmp);
            }
        }
    }

    public boolean checkTable(Category cat) {
        if (em == null) {
            return false;
        }
        String tableName = toTableName(cat.getCategoryName());
        String sqlCheckTable = "SELECT * FROM " + tableName;
        try {
            Query q = em.createNativeQuery(sqlCheckTable);
            q.getResultList();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean checkTable(int categoryID){
        if (em == null) {
            return false;
        }
        Category cat = em.find(Category.class, categoryID);
        return checkTable(cat);
    }

    //get table name and filter any used params
    protected void retrieveUsefulParams(Map<String, String[]> params) {
        if (params == null || params.isEmpty()) {
            return;
        }
        HashMap<String, String[]> result = new HashMap();
//        boolean hasFilterParam = false;
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();
            if (paramName.contains("categoryID")) {
                int categoryID = Integer.parseInt(paramValues[0]);
                retrieveTableNames(categoryID);
//                hasFilterParam = true;
            } else {
//            if (hasFilterParam) {
//                hasFilterParam = false;
//                continue;
//            }
//            if (paramValues.length != 0) {
                usefulParams.put(paramName, paramValues);
            }
        }

        //tableName adjustment
        if ((tableName == null || tableName.isEmpty()) && !tableNames.isEmpty()) {
            tableName = tableNames.get(0);
        }
    }

    //generate sql of parameters
    protected void generateSQLConditions() {
        if (usefulParams == null || usefulParams.isEmpty()) {
            return;
        }
        sqlConditions = " WHERE ";
        String sqlConditions2 = "";

        for (Map.Entry<String, String[]> param : usefulParams.entrySet()) {
            String paramName = param.getKey();
            String[] paramValues = param.getValue();
            if (!sqlConditions.equals(" WHERE ")) {
                sqlConditions += " AND ";
            }
            sqlConditions2 = "(";
            for (String paramValue : paramValues) {
                if (!sqlConditions2.equals("(")) {
                    sqlConditions2 += " OR ";
                }
                sqlConditions2 += "(LOWER(" + paramName.toLowerCase() + ")";
                if (paramValue.contains(paramValueSplitString)) {
                    String[] keys = paramValue.split(paramValueSplitString);
                    sqlConditions2 += " BETWEEN '" + keys[0] + "' AND '"
                            + keys[1] + "'";
                } else {
                    sqlConditions2 += " = '" + paramValue.toLowerCase() + "'";
                }
                sqlConditions2 += ")";
            }
            sqlConditions2 += ")";
            sqlConditions += sqlConditions2;
        }

        String paramName;
        String[] paramValues;
        String sqlMakeConditions = "";
        String sqlPart1 = "";
        String sqlPart2 = "";
        String[][][][] opts = tableOptions.get(tableName)
                .getFilterOptions();
        String[] tables;
        String[] keys;
        String[] keys2;
        String[] columns;
        boolean hasColumn = false;

        sqlMakeConditions = String.format("SELECT a.* FROM %s a", tableName);

        for (Map.Entry<String, String[]> param : usefulParams.entrySet()) {
            paramName = param.getKey();
            paramValues = param.getValue();
            for (int i = 1; i < opts.length; i++) {
                for (String[][] fCaseSets : opts[i]) {
                    tables = fCaseSets[0];
                    keys = fCaseSets[1];
                    if (fCaseSets.length == 2) {
                        columns = fCaseSets[1];
                    } else {
                        columns = fCaseSets[2];
                    }
                    for (String column : columns) {
                        if (column.equals(paramName)) {
                            hasColumn = true;
                        }
                    }
                    if (hasColumn) {
                        switch (i) {
                            case 1:
                                sqlMakeConditions += String.format(" INNER JOIN %s"
                                        + " ON a.%sID = %s.merchandiseID"
                                        + " AND a.categoryID = %s.categoryID"
                                        + " INNER JOIN brand ON brand.brandID ="
                                        + " a.brandID",
                                        tables[0], tableName, tables[0], tables[0]);
                                break;

                            case 2:
                                keys2 = fCaseSets[2];
                                columns = fCaseSets[3];
                                sqlMakeConditions += String.format(" INNER JOIN %s"
                                        + " ON a.%s = %s.%s", tables[0], keys[0],
                                        tables[0], keys2[0]);
                                for (int j = 1; j < keys.length; j++) {
                                    sqlMakeConditions += String.format(
                                            " AND %s.%s = %s.%s",
                                            tables[0], keys[j], tables[j], keys2[j]);
                                }
                                break;

                            case 3:
                                sqlMakeConditions += String.format(" INNER JOIN %s"
                                        + " ON a.%s = %s.%s INNER JOIN %s"
                                        + " ON %s.%s = %s.%s",
                                        tables[0], keys[0], tables[0], keys[0],
                                        tables[1], tables[0], keys[1], tables[1], keys[1]);
                        }
                    }
                    hasColumn = false;
                }
            }
        }
        sqlConditions = sqlMakeConditions + sqlConditions + " GROUP BY a."
                + tableName + "ID";
        this.usefulParams.remove("storeID");
    }

    //conditions filter
    protected void filterWithConditions() {
        //check
        if (tx == null || em == null) {
            return;
        }

        //create tmp table
        String sqlCreateTempResultX = "CREATE TEMPORARY TABLE IF NOT EXISTS"
                + " search_%s LIKE %s;";
        String sqlCreateTempResult = String.format(sqlCreateTempResultX,
                tableName, tableName);
        String sqlCreateTempIDResultX = "CREATE TEMPORARY TABLE IF NOT EXISTS"
                + " search_%s_tmp ( %sID INT(13) NOT NULL, PRIMARY KEY(%sID));";
        String sqlCreateTempIDResult = String.format(sqlCreateTempIDResultX,
                tableName, tableName, tableName);
        String sqlClearResultX = "DELETE FROM search_%s;";
        String sqlClearResult = String.format(sqlClearResultX, tableName);
        String sqlClearTempIDX = "DELETE FROM search_%s_tmp;";
        String sqlClearTempID = String.format(sqlClearTempIDX, tableName);
        tx.begin();
        em.createNativeQuery(sqlCreateTempResult).executeUpdate();
        em.createNativeQuery(sqlCreateTempIDResult).executeUpdate();
        em.createNativeQuery(sqlClearResult).executeUpdate();
        em.createNativeQuery(sqlClearTempID).executeUpdate();
        tx.commit();

        //filter record and insert into tmp table
        tx.begin();
        String sqlFilterRecord = "INSERT INTO search_" + tableName
                + " " + sqlConditions + ";";

        System.out.println(sqlCreateTempResult);
        System.out.println(sqlCreateTempIDResult);
        System.out.println(sqlClearResult);
        System.out.println(sqlClearTempID);
        System.out.println(sqlFilterRecord);

        em.createNativeQuery(sqlFilterRecord).executeUpdate();
        tx.commit();
    }

    //search with keywords in single table
    protected void searchWithOneKeyword(String singleKeyword) {
        //check
        if (tx == null || em == null) {
            return;
        }
        if (tableName == null || tableName.isEmpty() || singleKeyword.equals("")) {
            return;
        }

        char ch;
        String sqlClearResult = "DELETE FROM search_" + tableName;;
        //get search optional columns
        String[][][][] searchOptions = tableOptions.get(tableName)
                .getSearchOptions();

        for (int i = 0; i < searchOptions.length; i++) {
            String[][][] sCases = searchOptions[i];
            if (sCases == null) {
                continue;
            }
            for (int j = 0; j < sCases.length; j++) {
                String[][] sCaseSets = sCases[j];
                if (sCaseSets.length == 0) {
                    continue;
                }
                String sqlCase = "";
                String sqlKeyPart = "";
                String sqlKeyPart2 = "";
                String sqlCaseConditions = "";
                String[] searchTables;
                String[] keys;
                String[] keys2;
                String[] columns;
                String searchTable;
                switch (i) {
                    //Self-Table Search
                    case 0:
                        sqlCase = String.format("SELECT a.%sID FROM search_%s a"
                                + " INNER JOIN merchandise b"
                                + " ON a.%sID = b.merchandiseID AND a.categoryID"
                                + " = b.categoryID INNER JOIN brand c"
                                + " ON b.brandID = c.brandID WHERE ",
                                tableName, tableName, tableName);
                        sqlCaseConditions = "";
                        for (String searchColumn : sCaseSets[0]) {
                            if (!sqlCaseConditions.equals("")) {
                                sqlCaseConditions += " OR ";
                            }
                            sqlCaseConditions += String.format("LOWER(%s) LIKE '%%%s%%'",
                                    searchColumn, singleKeyword);
                        }
                        sqlCase += sqlCaseConditions;
                        sqlCase += ";";
                        break;

                    case 1:
                        searchTables = sCaseSets[0];
                        columns = sCaseSets[1];
                        sqlCase = String.format("SELECT a.%sID FROM search_%s a"
                                + " INNER JOIN %s b ON a.%sID = b.merchandiseID"
                                + " AND a.categoryID = b.categoryID WHERE ",
                                tableName, tableName, searchTables[0], tableName,
                                searchTables[0], searchTables[0]);
                        sqlCaseConditions = "";
                        for (String searchColumn : columns) {
                            if (!sqlCaseConditions.equals("")) {
                                sqlCaseConditions += " OR ";
                            }
                            sqlCaseConditions += String.format("LOWER(%s) LIKE '%%%s%%'",
                                    searchColumn, singleKeyword);
                        }
                        sqlCase += sqlCaseConditions;
                        sqlCase += ";";
                        break;

                    //One Associated Table Search
                    case 2:
                        searchTable = sCaseSets[0][0];
                        keys = sCaseSets[1];
                        keys2 = sCaseSets[2];
                        columns = sCaseSets[3];

                        sqlKeyPart = "";
                        char c = 'b';
                        for (int k = 1; k < keys.length; k++) {
                            sqlKeyPart += ", " + c + '.' + keys[k];
                            c++;
                        }
                        sqlKeyPart2 = "";
                        c = 'a';
                        for (int k = 0; k < keys2.length; k++) {
                            sqlKeyPart += ", " + c + '.' + keys[k];
                            c++;
                        }
                        sqlCase = String.format("SELECT a.%sID FROM search_%s a"
                                + " WHERE (a.%sID%s) IN (SELECT b.%s FROM %s b"
                                + " WHERE ",
                                tableName, tableName, tableName, sqlKeyPart,
                                sqlKeyPart2, searchTable);
                        sqlCaseConditions = "";
                        for (String searchColumn : columns) {
                            if (!sqlCaseConditions.equals("")) {
                                sqlCaseConditions += " OR ";
                            }
                            sqlCaseConditions += String.format("LOWER(%s) LIKE '%%%s%%'",
                                    searchColumn, singleKeyword);
                        }
                        sqlCase += sqlCaseConditions;
                        sqlCase += ");";
                        break;

                    //Two or More Associated Table Search -- With Simple Foreign Key
                    case 3:
                        searchTables = sCaseSets[0];
                        keys = sCaseSets[1];
                        columns = sCaseSets[2];
                        sqlCase = String.format("SELECT a.%sID FROM search_%s a"
                                + " WHERE a.%sID IN (SELECT b.%s FROM %s b",
                                tableName, tableName, tableName, keys[0],
                                searchTables[0]);
                        ch = 'b';
                        for (int k = 1; k < searchTables.length; k++) {
                            ch += 1;
                            sqlCase += String.format(" INNER JOIN %s %s"
                                    + " ON b.%s = %s.%s", searchTables[k],
                                    ch, keys[k], ch, keys[k]);
                        }
                        sqlCase += " WHERE ";
                        sqlCaseConditions = "";
                        for (String searchColumn : columns) {
                            if (!sqlCaseConditions.equals("")) {
                                sqlCaseConditions += " OR ";
                            }
                            sqlCaseConditions += String.format("LOWER(%s) LIKE '%%%s%%'",
                                    searchColumn, singleKeyword);
                        }
                        sqlCase += sqlCaseConditions;
                        sqlCase += ");";
                }
                tx.begin();
                sqlCase = "INSERT IGNORE INTO search_" + tableName + "_tmp " + sqlCase;
                em.createNativeQuery(sqlCase).executeUpdate();
                em.createNativeQuery(sqlClearResult + ";").executeUpdate();
                System.out.println(sqlCase);

                tx.commit();
            }
        }
        tx.begin();
        String sqlUpdateResult = String.format("INSERT INTO search_%s SELECT a.* FROM"
                + " %s a INNER JOIN search_%s_tmp b ON a.%sID = b.%sID;",
                tableName, tableName, tableName, tableName, tableName);
        em.createNativeQuery(sqlUpdateResult).executeUpdate();
        String sqlClearCache = sqlClearResult + "_tmp;";
        em.createNativeQuery(sqlClearCache).executeUpdate();
        System.out.println(sqlClearResult + ";");
        System.out.println(sqlUpdateResult);
        System.out.println(sqlClearCache);
        tx.commit();
    }

    //function used
    protected void splitKeywords(String keywords) {
        keywords = keywords.toLowerCase();
        if (this.keywords == null) {
            return;
        }
        String keyword = "";
        char[] chs = keywords.toCharArray();
        int chsLength = chs.length;
        boolean hasSpace = false;
        for (int i = 0; i < chsLength; i++) {
            if (chs[i] == ' ') {
                hasSpace = true;
                continue;
            }
            if (hasSpace) {
                if (keyword.length() != 0) {
                    this.keywords.add(keyword);
                }
                keyword = "";
                hasSpace = false;
            }
            keyword += chs[i];
        }
        if (keyword.length() != 0) {
            this.keywords.add(keyword);
        }
    }

    protected void search(String keywords) {
        if (keywords == null || keywords.length() == 0) {
            return;
        }
        splitKeywords(keywords);
        if (keywords.isEmpty()) {
            return;
        }
        for (String keyword : this.keywords) {
            searchWithOneKeyword(keyword);
        }
    }

    //select result
    public <T> ArrayList<T> selectSingleResult(Class T) {
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        if (em == null) {
            return null;
        }
        ArrayList<T> result = null;
        String sqlGetResult = "SELECT * FROM search_" + tableName;
        Query q = em.createNativeQuery(sqlGetResult, T);
        System.out.println(sqlGetResult);
        System.out.println(T.getName());
        result = new ArrayList(q.getResultList());
        System.out.println(sqlGetResult);
        System.out.println(T + ": " + result.size());
        return result;
    }

    public <T> ArrayList selectSingleResult(String entityName) throws ClassNotFoundException {
        if (em == null) {
            return null;
        }
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        if (entityName == null || entityName.isEmpty()) {
            return null;
        }
        if (!entityName.startsWith("bean.")) {
            entityName = "bean." + entityName;
        }
        Class T = Class.forName(entityName);
        ArrayList<T> result = this.selectSingleResult(T);
        return result;
    }

    public ArrayList selectSingleResult() throws ClassNotFoundException {
        if (em == null) {
            return null;
        }
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        return selectSingleResult(tableName);
    }

    //generate new filters
    public ArrayList<Brand> generateNewBrandFilter() {
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        ArrayList<Brand> brands = null;

        if (em == null) {
            return brands;
        }

        String sqlGetBrand = "SELECT b.* FROM brand b"
                + " WHERE b.brandID IN"
                + " (SELECT DISTINCT brandID"
                + " FROM search_" + tableName + " mo "
                + " INNER JOIN merchandise m"
                + " ON mo." + tableName + "ID = m.merchandiseID"
                + " AND mo.categoryID = m.categoryID)";
        Query q = em.createNativeQuery(sqlGetBrand, Brand.class);
        brands = new ArrayList(q.getResultList());

        return brands;
    }

    public HashMap<String, ArrayList<String>> generateNewFilters() {
        //check
        if (em == null) {
            return null;
        }
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        HashMap<String, ArrayList<String>> filters = new HashMap();
        //get filter options
        String[][][][] filterOptions = tableOptions.get(tableName).getFilterOptions();

        String sqlChk = "SELECT DISTINCT " + tableName + "ID FROM"
                + " search_" + tableName;

        ArrayList<String> r = toStringArrayList(
                em.createNativeQuery(sqlChk).getResultList()
        );
        if (r.size() < 2) {
            return null;
        }

        Query q;

        String sqlGetRows = String.format("SET @rows := (SELECT count(*) FROM search_%s);", tableName);
        q = em.createNativeQuery(sqlGetRows);
        tx.begin();
        q.executeUpdate();
        tx.commit();

        for (int i = 0; i < filterOptions.length; i++) {
            String[][][] sCase = filterOptions[i];
            String[] keys;
            ArrayList<String> filterValues;

            for (int j = 0; j < sCase.length; j++) {
                String[][] sCaseSet = sCase[j];
                String[] filterTables = sCaseSet[0];
                String sqlCase = "";
                switch (i) {
                    //get filter values from current table
                    case 0:
                        for (String filterName : sCaseSet[0]) {
                            sqlCase = String.format("SELECT vx.%s FROM"
                                    + " (SELECT %s, count(*) FROM"
                                    + " search_%s s INNER JOIN Merchandise m ON"
                                    + " m.merchandiseID = s.%sID AND m.categoryID"
                                    + " = s.categoryID INNER JOIN Brand b ON"
                                    + " b.brandID = m.brandID"
                                    + " GROUP BY %s"
                                    + " HAVING count(*) < @rows) vx;", filterName,
                                    filterName, tableName, tableName, filterName);
                            q = em.createNativeQuery(sqlCase);
                            filterValues = toStringArrayList(q.getResultList());
                            if (filterValues.size() > 1) {
                                filters.put(filterName, filterValues);
                            }
                        }
                        break;

                    case 1:
                        for (String filterName : sCaseSet[1]) {

                            sqlCase = String.format("SELECT vx.%s FROM"
                                    + " (SELECT a.%s, count(*) FROM %s a,"
                                    + " (SELECT %sID, categoryID  FROM search_%s"
                                    + " GROUP BY %sID, categoryID) b"
                                    + " WHERE b.%sID = a.merchandiseID"
                                    + " AND b.categoryID = a.categoryID"
                                    + " GROUP BY a.%s HAVING count(*) < @rows) vx;",
                                    filterName, filterName, filterTables[0], tableName,
                                    tableName, tableName, tableName, filterName);
                            q = em.createNativeQuery(sqlCase);
                            filterValues = toStringArrayList(q.getResultList());
                            if (filterValues.size() > 1) {
                                filters.put(filterName, filterValues);
                            }
                        }
                        break;

                    //get filter values from one associated table
                    case 2:
                        keys = sCaseSet[1];
                        for (String filterName : sCaseSet[2]) {
                            sqlCase = String.format("SELECT vx.%s FROM"
                                    + " (SELECT a.%s, count(*) FROM %s a,"
                                    + " (SELECT DISTINCT %s FROM search_%s) b"
                                    + " WHERE b.%s = a.%s"
                                    + " GROUP BY a.%s"
                                    + " HAVING count(*) < @rows) vx;", filterName,
                                    filterName, filterTables[0], keys[0], tableName,
                                    keys[0], keys[0], filterName);
                            q = em.createNativeQuery(sqlCase);
                            filterValues = toStringArrayList(q.getResultList());
                            if (filterValues.size() > 1) {
                                filters.put(filterName, filterValues);
                            }
                        }
                        break;

                    //get filter values from two associated table, include 
                    case 3:
                        keys = sCaseSet[1];
                        for (String filterName : sCaseSet[2]) {
                            sqlCase = String.format("SELECT vx.%s FROM"
                                    + " (SELECT a.%s, count(*) FROM %s a"
                                    + " INNER JOIN %s b ON a.%s = b.%s,"
                                    + " (SELECT DISTINCT %sID FROM search_%s) c"
                                    + " WHERE b.%sID = c.%s"
                                    + " GROUP BY a.%s"
                                    + " HAVING count(*) < @rows) vx;", filterName,
                                    filterName, filterTables[1], filterTables[0],
                                    keys[1], keys[1],
                                    tableName, tableName,
                                    tableName, keys[0], filterName);
                            q = em.createNativeQuery(sqlCase);
                            filterValues = toStringArrayList(q.getResultList());
                            if (filterValues.size() > 1) {
                                filters.put(filterName, filterValues);
                            }
                        }
                        break;
                }
            }
        }
        return filters;
    }

    public ArrayList<String> toStringArrayList(List list) {
        if (list == null) {
            return null;
        }
        ArrayList<String> result = new ArrayList();
        String val = "";
        for (Object obj : list) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                String s = d.longValue() == d ? "" + d.longValue() : "" + d;
                result.add(s);
            } else if (!String.valueOf(obj).equals("null")) {
                result.add(String.valueOf(obj));
            }
        }
        return result;
    }

    //main business method
    public HashMap<String, QueryResult> doSearch(Map<String, String[]> params,
            String keywords) throws ClassNotFoundException {

        if (params == null) {
            return null;
        }

        HashMap<String, QueryResult> results = new HashMap();
        QueryResult qr;

        retrieveUsefulParams(params);
        generateSQLConditions();
        ArrayList singleResult;
        HashMap<String, ArrayList<String>> filters;

        for (int i = 0; i < tableNames.size(); i++) {
            tableName = tableNames.get(i);
            qr = new QueryResult(tableName, categories.get(i));
            this.tableName = tableName;
            if (tableOptions.get(tableName) == null) {
                continue;
            }
            if (usefulParams == null || usefulParams.isEmpty()) {
                sqlConditions = "SELECT * FROM " + tableName;
            }
            filterWithConditions();
            search(keywords);
            singleResult = this.selectSingleResult(tableName);
            qr.setResult(singleResult);
            filters = this.generateNewFilters();
            qr.setFilters(filters);
            if (filters != null && filters.get("brandName") != null) {
                ArrayList<Brand> brandFilter = this.generateNewBrandFilter();
                qr.setBrandFilter(brandFilter);
            }
            results.put(tableName, qr);
        }
        return results;
    }

    //close
    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
