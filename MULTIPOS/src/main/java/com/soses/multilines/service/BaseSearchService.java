package com.soses.multilines.service;

import org.springframework.data.domain.Page;

import com.soses.multilines.api.BaseSearchRequest;

/**
 * The Interface BaseSearchService.
 *
 * @author hso
 * @param <T> the generic type
 * @since Nov 8, 2024
 */
public interface BaseSearchService<T> {

	/**
	 * Search user.
	 *
	 * @param request the request
	 * @return the page
	 */
	Page<T> search(BaseSearchRequest request);
}
