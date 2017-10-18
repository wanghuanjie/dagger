package com.ziyoujiayuan.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.base.utils.ConvertUtils;

/**
 * 获取Request各类参数相关
 * @Author wanghjbuf
 * @Date 2017年10月18日
 */
public class ParamUtils {

    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ParamUtils(){}

    /**
     * 获取上下文 TODO
     * @return
     */
    public final static String getContextPath() {
        return "";
    }
    
    /**
     * 获取服务器路径 TODO
     * @return
     */
    public final static String getServerPath() {
        return "";
    }

    /**
     * 获取 Server
     * @param request
     * @return
     */
    public final static String getServer(HttpServletRequest request) {

        StringBuffer server = new StringBuffer(128);
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80;
        }
        server.append(scheme);
        server.append("://");
        server.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80))|| (scheme.equals("https") && (port != 443))) {
            server.append(':');
            server.append(port);
        }
        return server.toString();
    }

    /**
     * 获取 Server
     * @param request
     * @return
     */
    public final static String getServer2(HttpServletRequest request) {

        StringBuffer server = new StringBuffer(128);
        server.append(request.getScheme());
        server.append("://");
        server.append(request.getHeader("host"));
        return server.toString();
    }

    /**
     * 获取参数,默认返回空字符串
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static String getParameter(HttpServletRequest request, String param) throws AppException {
        String ret = request.getParameter(param);
        if (ret == null) { 
        		ret = ""; 
        	}
        ret = ret.trim();
        
        request.setAttribute(param, ret);
        return ret.trim();
    }
    
    /**
     * 获取参数,为空则返回默认值
     * @param request
     * @param param
     * @param def
     * @return
     * @throws AppException
     */
    public final static String getParameter(HttpServletRequest request, String param, String def)throws AppException {
        String ret = request.getParameter(param);
        if (ret == null || ret.length() <= 0) { ret = def; }
        if(ret != null){
            ret = ret.trim();
            request.setAttribute(param, ret);
        }else{
            request.setAttribute(param, "");
        }
        
        return ret;
    }

    /**
     * 获取参数,根据参数判断是否抛出异常
     * @param request
     * @param param
     * @param checkEmpty
     * @return
     * @throws AppException
     */
    public final static String getParameter(HttpServletRequest request, String param,boolean checkEmpty) throws AppException{
    		return getParameter(request, param,"",checkEmpty);
    }

    /**
     * 获取参数,根据参数判断是否抛出指定信息的异常
     * @param request
     * @param param
     * @param errMsg
     * @param checkEmpty
     * @return
     * @throws AppException
     */
    public final static String getParameter(HttpServletRequest request, String param,String errMsg,boolean checkEmpty) throws AppException {
        return getParameter(request,param,errMsg,checkEmpty,true);
    }
    
    private final static String getParameter(HttpServletRequest request, String param,String errMsg,boolean checkEmpty,boolean safe) throws AppException {
    	String ret = request.getParameter(param);
        if (ret == null) { ret = ""; }
        ret = ret.trim();
        request.setAttribute(param, ret);
        
        if (checkEmpty && (ret.length() == 0)) {
            String localizedMessage = errMsg;
            if(errMsg == null || errMsg.length() <= 0)
            	localizedMessage = "提交的数据项不能为空！";
            throw new AppException(localizedMessage);
        }
        
        if(ret != null && ret.length() > 0){
	        	if(safe)
	        		checkSafe(ret);
        }
        return ret;
    }

    /**
     * 参数值里面带有 HTML 标记 <>
     * @param request
     * @param param
     * @param checkEmpty
     * @return
     * @throws AppException
     */
    public final static String getParameterHtml(HttpServletRequest request,String param) throws AppException {
        String ret = getParameter(request, param,"",false,false);
        return ret;
    }
    
    /**
     * 参数值里面不能带有 HTML 标记 <>
     * @param request
     * @param param
     * @param checkEmpty
     * @return
     * @throws AppException
     */
    public final static String getParameterSafe(HttpServletRequest request,String param, boolean checkEmpty) throws AppException {
        String ret = getParameter(request, param, checkEmpty);
        if ((ret.indexOf('<') != -1) || (ret.indexOf('>') != -1)|| (ret.indexOf("'") != -1)) {
            throw new AppException("提交的数据项不能带有非法字符标记内容！");
        }
        return ret;
    }

    /**
     * 获取整形参数
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static int getParameterInt(HttpServletRequest request, String param) throws AppException {
        String inputStr = getParameter(request, param, true);
        int ret;
        try {
            ret = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
            throw new AppException("提交的数据项非整数型！");
        }
        return ret;
    }
    
    /**
     * 获取无符号整型数
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static int getParameterUnsignedInt(HttpServletRequest request,String param) throws AppException {
        int retValue = getParameterInt(request, param);
        if (retValue < 0) {
            throw new AppException("提交的数据项非整数型！");
        }
        return retValue;
    }
    
    /**
     * 获取整型数,为空则取默认值
     * @param request
     * @param param
     * @param defaultValue
     * @return
     * @throws AppException
     */
    public final static int getParameterInt(HttpServletRequest request, String param,int defaultValue) throws AppException {
        int ret;
        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }
        
        try {
            ret = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
            throw new AppException("提交的数据项非整数型！");
        }
        return ret;
    }
    
    /**
     * 获取整形参数,并指定错误信息
     * @param request
     * @param param
     * @param errMsg
     * @return
     * @throws AppException
     */
    public final static int getParameterInt(HttpServletRequest request, String param,String errMsg) throws AppException {
        int ret = getParameterInt(request, param,-1);
        if(ret == -1){
	        String localizedMessage = errMsg;
	        if(errMsg == null || errMsg.length() <= 0)
	        	localizedMessage = "提交的数据项不能为空！";
	        throw new AppException(localizedMessage);
	    }
        return ret;
    }
    
    /**
     * 获取无符号整型数,并指定默认值
     * @param request
     * @param param
     * @param defaultValue
     * @return
     * @throws AppException
     */
    public final static int getParameterUnsignedInt(HttpServletRequest request, String param, int defaultValue) throws AppException {
        int retValue = getParameterInt(request, param, defaultValue);
        if (retValue < 0) {
            throw new AppException("提交的数据项非整数型！");
        }
        return retValue;
    }
    
    /**
     * 获取长整型参数
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static long getParameterLong(HttpServletRequest request, String param) throws AppException {
        String inputStr = getParameter(request, param, true);
        long ret;
        try {
            ret = Long.parseLong(inputStr);
        }
        catch (NumberFormatException e) {
            throw new AppException("提交的数据项非整数型！");
        }
        return ret;
    }
    
    /**
     * 获取长整型数,并指定默认值
     * @param request
     * @param param
     * @param defaultValue
     * @return
     * @throws AppException
     */
    public final static long getParameterLong(HttpServletRequest request, String param, long defaultValue) throws AppException {
        long ret;
        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }

        try {
            ret = Long.parseLong(inputStr);
        } catch (NumberFormatException e) {
            ret = defaultValue;
        }
        return ret;
    }

    /**
     * 判断是否存在参数
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static boolean getParameterBoolean(HttpServletRequest request,String param) throws AppException {
        String inputStr = getParameter(request, param);
        return inputStr.length() > 0 ;  
    }
    
    /**
     * 判断是否存在参数,并且为结果赋默认值
     * @param request
     * @param param
     * @param def
     * @return
     * @throws AppException
     */
    public final static boolean getParameterBoolean(HttpServletRequest request,String param, boolean def)throws AppException {
        String inputStr = getParameter(request, param);
        boolean t=def;
        if(inputStr.length()>0){
        	   try {
				t=Boolean.parseBoolean(inputStr);
			} catch (Exception e) {
				
			}
        }
        return t;
    }
    
    /**
     * 获取byte类型
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static byte getParameterByte(HttpServletRequest request, String param) throws AppException {
        byte ret;
        String inputStr = getParameter(request, param, true);       
        try {
            ret = Byte.parseByte(inputStr);
        } catch (NumberFormatException e) {
            throw new AppException("提交的数据项非字节型！");
        }
        return ret;
    }
    
    /**
     * 获取double参数
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static double getParameterDouble(HttpServletRequest request,String param) throws AppException {
        double ret;
        String inputStr = getParameter(request, param, true);
        try {
            ret =  ConvertUtils.str2double(inputStr);
        } catch (NumberFormatException e) {
            throw new AppException("提交的数据项非数字型！");
        }
        return ret;
    }
    
    /**
     * 获取double参数,并且赋予默认值
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static double getParameterDouble(HttpServletRequest request,String param,double defaultValue) throws AppException{
        double ret;
        String inputStr = getParameter(request, param, false);
        try {
            if(inputStr != null && inputStr.length() > 0)            
                ret = ConvertUtils.str2double(inputStr);
            else
                ret = defaultValue;
        } catch (NumberFormatException e) {
        	    ret = defaultValue;
        }
        return ret;
    }
    
    /**
     * 获取url类型的字符串
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static String getParameterUrl(HttpServletRequest request,String param) throws AppException {
        String ret = getParameter(request, param);
        if (ret.length() > 0) {
            if (!ret.startsWith("http://") && !ret.startsWith("https://") && !ret.startsWith("ftp://")) {
                  throw new AppException("提交的数据项非URL格式！");
            }
        }
        return ret;
    }

    /**
     * 获取日期(Date)
     * @param request
     * @param param
     * @return java.util.Date Or null
     * @throws AppException
     */
    public final static Date getParameterDate(HttpServletRequest request,String param) throws AppException {
        String inputStr = getParameter(request, param);
        if(inputStr != null && inputStr.length() > 0){
            Date ret = null;
            try {
                ret = dateFormat.parse(inputStr);
            } catch (ParseException e) {
                throw new AppException("提交的数据项非日期格式！");
            }
            return new Date(ret.getTime());
        }else{
            return null;
        }
    }
    
    /**
     * 获取日期(Date),指定日期格式
     * @param request
     * @param param
     * @param Format 日期格式,如:YYYY-MM 或者YYYY-MM-DD
     * @return java.util.Date Or null
     * @throws AppException
     */
    @SuppressWarnings("null")
	public final static Date getParameterDate(HttpServletRequest request,String param,String Format) throws AppException {
        String inputStr = getParameter(request, param);
        if(inputStr != null && inputStr.length() > 0){
            Date ret = null;
            try {
                if(Format == null && Format.length() <= 0)
                    return getParameterDate(request,param);
                
                DateFormat dateFormatTemp = new SimpleDateFormat(Format);
                ret = dateFormatTemp.parse(inputStr);
            } catch (ParseException e) {
                throw new AppException("提交的数据项非有效日期格式！");
            }
            return new Date(ret.getTime());
        }else{
            return null;
        }
    }
    
    /**
     * 获取时区
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static double getParameterTimeZone(HttpServletRequest request,String param) throws AppException {

        double timeZone = getParameterDouble(request, param);
        if (timeZone < -12 || timeZone > 13) {
            timeZone = 0;
        }
        return timeZone;
    }
 

    /**
     * 获取 request 中的属性
     * @param request
     * @param name
     * @return
     */
    public final static String getAttribute(ServletRequest request, String name) {
        Object ret = request.getAttribute(name);
        return ret != null ? ret.toString().trim() : "";
    }
    
    /**
     * 获取字符串数组
     * @param request
     * @param param
     * @return
     */
    public final static String[] getParameterStringValues(HttpServletRequest request,String param) {
        String[] values = request.getParameterValues(param);
        return values != null ? values : new String[0];
    }
    
    /**
     * 获取整形数组参数
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static int[] getParameterIntValues(HttpServletRequest request,String param)throws AppException{
        return getParameterIntValues(request,param, 0);
    }
    
    /**
     * 获取整型数组
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static int[] getParameterIntValues(HttpServletRequest request,String param, int def) throws AppException {
        String[] stringValues = getParameterStringValues(request , param);
        int[] intValues = new int[stringValues.length];
        for(int i = 0 ; i < stringValues.length ; i++) {
            try {      
                intValues[i] = Integer.parseInt(stringValues[i]);
            }
            catch (NumberFormatException e) {
                intValues[i] = def;
            }
        }
        return intValues;
    }
    
    /**
     * 获取double数组
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static double[] getParameterDdoubleValues(HttpServletRequest request,String param) throws AppException {
        return getParameterDdoubleValues(request, param,0);
    }
   
    /**
     * 获取double数组
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static double[] getParameterDdoubleValues(HttpServletRequest request,String param, double def) throws AppException {
        String[] stringValues = getParameterStringValues(request , param);
        double[] intValues = new double[stringValues.length];
        for(int i = 0 ; i < stringValues.length ; i++) {
            try {
               intValues[i] = ConvertUtils.str2double(stringValues[i],def);
            } catch (Exception e) {
                intValues[i] = def;
            }
        }
        return intValues;
    }
    
    /**
     * 获取整形数组
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static long[] getParameterLongValues(HttpServletRequest request,String param) throws AppException {
        return getParameterLongValues(request, param, 0);
    }
    
    /**
     * 获取Long数组
     * @param request
     * @param param
     * @return
     * @throws AppException
     */
    public final static long[] getParameterLongValues(HttpServletRequest request,String param, long def) throws AppException {
        String[] stringValues = getParameterStringValues(request , param);
        long[] intValues = new long[stringValues.length];
        for(int i = 0 ; i < stringValues.length ; i++) {
            try {
                intValues[i] = Long.parseLong(stringValues[i]);
                
            } catch (NumberFormatException e) {
                intValues[i] = def;
            }
        }
        return intValues;
    }
    
    private final static boolean checkSafe(String ret)throws AppException{
        if ((ret.indexOf('<') != -1) || (ret.indexOf('>') != -1)|| (ret.indexOf("'") != -1)) {
            throw new AppException("提交的数据项不能带有非法字符标记内容！");
        } 
        return true;
    }
    
    public static String filter(String input) {
        if (input == null) {
            return null;
        }
        
        char[] s = input.toCharArray();
        int length = s.length;
        StringBuffer ret = new StringBuffer(length + 100);// add more room to the result String

        for (int i = 0; i < length; i++) {
            if (s[i] == '\'') {
                ret.append("\\'");
            } else if (s[i] == '<') {    
                ret.append("&lt;");
            } else if (s[i] == '>') {              
                ret.append("&gt;");
            } else if (s[i] == '"') {              
                ret.append("\\\"");
            } else {
                ret.append(s[i]);
            }
        }
        return ret.toString();
    }
    
    public final static void addCookie(String id,String val,int maxAge,boolean httponly,HttpServletResponse response){
		try{
			Cookie tgc = new Cookie(id, val);
			tgc.setPath("/");
			tgc.setMaxAge(maxAge);
			
			tgc.setHttpOnly(httponly);
			response.addCookie(tgc);
		}catch(Exception ex){

		}
	}
    
    @SuppressWarnings("rawtypes")
	public final static Map getCookie(HttpServletRequest request) {
        if (request == null)
            return null;
        Map<String,String> map = new HashMap<String,String>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                try{             
                		cookieValue = java.net.URLDecoder.decode(cookieValue,"utf-8");
                }catch(Exception ex){
                	
                }
                map.put(cookieName, cookieValue);
            }
        }
        return map;
    }
    
    public static void main(String[] arg){
     	String str = "\" <script>alert('sss');</script>";    	
    		System.out.println(filter(str));
    }
}
