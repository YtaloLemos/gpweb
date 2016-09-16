package com.sth.gpweb.service;

import com.sth.gpweb.domain.ProdutoFilial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing ProdutoFilial.
 */
public interface ProdutoFilialService {

    /**
     * Save a produtoFilial.
     * 
     * @param produtoFilial the entity to save
     * @return the persisted entity
     */
    ProdutoFilial save(ProdutoFilial produtoFilial);

    /**
     *  Get all the produtoFilials.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProdutoFilial> findAll(Pageable pageable);

    /**
     *  Get the "id" produtoFilial.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    ProdutoFilial findOne(Long id);

    /**
     *  Delete the "id" produtoFilial.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the produtoFilial corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<ProdutoFilial> search(String query, Pageable pageable);
}
