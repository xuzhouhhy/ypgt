package com.geocraft.electrics.utils;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.GroupAndDataSetName;
import com.geocraft.electrics.entity.PhotoRules;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.event.ExportEvent;
import com.huace.log.logger.L;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import common.geocraft.untiltools.ConvertHelper;
import common.geocraft.untiltools.SDCardManager;

/**
 * 作者  zhouqin
 * 时间 2016/6/3.
 */
public class Utils {



	private static final double EARTH_RADIUS = 6378.137;//地球半径

	public static List<String> getFirstList(Map<String,ArrayList<String>> map){
		return new ArrayList<>(map.keySet());
	}
	public static List<String> getSecondList(Map<String,ArrayList<String>> map,String firstValue){
		return  map.get(firstValue);
	}

	public static List<String> getOptionTwoFromOne(Map<String,Map<String, ArrayList<String>>> map,String firstValue) {
		Map<String, ArrayList<String>> selectedTwo = map.get(firstValue);
		return new ArrayList<>(selectedTwo.keySet());
	}

	public static List<String> getOptionThreeFromTwo(Map<String,Map<String, ArrayList<String>>> map,String firstValue,String secondValue) {
		Map<String, ArrayList<String>> selectedTwo = map.get(firstValue);
		return selectedTwo.get(secondValue);
	}

	public static long ConverLong(String str)
	{
		if(str == null || str.isEmpty())
		{
			str = "0";
		}

		long nResult = 0;
		try
		{
			nResult =  Long.valueOf(str);
		}
		catch (Exception ex)
		{
			L.printException(ex);
			nResult = 0;
		}
		return nResult;
	}

