package com.geocraft.electrics.constants;

/**
 * 作者  zhouqin
 * 时间 2016/6/3.
 */
public class Constants {
    public final static String PERMITION_DEVICE_TYPE = "SCANNER_HARDWARE_SE955_72E";
    public final static String PERMITION_DEVICE_TYPE2 = "北斗移动终端";
    public final static String INTENT_DATA_NEW_TASK_RESULT = "intent_data_new_task_result";
    public final static String INTENT_DATA_TASK_MANAGER_RESULT = "intent_data_task_manager_result";
    public final static String INTENT_DATA_SET_GROUP_NAME = "intent_data_set_group_name";
    public final static String INTENT_DATA_SET_NAME = "intent_data_data_set_name";
    public final static String INTENT_DATA_GROUP_TO_DATA_NAME = "intent_data_group_to_data_name";
    public final static String INTENT_CALCULATEBOX_CODE = "intent_calculatebox_code";
    public final static String INTENT_CALCULATEBOX_NAME = "intent_calculatebox_name";
    public final static String INTENT_PHYSICSTOWER_TOEWENAME = "intent_physicstower_towername";
    public final static String INTENT_DATA_IS_CREATE_RECORD = "intent_data_is_create_record";
    public final static String INTENT_DATA_SET_KEY = "intent_data_data_set_key";
    public final static String INTENT_DATA_PHOTO_ITEM_POSITION = "intent_data_photo_item_position";
    public final static String INTENT_DATA_IS_REFRESH_DATA_SET_LIST = "intent_data_is_refresh_data_set_list";
    public final static String INTENT_DATA_SEARCHENTITRY = "intent_data_searchentitry";
    public final static String INTENT_DATA_IS_CURRENT_DATASET = "intent_data_is_current_data_set";
    public static final String TASK_TEMPLATE_FOLDER = "模板/";
    public static final String TASK_SUFFIX = ".tsk";
    public static final String DB_SUFFIX = ".db";
    public static final String PHOTO_SUFFIX = ".jpg";
    public static final String TASK_RESULT_FOLDER = "成果/";
    public static final String TASK_PHOTO_FOLDER = "照片/";
    public static final String TASK_CACHE_PATH = "Cache/";
    public static final String TEXT_PHOTO_SUFFIX = "照片";

    public static final String DEFAULT_TASK_NAME = "默认任务";
    public static final String DEFAULT_TASK_COLLECTOR = "采集人";
    public static final String DEFAULT_TASK_TEMPLATE_NAME = "默认模板";
    public static final String DEFAULT_TASK_DESCRIPTION = "默认任务";

    public static final String XML_TAG_TASK = "Task";
    public static final String XML_TAG_NAME = "Name";
    public static final String XML_TAG_COLLECTOR = "Collector";
    public static final String XML_TAG_TIME = "Time";
    public static final String XML_TAG_DESCRIPTION = "Description";
    public static final String XML_TAG_TEMPLATE = "Template";

    public static final String INTENT_DATA_SET_QUERY_FIELD = "INTENT_DATA_SET_QUERY_FIELD";
    public static final String INTENT_DATA_SET_QUERY_VALUE = "INTENT_DATA_SET_QUERY_VALUE";
    public static final String INTENT_DATA_SET_PARENT_KEY = "INTENT_DATA_SET_PARENT_KEY";
    public static final String INTENT_DATA_SEARCH_FIELD_VALUE = "INTENT_DATA_SEARCH_FIELD_VALUE";

    public static final int STATUS_CREATE = 0;
    public static final int STATUS_IMPORT = 1;
    public static final int STATUS_EDIT = 2;

    public static final String DATA_SET_STATE_ALIAS = "状态";
    public static final String DATA_SET_STATE_CREATE = "新建";
    public static final String DATA_SET_STATE_EDIT = "修改";
    public static final String DATA_SET_STATE_IMPORT = "导入";
    public static final String DATA_SET_STATE_DELETE = "删除";
    public static final String INTENT_DATA_IS_FROM_EDIT_PARENT = "INTENT_DATA_IS_FROM_EDIT_PARENT";


    public static final String SP_KEY_IS_DEFAULT_TASK_MODE = "SP_KEY_IS_DEFAULT_TASK_MODE";
    public static final String SCAN_ACTION = "SCAN_ACTION";
    public static final String SCAN_DATA = "SCAN_DATA";
    public static final String INTENT_DATA_PREFERENCE_JRDBS = "INTENT_DATA_PREFERENCE_JRDBS";
    public static final String INTENT_DATA_JRDBS_LONGITUDE = "INTENT_DATA_JRDBS_LONGITUDE";
    public static final String INTENT_DATA_JRDBS_LATITUDE = "INTENT_DATA_JRDBS_LATITUDE";

    public static final String INTENT_DATA_LINE_NAMES = "INTENT_DATA_LINE_NAMES";

    /**
     * 打开新建杆塔、地井、电源点界面，传递线路名
     */
    public static final String INTENT_DATA_LINE_NAMES_FOR_NEW_TOWEER = "INTENT_DATA_LINE_NAMES_FOR_NEW_TOWEER";

    /**
     * 传递线路id
     */
    public final static String INTENT_DATA_LINE_ID = "intent_data_line_id";
    /**
     * 传递杆桩/井id
     */
    public final static String INTENT_DATA_WELL_ID = "intent_data_well_id";

    /**
     * 传递杆桩/井类型
     */
    public final static String INTENT_DATA_WELL_TYPE = "intent_data_well_type";

    /**
     * BusinessConcatSpinner的添加按钮是否显示
     */
    public final static String SPKEY_SPINNER_SWITCH = "SPKEY_SPINNER_SWITCH";

}
