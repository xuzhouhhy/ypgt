package com.geocraft.electrics.entity;

import com.huace.log.logger.L;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hanpengfei on 2016/6/4.
 */
public class PropertyDictionay implements Cloneable {
    public ArrayList<String> menuList = new ArrayList<String>();
    public SearchEntiry searchEntiry = new SearchEntiry();
    public ThreeLevelMenu threeLevelMenu = new ThreeLevelMenu();
    public CombinationMenu combinationMenu = new CombinationMenu();

    public PropertyDictionay() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PropertyDictionay propertyDictionay = null;
        try {
            propertyDictionay = (PropertyDictionay) super.clone();
            propertyDictionay.menuList = (ArrayList<String>) menuList.clone();
            propertyDictionay.searchEntiry = (SearchEntiry) searchEntiry.clone();
            propertyDictionay.threeLevelMenu = (ThreeLevelMenu) threeLevelMenu.clone();
            propertyDictionay.combinationMenu = (CombinationMenu) combinationMenu.clone();
        } catch (CloneNotSupportedException e) {
            L.printException(e);
        }
        return propertyDictionay;
    }
    public interface OperateCode {
        int Type_BaseText = 0;
        int Type_MenuList = 1;
        int Type_CombinationMenu = 2;
        int Type_Search = 3;
        int Type_ThreeLevelMenu = 4;
        int Type_Scan = 5;
        int Type_Administrator = 6;
        int Type_Manager = 7;
        int Type_Address = 8;
        int Type_BusinessID = 9;
        int Type_dateTime = 10;
        int type_concat = 11;
    }

    static public class SearchEntiry implements Cloneable, Serializable {
        public String tableName;
        public String fieldName;
        public String filedValue;
        public boolean editable;
        public String uniqueFlag;

        public SearchEntiry() {
            tableName = "";
            fieldName = "";
            filedValue = "";
            editable = false;
            uniqueFlag = "";
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            SearchEntiry searchEntiry = null;
            try {
                searchEntiry = (SearchEntiry) super.clone();
            } catch (CloneNotSupportedException e) {
                L.printException(e);
            }
            return searchEntiry;
        }
    }

    static public class ThreeLevelMenu implements Cloneable {
        public String firstCaption;
        public String secondCaption;
        public String thirdCaption;
        public String fileName;
        public Map<String, Map<String, ArrayList<String>>> menu = new HashMap<>();

        public ThreeLevelMenu() {
            firstCaption = "";
            secondCaption = "";
            thirdCaption = "";
            fileName = "";
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            ThreeLevelMenu threeLevelMenu = null;
            try {
                threeLevelMenu = (ThreeLevelMenu) super.clone();

                Map<String, Map<String, ArrayList<String>>> mapTemp = new HashMap<>();
                Iterator it = menu.keySet().iterator();
                while (it.hasNext()) {
                    String key;
                    Map<String, ArrayList<String>> value;
                    key = it.next().toString();
                    value = menu.get(key);
                    mapTemp.put(key, value);
                }

                threeLevelMenu.menu = mapTemp;
            } catch (CloneNotSupportedException e) {
                L.printException(e);
            }
            return threeLevelMenu;
        }
    }

    static public class CombinationMenu implements Cloneable {
        public String firstCaption = "";
        public String secondCaption = "";
        public Map<String, ArrayList<String>> combinationMenu = new HashMap<String, ArrayList<String>>();

        public CombinationMenu() {
            firstCaption = "";
            secondCaption = "";
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            CombinationMenu menu = null;
            try {
                menu = (CombinationMenu) super.clone();

                Map<String, ArrayList<String>> mapTemp = new HashMap<>();
                Iterator it = combinationMenu.keySet().iterator();
                while (it.hasNext()) {
                    String key;
                    ArrayList<String> value;
                    key = it.next().toString();
                    value = combinationMenu.get(key);
                    mapTemp.put(key, value);
                }

                menu.combinationMenu = mapTemp;
            } catch (CloneNotSupportedException e) {
                L.printException(e);
            }
            return menu;
        }
    }
}
