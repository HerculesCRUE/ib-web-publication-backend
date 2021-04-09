package es.um.asio.service.util;

public class FusekiConstants {

	public static final String QUERY_TEMPLATE_SELECT = "PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> SELECT %s WHERE { %s %s %s %s } %s %s LIMIT %s offset %s";

	public static final String QUERY__SIMPLE_TEMPLATE_SELECT = "PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> SELECT %s WHERE { %s %s %s %s } ";

	public static final String QUERY__SIMPLE_COUNT_TEMPLATE_SELECT = "PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> SELECT %s %s WHERE { %s %s %s %s } %s ORDER BY DESC (?count) LIMIT 50";

	public static final String QUERY_TEMPLATE_COUNT = "PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> SELECT %s WHERE { %s %s %s %s } ";

	public static final String COUNT_CHUNK_TEMPLATE = "(COUNT(*) AS ?count)";

	public static final String COUNT_CHUNK = "COUNT_CHUNK";
	public static final String SELECT_CHUNK = "SELECT_CHUNK";
	public static final String TYPE_CHUNK = "TYPE_CHUNK";
	public static final String FIELDS_CHUNK = "FIELDS_CHUNK";
	public static final String FILTERS_CHUNK = "FILTERS_CHUNK";
	public static final String JOIN_CHUNK = "JOIN_CHUNK";
	public static final String LIMIT = "LIMIT";
	public static final String OFFSET = "OFFSET";
	public static final String GROUP = "GROUP BY";
	public static final String ORDER = "ORDER BY";
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";

}
