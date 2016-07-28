package com.sth.gpweb.service;

import com.sth.gpweb.domain.ClassProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing ClassProduto.
 */
public interface ClassProdutoService {

    /**
     * Save a classProduto.
     * 
     * @param classProduto the entity to save
     * @return the persisted entity
     */
    ClassProduto save(ClassProduto classProduto);

    /**
     *  Get all the classProdutos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ClassProduto> findAll(Pageable pageable);

    /**
     *  Get the "id" classProduto.
     *  
     *  @param id the id of the entity
     *  @return the entity
     */
    ClassProduto findOne(Long id);

    /**
     *  Delete the "id" classProduto.
     *  
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the classProduto corresponding to the query.
     * 
     *  @param query the query of the search
     *  @return the list of entities
     */
    Page<ClassProduto> search(String query, Pageable pageable);
    
    /**
      * Search for the cdClassProduto already exists.
      * 
      *  @param query the cdClassProduto
      *  @return the list of entities
      */
     String findCdClassProdutoExists(String cdClassProduto);   
    
    /**
      * Search for the dsClassProduto already exists.
      * 
      *  @param query the dsClassProduto
      *  @return the list of entities
      */
     String findDsClassProdutoExists(String dsClassProduto);
    
    /**
     * Search for the classproduto corresponding to the query name.
     * 
     *  @param description query for the name
     *  @return the list of entities
     */
     Page<ClassProduto> findByDsClassProdutoStartingWithOrderByDsClassProdutoAsc(String descricao, Pageable pageable);  
}
