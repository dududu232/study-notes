package com.tiamo.dt.common.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 */
public class CommonUtil {

	/**
	 * ids字符串转Long list，逗号分隔
	 * @param ids
	 * @return
	 */
	public static List<Long> toList(String ids){
		if(ids == null || ids.isEmpty()){
			return new ArrayList<>();
		}

		String[] strArr = ids.split(",");
		Long[] longArr = new Long[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			Long v = Long.valueOf(strArr[i]);
			longArr[i] = v;
		}
		return Arrays.asList(longArr);
	}

	/**
	 * Long list转ids字符串，逗号分隔
	 * @param list
	 * @return
	 */
	public static <T> String toStr(List<T> list){
		if(list == null || list.isEmpty()){
			return "";
		}

		Iterator<T> it = list.iterator();

		//使用StringJoiner方式实现
		StringJoiner sj = new StringJoiner(",");
		while (it.hasNext()){
			sj.add(String.valueOf(it.next()));
		}
		return sj.toString();

		//使用StringBuilder方式实现
		/*StringBuilder sb = new StringBuilder();
		while (it.hasNext()){
			sb.append(it.next());
			if(it.hasNext()){
				sb.append(",");
			}
		}
		return sb.toString();*/
	}

	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 *
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;

	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。
	 * 如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：hello_world->helloWorld
	 *
	 * @param name
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			//update-begin--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
			//update-begin--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
			return name.substring(0, 1).toLowerCase() + name.substring(1).toLowerCase();
			//update-end--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。
	 * 如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：hello_world,test_id->helloWorld,testId
	 *
	 * @param names
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelNames(String names) {
		if(names==null||names.equals("")){
			return null;
		}
		StringBuffer sf = new StringBuffer();
		String[] fs = names.split(",");
		for (String field : fs) {
			field = camelName(field);
			sf.append(field + ",");
		}
		String result = sf.toString();
		return result.substring(0, result.length() - 1);
	}

	//update-begin--Author:zhoujf  Date:20180503 for：TASK #2500 【代码生成器】代码生成器开发一通用模板生成功能
	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。(首字母写)
	 * 如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：hello_world->HelloWorld
	 *
	 * @param name
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelNameCapFirst(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 其他的驼峰片段，首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1).toLowerCase());
		}
		return result.toString();
	}

	/**
	 * 将驼峰命名转化成下划线
	 * @param para
	 * @return
	 */
	public static String camelToUnderline(String para){
        if(para.length()<3){
        	return para.toLowerCase();
        }
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        //从第三个字符开始 避免命名不规范
        for(int i=2;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toLowerCase();
	}

}
