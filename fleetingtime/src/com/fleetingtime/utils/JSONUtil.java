package com.fleetingtime.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * <p>Title:JSONUtil </p>
 * <p>Description:JSON对象处理类 </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author zhaoxy
 * @version 1.0
 * @date 2013-4-7 下午02:02:48
 * 修改日期  修改人  修改目的
 */
public class JSONUtil {
	
	
	
	/**
	 * 
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * 
	 * @param pojoCalss
	 * 
	 * @return
	 * 
	 */

	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {

		Object pojo;

		JSONObject jsonObject = JSONObject.fromObject(jsonString);

		pojo = JSONObject.toBean(jsonObject, pojoCalss);

		return pojo;

	}

	/** */
	/**
	 * 
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static Map getMap4Json(String jsonString) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);

		Iterator keyIter = jsonObject.keys();

		String key;

		Object value;

		Map valueMap = new HashMap();

		while (keyIter.hasNext())

		{

			key = (String) keyIter.next();

			value = jsonObject.get(key);

			valueMap.put(key, value);

		}

		return valueMap;

	}

	/** */
	/**
	 * 
	 * 从json数组中得到相应java数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static Object[] getObjectArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		return jsonArray.toArray();

	}

	/** */
	/**
	 * 
	 * 从json对象集合表达式中得到一个java对象列表
	 * 
	 * @param jsonString
	 * 
	 * @param pojoClass
	 * 
	 * @return
	 * 
	 */

	public static List getList4Json(String jsonString, Class pojoClass) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		JSONObject jsonObject;

		Object pojoValue;

		List list = new ArrayList();

		for (int i = 0; i < jsonArray.size(); i++) {

			jsonObject = jsonArray.getJSONObject(i);

			pojoValue = JSONObject.toBean(jsonObject, pojoClass);

			list.add(pojoValue);

		}

