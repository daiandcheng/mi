package com.edu.util;
import com.edu.bean.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	/**
	 * 把一个对象转换成json格式的字符串
	 * @param obj
	 * @return
	 */
	public static String convertObjectToString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null ;
	}
	public static String convertObjectToString2(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null ;
	}
	public static void main(String[] args) {
		News news = new News(222, "新增", "放假", "sss");
		System.out.println(convertObjectToString(news));
		System.out.println(convertObjectToString2(news));
	}
	
}
