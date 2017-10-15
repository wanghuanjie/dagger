package com.ziyoujiayuan.base.exception;

/**
 * 基础服务异常类
 * 
 * @author wanghjbuf
 * @date 2017年9月11日
 */
@SuppressWarnings("serial")
public class AppException extends Exception
{

    public AppException(String paramString, Throwable paramThrowable)
    {
        super(paramString, paramThrowable);
    }

    public AppException(String paramString)
    {
        super(paramString);
    }

    public AppException()
    {
    }

    public String toString()
    {
        return getMessage();
    }
}
