package com.yiban.suoai.forepojo;

public class Page {

	private Integer total;

	private Integer total_page;
	
	private Integer current_page;

	private Integer per_page;

	public Page() {
        // Auto-generated constructor stub
    }

	public Page(int total, int total_page, int current_page, int per_page) {

		this.total = total;
		this.per_page = per_page;
		this.total_page=total_page;
		this.current_page = current_page;
	}

	public Integer getTotal_page() {
		return total_page;
	}

	public void setTotal_page(Integer total_page) {
		this.total_page = total_page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

}