	private static DataSet getDataSet(Element element)
	{
		DataSet dataSet = new DataSet();

		dataSet.Name = element.getAttribute("Name");
		dataSet.Alias = element.getAttribute("Alias");
		dataSet.GroupName = element.getAttribute("GroupName");
		dataSet.EntityType = element.getAttribute("EntityType");
		dataSet.DeviceType = element.getAttribute("DeviceType");
		dataSet.Mark = element.getAttribute("Mark");
		dataSet.Symbol = element.getAttribute("Symbol");
		dataSet.IsVisible = ConvertHelper.ConvertBoolean(element.getAttribute("IsVisible"));
		dataSet.First = element.getAttribute("First");
		dataSet.Second = element.getAttribute("Second");
		dataSet.Third = element.getAttribute("Third");
		dataSet.IsExport = ConvertHelper.ConvertBoolean(element.getAttribute("IsExport"));
		dataSet.IsShowPhoto = ConvertHelper.ConvertBoolean(element.getAttribute("IsShowPhoto"));
		dataSet.IsShowCoordinate = ConvertHelper.ConvertBoolean(element.getAttribute("IsShowCoordinate"));
		dataSet.IsShowBase = ConvertHelper.ConvertBoolean(element.getAttribute("IsShowBase"));
		dataSet.IsShowNecessary = ConvertHelper.ConvertBoolean(element.getAttribute("IsShowNecessary"));
		dataSet.SearchField = element.getAttribute("SearchField");
		dataSet.ValueField = element.getAttribute("ValueField");
		dataSet.setmIsShowInDeviceList(ConvertHelper.ConvertBoolean(element.getAttribute("isGoToThird")));

		NodeList listField = element.getChildNodes();
		for (int m = 0; m < listField.getLength(); m++) {
			if ("Field".equals(listField.item(m).getNodeName())) {
				FieldInfo field = new FieldInfo();
				field.Name = ((Element) listField.item(m)).getAttribute("Name");
				field.Alias = ((Element) listField.item(m)).getAttribute("Alias");
				field.Type = ((Element) listField.item(m)).getAttribute("Type");
				field.Length = ConvertHelper.ConverInt(((Element) listField.item(m)).getAttribute("Length"));
				field.Default = ((Element) listField.item(m)).getAttribute("Default");
				field.IsSystemField = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsSystemField"));
				field.IsRequired = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsRequired"));
				field.IsUnique = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsUnique"));
				field.IsVisible = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsVisible"));
				field.IsDisable = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsDisable"));
				field.IsCopy = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsCopy"));
				field.IsNum = ConvertHelper.ConvertBoolean(((Element) listField.item(m)).getAttribute("IsNum"));
				field.Sort = ConvertHelper.ConverInt(((Element) listField.item(m)).getAttribute("Sort"));
				field.OperateCode = ConvertHelper.ConverInt(((Element) listField.item(m)).getAttribute("OperateCode"));

				dataSet.FieldInfos.add(field);

				//在这里初始化数据字典
				NodeList dicNodeList = listField.item(m).getChildNodes();
				for(int y = 0; y < dicNodeList.getLength(); y++)
				{
					if("MenuList".equals(dicNodeList.item(y).getNodeName()))
					{
						NodeList itemNodeList = dicNodeList.item(y).getChildNodes();
						for(int q = 0; q < itemNodeList.getLength(); q++)
						{
							if("Item".equals(itemNodeList.item(q).getNodeName()))
							{
								field.Dictionay.menuList.add(((Element) itemNodeList.item(q)).getAttribute("Value"));
							}
						}
					}
					if("CombinationMenu".equals(dicNodeList.item(y).getNodeName()))
					{
						//获取属性
						field.Dictionay.combinationMenu.firstCaption = ((Element)dicNodeList.item(y)).getAttribute("FirstCaption");
						field.Dictionay.combinationMenu.secondCaption = ((Element)dicNodeList.item(y)).getAttribute("SecondCaption");
						//获取组
						NodeList groupNodeList = dicNodeList.item(y).getChildNodes();
						for(int g = 0; g < groupNodeList.getLength();g++)
						{
							String groupName = ((Element)groupNodeList.item(y)).getAttribute("Name");
							ArrayList<String> items = new ArrayList<>();

							NodeList itemNodeList = groupNodeList.item(g).getChildNodes();
							for(int t = 0; t < itemNodeList.getLength(); t++)
							{
								if("Item".equals(itemNodeList.item(t).getNodeName()))
								{
									items.add(((Element) itemNodeList.item(t)).getAttribute("Value"));

									field.Dictionay.combinationMenu.combinationMenu.put(groupName, items);
								}
							}
						}
					}

					if("Search".equals(dicNodeList.item(y).getNodeName()))
					{
						field.Dictionay.searchEntiry.tableName = ((Element)dicNodeList.item(y)).getAttribute("TableName");
						field.Dictionay.searchEntiry.fieldName = ((Element)dicNodeList.item(y)).getAttribute("FieldName");
						field.Dictionay.searchEntiry.filedValue = ((Element)dicNodeList.item(y)).getAttribute("FieldValue");
						field.Dictionay.searchEntiry.editable = ConvertHelper.ConvertBoolean(((Element)dicNodeList.item(y)).getAttribute("Editable"));
					}

					if("ThreeLevelMenu".equals(dicNodeList.item(y).getNodeName()))
					{
						//获取属性
						field.Dictionay.threeLevelMenu.firstCaption = ((Element)dicNodeList.item(y)).getAttribute("FirstCaption");
						field.Dictionay.threeLevelMenu.secondCaption = ((Element)dicNodeList.item(y)).getAttribute("SecondCaption");
						field.Dictionay.threeLevelMenu.thirdCaption = ((Element)dicNodeList.item(y)).getAttribute("ThirdCaption");
						field.Dictionay.threeLevelMenu.fileName = ((Element)dicNodeList.item(y)).getAttribute("FileName");
					}
				}
			}

			if ("Photo".equals(listField.item(m).getNodeName())) {
				PhotoRules rules = new PhotoRules();
				rules.Type = ((Element) listField.item(m)).getAttribute("Type");
				rules.Number = ConvertHelper.ConverInt(((Element) listField.item(m)).getAttribute("Number"));
				rules.Rules = ((Element) listField.item(m)).getAttribute("Rules");

				dataSet.PhotoRules.add(rules);
			}

			if("DataSet".equals(listField.item(m).getNodeName()))
			{
				dataSet.DataSets.add(getDataSet((Element) listField.item(m)));
			}
		}

		return dataSet;
	}