		return list;

	}

	/** */
	/**
	 * 
	 * 从json数组中解析出java字符串数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static String[] getStringArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		String[] stringArray = new String[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			stringArray[i] = jsonArray.getString(i);

		}

		return stringArray;

	}

	/** */
	/**
	 * 
	 * 从json数组中解析出javaLong型对象数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static Long[] getLongArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Long[] longArray = new Long[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			longArray[i] = jsonArray.getLong(i);

		}

		return longArray;

	}

	/** */
	/**
	 * 
	 * 从json数组中解析出java Integer型对象数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static Integer[] getIntegerArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Integer[] integerArray = new Integer[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			integerArray[i] = jsonArray.getInt(i);

		}

		return integerArray;

	}

	/** */
	/**
	 * 
	 * 从json数组中解析出java Date 型对象数组，使用本方法必须保证
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static Date[] getDateArray4Json(String jsonString, String DataFormat) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Date[] dateArray = new Date[jsonArray.size()];

		String dateString;

		Date date = null;

		for (int i = 0; i < jsonArray.size(); i++) {

			dateString = jsonArray.getString(i);

			try {
				date = new SimpleDateFormat("DataFormat").parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			date = DateUtil..stringToDate(dateString, DataFormat);

			dateArray[i] = date;

		}

		return dateArray;

	}

	/**
	 * 
	 * 从json数组中解析出java Integer型对象数组
	 * 
	 * @param jsonString
	 * 
	 * @return
	 * 
	 */

	public static Double[] getDoubleArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		Double[] doubleArray = new Double[jsonArray.size()];

		for (int i = 0; i < jsonArray.size(); i++) {

			doubleArray[i] = jsonArray.getDouble(i);

		}

		return doubleArray;

	}

	/**
	 * 
	 * 将java对象转换成json字符串
	 * 
	 * @param javaObj
	 * 
	 * @return
	 * 
	 */

	public static String getJsonString4JavaPOJO(Object javaObj) {

		JSONObject json;

		json = JSONObject.fromObject(javaObj);

		return json.toString();

	}

	/**
	 * 
	 * JSON 时间解析器具
	 * 
	 * @param datePattern
	 * 
	 * @return
	 * 
	 */
	/**
	 * 默认的日期格式:"yyyy-MM-dd"
	 */
	private final static String defaultDateFormatPattern = "yyyy-MM-dd HH:mm:ss";
	/**
	 * jsonReader 中的属性root对应的值,默认：rows<br/>
	 * root对应的值对应于json中的键,该键对于的值是所有的需要在gridPanel中显示的数据
	 */
	private final static String ROOT = "dataList";
	/**
	 * jsonReader 中的属性totalProperty对应的值,默认：totalCount<br/>
	 * totalProperty对应的值(如totalCount)对应于json中的键,该键对于的值是所有的数据总数
	 */
	private final static String TOTALPROPERTY = "totalCount";
	
	
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * <br/>
	 * 支持分页和不分页的格式转换
	 * <br/>
	 * 支持时间默认和自定义
	 * <br/>
	 * 支持自定义root和totalProperty
	 * @param <T>
	 * @param resultList
	 * @param root
	 * @param totalProperty
	 * @param dateFormatPattern
	 * @param dataCount
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * <b>参数root</b>： jsonReader 中的属性root对应的值,默认："rows".<br/>
	 * root对应的值对应于json中的键,该键对于的值是所有的需要在gridPanel中显示的数据
	 * <br/>
	 * <b>参数totalProperty</b>：jsonReader 中的属性totalProperty对应的值,默认：totalCount<br/>
	 * totalProperty对应的值(如totalCount)对应于json中的键,该键对于的值是所有的数据总数
	 * <br/>
	 * <b>参数dateFormatPattern</b>：日期格式，如yyyy-MM-dd,可参见java的API的DateFormat中定义的格式
	 * <br/>
	 * <b>参数dataCount</b>:数据的总条数,为分页准备
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList,String root,String totalProperty, String dateFormatPattern,Integer dataCount) {
		if(resultList == null) {
			return "";
		}
		if(dateFormatPattern == null || "".equals(dateFormatPattern)) {
			dateFormatPattern = defaultDateFormatPattern;
		}
		if(root == null || "".equals(root)) {
			root = ROOT;
		}
		if(totalProperty == null || "".equals(totalProperty)) {
			totalProperty = TOTALPROPERTY;
		}
		JSONArray jsonArr = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(dateFormatPattern));
		jsonConfig.registerJsonValueProcessor(Long.class, new LongJsonValueProcessor());
		for (T entity : resultList) {
			JSONObject json = JSONObject.fromObject(entity, jsonConfig);
			jsonArr.add(json);
		}
		JSONObject json = new JSONObject();
		//如果有分页，totalCount就显示数据库查出来的记录条数，否则，显示当此传入到页面的记录总条数
		if(dataCount == null) {
			json.put(totalProperty, resultList.size());
		} else {
			json.put(totalProperty, (int)dataCount);
		}
		json.put(root, jsonArr);
		return json.toString();
	}*/
	
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * <br/>
	 * 默认：root:"rows",totalProperty:"totalCount"
	 * @param <T>
	 * @param resultList
	 * @param root
	 * @param totalProperty
	 * @param dateFormatPattern
	 * @param dataCount
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * <b>参数dateFormatPattern</b>：日期格式，如yyyy-MM-dd,可参见java的API的DateFormat中定义的格式
	 * <br/>
	 * <b>参数dataCount</b>:数据的总条数,为分页准备
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList, String dateFormatPattern,Integer dataCount) {
		return getJsonResult(resultList, null, null, dateFormatPattern, dataCount);
	}*/
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * <br/>
	 * 记录总数 默认为当前resultList的size
	 * @param <T>
	 * @param resultList
	 * @param root
	 * @param totalProperty
	 * @param dateFormatPattern
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * <b>参数root</b>： jsonReader 中的属性root对应的值,默认："rows".<br/>
	 * root对应的值对应于json中的键,该键对于的值是所有的需要在gridPanel中显示的数据
	 * <br/>
	 * <b>参数totalProperty</b>：jsonReader 中的属性totalProperty对应的值,默认：totalCount<br/>
	 * totalProperty对应的值(如totalCount)对应于json中的键,该键对于的值是所有的数据总数
	 * <br/>
	 * <b>参数dateFormatPattern</b>：日期格式，如yyyy-MM-dd,可参见java的API的DateFormat中定义的格式
	 * <br/>
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList,String root,String totalProperty,String dateFormatPattern) {
		return getJsonResult(resultList, root, totalProperty, dateFormatPattern, null);
	}*/
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * <br/>
	 * 记录总数 默认为当前resultList的size<br/>
	 * 时间格式默认为：yyyy-MM-dd
	 * @param <T>
	 * @param resultList
	 * @param root
	 * @param totalProperty
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * <b>参数root</b>： jsonReader 中的属性root对应的值,默认："rows".<br/>
	 * root对应的值对应于json中的键,该键对于的值是所有的需要在gridPanel中显示的数据
	 * <br/>
	 * <b>参数totalProperty</b>：jsonReader 中的属性totalProperty对应的值,默认：totalCount<br/>
	 * totalProperty对应的值(如totalCount)对应于json中的键,该键对于的值是所有的数据总数
	 * <br/>
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList,String root,String totalProperty) {
		return getJsonResult(resultList, root, totalProperty, null, null);
	}*/
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * <br/>
	 * 记录总数 默认为当前resultList的size
	 * <br/>
	 * 默认：root:"rows",totalProperty:"totalCount"
	 * @param <T>
	 * @param resultList
	 * @param dateFormatPattern
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * <b>参数dateFormatPattern</b>：日期格式，如yyyy-MM-dd,可参见java的API的DateFormat中定义的格式
	 * <br/>
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList,String dateFormatPattern) {
		return getJsonResult(resultList, null, null, dateFormatPattern, null);
	}*/
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * 默认的日期格式:"yyyy-MM-dd"
	 * <br/>
	 * @param <T>
	 * @param resultList
	 * @param root
	 * @param totalProperty
	 * @param dataCount
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * <b>参数dateFormatPattern</b>：日期格式，如yyyy-MM-dd,可参见java的API的DateFormat中定义的格式
	 * <br/>
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList,String root,String totalProperty,Integer dataCount) {
		return getJsonResult(resultList, root, totalProperty, null, dataCount);
	}*/
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * 默认的日期格式:"yyyy-MM-dd"
	 * <br/>
	 * <br/>
	 * 默认：root:"rows",totalProperty:"totalCount"
	 * @param <T>
	 * @param resultList
	 * @param dataCount
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList,Integer dataCount) {
		return getJsonResult(resultList, null, null, null, dataCount);
	}*/
	/**
	 * 得到符合Extjs jsonReader中json格式的字符串
	 * 默认的日期格式:"yyyy-MM-dd"
	 * <br/>
	 * 默认：root:"rows",totalProperty:"totalCount"
	 * <br/>
	 * 记录总数 默认为当前resultList的size
	 * @param <T>
	 * @param resultList
	 * <br/>
	 * <b>参数说明：</b>
	 * <br/>
	 * <b>参数resultList</b>：返回的结果集list
	 * <br/>
	 * @return result 
	 * <br/>
	 * 符合Extjs jsonReader中json格式的字符串
	 */
	/*public static <T> String getJsonResult(List<T> resultList) {
		return getJsonResult(resultList, null, null, null, null);
	}*/
	
	
	/**
	 * -------------------------------
	 * 以下方法和Extjs无关
	 * -------------------------------
	 */
	
	/**
	 * 得到一个实体的标准json格式：
	 * <br>
	 * {"键1":"值1","键2":"值2",..."键n":"值n"},{对象2},...,{对象n}
	 * @param <T>
	 * @return
	 */
	/*public static<T> String getStandardJsonResult(T entity,String dateFormatPattern) {
		JsonConfig jsonConfig = new JsonConfig();
		if(dateFormatPattern == null || "".equals(dateFormatPattern)) {
			dateFormatPattern = defaultDateFormatPattern;
		}
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(dateFormatPattern));
		jsonConfig.registerJsonValueProcessor(Long.class, new LongJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(null));
		JSONObject jsonObject = JSONObject.fromObject(entity, jsonConfig);
		return jsonObject.toString();
	}*/
	
	/**
	 * 得到一个实体的标准json格式：
	 * <br>
	 * {"键1":"值1","键2":"值2",..."键n":"值n"},{对象2},...,{对象n}
	 * @param <T>
	 * @param entity
	 * @return
	 */
	/*public static<T> String getStandardJsonResult(T entity) {
		return getStandardJsonResult(entity, null);
	}*/
	
	
	
	
	/**
	 * [{"键1":"值1","键2":"值2",...,"键n":"值n"},{对象2},...,{对象n}]
	 * @param <T>
	 * @param resultList
	 * @param dateFormatPattern
	 * @return
	 */
	/*public static<T> String getStandardArrayJsonResult(List<T> resultList,String dateFormatPattern) {
		JsonConfig jsonConfig = new JsonConfig();
		if(dateFormatPattern == null || "".equals(dateFormatPattern)) {
			dateFormatPattern = defaultDateFormatPattern;
		}
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(dateFormatPattern));
		jsonConfig.registerJsonValueProcessor(Long.class, new LongJsonValueProcessor());
		JSONArray jsonArray = new JSONArray();
		for(T entity : resultList) {
			JSONObject jsonObject = JSONObject.fromObject(entity, jsonConfig);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}*/
	/**
	 * [{"键1":"值1","键2":"值2",...,"键n":"值n"},{对象2},...,{对象n}]
	 * @param <T>
	 * @param resultList
	 * @return
	 */
	public static<T> String getStandardArrayJsonResult(List<T> resultList) {
		return getStandardArrayJsonResult(resultList,null);
	}
	/**
	 * [{"键1":"值1","键2":"值2",...,"键n":"值n"},{对象2},...,{对象n}]
	 * @param <T>
	 * @param entity
	 * @param dateFormatPattern
	 * @return
	 */
	public static<T> String getStandardArrayJsonResult(T entity,String dateFormatPattern) {
		List<T> resultList = new ArrayList<T>();
		resultList.add(entity);
		return getStandardArrayJsonResult(resultList, dateFormatPattern);
	}
	/**
	 * [{"键1":"值1","键2":"值2",...,"键n":"值n"},{对象2},...,{对象n}]
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public static<T> String getStandardArrayJsonResult(T entity) {
		return getStandardArrayJsonResult(entity,null);
	}
	/**
	 * 自定义键值对输出单个标准格式<br/>
	 * {"键1":"值1","键2":"值2",...,"键n":"值n"}<br/>
	 * @param <T>
	 * @param <S>
	 * @param map
	 * @return
	 */
	public static<T,S> String getStandardJsonResult(Map<T,S> map) {
		String result = null;
		StringBuffer appendStr = new StringBuffer("{");
		for(Map.Entry<T, S> entry : map.entrySet()) {
			T key = entry.getKey();
			S value = entry.getValue();
			appendStr.append("\"").append(key).append("\":");
			
			if(value != null && value.getClass().getName().equals("java.lang.Boolean")) {
				appendStr.append(value);
			} else {
				appendStr.append("\"").append(value).append("\"");
			}
			appendStr.append(",");
		}
		appendStr.deleteCharAt(appendStr.lastIndexOf(","));
		appendStr.append("}");
		result = appendStr.toString();
		return result;
	}
	/**
	 * 自定义键值对输出单个标准json数组格式<br/>
	 * [{"键1":"值1","键2":"值2",...,"键n":"值n"}]<br/>
	 * @param <T>
	 * @param <S>
	 * @param map
	 * @return
	 */
	public static<T,S> String getStandardArrayJsonResult(Map<T,S> map) {
		return "[" + getStandardJsonResult(map) + "]";
	}
	
	/**
	 * 通过json串得到一个指定类型的list
	 * @param <T>
	 * @param jsonStr
	 * @param entityClass
	 * @return
	 */
	public static <T> List<T> getListFromJsonStr(String jsonStr,Class<T> entityClass,String dateFormatPattern) {
		List<T> resultList = null;
		if(jsonStr == null || jsonStr.trim().equals("") || !jsonStr.startsWith("[")) {
			return null;
		}
		if(dateFormatPattern == null || dateFormatPattern.trim().equals("")) {
			dateFormatPattern = defaultDateFormatPattern;
		}
		
		String[] dateFormats = new String[] {dateFormatPattern};   
		
		
		
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		for(int i=0;i<jsonArr.size();i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			T entity = (T)JSONObject.toBean(jsonObj, entityClass);
			if(resultList == null) {
				resultList = new ArrayList<T>();
			}
			resultList.add(entity);
		}
		return resultList;
	}
	
	/**
	 * 通过json字符串得到一个制定类型的实体对象
	 * @param <T>
	 * @param jsonStr
	 * @param entityClass
	 * @param dateFormatPattern
	 * @return
	 */
	public static <T> T getBeanFromJsonStr(String jsonStr , Class<T> entityClass,String dateFormatPattern) {
		return getBeanFromJsonStr(jsonStr, entityClass, dateFormatPattern, null);
	}
	
	/**
	 * 通过json字符串得到一个制定类型的实体对象
	 * @param <T>
	 * @param jsonStr
	 * @param entityClass
	 * @param dateFormatPattern
	 * @param classMap
	 * @return
	 */
	public static <T> T getBeanFromJsonStr(String jsonStr , Class<T> entityClass,String dateFormatPattern,Map<String,Class> classMap) {
		T entity = null;
		if(jsonStr == null || jsonStr.trim().equals("") || !jsonStr.startsWith("{")) {
			return null;
		}
		if(dateFormatPattern == null || dateFormatPattern.trim().equals("")) {
			dateFormatPattern = defaultDateFormatPattern;
		}
		String[] dateFormats = new String[] {dateFormatPattern};   
//		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherImpl(dateFormats));   
		if(classMap != null &&classMap.size() > 0) {
			entity = (T)JSONObject.toBean(JSONObject.fromObject(jsonStr), entityClass , classMap);
		} else {
			entity = (T)JSONObject.toBean(JSONObject.fromObject(jsonStr), entityClass);
		}
		
		return entity ;
	}
	
	/**
	 * 通过json字符串得到一个制定类型的实体对象
	 * @param <T>
	 * @param jsonStr
	 * @param entityClass
	 * @return
	 */
	public static <T> T getBeanFromJsonStr(String jsonStr , Class<T> entityClass) {
		return getBeanFromJsonStr(jsonStr, entityClass, null, null);
	}
	
	/**
	 * 通过json字符串得到一个制定类型的实体对象
	 * @param <T>
	 * @param jsonStr
	 * @param entityClass
	 * @param classMap
	 * @return
	 */
	public static <T> T getBeanFromJsonStr(String jsonStr , Class<T> entityClass , Map<String,Class> classMap) {
		return getBeanFromJsonStr(jsonStr, entityClass, null, classMap);
	}
	
	
	/**
	 * 通过json串得到一个指定类型的list
	 * @param <T>
	 * @param jsonStr
	 * @param entityClass
	 * @return
	 */
	public static <T> List<T> getListFromJsonStr(String jsonStr,Class<T> entityClass) {
		return getListFromJsonStr(jsonStr, entityClass, null);
	}
	
	/**
	 * 返回key - value 的json标准json串<br>
	 * 格式：{"键1":"值1","键2":"值2",...,"键n":"值n"}
	 * @param retCode
	 * @param retMsg
	 * @return
	 */
	public static String returnJsonResult(String retCode_key,String retCode_value,String retMsg_key,String retMsg_value) {
		String retJson = null;
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put(retCode_key, retCode_value);
		retMap.put(retMsg_key, retMsg_value);
		retJson  = getStandardJsonResult(retMap);
		return retJson ;
	}
	
	/**
	 * 从[{key : value},{key : value}]中返回List<Sting>
	 * @param jsonArrStr
	 * @return
	 */
	public static List<String> getListFromJsonArrStr(String jsonArrStr,String key) {
		List<String> result = null;
		if(jsonArrStr == null || jsonArrStr.isEmpty()) return result ;
		JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);
		for(int i = 0;i < jsonArr.size();i ++) {
			if(result == null) result = new ArrayList<String>();
			result.add(jsonArr.getJSONObject(i).getString(key));
		}
		return result ;
	}
	
	/**
	 * 通过json对象和key获取value的工具类
	 * @author qiubb
	 *
	 */
	public static class JsonObject {
		private JSONObject jsonObject ;
		public JsonObject(String jsonStr) {
			this.jsonObject = JSONObject.fromObject(jsonStr);
		}
		
		public String getString(String key) {
			String value = null ; 
			if(key == null || key.equals("") || this.jsonObject == null) return null ;
			try {
				value = jsonObject.getString(key);
			} catch(Exception e) {
			}
			return value ;
		}

		public JSONObject getJsonObject() {
			return jsonObject;
		}

		public void setJsonObject(JSONObject jsonObject) {
			this.jsonObject = jsonObject;
		}
	}

}
