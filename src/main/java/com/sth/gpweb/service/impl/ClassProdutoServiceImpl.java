package com.sth.gpweb.service.impl;

import com.sth.gpweb.service.ClassProdutoService;
import com.sth.gpweb.domain.ClassProduto;
import com.sth.gpweb.repository.ClassProdutoRepository;
import com.sth.gpweb.repository.search.ClassProdutoSearchRepository;
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
 * Service Implementation for managing ClassProduto.
 */
@Service
@Transactional
public class ClassProdutoServiceImpl implements ClassProdutoService{

    private final Logger log = LoggerFactory.getLogger(ClassProdutoServiceImpl.class);
    
    @Inject
    private ClassProdutoRepository classProdutoRepository;
    
    @Inject
    private ClassProdutoSearchRepository classProdutoSearchRepository;
    
    /**
     * Save a classProduto.
     * 
     * @param classProduto the entity to save
     * @return the persisted entity
     */
    public ClassProduto save(ClassProduto classProduto) {
        log.debug("Request to save ClassProduto : {}", classProduto);
        ClassProduto result = classProdutoRepository.save(classProduto);
        classProdutoSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the classProdutos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ClassProduto> findAll(Pageable pageable) {
        log.debug("Request to get all ClassProdutos");
        Page<ClassProduto> result = classProdutoRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one classProduto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ClassProduto findOne(Long id) {
        log.debug("Request to get ClassProduto : {}", id);
        ClassProduto classProduto = classProdutoRepository.findOne(id);
        return classProduto;
    }

    /**
     *  Delete the  classProduto by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ClassProduto : {}", id);
        classProdutoRepository.delete(id);
        classProdutoSearchRepository.delete(id);
    }

    /**
     * Search for the classProduto corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ClassProduto> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ClassProdutos for query {}", query);
        return classProdutoSearchRepository.search(queryStringQuery(query), pageable);
    }
    
    /**
     * Search for the cdClassProduto already exists.
     *
     *  @param query the cdClassProduto
     *  @return the list of entities
     */
    @Transactional(readOnly = true)    
    public String findCdClassProdutoExists(String cdClassProduto){
    	log.debug("Request to search if the cdClassProduto: {} already exists", cdClassProduto);
        return classProdutoRepository.findCdClassProdutoExists(cdClassProduto);
    }
    
    /**
     * Search for the dsClassProduto already exists.
     *
     *  @param query the dsClassProduto
     *  @return the list of entities
     */
    @Transactional(readOnly = true)    
    public String findDsClassProdutoExists(String dsClassProduto){
    	log.debug("Request to search if the dsClassProduto: {} already exists", dsClassProduto);
        return classProdutoRepository.findDsClassProdutoExists(dsClassProduto);
    }    
    
    /**
     * Search for the nmUnidade
     *
     *  @param query the nmFabricante
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ClassProduto> findByDsClassProdutoStartingWithOrderByDsClassProdutoAsc(String descricao, Pageable pageable){
	    log.debug("Request to...", descricao);
	    return classProdutoRepository.findByDsClassProdutoStartingWithOrderByDsClassProdutoAsc(descricao, pageable);
    }
}