	public static DataSource XmlDataSource(String path) {
		DataSource dataSource = new DataSource();
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(path));

			//获取根标签
			Element workSpeceNode = (Element) document.getDocumentElement();

			//获取DataSource标签
			Element dataSourceNode = (Element) workSpeceNode.getElementsByTagName("DataSource").item(0);
			dataSource.Name = dataSourceNode.getAttribute("Name");
			dataSource.Alias = dataSourceNode.getAttribute("Alias");
			dataSource.Type = ConvertHelper.ConverInt(dataSourceNode.getAttribute("Type"));
			dataSource.Description = dataSourceNode.getAttribute("Description");
			dataSource.AssignPeople = dataSourceNode.getAttribute("AssignPeople");
			dataSource.AcceptPeople = dataSourceNode.getAttribute("AcceptPeople");
			dataSource.CreateTime = dataSourceNode.getAttribute("CreateTime");
			dataSource.CompleteTime = dataSourceNode.getAttribute("CompleteTime");
			dataSource.State = dataSourceNode.getAttribute("State");

			NodeList listDataSetGroup = dataSourceNode.getChildNodes();
			for (int i = 0; i < listDataSetGroup.getLength(); i++) {
				if ("DataSetGroup".equals(listDataSetGroup.item(i).getNodeName())) {
					DataSetGroup group = new DataSetGroup();
					group.Name = ((Element) listDataSetGroup.item(i)).getAttribute("Name");
					group.Alias = ((Element) listDataSetGroup.item(i)).getAttribute("Alias");
					group.Mark = ((Element) listDataSetGroup.item(i)).getAttribute("Mark");
					group.IsVisible = ConvertHelper.ConvertBoolean(((Element) listDataSetGroup.item(i)).getAttribute("IsVisible"));
					group.setmIsShowInDeviceList(ConvertHelper.ConvertBoolean(((Element)
							listDataSetGroup.item(i)).getAttribute("isGoToThird")));

					NodeList listDataSet = ((Element) listDataSetGroup.item(i)).getChildNodes();

					for (int j = 0; j < listDataSet.getLength(); j++) {
						if ("DataSet".equals(listDataSet.item(j).getNodeName())) {
							group.DataSets.add(getDataSet((Element) listDataSet.item(j)));
						}
					}

					dataSource.DataSourceGroups.add(group);
				}
			}

		} catch (Exception e) {
			L.printException(e);
			return null;
		}


		return dataSource;
	}


	//将数据集写入Excel
	//template 模板目录
	//exprotPath 导出目录
	public static boolean DataSetsToXLS(ProgressDialog pd, List<DataSet> list, String dataSetName,String dataSetAlias,String templatePath, String exortPath) {

		String pathTemplate = "";
		String pathExcel = "";

		pathTemplate = templatePath + dataSetName + ".xls";
		pathExcel = exortPath + dataSetAlias + ".xls";

		try
		{
			InputStream in = new FileInputStream(pathTemplate);
			Workbook work = new HSSFWorkbook(in);

			if(list.size() == 0)
			{
				pd.setMax(0);
			}

			for (int i = 0; i < list.size(); i++)
			{
				//进度条
				pd.setMax(list.size());

				DataSet dataSet = list.get(i);

				// 得到excel的第0张表
				Sheet sheet = work.getSheetAt(0);
				//获取第一行
				Row row = sheet.getRow(1);
				//获取第一行的所有单元格值
				int nCount = row.getLastCellNum();
				ArrayList<String> listCellName = new ArrayList<String>();

				String strTemp = "";
				Cell cell = null;
				for (int j = 0; j < nCount; j++)
				{
					cell = row.getCell(j);
					if (cell == null) {
						break;
					}
					strTemp = getValue(cell);
					if (strTemp.isEmpty()) {
						break;
					}
					listCellName.add(strTemp);
				}

				Row rowNew = sheet.getRow(2 + i);
				if (rowNew == null)
				{
					rowNew = sheet.createRow(2 + i);
				}

				for (int m = 0; m < listCellName.size(); m++)
				{
					cell = null;
					cell = rowNew.getCell(m);
					if (cell == null) {
						cell = rowNew.createCell(m);
					}

					if(m==0)
					{
						cell.setCellValue(Integer.valueOf(i + 1).toString());
					}
					else {
						cell.setCellValue(dataSet.GetFieldValueByAlias(listCellName.get(m)));
					}
				}

				ElectricApplication.BUS.post(new ExportEvent("ExportEvent",""));
			}

			FileOutputStream os = new FileOutputStream(pathExcel);
			work.write(os);
			os.close();
		}
		catch (FileNotFoundException e)
		{
			L.printException(e);
			return false;
		}
		catch (IOException e)
		{
			L.printException(e);
			return false;
		}

		return true;
	}

	private static String getValue(Cell hssfCell) {
		String strReturn = "";
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			strReturn =  String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			double d = hssfCell.getNumericCellValue();
			if((d - (int)d) == 0)
			{
				strReturn =  String.valueOf((int)d);
			}
			else
			{
				strReturn =  String.valueOf(d);
			}
		} else {
			// 返回字符串类型的值
			strReturn =  String.valueOf(hssfCell.getStringCellValue());
		}

		return  replaceBlank(strReturn);
	}

	public static ArrayList<DataSet> XLSToDataSets(DataSet tmplateDataSet,String excelPath) {
		ArrayList<DataSet> listDataSets = new ArrayList<DataSet>();

		try {
			InputStream is = new FileInputStream(excelPath);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

			//获取第1一个Sheet页
			Sheet sheet = hssfWorkbook.getSheetAt(0);

			int rowCount = sheet.getLastRowNum();
			if (rowCount < 1) {
				throw new Exception();
			}

			ArrayList<String> listCellName = new ArrayList<String>();
			Row rowCellName = sheet.getRow(1);
			int nCount = rowCellName.getLastCellNum();

			Cell cell = null;
			String strTemp = "";
			for (int i = 0; i < nCount; i++) {
				cell = rowCellName.getCell(i);
				if (cell == null) {
					break;
				}

				strTemp = getValue(cell);
				if (strTemp.isEmpty()) {
					break;
				}

				listCellName.add(strTemp);
			}

			nCount = sheet.getLastRowNum();
			Row rowTmp = null;
			for (int i = 2; i <= nCount; i++) {
				DataSet dataSet = (DataSet) tmplateDataSet.clone();

				//每一行里的所有字段
				for (int j = 0; j < listCellName.size(); j++)
				{
					rowTmp = sheet.getRow(i);
					cell = null;
					cell = rowTmp.getCell(j);
					if (cell != null)
					{
						strTemp = getValue(cell);
						dataSet.SetFiledValueByAlias(listCellName.get(j),strTemp);
						if(listCellName.get(j).equals("序号"))
						{
							dataSet.SetFiledValueByName("F_XH",strTemp);
							dataSet.SetFiledValueByName("F_XLH",strTemp);
						}
					}
				}

				//将 状态 字段 设备为导入
				dataSet.SetFiledValueByAlias("状态","导入");

				if((!dataSet.GetFieldValueByName("F_XH").isEmpty()) || (!dataSet.GetFieldValueByName("F_XLH").isEmpty()))
				{
					listDataSets.add(dataSet);
				}

				is.close();
			}
		} catch (FileNotFoundException e) {
			L.printException(e);
		} catch (IOException e) {
			L.printException(e);
		} catch (Exception e) {
			L.printException(e);
		}

		return listDataSets;
	}

	public static  GroupAndDataSetName getGourpAndDataSetName(String name)
	{
		GroupAndDataSetName obj = new GroupAndDataSetName();

		int start=name.lastIndexOf("_");
		if(start >= 0)
		{
			obj.groupName = name.substring(0, start);
			obj.dataSetName = name.substring(start+1);
		}

		return obj;
	}

	public static boolean isTopActivy(String cmdName, Context context)
	{
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(Integer.MAX_VALUE);
		String cmpNameTemp = null;
		if (null != runningTaskInfos)
		{
			cmpNameTemp = runningTaskInfos.get(0).topActivity.getClassName();
		}

		if (null == cmpNameTemp)
		{
			return false;
		}

		return cmpNameTemp.equals(cmdName);
	}

	public static String replaceBlank(String str)
	{
		String dest = "";
		if (str!=null)
		{
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest.trim();
	}

	private static double rad(double d)
	{
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * EARTH_RADIUS;
		s = (s * 10000) / 10;
		return s;
	}
	public static double GetDis(double lat1, double lng1, double lat2, double lng2)
	{
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * EARTH_RADIUS*1000;//换算成米


		//保留2位小数
		DecimalFormat dFormat  =new DecimalFormat("0.00");
		return  Double.valueOf(dFormat.format(s).toString());
	}

	public static PropertyDictionay.ThreeLevelMenu loadThreeLevelMenuFromFile(String path)
	{
		PropertyDictionay.ThreeLevelMenu menu = new PropertyDictionay.ThreeLevelMenu();

		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(path));

			//获取根标签
			Element threeLevelMenu = (Element) document.getDocumentElement();

			menu.firstCaption = threeLevelMenu.getAttribute("FirstCaption");
			menu.secondCaption = threeLevelMenu.getAttribute("SecondCaption");
			menu.thirdCaption = threeLevelMenu.getAttribute("ThirdCaption");

			NodeList group1List = threeLevelMenu.getChildNodes();
			for(int b = 0; b < group1List.getLength();b++)
			{
				if("Group".equals(group1List.item(b).getNodeName()))
				{
					String group1Name = ((Element) group1List.item(b)).getAttribute("Name");
					NodeList group2List = group1List.item(b).getChildNodes();

					Map<String, ArrayList<String>> secondThree = new HashMap<>();
					for (int d = 0; d < group2List.getLength(); d++)
					{
						if("Group".equals(group2List.item(d).getNodeName()))
						{
							String group2Name = ((Element) group2List.item(d)).getAttribute("Name");
							NodeList itemList = group2List.item(d).getChildNodes();
							ArrayList<String> items = new ArrayList<>();
							for (int e = 0; e < itemList.getLength(); e++) {
								if ("Item".equals(itemList.item(e).getNodeName())) {
									items.add(((Element) itemList.item(e)).getAttribute("Value"));
								}
							}

							secondThree.put(group2Name, items);
							menu.menu.put(group1Name, secondThree);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			L.printException(e);
		}

		return menu;
	}

	public static  boolean isNumeric(String str)
	{
		if(str.isEmpty())
		{
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str.trim());
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	}

	public static String getString(Spinner spinner)
	{
		String str = "";
		str = spinner.getSelectedItem() == null ? "" : spinner.getSelectedItem().toString();
		if(str == null)
		{
			str = "";
		}

		return str;
	}

	public static void setSingleSelected(Spinner spinner)
	{
		try
		{
			Field field = AdapterView.class.getDeclaredField("mOldSelectedPosition");
			field.setAccessible(true);  //设置mOldSelectedPosition可访问
			field.setInt(spinner, AdapterView.INVALID_POSITION); //设置mOldSelectedPosition的值
		} catch (Exception e) {
			L.printException(e);
		}
	}

	public static String getPhotoDir(String path,PhotoRules photoRules, DataSet dataSet)
	{
		String strTmp = path;

		strTmp += dataSet.Alias;
		if(!SDCardManager.IsExisted(strTmp))
		{
			SDCardManager.CreateFolder(strTmp);
		}

		strTmp += File.separator + photoRules.Type;
		if(!SDCardManager.IsExisted(strTmp))
		{
			SDCardManager.CreateFolder(strTmp);
		}

		return strTmp + File.separator;
	}

}
