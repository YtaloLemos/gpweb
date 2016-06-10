package com.sth.gpweb.repository.search;

import com.sth.gpweb.domain.Unidade;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Unidade entity.
 */
public interface UnidadeSearchRepository extends ElasticsearchRepository<Unidade, Long> {
}
