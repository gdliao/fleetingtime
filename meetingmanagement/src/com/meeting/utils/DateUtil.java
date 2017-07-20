package com.meeting.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.meeting.constant.DateConst;

public class DateUtil {
	
	public static Date parseDateNull2Sysdate(String datestr, String format) {
		Date date = null;
		if (datestr == null || "".equals(datestr)) {
			date = new Date();
		} else {
			try {
				date = stringtoDate(datestr, format);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 字符串转换为指定格式的日期,要求字符串格式和日期格式一样
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date stringtoDate(String str, String format)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		if (str == null || "".equals(str)) {
			return null;
		} else {
			return sdf.parse(str);
		}
	}
	
	/**
	 * 字符串转换为指定格式的日期,当字符串格式不确定的时候必须使用此方法
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date stringParseDate(String str,String format) throws ParseException{
		   
	    String strFormat =  DateConst.getType(str);
		
		SimpleDateFormat  sdf = new SimpleDateFormat(strFormat);
		SimpleDateFormat  sdf1 = new SimpleDateFormat(format);
		
		if(str==null || "".equals(str)){
			return null;
		}else{
			Date date = sdf.parse(str);
			String date1 = datetoString(date, format);
			return sdf1.parse(date1) ;
		}
	}
	
	/**
	 * 日期转换为指定格式的字符串
	 */
	public static String datetoString(Date date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date == null) {
			return null;
		} else {
			return sdf.format(date);
		}
	}
	
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return gc;
    }
	
	public static Date convertToDate(XMLGregorianCalendar cal) {
		GregorianCalendar ca = cal.toGregorianCalendar();
    	return ca.getTime();
	}
	
	/**
	* 获得当前的年月日
	* 时间格式为yyyy-MM-odd
	* @return
	*/
	public static String getYearMonth(){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
		String string=dateFormat.format(cal.getTime());
		return string;
	}
	
	/**
	 * 返回指定时间格式的当前时间的年月日
	 * @param format
	 * @return
	 */
	public static String getYearMonthByType(String format){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat=new SimpleDateFormat(format);
		String string=dateFormat.format(cal.getTime());
		return string;
	}
	
	/**
	 * 在给定时间的基础上增加offsetDay天
	 * @param date
	 * @param offsetDay
	 * @return
	 */
	public static Date timeAddDay(Date date,int offsetDay){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, offsetDay);
		return cal.getTime();
	}
	
	/**
	 * 在给定时间的基础上增加offsetYear年
	 * @param date
	 * @param offsetYear
	 * @return
	 */
	public static Date timeAddYear(Date date,int offsetYear){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, offsetYear);
		return cal.getTime();
	}
	
	
	/**
	 * 获取指定时间当天的开始时间
	 * @param date
	 * @author chenqiang_crm_cui
	 */
	public static Date getStartTime(Date date){
		   if(null==date){
			   date = new Date();
		   }
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    c.set(Calendar.HOUR_OF_DAY, 0);
		    c.set(Calendar.MINUTE, 0);
		    c.set(Calendar.SECOND, 0);
		    date = c.getTime();
		    return date;
	}
	
	/**
	 * 获取指定时间当天的结束时间
	 * @param date
	 * @author chenqiang_crm_cui
	 */
	public static Date getEndTime(Date date){
		if(null==date){
			   date = new Date();
		   }
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    c.set(Calendar.HOUR_OF_DAY, 23);
		    c.set(Calendar.MINUTE, 59);
		    c.set(Calendar.SECOND, 59);
		    date = c.getTime();
		    return date;
	}
	
	public static void main(String[] args) {
		System.out.println(getYearMonth());
		Date date=timeAddDay(new Date(), 8);
		System.out.println(datetoString(date, DateConst.DATE_MODEL_2));
	}
}
