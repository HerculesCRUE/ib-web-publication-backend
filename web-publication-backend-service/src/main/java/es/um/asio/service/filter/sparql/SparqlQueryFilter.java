package es.um.asio.service.filter.sparql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import es.um.asio.service.domain.SparqlQuery;
import es.um.asio.service.domain.SparqlQuery_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SparqlQueryFilter extends AbstractJpaSpecification<SparqlQuery> implements EntityFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1985967650272588054L;

	private String tipo;

	private String username;

	private String sparqlName;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Predicate toPredicate(final Root<SparqlQuery> root, final CriteriaQuery<?> query,
			final CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		if (this.username != null) {
			predicates.add(this.createEquals(root, criteriaBuilder, SparqlQuery_.USERNAME, this.username));
		}

		if (this.sparqlName != null) {
			predicates.add(this.createEquals(root, criteriaBuilder, SparqlQuery_.SPARQL_NAME, this.sparqlName));
		}

		return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
	}

}
