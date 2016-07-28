package com.sth.gpweb.service;

import com.sth.gpweb.domain.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Marca.
 */
public interface MarcaService {

    /**
     * Save a marca.
     * 
     * @param marca the entity to save
     * @return the persisted entity
     */
    Marca save(Marca marca);

    /**
     *  Get all the marcas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Marca> findAll(Pageable pageable);

    /**
     *  Get the "id" marca.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    Marca findOne(Long id);

    /**
     *  Delete the "id" marca.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the marca corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<Marca> search(String query, Pageable pageable);
    
    /**
     * Search for the nmFabricante already exists.
     * 
     *  @param query the nmFabricante
     *  @return the list of entities
     */
    String findNmFabricanteExists(String nmFabricante);
    
    /**
     * Search for the marca corresponding to the query name.
     * 
     *  @param description query for the name
     *  @return the list of entities
     */
     Page<Marca> findByNmFabricanteStartingWithOrderByNmFabricanteAsc(String descricao, Pageable pageable);
}
