package com.sth.gpweb.service.impl;

import com.sth.gpweb.service.FilialService;
import com.sth.gpweb.domain.Filial;
import com.sth.gpweb.repository.FilialRepository;
import com.sth.gpweb.repository.search.FilialSearchRepository;
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
 * Service Implementation for managing Filial.
 */
@Service
@Transactional
public class FilialServiceImpl implements FilialService{

    private final Logger log = LoggerFactory.getLogger(FilialServiceImpl.class);
    
    @Inject
    private FilialRepository filialRepository;
    
    @Inject
    private FilialSearchRepository filialSearchRepository;
    
    /**
     * Save a filial.
     * 
     * @param filial the entity to save
     * @return the persisted entity
     */
    public Filial save(Filial filial) {
        log.debug("Request to save Filial : {}", filial);
        Filial result = filialRepository.save(filial);
        filialSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the filials.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Filial> findAll(Pageable pageable) {
        log.debug("Request to get all Filials");
        Page<Filial> result = filialRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one filial by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Filial findOne(Long id) {
        log.debug("Request to get Filial : {}", id);
        Filial filial = filialRepository.findOne(id);
        return filial;
    }

    /**
     *  Delete the  filial by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Filial : {}", id);
        filialRepository.delete(id);
        filialSearchRepository.delete(id);
    }

    /**
     * Search for the filial corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Filial> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Filials for query {}", query);
        return filialSearchRepository.search(queryStringQuery(query), pageable);
    }
    
    /**
     * Search for the nmFilial already exists.
     *
     *  @param query the nmFilial
     *  @return the list of entities
     */
    @Transactional(readOnly = true)    
    public String findNmFilialExists(String nmFilial){
        log.debug("Request to search if the nmFilial: {} already exists", nmFilial);
        return filialRepository.findNmFilialExists(nmFilial);
    }
}
