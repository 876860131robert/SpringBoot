package com.robert.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 字符串工具类
 * 
 * 
 */
public class StringUtil {
	/**
	 * html文件中的特定替换
	 * 
	 * @param source
	 *            String 源字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlEncode(String source) {
		source = StringUtils.replace(source, "&", "&amp;");
		source = StringUtils.replace(source, "'", "&quot;");
		source = StringUtils.replace(source, "<", "&lt;");
		source = StringUtils.replace(source, ">", "&gt;");
		source = StringUtils.replace(source, " ", "&nbsp;");
		source = StringUtils.replace(source, "\"", "&quot;");
		return source;
	}

	/**
	 * 替换回hmtl中的格式
	 * 
	 * @param source
	 *            String 源字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlDecode(String source) {
		source = StringUtils.replace(source, "&amp;", "&");
		source = StringUtils.replace(source, "&quot;", "'");
		source = StringUtils.replace(source, "&lt;", "<");
		source = StringUtils.replace(source, "&gt;", ">");
		source = StringUtils.replace(source, "&nbsp;", " ");
		source = StringUtils.replace(source, "&quot;", "\"");
		return source;
	}

	/**
	 * 将Html中的<br>
	 * 替换为回车
	 * 
	 * @param source
	 *            String 源字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlBrToLine(String source) {
		source = StringUtils.replace(source, "<br>", "\r\n");
		return source;
	}

	/**
	 * 将回车替换为html中的<br>
	 * 
	 * @param source
	 *            String 源字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlLineToBr(String source) {
		source = StringUtils.replace(source, "\r\n", "<br>");
		source = StringUtils.replace(source, "\n", "<br>");
		return source;
	}

	/**
	 * 中文转换为UNICODE字符
	 * 
	 * @param source
	 *            String 源字符串
	 * @return String 替换后的字符串
	 */
	public static String gbToUnicode(String source) {
		try {
			return new String(source.getBytes("ISO8859_1"), "gb2312");
		} catch (Exception e) {
			return source;
		}
	}

	/**
	 * UNICODE字符转换为中文
	 * 
	 * @param source
	 *            String 源字符串
	 * @return String 替换后的字符串
	 */
	public static String unicodeToGB(String source) {
		try {
			return new String(source.getBytes("gb2312"), "ISO8859_1");
		} catch (Exception e) {
			return source;
		}
	}

