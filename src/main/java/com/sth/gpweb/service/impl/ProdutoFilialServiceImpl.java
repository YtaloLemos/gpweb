package com.sth.gpweb.service.impl;

import com.sth.gpweb.service.ProdutoFilialService;
import com.sth.gpweb.domain.ProdutoFilial;
import com.sth.gpweb.repository.ProdutoFilialRepository;
import com.sth.gpweb.repository.search.ProdutoFilialSearchRepository;
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
 * Service Implementation for managing ProdutoFilial.
 */
@Service
@Transactional
public class ProdutoFilialServiceImpl implements ProdutoFilialService{

    private final Logger log = LoggerFactory.getLogger(ProdutoFilialServiceImpl.class);
    
    @Inject
    private ProdutoFilialRepository produtoFilialRepository;
    
    @Inject
    private ProdutoFilialSearchRepository produtoFilialSearchRepository;
    
    /**
     * Save a produtoFilial.
     * 
     * @param produtoFilial the entity to save
     * @return the persisted entity
     */
    public ProdutoFilial save(ProdutoFilial produtoFilial) {
        log.debug("Request to save ProdutoFilial : {}", produtoFilial);
        ProdutoFilial result = produtoFilialRepository.save(produtoFilial);
        produtoFilialSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the produtoFilials.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdutoFilial> findAll(Pageable pageable) {
        log.debug("Request to get all ProdutoFilials");
        Page<ProdutoFilial> result = produtoFilialRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one produtoFilial by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdutoFilial findOne(Long id) {
        log.debug("Request to get ProdutoFilial : {}", id);
        ProdutoFilial produtoFilial = produtoFilialRepository.findOne(id);
        return produtoFilial;
    }

    /**
     *  Delete the  produtoFilial by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProdutoFilial : {}", id);
        produtoFilialRepository.delete(id);
        produtoFilialSearchRepository.delete(id);
    }

    /**
     * Search for the produtoFilial corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ProdutoFilial> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ProdutoFilials for query {}", query);
        return produtoFilialSearchRepository.search(queryStringQuery(query), pageable);
    }
}
