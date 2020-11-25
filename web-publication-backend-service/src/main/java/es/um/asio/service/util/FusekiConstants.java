package es.um.asio.service.util;

public class FusekiConstants {

	public static final String QUERY_TEMPLATE = "SELECT %s WHERE { %s %s %s } LIMIT %s offset %s";
	public static final String COUNT_CHUNK_TEMPLATE = "(COUNT(*) AS ?count)";
	
	public static final String COUNT_CHUNK = "COUNT_CHUNK";
	public static final String SELECT_CHUNK = "SELECT_CHUNK";
	public static final String TYPE_CHUNK = "TYPE_CHUNK";
	public static final String FIELDS_CHUNK = "FIELDS_CHUNK";
	public static final String FILTERS_CHUNK = "FILTERS_CHUNK";
	public static final String LIMIT = "LIMIT";
	public static final String OFFSET = "OFFSET";

	
}