	/**
	 * 将中文文件名转为utf8格式
	 * 
	 * @param s
	 *            原中文文件名
	 * @return 转换后的utf8格式文件名
	 */
	public static String gbToUtf8(String s) {
		if (s == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = String.valueOf(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}

				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 检查两个string是否相同，都为空返回true
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalsIfEmpty(String str1, String str2) {
		return StringUtils.trimToEmpty(str1).equals(StringUtils.trimToEmpty(str2));
	}

	/**
	 * 按照分隔符";"将字符串转成list
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static List<String> string2ListBySemiColon(String sourceStr) {
		return string2ListByDelimiter(sourceStr, ";");
	}

	/**
	 * 按照分隔符将字符串转成list
	 * 
	 * @param sourceStr
	 * @param delimiter
	 *            分隔符
	 * @return List{String}
	 */
	public static List<String> string2ListByDelimiter(String sourceStr, String delimiter) {
		List<String> targetList = new ArrayList<String>();
		if (StringUtils.isBlank(sourceStr)) {
			return targetList;
		}
		StringTokenizer token = new StringTokenizer(sourceStr, delimiter);
		while (token.hasMoreTokens()) {
			targetList.add(token.nextToken());
		}
		return targetList;
	}

	/**
	 * 数字转换
	 * 
	 * @param str
	 * @return
	 */
	public static String string2Cn(String str) {
		String[] b = { "○", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		return b[Integer.parseInt(str)];
	}

	/**
	 * toSTring
	 * 
	 * @param obj
	 * @return
	 */
	public static String objToString(Object obj) {
		if (obj == null || obj.toString().equalsIgnoreCase("null")) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}

	/**
	 * 
	 * @param obj
	 * @param defaultStr
	 * @return
	 */
	public static String objToStringWithDefault(Object obj, String defaultStr) {
		String ret = objToString(obj);
		if (ret.length() < 1) {
			return defaultStr;
		} else {
			return ret;
		}
	}

	/**
	 * 将List转换成String，用‘，’隔开
	 * 
	 * @param list
	 * @return
	 */
	public static String list2String(Collection<?> list) {

		StringBuffer sbf = new StringBuffer();
		if (list.isEmpty() || list.size() < 1) {
			return sbf.toString();
		}

		for (Object obj : list) {
			if (obj instanceof java.util.Date) {
				sbf.append(",").append(TimeUtil.format((java.util.Date) obj, TimeUtil.FORMAT_NORMAL));
			} else {
				sbf.append(",").append(obj);
			}
		}
		return sbf.toString();
	}

	/**
	 * list转换为string
	 * 
	 * @param list
	 * @param a
	 * @param b
	 * @return
	 */
	public static String list2String(Collection<String> list, String a, String b) {

		StringBuffer sbf = new StringBuffer();
		if (list.isEmpty() || list.size() < 1) {
			return sbf.toString();
		}

		for (String str : list) {
			sbf.append(b).append(a).append(str).append(a);
		}
		return sbf.toString().substring(b.length());
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String convert2String(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}

	/**
	 * 
	 * 字符数组变成字符串
	 * 
	 * 
	 * 
	 * @param arr
	 * @return
	 */
	public static String array2String(String[] arr) {
		return array2String(arr, "");
	}

	/**
	 * 
	 * @param arr
	 * @param spacer
	 * @return
	 */
	public static String array2String(String[] arr, String spacer) {
		if (arr != null) {
			StringBuffer sbf = new StringBuffer();
			for (String str : arr) {
				sbf.append(",").append(spacer).append(str).append(spacer);
			}
			return sbf.substring(1).toString();
		}
		return "";
	}

	/**
	 * 
	 * @param arr
	 * @param spacer
	 * @return
	 */
	public static String array2String(Object[] arr, String spacer) {
		if (arr != null) {
			StringBuffer sbf = new StringBuffer();
			for (Object str : arr) {
				sbf.append(",").append(spacer).append(str).append(spacer);
			}
			return sbf.substring(1).toString();
		}
		return "";
	}

	/**
	 * 字符串变成字符数组
	 * 
	 * @param keyWork
	 * @return
	 */
	public static String[] string2Array(String keyWord) {
		keyWord = objToString(keyWord);
		StringTokenizer tokenizer = new StringTokenizer(keyWord, ",");
		String[] keywordArr = { "", "", "", "" };
		for (int i = 0; tokenizer.hasMoreTokens(); i++) {
			keywordArr[i] = tokenizer.nextToken();
		}
		return keywordArr;
	}

	/**
	 * 时间字符串-添加最小后缀
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toMinDate(String dateStr) {
		return dateStr + " 0:0:0.000";
	}

	/**
	 * 时间字符串-添加最大后缀
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toMaxDate(String dateStr) {
		return dateStr + " 23:59:59.999";
	}

	/**
	 * 去掉右边的指定字符
	 * 
	 * 
	 * 
	 * @param accuserAddress
	 * @param string
	 * @return
	 */
	public static String rightTrimStr(String source, String str) {
		while (source.endsWith(str)) {
			source = source.substring(0, source.length() - str.length());
		}
		return source;
	}

	/**
	 * 去掉左边的指定字符
	 * 
	 * @return xie
	 */

	public static String leftTrimStr(String source, String str) {
		while (source.startsWith(str)) {
			source = source.substring(str.length(), source.length());
		}
		return source;
	}

	/**
	 * 获取指定字符串
	 * 
	 * 
	 * 
	 * @param length
	 * @param str
	 * @return
	 */
	public static String getLimitString(int length, String str) {
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < length; i++) {
			ret.append(str);
		}
		return ret.toString();
	}

	/**
	 * 获取指定字符串
	 * 
	 * <br/>
	 * 例如： <br/>
	 * ('1',5,'0') --> 00001; <br/>
	 * ('2',4,'0') --> 0002;
	 * 
	 * @param length
	 * @param str
	 * @return
	 */
	public static String getLimitString(String source, int length, String str) {
		StringBuffer ret = new StringBuffer(source);
		for (int i = 0; i < length; i++) {
			ret.append(str);
		}
		return ret.substring(0, length);
	}

	/**
	 * 
	 * @param authoritys
	 * @return
	 */
	public static List<String> array2List(String[] authoritys) {
		return Arrays.asList(authoritys);
	}

	/**
	 * 将数组转换为map
	 * 
	 * @param array
	 * @return
	 */
	public static Map<String, Integer> array2Map(String[] array) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int idx = 0; idx < array.length; idx++) {
			map.put(array[idx], idx);
		}
		return map;
	}

	/**
	 * 字符串中相同字符的个数
	 * 
	 * @param str
	 * @param ch
	 * @return
	 */
	public static int getCharCount(String str, char ch) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				count += 1;
			}
		}
		return count;

	}

	public static String contractString(String strSource, String strAdd, String strFlag) {

		if (StringUtils.isEmpty(strSource)) {
			return strAdd;
		} else {
			return strSource + strFlag + strAdd;
		}
	}

	/**
	 * 将长字符串只显示前15个
	 * 
	 * @param str
	 * @return
	 */
	public static String getLongToShort(String str) {
		if (str.trim().length() > STRINGLENGTH4SHOW) {
			str = new StringBuilder(str.trim().substring(0, STRINGLENGTH4SHOW)).append("...").toString();
		}
		return str;
	}

	/**
	 * 将长字符串前lengthLimit个
	 * 
	 * @param str
	 * @return
	 */
	public static String getLongToShort(String str, int lengthLimit) {
		if (str.trim().length() > lengthLimit) {
			str = new StringBuilder(str.trim().substring(0, lengthLimit)).append("...").toString();
		}
		return str;
	}

	/**
	 * 获取固定长度的字符串
	 * 
	 * @param str
	 * @param fixedLength
	 * @return
	 */
	public static String getFixedLength(String str, int fixedLength) {
		if (str.length() > fixedLength) {
			return str.substring(0, fixedLength);
		}
		return str;
	}

	public static final int STRINGLENGTH4SHOW = 15;

	// add by songxiaoliang 2012-7-16 | start
	static private DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	static public Random secureRand = new Random(new Date().getTime());

	/**
	 * 字符串转换成数组
	 * 
	 * @param str
	 *            字符串
	 * @param delimiters
	 *            分隔符
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return (String[]) tokens.toArray(new String[tokens.size()]);
	}

	/**
	 * 转换成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	/**
	 * 转换成小写
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	/**
	 * 根据数组生成id字符串,供查询是的in条件使用
	 * 
	 * @param ids
	 * @return
	 */
	public static String idArrayToString(Object[] ids) {
		if ((ids == null) || (ids.length == 0)) {
			return "''";
		}

		String result = "";

		for (int i = 0; i < ids.length; i++) {
			result += ("'" + ids[i] + "',");
		}

		result = result.substring(0, result.length() - 1);

		return result;
	}

	/**
	 * 根据数组生成id字符串,供查询是的in条件使用
	 * 
	 * @param ids
	 * @return
	 */
	public static String idArrayToString(int[] ids) {
		if ((ids == null) || (ids.length == 0)) {
			return "''";
		}

		String result = "";

		for (int i = 0; i < ids.length; i++) {
			result += ("'" + ids[i] + "',");
		}

		result = result.substring(0, result.length() - 1);

		return result;
	}

	/**
	 * 根据数组生成id字符串,供查询是的in条件使用
	 * 
	 * @param ids
	 * @return
	 */
	public static String idArrayToString(long[] ids) {
		if ((ids == null) || (ids.length == 0)) {
			return "''";
		}

		String result = "";

		for (int i = 0; i < ids.length; i++) {
			result += ("'" + ids[i] + "',");
		}

		result = result.substring(0, result.length() - 1);

		return result;
	}

	/**
	 * 功能：将字符串数据转换成ArrayList
	 * 
	 * @param strArray
	 * @return
	 */
	public static List toArrayList(String[] strArray) {
		List result = new ArrayList();
		if (null == strArray || strArray.length == 0) {
			return result;
		}

		for (int i = 0; i < strArray.length; i++) {
			result.add(strArray[i]);
		}

		return result;
	}

	/**
	 * 功能：去除指定的字符(串)
	 * 
	 * @param oriStr
	 * @param c
	 * @return
	 */
	public static String trim(String oriStr, String c) {
		return oriStr.replaceAll(c, "");
	}

	/**
	 * 把""转换null
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str != null && str.length() == 0) {
			return null;
		} else {
			return str;
		}
	}

	/**
	 * 把null转换""
	 * 
	 * @param str
	 * @return
	 */
	public static String charNull(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 过滤字符串中的无效字符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceEnter(String str) {

		return str.replaceAll("\\s*", "");
	}

	public static String unicode(byte[] oriData) {
		String strData = null;
		try {
			if (oriData.length > 2) {
				if (oriData[0] != 0xFF && oriData[1] != 0xFE) {
					byte[] unicodeData = new byte[oriData.length + 2];
					unicodeData[0] = (byte) 0xFF;
					unicodeData[1] = (byte) 0xFE;
					System.arraycopy(oriData, 0, unicodeData, 2, oriData.length);

					oriData = unicodeData;
				}
				if (oriData[oriData.length - 1] == 0x0 && oriData[oriData.length - 2] == 0x0)
					strData = new String(oriData, 0, oriData.length - 2, "UNICODE");
				else {
					strData = new String(oriData, "UNICODE");
				}

			}
		} catch (UnsupportedEncodingException e) {
			strData = null;
			e.printStackTrace();
		}

		return strData;
	}

	/**
	 * 产生一个随机的字符串（大小写字母和数字的组合）
	 * 
	 * @param size
	 *            指定长度
	 * @return
	 */
	public static String getRandomString(int size) {// 随机字符串
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(secureRand.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	/**
	 * 产生一个随机的字符串
	 * 
	 * @param size
	 *            指定长度
	 * @return
	 */
	public static String getRandomNumber(int size) {// 随机字符串
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(secureRand.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	/**
	 * 把double类型数字专函成money格式(保留两位小数,不足两位用0补齐,超过两位四舍五入)
	 * 
	 * @param money
	 *            double类型数字
	 * @return
	 */
	public static String formatMoney(double money) {
		if (money == 0) {
			return "0.00";
		} else {
			return decimalFormat.format(money);
		}
	}

	public static String formatMoney(long money) {
		if (money == 0) {
			return "0.00";
		} else {
			return decimalFormat.format(money);
		}
	}

	/**
	 * 在字符串左端补齐
	 */
	public static String leftPadding(String text, int len, char c) {
		StringBuffer buffer = null;
		int curSize = 0;

		if (text != null) {
			curSize = text.length();
			buffer = new StringBuffer(text);
		} else {
			curSize = 0;
			buffer = new StringBuffer();
		}

		for (; curSize < len; curSize++) {
			buffer.insert(0, c);
		}

		return buffer.toString();
	}

	/**
	 * 在字符串右端补齐
	 */
	public static String rightPadding(String text, int len, char c) {
		StringBuffer buffer = null;
		int curSize = 0;

		if (text != null) {
			curSize = text.length();
			buffer = new StringBuffer(text);
		} else {
			curSize = 0;
			buffer = new StringBuffer();
		}

		for (; curSize < len; curSize++) {
			buffer.append(c);
		}

		return buffer.toString();
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		String type = "";
		if (fileName == null || fileName.equals(""))
			return type;
		int position = fileName.lastIndexOf(".");
		if (position != -1) {
			type = fileName.substring(position + 1, fileName.length());
		}
		return type;
	}

	/**
	 * 将字符串，按分符数。 分成一个字符数组
	 * 
	 * @param splitMsg
	 * @param splitNum
	 * @return
	 */
	public static String[] getSplitByNumber(String splitMsg, int splitNum) {
		List<String> msgs = new ArrayList<String>();
		if (splitMsg.length() <= splitNum) {
			return new String[] { splitMsg };
		}
		while (splitMsg != null) {
			String msg = null;
			if (splitMsg.length() > splitNum) {
				msg = splitMsg.substring(0, splitNum);
				splitMsg = splitMsg.substring(splitNum);
			} else {
				msg = splitMsg.substring(0);
				splitMsg = null;
			}
			msgs.add(msg);
		}

		return msgs.toArray(new String[msgs.size()]);
	}

	// add by songxiaoliang 2012-7-16 | end

	/**
	 * 分割字数组
	 * 
	 * @param ary
	 * @param subSize
	 * @return
	 */
	public static Object[] splitAry(Object[] ary, int subSize) {
		int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

		List<List<Object>> subAryList = new ArrayList<List<Object>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			List<Object> list = new ArrayList<Object>();
			int j = 0;
			while (j < subSize && index < ary.length) {
				list.add(ary[index++]);
				j++;
			}

			subAryList.add(list);
		}

		Object[] subAry = new Object[subAryList.size()];

		for (int i = 0; i < subAryList.size(); i++) {
			List<Object> subList = subAryList.get(i);

			Object[] subAryItem = new Object[subList.size()];
			for (int j = 0; j < subList.size(); j++) {
				subAryItem[j] = subList.get(j);
			}

			subAry[i] = subAryItem;
		}

		return subAry;
	}
	
	/**
	 * 拼接url
	 * @param preUrl
	 * @param postUrl
	 * @return
	 */
	public static String joinUrl(String preUrl, String postUrl){
		String url = preUrl;
		if(url.lastIndexOf('/')!=url.length()-1){
			url += '/';
		}
		if(postUrl.indexOf('/')==0){
			postUrl = postUrl.substring(1);
		}

		return url + postUrl;
	}
	
	/**
	 * 给url添加参数
	 * @param url
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public static String joinParam(String url, String paramName, String paramValue){
		if (url.indexOf("?") != -1) {
			if(url.lastIndexOf("&")==url.length()-1){
				url = url.substring(0, url.length()-1);
			}
			url = url + "&" + paramName + "=" + paramValue;
		} 
		else {
			url = url + "?" + paramName + "=" + paramValue;
		}
		
		return url;
	}	
	
	public static String fmtTextForHtml(String text){
		if(StringUtils.isNotEmpty(text)){
			text = text.replaceAll("\r\n", "<br/>");
			text = text.replaceAll("\n", "<br/>");
			text = text.replaceAll(" ", "&nbsp;");
			return text;
		}
		return "";
	}
	
	public static String utf8ToStr(String s, String charsetname) {
		String ret = "null";
		try {
			ret = java.net.URLDecoder.decode(s, charsetname);
		} catch (UnsupportedEncodingException ex) {

		}
		return ret;
	}

	/**
	 * @return 获得优惠
	 */
	public static String getFavour(String s) {
		if (s == null || s.equals(""))
			return "";
		String sign = s.substring(0, 1);
		StringTokenizer st = new StringTokenizer(s, "$");
		int cnt = 0;
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken().trim();
			int i = tmp.indexOf(",");
			if (i != -1) {
				try {
					cnt = cnt + Integer.parseInt(tmp.substring(i + 1));
				} catch (Exception e) {
					// ignore
				}
			}
		}
		// 判断是否分钟
		if (sign != null && sign.equals("1")) {
			cnt = cnt * 60;
		}
		return String.valueOf(cnt);
	}
	
	

	/**
	 * 将首写字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String getFirstUpper(String str) {
		String tmp = "";
		str = trim(str);
		if (!str.equals("")) {
			if (str.length() > 1)
				tmp = toUpperCase(str.substring(0, 1))
						+ str.substring(1, str.length());
			else
				tmp = toUpperCase(str);
		}

		return tmp;
	}


	public static String trim(String s) {
		return getNotNullString(s);
	}

	/*
	 * 若String为null或"",则转换为{}
	 */
	public static String blankToBracket(String str) {
		if (str == null || str.equals(""))
			return "{}";
		return str;
	}

	/*
	 * 若String为null或"",则转换为{}
	 */
	public static String blankToZero(String str) {
		if (str == null || str.equals(""))
			return "0";
		return str;
	}

	/**
	 * 获得一个前边补n个var的src字段
	 * 
	 * @param src
	 * @param n
	 * @param var
	 * @return
	 */
	public static String getTrimString(String src, int n, String var) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < n; i++) {
			buf.append(var);
		}
		buf.append(src);

		return buf.toString();
	}

	/**
	 * 获得一个修饰后的定长字串<br>
	 * 注：长度=conzt字串的长度
	 * 
	 * @param conzt
	 *            常量字串
	 * @param var
	 *            变量字串
	 * @return String
	 */
	public static String getTrimString(String conzt, String var) {
		String ret = conzt + var;
		return ret.substring(var.length());
	}

	/**
	 * 获得一个长度为n的字段<br>
	 * 规则：<br>
	 * 1、src的长度<n，则在src后补空格<br>
	 * 2、src==null,则返回n个空格<br>
	 * 3、src.length>=n,则返回字串长度=src.length
	 * 
	 * @param src
	 * @param n
	 * @return
	 */
	public static String getTrimRight(String src, int n) {
		StringBuffer buf = new StringBuffer();

		int lngth = src != null ? src.length() : 0;
		buf.append(src);
		for (int i = 0; i < n - lngth; i++) {
			buf.append(" ");
		}

		return buf.toString();
	}

	/**
	 * 获得一个修饰后的定长字串<br>
	 * 注：长度=conzt字串的长度
	 * 
	 * @param conzt
	 *            常量字串
	 * @param var
	 *            变量字串
	 * @return String
	 */
	public static String getTrimString(String conzt, int var) {
		String ret = conzt + var;
		return ret.substring(ret.length() - conzt.length());
	}

	/**
	 * 将输入字串作如下处理： 1、字串中存在【|】，用空格代替； 2、字串中存在【\n】，用\\n代替； 3、字串中存在【\r\n】，用\r\\n代替；
	 * 4、字串为空，直接返回空字串。
	 * 
	 * @param src
	 * @return
	 */
	public static String getTrimString(String src) {
		if (src == null) {
			return "";
		}
		byte[] bytes = src.getBytes();
		ByteList bList = new ByteList(bytes.length);
		byte word;
		for (int i = 0; i < bytes.length; i++) {
			switch (word = bytes[i]) {
			case '\r':
				if ((word = bytes[i + 1]) == '\n') {
					bList.addAll("\\n".getBytes());
					i++;
				}
				break;
			case '\n':
				bList.addAll("\\n".getBytes());
				break;
			case '|':
				bList.addAll(" ".getBytes());
				break;
			default:
				bList.add(word);
			}
		}

		return bList.toString();
	}

	/**
	 * 获取非空字串<br>
	 * 满足条件：如果字串s为null，返回默认字串sdefault，否则返回字串s
	 * 
	 * @param s
	 * @param sdefault
	 *            默认值
	 * @return
	 */
	public static String getNotNullString(String s, String sdefault) {
		return s != null ? s.trim() : sdefault;
	}

	/**
	 * 获取非空字串 满足条件：如果字串s为null，返回空字串，否则返回字串s
	 * 
	 * @param s
	 * @return
	 */
	public static String getNotNullString(String s) {
		return s != null ? s.trim() : "";
	}

	/**
	 * 返回数字型的String。将""转换为0
	 * 
	 * @param s
	 * @return
	 */
	public static String getDoubleString(String s) {
		if (s == null)
			s = "0";
		if (s.trim().equals(""))
			s = "0";
		return s;
	}

	/**
	 * 将数值类型转换成字串<br>
	 * 满足条件：如果数据值iData等于默认值iNull，返回空串，否则返回将iData作为字串返回
	 * 
	 * @param iData
	 * @param iNull
	 * @return
	 */
	public static String getInt2String(int iData, int iNull) {
		return iData != iNull ? String.valueOf(iData) : "";
	}

	/**
	 * 将数值类型转换成字串<br>
	 * 满足条件：如果数据值lData等于默认值lNull，返回空串，否则返回将lData作为字串返回
	 * 
	 * @param lData
	 * @param lNull
	 * @return
	 */
	public static String getLong2String(long lData, long lNull) {
		return lData != lNull ? String.valueOf(lData) : "";
	}

	/**
	 * 将数值类型转换成字串<br>
	 * 满足条件：如果数据值fData等于默认值fNull，返回空串，否则返回将fData作为字串返回
	 * 
	 * @param fData
	 * @param fNull
	 * @return
	 */
	public static String getFloat2String(float fData, float fNull) {
		return fData != fNull ? String.valueOf(fData) : "";
	}

	/**
	 * 将数值类型转换成字串<br>
	 * 满足条件：如果数据值dData等于默认值dNull，返回空串，否则返回将dData作为字串返回
	 * 
	 * @param dData
	 * @param dNull
	 * @return
	 */
	public static String getDouble2String(double dData, double dNull) {
		return dData != dNull ? String.valueOf(dData) : "";
	}

	public static int getString2Int(String str) {
		int t = -1;
		try {
			t = Integer.parseInt(str);
		} catch (Exception e) {
			t = -1;
		}
		return t;

	}

	/*
	 * 将double类型去掉小数点后面的0（在.0和.00的情况下） dData 被转换的double
	 */
	public static String doubleRemove0(double dData) {
		String tmp = Double.toString(dData);
		if (tmp.length() >= 3) {
			String a = tmp.substring(tmp.length() - 2, tmp.length());
			if (".0".equals(a))
				tmp = tmp.substring(0, tmp.length() - 2);
		}
		if ("0".equals(tmp))
			tmp = "";

		return tmp;
	}

	/**
	 * 将数组中的数据转换成sql的语句,比如 field in ('a ','b ')
	 * 
	 * @param feildname
	 *            字段名称
	 * @param arrays
	 *            数组('a','b',...)
	 * @param fieldtype
	 *            数据类型("CHAR")
	 * @param fieldlen
	 *            字段长度
	 * @return
	 */
	public static String getSqlIn(String feildname, String[] arrays,
			String fieldtype, int fieldlen) {
		String tmp = "";
		if (feildname != null) {
			for (int i = 0; i < arrays.length; i++) {
				if (fieldtype.equals("CHAR")) {
					if (i == 0)
						tmp = tmp + "'" + getTrimRight(arrays[i], fieldlen)
								+ "'";
					else
						tmp = tmp + ",'" + getTrimRight(arrays[i], fieldlen)
								+ "'";
				} else {
					if (i == 0)
						tmp = tmp + arrays[i];
					else
						tmp = tmp + "," + arrays[i];
				}
			}
		}

		tmp = " " + feildname + " in (" + tmp + ")";
		return tmp;
	}

	/**
	 * 将字符串转换成日期YYYY-MM-DD HH24:MI:SS
	 * 
	 * @param str
	 *            日期字符串YYYYMMDDHH24MISS
	 * @return
	 */
	public static String toFormatDate(String str) {
		String sRet;
		if (str != null) {
			str = str.trim();
			if (str.trim().length() == 8)
				sRet = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
						+ str.substring(6, 8);
			else if (str.trim().length() == 14)
				sRet = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
						+ str.substring(6, 8) + " " + str.substring(8, 10)
						+ ":" + str.substring(10, 12) + ":"
						+ str.substring(12, 14);
			else
				sRet = str;
		} else {
			sRet = " ";
		}
		return sRet;
	}

	/**
	 * 返回前面带0的数字
	 * 
	 * @param i
	 * @param length
	 * @return
	 */
	public static String getFormatInt(int i, int length) {
		String sRet = Integer.toString(i);
		String sI = "";
		sI = Integer.toString(i);
		if (sI.length() < length) {
			for (int k = 0; k < length - sI.length(); k++) {
				sRet = "0" + sRet;
			}
		}

		return sRet;
	}

	/**
	 * 将分隔符拆分到数组
	 * 
	 * @param str
	 *            被拆分字符串
	 * @param splitstr
	 *            分隔符
	 * @return
	 */
	public static String[] splitStr(String str, String splitstr) {
		String[] tmp = new String[0];
		str.replaceAll(splitstr, " " + splitstr);
		if (!getNotNullString(str).equals("")) {
			StringTokenizer tst = new StringTokenizer(str, splitstr);
			tmp = new String[tst.countTokens()];
			int i = 0;
			while (tst.hasMoreTokens()) {
				tmp[i] = getNotNullString(tst.nextToken());
				i++;
			}
		}
		return tmp;
	}

	/**
	 * 字符串替换，(因为String.replaceAll方法有些字符会不能替换).不适合大数据量的替换
	 * 
	 * @param strSource
	 * @param strFrom
	 * @param strTo
	 * @return
	 */
	public static java.lang.String replace(java.lang.String strSource,
			java.lang.String strFrom, java.lang.String strTo) {
		java.lang.String strDest = "";
		int intFromLen = strFrom.length();
		int intPos;

		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;

		return strDest;
	}

	/**
	 * yq added 左边添加0。比如工单ID的生成规则是：日期＋sequence 200605 ＋ 00000000＋ 1
	 * 
	 * @param str
	 * @param size
	 * @param padChar
	 * @return
	 */
	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str;
		}
		return padding(pads, padChar).concat(str);
	}

	private static String padding(int repeat, char padChar)
			throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException(
					"Cannot pad a negative amount: " + repeat);
		}
		final char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf);
	}

	/**
	 * 功能：根据限制长度截取字符串（字符串中包括汉字，一个汉字等于两个字符）
	 * 
	 * @param strParameter
	 *            要截取的字符串
	 * @param limitLength
	 *            截取的长度
	 * @return 截取后的字符串
	 */
	public static String getStrByLen(String strParameter, int limitLength) {
		String return_str = strParameter;// 返回的字符串
		int temp_int = 0;// 将汉字转换成两个字符后的字符串长度
		int cut_int = 0;// 对原始字符串截取的长度
		byte[] b = strParameter.getBytes();// 将字符串转换成字符数组

		for (int i = 0; i < b.length; i++) {
			if (b[i] >= 0) {
				temp_int = temp_int + 1;
			} else {
				temp_int = temp_int + 2;// 一个汉字等于两个字符
				i++;
			}
			cut_int++;

			if (temp_int >= limitLength) {
				if (temp_int % 2 != 0 && b[temp_int - 1] < 0) {
					cut_int--;
				}
				return_str = return_str.substring(0, cut_int);
				break;
			}
		}
		return return_str;

	}

	/**
	 * 比较两个String对象值是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compareString(String str1, String str2) {
		if (null == str1) {
			str1 = "";
		}
		if (null == str2) {
			str2 = "";
		}
		if (str1.trim().equals(str2.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是空的（包括null、""、空格）
	 * 
	 * @param str
	 * @return
	 * @throws UtilException
	 */
	public static boolean isBlank(String str) {
		if (null == str) {
			return true;
		}
		if ("".equals(str.trim())) {
			return true;
		}

		return false;
	}

	/**
	 * 半角转全角<br>
	 * 全角空格为12288，半角空格为32<br>
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @author longtao
	 */
	public static String ToSBC(String input) {
		input = (null == input) ? "" : input;
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
			} else if (c[i] < 127) {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角<br>
	 * 全角空格为12288，半角空格为32<br>
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * @author longtao
	 */
	public static String ToDBC(String input) {
		input = (null == input) ? "" : input;
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
			} else if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 多关键字查询：将多个关键字中间的空格" "替换为通配符%
	 * 
	 * @author longtao
	 */
	public static String assembleMultiKeyWords(String keyWordStr) {
		// 全角转半角
		keyWordStr = ToDBC(keyWordStr);
		// 去空、首尾空格
		keyWordStr = (null == keyWordStr) ? "" : keyWordStr.trim();
		// 按照" "分隔
		String[] keyWords = keyWordStr.split(" ");
		StringBuffer retStr = new StringBuffer("");
		for (int index = 0, size = keyWords.length; index < size; index++) {
			if (!"".equals(keyWords[index].trim())) {
				retStr.append(keyWords[index].trim()).append("%");
			}
		}
		if (retStr.length() > 0) {
			// 去掉最后一个%
			return retStr.substring(0, retStr.length() - 1);
		} else {
			return "";
		}
	}
	
	public static  String list2String(List list,String splitChar){
		if(list==null) return "";
		StringBuilder returnstr=new StringBuilder();
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1)
			   returnstr.append(list.get(i)==null?"":list.get(i).toString());
			else
			   returnstr.append(list.get(i)==null?"":list.get(i).toString()+splitChar);
		}
		return returnstr.toString();
	}
	
	public static  String list2String(List list){
		return list2String(list, ",");
	}

