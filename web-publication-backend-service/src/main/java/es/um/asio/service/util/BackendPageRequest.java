package es.um.asio.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Customizes JSON deserialization for Spring's Pageable objects that lack of a
 * default constructor.
 */
public class BackendPageRequest extends PageRequest {

	/** The serial version uid. */
	private static final long serialVersionUID = -2221059457237603304L;

	/**
	 * Constructor.
	 *
	 * @param page the page number
	 * @param size the page size
	 * @param sort the sort mode
	 */
	public BackendPageRequest(final int page, final int size, final Sort sort) {
		super(page, size, sort);
	}

}
