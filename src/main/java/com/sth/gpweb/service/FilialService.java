package com.sth.gpweb.service;

import com.sth.gpweb.domain.Filial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Filial.
 */
public interface FilialService {

    /**
     * Save a filial.
     * 
     * @param filial the entity to save
     * @return the persisted entity
     */
    Filial save(Filial filial);

    /**
     *  Get all the filials.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Filial> findAll(Pageable pageable);

    /**
     *  Get the "id" filial.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Filial findOne(Long id);

    /**
     *  Delete the "id" filial.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the filial corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<Filial> search(String query, Pageable pageable);
    
    /**
     * Search for the nmFilial already exists.
     * 
     *  @param query the nmFilial
     *  @return the list of entities
     */
    String findNmFilialExists(String nmFilial);
}
