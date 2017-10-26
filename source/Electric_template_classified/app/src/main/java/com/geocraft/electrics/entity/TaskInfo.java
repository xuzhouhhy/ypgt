package com.geocraft.electrics.entity;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
public class TaskInfo {
    private String mTaskName;
    private String mCollector;
    private String mCollectTime;
    private String mTemplateName;
    private String mDescription;
    private boolean mIsExpand;

    public TaskInfo() {
        mTaskName = "";
        mCollector = "";
        mCollectTime = "";
        mTemplateName = "";
        mDescription = "";
        mIsExpand =false;
    }

    public TaskInfo(String taskName, String collector, String collectTime, String templateName, String description) {
        mTaskName = taskName;
        mCollector = collector;
        mCollectTime = collectTime;
        mTemplateName = templateName;
        mDescription = description;
        mIsExpand =false;
    }

    public boolean isExpand() {
        return mIsExpand;
    }

    public void setExpand(boolean expand) {
        mIsExpand = expand;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public String getCollector() {
        return mCollector;
    }

    public void setCollector(String collector) {
        mCollector = collector;
    }

    public String getCollectTime() {
        return mCollectTime;
    }

    public void setCollectTime(String collectTime) {
        mCollectTime = collectTime;
    }

    public String getTemplateName() {
        return mTemplateName;
    }

    public void setTemplateName(String templateName) {
        mTemplateName = templateName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
