package com.ziyoujiayuan.base.utils;

import java.text.DecimalFormat;

/**
 * 转化工具
 * @Author wanghjbuf
 * @Date 2017年10月17日
 */
public class ConvertUtils {

	/**
	 * 小数的四舍五入
	 * 
	 * @param in 浮点型小数
	 * @return 四舍五入后的整型数据
	 */
	public static int float2int(float in) {
		int flag = 1;
		if (in < 0)
			flag = -1;
		int out = (int) (Math.abs(in) + 0.5000001);
		return out * flag;
	}
	
	/**
	 * 小数的四舍五入
	 * 
	 * @param in 双精度小数
	 * @return 长整型
	 */
	public static long double2long(double in)
	{
		long flag = 1;
		if (in < 0)
			flag = -1;
		long out = (long) (Math.abs(in) + 0.5000001);
		return out * flag;
	}

	/**
	 * 字符串转换为int型
	 * 
	 * @param str 数字字符串
	 * @param defaultValue 缺省值
	 * @return 转换后的整型
	 */
	public static int str2int(String str, int defaultValue)
	{
		try
		{
			return new Integer(str.trim()).intValue();
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为int型<br>
	 * 缺省值为0
	 * 
	 * @param str 数字字符串
	 * @return 转换后的整型数据
	 */
	public static int str2int(String str)
	{
		return str2int(str, 0);
	}

	/**
	 * 将其他类型的数字转换为int<br>
	 * 如果obj不是Number的子类，则必须实现toString方法来返回数字的字符串<br>
	 * 否则返回0
	 * 
	 * @param obj
	 * @return 转换后的整型数据
	 */
	public static int gint(Object obj)
	{
		int res = 0;
		if (obj == null)
		{
		}
		else if (obj instanceof Number)
		{
			res = ((Number) obj).intValue();
		}
		else
		{
			res = str2int(obj.toString());
		}
		return res;
	}

	/**
	 * 字符串转换为长整型
	 * 
	 * @param str 数字字符串"1234567890"
	 * @param defaultValue 缺省值
	 * @return 转换后的长整型
	 */
	public static long str2long(String str, long defaultValue)
	{
		try
		{
			return new Long(str.trim()).longValue();
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为长整型<br>
	 * 缺省值为0
	 * 
	 * @param str 数字字符串"1234567890"
	 * @return 转换后的长整型
	 */
	public static long str2long(String str)
	{
		return str2long(str, 0);
	}

	/**
	 * 见gint的说明
	 * 
	 * @param obj
	 * @return
	 */
	public static long glong(Object obj)
	{
		long res = 0;
		if (obj == null)
		{
		}
		else if (obj instanceof Number)
		{
			res = ((Number) obj).longValue();
		}
		else
		{
			// System.out.println("string="+obj.toString());
			res = str2long(obj.toString());
		}
		return res;
	}

	/**
	 * 字符串转换为浮点型
	 * 
	 * @param str 数字型的字符串"123456.123456"
	 * @param defaultValue 缺省值
	 * @return 转换后的浮点型值
	 */
	public static float str2float(String str, float defaultValue)
	{
		try
		{
			return new Float(str.trim()).floatValue();
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为浮点型<br>
	 * 缺省值为0
	 * 
	 * @param str 数字型的字符串"123456.123456"
	 * @return 转换后的浮点型值
	 */
	public static float str2float(String str)
	{
		return str2float(str, 0);
	}

	/**
	 * 见gint的说明
	 * 
	 * @param obj
	 * @return
	 */
	public static float gfloat(Object obj)
	{
		float res = 0;
		if (obj == null)
		{
		}
		else if (obj instanceof Number)
		{
			res = ((Number) obj).floatValue();
		}
		else
		{
			res = str2float(obj.toString());
		}
		return res;
	}

	/**
	 * 字符串转换为双精度型
	 * 
	 * @param str 数字型的字符串"123456.123456"
	 * @param defaultValue 缺省值
	 * @return 转换后的双精度型
	 */
	public static double str2double(String str, double defaultValue)
	{
		try
		{
			str = str.trim();
			
			char[] s = str.toCharArray();
			StringBuffer ret = new StringBuffer();
			int ct = 0;
			boolean star = false;
			for(int i= 0;i < s.length;i++){
				
				if(star){ct ++;}
				
				if(ct > 6){break;}
				
				if(s[i] == '.'){
					star = true;
				}
				
				ret.append(s[i]);
			}
			str = ret.toString();
			return new Double(str).doubleValue();
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为双精度型<br>
	 * 缺省值为0
	 * 
	 * @param str 数字型的字符串"123456.123456"
	 * @return 转换后的双精度型
	 */
	public static double str2double(String str)
	{
		return str2double(str, 0);
	}

	/**
	 * 见gint的说明
	 * 
	 * @param obj
	 * @return
	 */
	public static double gdouble(Object obj)
	{
		double res = 0;
		if (obj == null)
		{
		}
		else if (obj instanceof Number)
		{
			res = ((Number) obj).doubleValue();
		}
		else
		{
			res = str2double(obj.toString());
		}
		return res;
	}

	/**
	 * 见gint的说明
	 * 
	 * @param obj
	 * @return
	 */
	public static String gstring(Object obj)
	{
		String s = "";
		if (obj != null)
		{
			if (obj instanceof String)
			{
				s = (String) obj;
			}
			else
			{
				s = obj.toString();
			}
		}
		return s;
	}

	/**
	 * 货币转换接口：元-->分
	 * 
	 * @param yuan 元
	 * @return 返回转换后的: 分
	 */
	public static long yuan2fen(double yuan)
	{
		return (long) (Math.round(yuan * 100));
	}

	/**
	 * 货币转换接口：分-->元
	 * 
	 * @param fen 分
	 * @return 返回转换后的: 元
	 */
	public static double fen2yuan(long fen)
	{
		return fen / 100.00;
	}

	/**
	 * 货币转换接口：分-->元 ,格式化 数字字符串#.00
	 * 
	 * @param fen 分
	 * @return 返回转换后的: String
	 */
	public static String formatFen2yuan(long fen)
	{
		DecimalFormat db = new DecimalFormat("#0.00");
		return db.format(fen2yuan(fen));
	}

	/**
	 * 货币转换接口：元-->分 ,格式化 数字字符串
	 * 
	 * @param yuan 元
	 * @return 返回转换后的: String
	 */
	public static String formatYuan2fen(double yuan)
	{
		DecimalFormat db = new DecimalFormat("#");

		// System.out.println("inner = " + fen / 100.00);
		return db.format(yuan2fen(yuan));
	}


	/**
	 * 价格转换，根据价格转化系数对价格进行处理，进入系统调用该方法
	 * 
	 * @param _price 价格
	 * @param _rate 价格转换系数
	 * @return
	 */
	public static long convertPriceByRate(long _price, long _rate)
	{
		return _price * _rate;
	}

	/**
	 * 价格转换，根据价格转化系数对价格进行处理，显示给用户调用该方法
	 * 
	 * @param _price 价格
	 * @param _rate 价格转换系数
	 * @return
	 */
	public static long reversePriceByRate(long _price, long _rate)
	{
		if (_rate == 0)
		{
			_rate = 1;
		}
		return _price / _rate;
	}

	/**
	 * 价格转换，根据价格转化系数对价格进行处理，进入系统调用该方法。包含元分转换，只有价格使用。
	 * 
	 * @param _price 价格
	 * @param _rate 价格转换系数
	 * @return
	 */
	public static long convertPrice(long _price, long _rate)
	{
		return yuan2fen(_price * _rate);
	}

	/**
	 * 价格转换，根据价格转化系数对价格进行处理，显示给用户调用该方法。包含元分转换，只有价格使用。
	 * 
	 * @param _price 价格
	 * @param _rate 价格转换系数
	 * @return
	 */
	public static double reversePrice(long _price, long _rate)
	{
		if (_rate == 0)
		{
			_rate = 1;
		}
		return fen2yuan(_price / _rate);
	}

	/**
	 * 时间转换接口 秒 转 分
	 * 
	 * @param miao 秒
	 * @return 分
	 */
	public static double miao2fen(long miao)
	{
		return miao / 60.00;
	}

	/**
	 * 时间格式化转换接口 秒 转 分
	 * 
	 * @param miao 秒
	 * @return 分
	 */
	public static String formatMiao2fen(long miao)
	{
		double fen = miao2fen(miao);
		DecimalFormat decimalFormat = new DecimalFormat(":00");
		return (long) fen + decimalFormat.format((fen - (long) fen) * 60);
	}

	/**
	 * 字节单位转换接口 byte 转 kiloByte
	 * 
	 * @param _byte
	 * @return
	 */
	public static double byte2kiloByte(long _byte)
	{
		return _byte / 1024.0;
	}

	/**
	 * 字节单位格式化转换接口 byte 转 kiloByte
	 * 
	 * @param _byte
	 * @return
	 */
	public static String formatByte2kiloByte(long _byte)
	{
		DecimalFormat decimalFormat = new DecimalFormat("0.###");
		return decimalFormat.format(byte2kiloByte(_byte));
	}
}
