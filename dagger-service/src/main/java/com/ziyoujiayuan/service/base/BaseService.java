package com.ziyoujiayuan.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.ziyoujiayuan.base.datapager.Pager;
import com.ziyoujiayuan.base.exception.AppException;
import com.ziyoujiayuan.base.utils.ConvertUtils;

/**
 * 基础服务
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
public abstract class BaseService {

    @Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
    /**
     * 查询列表分页
     * @param sql
     * @param paramMap
     * @return
     * @throws AppException
     */
    public Pager queryForPager(String sql, Map<String, Object> paramMap) throws AppException{
    		Map<String, Object> whereMap =  new HashMap<>();
        try{
      	    RowBounds rowBounds;
            Pager Pagerr = null;
      	    int offset = 0;
      	    int limit = 0;

      	    whereMap = filterParamMap(paramMap);
	        	Object startMap =  whereMap.get("start");
	        	if(null == startMap){
	        	   offset = 0;
	        	}else{
	        	   whereMap.remove("start");
	       		    offset = Integer.parseInt(startMap.toString());
	        	}
        	
	        	Object limitMap =  whereMap.get("limit");
	        	if(null == limitMap){
	        		limit = 20;
	        	}else{
	        		whereMap.remove("limit");
	        		limit = Integer.parseInt(limitMap.toString());
	        	}

            List<?> retValue = new ArrayList<Object>();
            
            long count = ConvertUtils.gint(sqlSessionTemplate.selectOne(sql + "_count", whereMap)); //记录总条数   
            if (count <= 0)
                return new Pager();

            if (offset >= count) {
            	   offset = 0;
            }
        	    rowBounds = new RowBounds(offset,limit);

            if(limit > 0){
	    	        long endPager = offset + limit;
	    	        if (endPager >= count){
	    	            endPager = count;
	    	        }
	    	        whereMap.put("end", endPager);
	    	        
	    	        retValue = sqlSessionTemplate.selectList(sql, whereMap, rowBounds);
	    	        Pagerr = new Pager(offset, count, limit, retValue);
            }else{
            		retValue = sqlSessionTemplate.selectList(sql, whereMap, rowBounds);
                Pagerr = new Pager(0, count, 20, retValue);
            }
            
            return Pagerr;
        } catch (Exception e){
            throw new AppException("执行SQL=" + sql + "出错",e);
        }
    }
    
    /**
     * 获取查询对象
     * @param sql
     * @param paramMap
     * @return
     * @throws AppException
     */
    public Object queryObject(String sql,  Map<String, Object> paramMap) throws AppException {
	    	Map<String, Object> whereMap =  new HashMap<String, Object>();
	    	Object result = new Object();
      	try{
	    		whereMap = filterParamMap(paramMap);
	    		result = sqlSessionTemplate.selectOne(sql, whereMap);
         }catch (Exception e){
            throw new AppException("执行SQL=" + sql + "出错",e);
         }
     	return result;
    }
    
    /**
     * 获取查询对象
     * @param sql
     * @param paramMap
     * @return
     * @throws AppException
     */
    public Object queryObject(String sql) throws AppException {
    		Object result = new Object();
	    	try{
	    		result = sqlSessionTemplate.selectOne(sql);
	    }catch (Exception e){
	        throw new AppException("执行SQL=" + sql + "出错",e);
	    }
	    	return result;
    }
    
    /**
     * 获取查询列表
     * @param sql
     * @param paramMap
     * @return
     * @throws AppException
     */
    public List<?> queryForList(String sql,  Map<String, Object> paramMap) throws AppException {
    		Map<String, Object> whereMap =  new HashMap<String, Object>();
    		try{
    			whereMap = filterParamMap(paramMap);
            return sqlSessionTemplate.selectList(sql, whereMap);
        } catch (Exception e){
            throw new AppException("执行SQL=" + sql + "出错",e);
        }
    }

    /**
     * 获取查询列表
     * @param sql
     * @return
     * @throws AppException
     */
    public List<?> queryForList(String sql) throws AppException {
    		try{
            return sqlSessionTemplate.selectList(sql);
    		}catch(Exception e){
    			throw new AppException("执行SQL=" + sql + "出错",e);
    		}
    } 
    
    /**
     * 插入数据操作
     * @param sql
     * @param paramMap
     * @throws AppException
     */
    public void insert(String sql, Map<String, Object> paramMap) throws AppException {
    		Map<String, Object> whereMap =  new HashMap<String, Object>();
    		try{
    			whereMap = filterParamMap(paramMap);
    			sqlSessionTemplate.insert(sql, whereMap);
    		}catch(Exception e){
    			throw new AppException("执行SQL=" + sql + "出错",e);
    		}
    }
    
    /**
     * 插入操作
     * @param sql
     * @param paramMap
     * @throws AppException
     */
    public void insert(String sql) throws AppException {
	    	try{
	    		sqlSessionTemplate.insert(sql);
	    	}catch(Exception e){
	    		throw new AppException("执行SQL=" + sql + "出错",e);
	    	}
    }

    /**
     * 更新操作
     * @param sql
     * @param where
     * @return
     * @throws Exception
     */
	public int update(String sql, Map<String, Object> paramMap) throws AppException{
	    	Map<String, Object> whereMap =  new HashMap<String, Object>();
	    	int result;
	    	try{
	    		whereMap = filterParamMap(paramMap);
	    		result = sqlSessionTemplate.update(sql,whereMap);
	    	}catch(Exception e){
	    		throw new AppException("执行SQL=" + sql + "出错",e);
	    	}
	    	return result;
    }
    
    /**
     * 更新操作
     * @param sql
     * @param where
     * @return
     * @throws Exception
     */
	public int update(String sql) throws AppException{
	    	int result;
	    	try{
	    		result = sqlSessionTemplate.update(sql);
	    	}catch(Exception e){
	    		throw new AppException("执行SQL=" + sql + "出错",e);
	    	}
	    	return result;
    }
    
    
    /**
     * 删除操作
     * @param sql
     * @param where
     * @return
     * @throws Exception
     */
	public int delete(String sql, Map<String, Object> paramMap) throws AppException{
	    	Map<String, Object> whereMap =  new HashMap<String, Object>();
	    	int result;
	    	try{
	    		whereMap = filterParamMap(paramMap);
	    		result = sqlSessionTemplate.delete(sql,whereMap);
	    	}catch(Exception e){
	    		throw new AppException("执行SQL=" + sql + "出错",e);
	    	}
	    	return result;
    }
    
    /**
     * 删除操作
     * @param sql
     * @param where
     * @return
     * @throws Exception
     */
	public int delete(String sql) throws AppException{
	    	int result;
	    	try{
	    		result = sqlSessionTemplate.delete(sql);
	    	}catch(Exception e){
	    		throw new AppException("执行SQL=" + sql + "出错",e);
	    	}
	    	return result;
    }
    
    /**
     * 过滤输入结果
     * @param paramMap
     * @return
     */
    @SuppressWarnings("rawtypes")
	private Map<String, Object> filterParamMap(Map<String, Object> paramMap){
	    	for(Iterator its = paramMap.entrySet().iterator(); its.hasNext();) {
			Map.Entry entrys = (Map.Entry) its.next();
			String key = ""+entrys.getKey();
			if(entrys.getValue()  != null){
				if(entrys.getValue()  instanceof String){				
					paramMap.put(key, filter(String.valueOf(entrys.getValue() )));
				}
			}
	    	}
	    	return paramMap;
    }
    

    /**
     * 防止sql注入请求
     * @param input
     * @return
     */
	private String filter(String input) {
	    if (input == null) {
	        return null;
	    }
	    
	    String t = input.toLowerCase(); 
	    if(t.indexOf("join ")!= -1 ||t.indexOf("execute ")!= -1 ||t.indexOf("exec ")!= -1 ||t.indexOf("union ")!= -1 ||
	       t.indexOf("join ")!= -1 ||t.indexOf("join ")!= -1 ||t.indexOf("join ")!= -1 ||t.indexOf("join ")!= -1 ||
	       t.indexOf("where ")!= -1 ||t.indexOf("create ")!= -1 ||t.indexOf("drop ")!= -1 ||t.indexOf("select ")!= -1 ||
	       t.indexOf("delete ")!= -1 ||t.indexOf("update ")!= -1 ||t.indexOf("insert ")!= -1 ){
	    	
	    	   input = input.toLowerCase();
	       input = input.replaceAll("join\\s+", "");
	       input = input.replaceAll("execute\\s+", "");
	       input = input.replaceAll("exec\\s+", "");
	       input = input.replaceAll("union\\s+", "");
	       input = input.replaceAll("where\\s+", "");
	       input = input.replaceAll("create\\s+table", "");
	        
	       input = input.replaceAll("drop\\s+table", "");
	       input = input.replaceAll("select\\s+", "");
	       input = input.replaceAll("delete\\s+from", "");
	       input = input.replaceAll("update\\s+", "");
	       input = input.replaceAll("insert\\s+into", "");
	    }
	    input = input.replaceAll("--", "");
	    input = input.replaceAll("--\\s+", "");
	    input = input.replaceAll("-\\s+", "");
	    
	    char[] s = input.toCharArray();
	    int length = s.length;
	    StringBuffer ret = new StringBuffer(length + 100);// add more room to the result String
	    for (int i = 0; i < length; i++) {
	        if (s[i] == '\'') {
	            ret.append("");
	        } else if (s[i] == '"') {              
	            ret.append("");
	        } else if (s[i] == ';') {              
	            ret.append("");
	        } else {
	            ret.append(s[i]);
	        }
	    }
	    
	    return ret.toString();
	}
}
