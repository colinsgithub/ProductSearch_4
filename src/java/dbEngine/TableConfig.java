/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbEngine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class TableConfig {

    // {"search": sCase{ sCaseSet{[Case1]values{}} } }
    // {"search": sCase{ sCaseSet{[Case2]keys{}, values{} } }
    // {"search": sCase{ sCaseSet{[Case3]table{}, keys{}, values{}} } }
    // {"search": sCase{ sCaseSet{[Case4]table{}, keys{}, values{}} } }
    private HashMap<String, ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> options;
    private final String tableName;

    public TableConfig(String tableName) {
        this.tableName = tableName;
        options = new HashMap();
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> searchOptions = new ArrayList();
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> filterOptions = new ArrayList();
        options.put("search", searchOptions);
        options.put("filter", filterOptions);
        for (int i = 0; i < 4; i++) {
            searchOptions.add(new ArrayList<ArrayList<ArrayList<String>>>());
            filterOptions.add(new ArrayList<ArrayList<ArrayList<String>>>());
        }
    }

    public String getTableName() {
        return tableName;
    }

    protected String[][][][] toStringArray(
            ArrayList<ArrayList<ArrayList<ArrayList<String>>>> target) {
        String[][][][] sCases = new String[4][][][];
        ArrayList<ArrayList<ArrayList<String>>> caseSets;
        String[][][] sCaseSets;
        ArrayList<ArrayList<String>> segments;
        String[][] sSegments;
        ArrayList<String> columns;
        String[] sColumns;
        for (int i = 0; i < target.size(); i++) {
            caseSets = target.get(i);
            sCaseSets = new String[caseSets.size()][][];
            for (int j = 0; j < caseSets.size(); j++) {
                segments = caseSets.get(j);
                sSegments = new String[segments.size()][];
                for (int k = 0; k < segments.size(); k++) {
                    columns = segments.get(k);
                    sColumns = columns.toArray(new String[]{});
                    sSegments[k] = sColumns;
                }
                sCaseSets[j] = sSegments;
            }
            sCases[i] = sCaseSets;
        }
        return sCases;
    }

    public String[][][][] getSearchOptions() {
        return this.toStringArray(options.get("search"));
    }

    public String[][][][] getFilterOptions() {
        return this.toStringArray(options.get("filter"));
    }

    protected void addValues(
            ArrayList<ArrayList<ArrayList<ArrayList<String>>>> target,
            String[][][] opts) {

        if (target == null || opts == null || opts.length == 0) {
            return;
        }

        ArrayList<ArrayList<ArrayList<String>>> caseSets = null;
        ArrayList<ArrayList<String>> segments = null;
        ArrayList<String> columns = null;

        for (String[][] tSegments : opts) {
            switch (tSegments.length) {
                case 1:
                    caseSets = target.get(0);
                    if (caseSets.isEmpty()) {
                        segments = new ArrayList();
                        caseSets.add(segments);
                        columns = new ArrayList();
                        segments.add(columns);
                    } else {
                        segments = caseSets.get(0);
                        columns = segments.get(0);
                    }
                    for (String tColumnValue : tSegments[0]) {
                        if (!columns.contains(tColumnValue)) {
                            columns.add(tColumnValue);
                        }
                    }
                    break;

                case 2:
                    if (tSegments[0] == null || tSegments[1] == null
                            || tSegments[0].length == 0 || tSegments[1].length == 0) {
                        continue;
                    }

                    if (tSegments[0].length < 1) {
                        continue;
                    }

                    caseSets = target.get(1);
                    if (caseSets.isEmpty()) {
                        segments = new ArrayList();
                        caseSets.add(segments);
                        for (int i = 0; i < 2; i++) {
                            columns = new ArrayList();
                            segments.add(columns);
                        }
                    } else {
                        for (ArrayList<ArrayList<String>> segmentChk : caseSets) {
                            if (segmentChk.get(0).contains(tSegments[0][0])) {
                                segments = segmentChk;
                                break;
                            }
                        }
                        if (segments == null) {
                            segments = new ArrayList();
                            caseSets.add(segments);
                            for (int i = 0; i < 2; i++) {
                                columns = new ArrayList();
                                segments.add(columns);
                            }
                        }
                    }
                    for (int i = 0; i < tSegments.length; i++) {
                        columns = segments.get(i);
                        for (int j = 0; j < tSegments[i].length; j++) {
                            if (!columns.contains(tSegments[i][j])) {
                                columns.add(tSegments[i][j]);
                            }
                        }
                    }
                    break;

                case 3:
                    if (tSegments[0] == null || tSegments[1] == null
                            || tSegments[2] == null || tSegments[0].length == 0
                            || tSegments[1].length == 0 || tSegments[2].length == 0) {
                        continue;
                    }

                    if (tSegments[0].length < 1) {
                        continue;
                    }

                    caseSets = target.get(1 + tSegments[0].length);
                    if (caseSets.isEmpty()) {
                        segments = new ArrayList();
                        caseSets.add(segments);
                        for (int i = 0; i < 3; i++) {
                            columns = new ArrayList();
                            segments.add(columns);
                        }
                    } else {
                        segments = null;
                        for (ArrayList<ArrayList<String>> segmentChk : caseSets) {
                            if (segmentChk.get(0).contains(tSegments[0][0])) {
                                segments = segmentChk;
                                break;
                            }
                        }
                        if (segments == null) {
                            segments = new ArrayList();
                            caseSets.add(segments);
                            for (int i = 0; i < 3; i++) {
                                columns = new ArrayList();
                                segments.add(columns);
                            }
                        }
                    }

                    for (int i = 0; i < tSegments.length; i++) {
                        columns = segments.get(i);
                        for (int j = 0; j < tSegments[i].length; j++) {
                            if (!columns.contains(tSegments[i][j])) {
                                columns.add(tSegments[i][j]);
                            }
                        }
                    }
            }
        }

    }

    public boolean setSearchOptions(String[][][][] searchOptions) {
        if (searchOptions == null || searchOptions.length == 0) {
            return false;
        }
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> tmp = options.get("search");
        for (String[][][] opts : searchOptions) {
            this.addValues(tmp, opts);
        }
        return true;
    }

    public boolean setFilterOptions(String[][][][] filterOptions) {
        if (filterOptions == null || filterOptions.length == 0) {
            return false;
        }
        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> tmp = options.get("filter");
        for (String[][][] opts : filterOptions) {
            this.addValues(tmp, opts);
        }
        return true;
    }

    public void createOneSearch(String[] tables, String[] keys, String[] columns) {
        if (columns.length == 0) {
            return;
        }
        if ((tables == null || tables.length == 0) && (keys == null || keys.length == 0)) {
            String[][] searchOptions = new String[][]{columns};
            this.addValues(options.get("search"), new String[][][]{searchOptions});
        } else {
            String[][] searchOptions = new String[][]{tables, keys, columns};
            this.addValues(options.get("search"), new String[][][]{searchOptions});
        }
    }

    public void createOneFilter(String[] tables, String[] keys, String[] columns) {
        if (columns.length == 0) {
            return;
        }
        if ((tables == null || tables.length == 0) && (keys == null || keys.length == 0)) {
            String[][] filterOptions = new String[][]{columns};
            this.addValues(options.get("search"), new String[][][]{filterOptions});
        }
        String[][] filterOptions = new String[][]{tables, keys, columns};
        this.addValues(options.get("filter"), new String[][][]{filterOptions});
    }
}
