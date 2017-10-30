package com.geocraft.electrics.manager;

import android.util.Xml;

import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.utils.ElectricFileUtils;
import com.geocraft.electrics.utils.ElectricXmlUtils;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/5.
 */
@EBean(scope = EBean.Scope.Singleton)
public class TaskManager {

    @Bean
    DbManager mDbManager;
    private DataSource mDataSource;
    private TaskInfo mTaskInfo;

    public TaskInfo getTaskInfo() {
        return mTaskInfo;
    }

    public DataSource getDataSource() {
        return mDataSource;
    }

    public boolean checkTaskExist(TaskInfo taskInfo) {
        if (taskInfo == null) {
            return false;
        }
        if (FileUtils.existFile(ConstPath.getTaskRootFolder() + taskInfo.getTaskName())) {
            return true;
        }
        return false;
    }

    public boolean openOrCreateDefaultTask() {
        TaskInfo defaultTaskInfo = getDefaultTask();
        if (checkTaskExist(defaultTaskInfo)) {
            try {
                return openTask(defaultTaskInfo.getTaskName());
            } catch (TaskExistException e) {
                L.printException(e);
                return false;
            }
        } else {
            try {
                return createTask(defaultTaskInfo);
            } catch (TaskExistException e) {
                L.printException(e);
                return false;
            }
        }
    }

    public boolean createTask(TaskInfo taskInfo) throws TaskExistException {
        if (!createTaskFolder(taskInfo)) {
            throw new TaskExistException();
        }
        if (!createTemplateFolder(taskInfo)) {
            deleteFolderTask(taskInfo);
            return false;
        }
        if (!createDataBase(taskInfo)) {
            deleteFolderTask(taskInfo);
            return false;
        }
        createPhotoFolder(taskInfo);
        createResultFolder(taskInfo);
        if (!createTaskFile(taskInfo)) {
            deleteFolderTask(taskInfo);
            mDataSource = null;
            return false;
        }
        mTaskInfo = taskInfo;
        return true;
    }

    public boolean openTask(String taskName) throws TaskExistException {
        String taskPath = ConstPath.getTaskRootFolder() + taskName;
        if (!FileUtils.existFile(taskPath)) {
            throw new TaskExistException();
        }
        if (!initTaskInfo(taskPath, taskName)) {
            return false;
        }
        if (!initDataSource(taskPath, taskName)) {
            return false;
        }
        String dbPath = taskPath + File.separator + taskName + Constants.DB_SUFFIX;
        if (!mDbManager.openOrCreateDatabases(dbPath)) {
            return false;
        }
        return true;
    }

    public void closeCurrentTask() {
        mDbManager.close();
        mTaskInfo = null;
        mDataSource = null;
    }

    private TaskInfo getDefaultTask() {
        TaskInfo defaultTaskInfo = new TaskInfo();
        defaultTaskInfo.setTaskName(Constants.DEFAULT_TASK_NAME);
        defaultTaskInfo.setCollector(Constants.DEFAULT_TASK_COLLECTOR);
        defaultTaskInfo.setCollectTime(Tools.GetCurrentFormatTime1());
        defaultTaskInfo.setTemplateName(Constants.DEFAULT_TASK_TEMPLATE_NAME);
        defaultTaskInfo.setDescription(Constants.DEFAULT_TASK_DESCRIPTION);
        return defaultTaskInfo;
    }

    private boolean createTaskFolder(TaskInfo taskInfo) {
        File fileDirFile = new File(ConstPath.getTaskRootFolder() + taskInfo.getTaskName());
        if (fileDirFile.exists()) {
            return false;
        }
        return fileDirFile.mkdirs();
    }

    private boolean createTemplateFolder(TaskInfo taskInfo) {
        String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
                + Constants.TASK_TEMPLATE_FOLDER;
        File fileDirFile = new File(fileName);
        if (!fileDirFile.exists()) {
            fileDirFile.mkdirs();
        }
        String templateSourceFolderPath = ConstPath.getTemplateRootFolder() + taskInfo.getTemplateName();
        try {
            FileUtils.copyFolder(templateSourceFolderPath, fileName);
        } catch (IOException e) {
            L.printException(e);
            return false;
        }
        return true;
    }

    private boolean createDataBase(TaskInfo taskInfo) {
        String taskDbName = taskInfo.getTaskName() + Constants.DB_SUFFIX;
        String taskDbParentPath = ConstPath.getTaskRootFolder() + taskInfo.getTaskName();
        String taskDbPath = taskDbParentPath + File.separator + taskDbName;
        if (!mDbManager.openOrCreateDatabases(taskDbPath)) {
            return false;
        }
        initDataSource(taskDbParentPath, taskInfo.getTaskName());
        if (!mDbManager.crateTableByDataSource(mDataSource)) {
            mDbManager.close();
            return false;
        }
        return true;
    }

