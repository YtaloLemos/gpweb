package com.sth.gpweb.service.impl;

import com.sth.gpweb.service.UnidadeService;
import com.sth.gpweb.domain.Unidade;
import com.sth.gpweb.repository.UnidadeRepository;
import com.sth.gpweb.repository.search.UnidadeSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Unidade.
 */
@Service
@Transactional
public class UnidadeServiceImpl implements UnidadeService{

    private final Logger log = LoggerFactory.getLogger(UnidadeServiceImpl.class);
    
    @Inject
    private UnidadeRepository unidadeRepository;
    
    @Inject
    private UnidadeSearchRepository unidadeSearchRepository;
    
    /**
     * Save a unidade.
     * 
     * @param unidade the entity to save
     * @return the persisted entity
     */
    public Unidade save(Unidade unidade) {
        log.debug("Request to save Unidade : {}", unidade);
        Unidade result = unidadeRepository.save(unidade);
        unidadeSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the unidades.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Unidade> findAll(Pageable pageable) {
        log.debug("Request to get all Unidades");
        Page<Unidade> result = unidadeRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one unidade by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Unidade findOne(Long id) {
        log.debug("Request to get Unidade : {}", id);
        Unidade unidade = unidadeRepository.findOne(id);
        return unidade;
    }

    /**
     *  Delete the  unidade by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Unidade : {}", id);
        unidadeRepository.delete(id);
        unidadeSearchRepository.delete(id);
    }

    /**
     * Search for the unidade corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Unidade> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Unidades for query {}", query);
        return unidadeSearchRepository.search(queryStringQuery(query), pageable);
    }
}
