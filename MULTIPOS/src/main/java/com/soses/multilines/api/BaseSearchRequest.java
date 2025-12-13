package com.soses.multilines.api;

/**
 * The Class BaseSearchRequest.
 *
 * @author hso
 * @since Nov 8, 2024
 */
public class BaseSearchRequest {

	private String search = "";
	
	/** The size. */
	private String size;
	
	/** The page. */
	private String page;

	public BaseSearchRequest() {} 
	
	public BaseSearchRequest(String search, String size, String page) {
		super();
		this.search = search;
		this.size = size;
		this.page = page;
	}

	@Override
	public String toString() {
		return "BaseSearchRequest [search=" + search + ", size=" + size + ", page=" + page + "]";
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(String page) {
		this.page = page;
	}
	
}
