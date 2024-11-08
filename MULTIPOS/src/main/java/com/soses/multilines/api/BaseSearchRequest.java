package com.soses.multilines.api;

/**
 * The Class BaseSearchRequest.
 *
 * @author hso
 * @since Nov 8, 2024
 */
public class BaseSearchRequest {

	/** The size. */
	private String size;
	
	/** The page. */
	private String page;

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