//	public static void main(String[] args) {
//		try {
//			//System.out.println(StringUtils.getStrByLen("我ABC汉DEF", 6));
//			String str = "1|22418|20453|20091101000000|201011|20101101|,2$|137046216|1~1|22498|20506|20091201000000|201011|20101101|,28$|137563774|1 ";
//			System.out.println(getFavour(str));
//			
//			List<String> list=new ArrayList<String>();
//			list.add("123");
//			list.add("234");
//			list.add("345");
//			System.out.println(list2String(list, " "));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static Integer[] toIntegerArray(String[] strArr){
		if(strArr!=null && strArr.length>0){
			Integer[] intArr = new Integer[strArr.length];
			int i=0;
			for (String str : strArr) {
				intArr[i] = Integer.valueOf(str);
				i++;
			}
			return intArr;
		}
		return null;
	}
	
	/**
	 * 处理字符串，过长部分截断加...
	 * @param string
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String disposeString(String string, int length) throws Exception {
		byte[] s = string.getBytes("GBK");
		String result = null;
		// 如果字符串长度超过指定长度则截断加...
		if(s.length > length){
			s = ArrayUtils.subarray(s, 0, length - 3);
			result = new String(s, "GBK") + "...";
		} else {
			result = new String(s, "GBK");
		}
		return result;
	}
	
}
