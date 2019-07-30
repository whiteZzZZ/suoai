package com.yiban.suoai.util;

import java.util.HashMap;
import java.util.Map;

public class MapHelper {

	static final String success="success";
	static final String oserror="OSError";
	
	
	public static Map setInfor(String status,String code){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", status);
		map.put("code", code);
		
		return map;
	}
	
    public static Map setInfor(String status,String code,String errmsg){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", status);
		map.put("code", code);
		map.put("errmsg", errmsg);
		
		return map;
	}
    public static Map error(){

		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "0");
		map.put("errmsg", oserror);
		
		return map;
	}

	public static Map error(String s){

		Map<String, Object> map = new HashMap<>();

		map.put("status", "0");
		map.put("errmsg", oserror);
		map.put("value",s);
		return map;
	}

    public static Map success(){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "1");
		map.put("errmsg", success);
		return map;
	}
    
   public static Map success(String token,int userid){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "1");
		map.put("errmsg", success);
		map.put("token", token);
		map.put("userId", userid);
		return map;
	}
   
   public static Map success(int userid){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "1");
		map.put("errmsg", success);
		map.put("userId", userid);
		return map;
	}

	public static Map<String,Object> success(String key,Object object){

		Map<String, Object> map = new HashMap<>();

		map.put("status", "1");
		map.put("errmsg", success);
		map.put(key,object);
		return map;
	}

	public static Map<String,Object> nullObject(){
		Map<String, Object> map = new HashMap<>();

		map.put("status", "2");
		map.put("errmsg", "nullObjectException");
		return map;
	}

	public static Map<String, Object> oaOpenidIsNull(){
		Map<String, Object> map = new HashMap<>();
		map.put("status", "3");
		map.put("errmsg", "oaOpenidIsNull");
		return map;
	}
}
