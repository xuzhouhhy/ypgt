package com.geocraft.electrics.utils;

import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.TaskInfo;
import com.huace.log.logger.L;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import common.geocraft.untiltools.FileUtils;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ElectricXmlUtils {

	public static TaskInfo getTaskInfoByTsk(String taskFilePath) {
		if (!FileUtils.existFile(taskFilePath)) {
			return null;
		}
		TaskInfo taskInfo = new TaskInfo();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		Document document;
		try {
			DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
			InputStream is = new FileInputStream(taskFilePath);
			document = domBuilder.parse(is);

			//开始解析
			Element elementRoot = document.getDocumentElement();

			Node taskNameNode = elementRoot.getElementsByTagName(Constants.XML_TAG_NAME).item(0);
			taskInfo.setTaskName(getNodeValueString(taskNameNode));

			Node taskCollectorNode =
					elementRoot.getElementsByTagName(Constants.XML_TAG_COLLECTOR).item(0);
			taskInfo.setCollector(getNodeValueString(taskCollectorNode));

			Node taskTimeNode = elementRoot.getElementsByTagName(Constants.XML_TAG_TIME).item(0);
			taskInfo.setCollectTime(getNodeValueString(taskTimeNode));

			Node taskTemplateNode =
					elementRoot.getElementsByTagName(Constants.XML_TAG_TEMPLATE).item(0);
			taskInfo.setTemplateName(getNodeValueString(taskTemplateNode));

			Node taskDescriptionNode =
					elementRoot.getElementsByTagName(Constants.XML_TAG_DESCRIPTION).item(0);
			taskInfo.setDescription(getNodeValueString(taskDescriptionNode));
			return taskInfo;
		} catch (Exception e) {
			L.printException(e);
			return null;
		}
	}

	private static String getNodeValueString(Node node) {
		String temp = "";
		if (node.getFirstChild() != null) {
			temp = node.getFirstChild().getNodeValue();
		}
		return temp;
	}
}