    private boolean createTaskFile(TaskInfo taskInfo) {
        String taskFileName = taskInfo.getTaskName() + Constants.TASK_SUFFIX;
        String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
                + taskFileName;
        File fileDirFile = new File(fileName);
        if (!fileDirFile.exists()) {
            try {
                fileDirFile.createNewFile();
            } catch (IOException e) {
                L.printException(e);
                return false;
            }
            try {
                OutputStream task = new FileOutputStream(fileDirFile);
                writeTaskFile(task, taskInfo);
            } catch (Exception e) {
                L.printException(e);
                return false;
            }


        }
        return true;
    }

    private void writeTaskFile(OutputStream out, TaskInfo taskInfo) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out, "UTF-8");
        serializer.startDocument("UTF-8", true);


        serializer.startTag(null, Constants.XML_TAG_TASK);

        serializer.startTag(null, Constants.XML_TAG_NAME);
        serializer.text(taskInfo.getTaskName());
        serializer.endTag(null, Constants.XML_TAG_NAME);

        serializer.startTag(null, Constants.XML_TAG_COLLECTOR);
        serializer.text(taskInfo.getCollector());
        serializer.endTag(null, Constants.XML_TAG_COLLECTOR);

        serializer.startTag(null, Constants.XML_TAG_TIME);
        serializer.text(taskInfo.getCollectTime());
        serializer.endTag(null, Constants.XML_TAG_TIME);

        serializer.startTag(null, Constants.XML_TAG_TEMPLATE);
        serializer.text(taskInfo.getTemplateName());
        serializer.endTag(null, Constants.XML_TAG_TEMPLATE);

        serializer.startTag(null, Constants.XML_TAG_DESCRIPTION);
        serializer.text(taskInfo.getDescription());
        serializer.endTag(null, Constants.XML_TAG_DESCRIPTION);

        serializer.endTag(null, Constants.XML_TAG_TASK);

        serializer.endDocument();
        out.flush();
        out.close();
    }

    private void createPhotoFolder(TaskInfo taskInfo) {
        String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
                + Constants.TASK_PHOTO_FOLDER;
        File fileDirFile = new File(fileName);
        if (!fileDirFile.exists()) {
            fileDirFile.mkdirs();
        }
        String cachePath = fileName + Constants.TASK_CACHE_PATH;
        File cacheFile = new File(cachePath);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
    }

    //TODO:扩充
    private void createResultFolder(TaskInfo taskInfo) {
        String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
                + Constants.TASK_RESULT_FOLDER;
        File fileDirFile = new File(fileName);
        if (!fileDirFile.exists()) {
            fileDirFile.mkdirs();
        }
    }

    private void deleteFolderTask(TaskInfo taskInfo) {
        FileUtils.delFolder(ConstPath.getTaskRootFolder() + taskInfo.getTaskName());
    }

    private boolean initTaskInfo(String taskPath, String taskName) {
        String taskFileName = taskName + Constants.TASK_SUFFIX;
        String taskFilePath = taskPath + File.separator + taskFileName;
        mTaskInfo = null;
        mTaskInfo = ElectricXmlUtils.getTaskInfoByTsk(taskFilePath);
        if (mTaskInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean initDataSource(String taskPath, String taskName) {
        String taskDbName = taskName + Constants.DB_SUFFIX;
        String taskDbPath = taskPath + File.separator + taskDbName;
        if (!FileUtils.existFile(taskDbPath)) {
            return false;
        }
        initDataSourceByTemplate(taskPath);
        return true;
    }

    private boolean initDataSourceByTemplate(String taskPath) {
        String templateParentPath = taskPath + File.separator + Constants.TASK_TEMPLATE_FOLDER;
        if (!FileUtils.existFile(templateParentPath)) {
            return false;
        }
        File templateParentFile = new File(templateParentPath);
        File[] files = templateParentFile.listFiles();
        for (File file : files) {
            String templateSuffix = ElectricFileUtils.getFileExt(file);
            if (templateSuffix.equalsIgnoreCase("xml") && file.getName().contains("模板")) {
                mDataSource = null;
                mDataSource = Utils.XmlDataSource(file.getPath());
                return true;
            }
        }
        return false;
    }

    /**
     * 更新模板文件的menulist，spinner对应的下拉框
     *
     * @param mDataSet  更新字段所在的dataset
     * @param fieldInfo 更新字段
     * @param text      添加的mennulist内容
     */
    public void writeMenuList(DataSet mDataSet, FieldInfo fieldInfo, String text) {
        int groupIndex = getGroupIndex(mDataSet);
        if (groupIndex >= 0) {
            int datasetIndex = getDatasetIndex(groupIndex, mDataSet);
            if (datasetIndex >= 0) {
                int fieldIndex = getFieldIndex(groupIndex, datasetIndex, fieldInfo);
                if (fieldIndex >= 0) {
                    updateXmlFieldMenuList(groupIndex, datasetIndex, fieldIndex, text);
                }
            }
        }
    }

    private boolean updateXmlFieldMenuList(int groupIndex, int datasetIndex, int fieldIndex, final String text) {
        DataSetGroup group = mDataSource.DataSourceGroups.get(groupIndex);
        DataSet dataSet = group.DataSets.get(datasetIndex);
        FieldInfo fieldInfo = dataSet.FieldInfos.get(fieldIndex);
        try {
            DocumentBuilder dbs = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File file = getTaskTemplateFile();
            if (file == null) {
                return false;
            }
            Document dom = dbs.parse(new FileInputStream(file));
            Node root = dom.getDocumentElement();
            if (root.hasChildNodes()) {
                Element element = ((Element) root);
                if (element.getTagName().equals("WorkSpace")) {
                    NodeList nodeList = element.getChildNodes();
                    int size = nodeList.getLength();
                    for (int i = 0; i < size; i++) {
                        Node node = nodeList.item(i);
                        if (node.getNodeName().equals("DataSource")) {
                            NodeList dsourceList = node.getChildNodes();
                            for (int m = 0; m < dsourceList.getLength(); m++) {
                                if (dsourceList.item(i).getAttributes().getNamedItem("Name").getNodeValue().equals(group.Name)) {
                                    NodeList dsetList = dsourceList.item(i).getChildNodes();
                                    for (int n = 0; n < dsetList.getLength(); n++) {
                                        if (null == dsetList.item(n).getAttributes()) {
                                            continue;
                                        }
                                        dsetList.item(n).getAttributes().getNamedItem("Name").getNodeValue().equals(dataSet.Name);
                                        NodeList fieldList = dsetList.item(i).getChildNodes();
                                        for (int q = 0; q < fieldList.getLength(); q++) {
                                            if (null == fieldList.item(q).getAttributes()) {
                                                continue;
                                            }
                                            if (fieldList.item(q).getAttributes().getNamedItem("Name").getNodeValue().equals(fieldInfo.Name)) {
                                                NodeList meunList = fieldList.item(q).getChildNodes();
                                                for (int l = 0; l < meunList.getLength(); l++) {
                                                    if (null == meunList.item(l).getAttributes()) {
                                                        continue;
                                                    }
                                                    if (meunList.item(l).getNodeName().equals("MenuList")) {
                                                        NodeList itemList = meunList.item(l).getChildNodes();
                                                        for (int h = 0; h < itemList.getLength(); h++) {
                                                            if (null != itemList.item(h).getAttributes()) {
                                                                Node nodeCopy = itemList.item(h).cloneNode(true);
                                                                nodeCopy.getAttributes().item(0).setNodeValue(text);
                                                                meunList.item(h).appendChild(nodeCopy);
                                                                return writeFile(file, dom);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean writeFile(File file, Document dom) throws FileNotFoundException {
        dom.normalize();
        try {
            /** 将document中的内容写入文件中 */
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //编码
            DOMSource source = new DOMSource(dom);
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            return true;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    private File getTaskTemplateFile() {
        String templateParentPath = ConstPath.getTaskRootFolder() + mTaskInfo.getTaskName()
                + File.separator + Constants.TASK_TEMPLATE_FOLDER;
        if (!FileUtils.existFile(templateParentPath)) {
            return null;
        }
        File templateParentFile = new File(templateParentPath);
        File[] files = templateParentFile.listFiles();
        for (File file : files) {
            String templateSuffix = ElectricFileUtils.getFileExt(file);
            if (templateSuffix.equalsIgnoreCase("xml") && file.getName().contains("模板")) {
                return file;
            }
        }
        return null;
    }

    private int getFieldIndex(int groupIndex, int datasetIndex, FieldInfo fieldInfo) {
        DataSetGroup group = mDataSource.DataSourceGroups.get(groupIndex);
        DataSet dataSet = group.DataSets.get(datasetIndex);
        ArrayList<FieldInfo> fieldInfos = dataSet.FieldInfos;
        int fieldSize = fieldInfos.size();
        for (int i = 0; i < fieldSize; i++) {
            if (fieldInfos.get(i).Name.equals(fieldInfo.Name)) {
                return i;
            }
        }
        return -1;
    }

    private int getDatasetIndex(int groupIndex, DataSet mDataSet) {
        DataSetGroup group = mDataSource.DataSourceGroups.get(groupIndex);
        ArrayList<DataSet> dataSets = group.DataSets;
        int size = dataSets.size();
        for (int i = 0; i < size; i++) {
            DataSet dataSet = dataSets.get(i);
            if (dataSet.Name.equals(mDataSet.Name)) {
                return i;
            }
        }
        return -1;
    }

    private int getGroupIndex(DataSet mDataSet) {
        ArrayList<DataSetGroup> groupList = mDataSource.DataSourceGroups;
        int groupSize = groupList.size();
        for (int i = 0; i < groupSize; i++) {
            DataSetGroup group = groupList.get(i);
            if (group.Name.equals(mDataSet.GroupName)) {
                return i;
            }
        }
        return -1;
    }
}
