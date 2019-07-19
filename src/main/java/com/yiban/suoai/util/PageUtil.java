package com.yiban.suoai.util;


import com.yiban.suoai.forepojo.Page;

public class PageUtil {

	public final static  int pageSize=10;


	public static Page getPage(int total, int size, int start){

		int total_page = total / pageSize;

		if (total % pageSize > 0) {
			total_page++;
		}
		if (start < 0) {
			start = 0;
		}
		
		Page page = new Page(total, total_page,size, start + 1);
		
		return page;
	}
	
	/*public static Page getPageback(int total,int size,int start){

		int total_page = total / 30;

		if (total % 30 > 0) {
			total_page++;
		}
		if (start < 0) {
			start = 0;
		}
		
		Page page = new Page(total, total_page,size, start + 1);
		
		return page;
	}
	
	public static Page getPage(int total,int size,int start,int perNum){

		int total_page = total / perNum;

		if (total % perNum > 0) {
			total_page++;
		}
		if (start < 0) {
			start = 0;
		}
		
		Page page = new Page(total, total_page,size, start + 1);
		
		return page;
	}*/
}

