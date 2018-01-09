package com.fleetingtime.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
public final class SystemCfg {

	private static Logger logger = Logger.getLogger(SystemCfg.class);

	private static Properties properties = null;

	static {
		Properties props = null;
		InputStream inputStream = null;
		try {
			String[] filePaths = new String[] { "/config.properties" };
			for (String filePath : filePaths) {
				inputStream = SystemCfg.class.getResourceAsStream(filePath);
				props = new Properties();
				props.load(inputStream);
				inputStream.close();
				// 将加载的properties
				if (null != SystemCfg.properties) {
					SystemCfg.properties.putAll(props);
				} else {
					SystemCfg.properties = props;
				}
			}
		} catch (IOException ex) {
			logger.error("系统配置加载失败， 详情：" + ex);
		} 
		/*finally {
			FileUtils.closeConnection(inputStream);
		}*/
	}

	/**
	 * 根据资源配置名称，获取资源配置值
	 * 
	 * @param propName
	 * @return
	 */
	public static String get(String propName) {
		return (String) properties.get(propName);
	}

	/**
	 * 根据资源配置名称，获取资源配置值, 若没找到对应的资源名称，则返回默认值
	 * 
	 * @param propName
	 * @param defValue
	 * @return
	 */
	public static String get(String propName, String defValue) {
		String propValue = defValue;
		Object tempValue = properties.get(propName);
		if (tempValue != null) {
			propValue = (String) tempValue;
		}
		return propValue;
	}
	
	public static void main(String[] args) {
		SystemCfg systemCfg = new SystemCfg();
		String str = systemCfg.get("uploadPath");
		System.out.println("***********");
		System.out.println(str);
		System.out.println("***********");
	}
}
