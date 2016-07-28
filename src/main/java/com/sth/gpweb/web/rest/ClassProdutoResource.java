package com.sth.gpweb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sth.gpweb.domain.ClassProduto;
import com.sth.gpweb.domain.Unidade;
import com.sth.gpweb.service.ClassProdutoService;
import com.sth.gpweb.web.rest.util.HeaderUtil;
import com.sth.gpweb.web.rest.util.PaginationUtil;
import com.sth.gpweb.web.rest.util.Selection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing ClassProduto.
 */
@RestController
@RequestMapping("/api")
public class ClassProdutoResource {

    private final Logger log = LoggerFactory.getLogger(ClassProdutoResource.class);
        
    @Inject
    private ClassProdutoService classProdutoService;
    
    /**
     * POST  /class-produtos : Create a new classProduto.
     *
     * @param classProduto the classProduto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new classProduto, or with status 400 (Bad Request) if the classProduto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/class-produtos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClassProduto> createClassProduto(@Valid @RequestBody ClassProduto classProduto) throws URISyntaxException {
        log.debug("REST request to save ClassProduto : {}", classProduto);
        
        if (classProduto.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("classProduto", "idexists", "A new classProduto cannot already have an ID")).body(null);
        }
        
        if (classProdutoService.findCdClassProdutoExists(classProduto.getCdClassProduto()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("classProduto", "idexists", "A new classificação cannot already have an ID")).body(null);
        }
        
        if (classProdutoService.findDsClassProdutoExists(classProduto.getDsClassProduto()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("classProduto", "nmexists", "A new classificação cannot already have an ID")).body(null);
        }
        
        ClassProduto result = classProdutoService.save(classProduto);
        return ResponseEntity.created(new URI("/api/class-produtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("classProduto", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /class-produtos : Updates an existing classProduto.
     *
     * @param classProduto the classProduto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated classProduto,
     * or with status 400 (Bad Request) if the classProduto is not valid,
     * or with status 500 (Internal Server Error) if the classProduto couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/class-produtos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClassProduto> updateClassProduto(@Valid @RequestBody ClassProduto classProduto) throws URISyntaxException {
        log.debug("REST request to update ClassProduto : {}", classProduto);
        if (classProduto.getId() == null) {
            return createClassProduto(classProduto);
        }
        ClassProduto result = classProdutoService.save(classProduto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("classProduto", classProduto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /class-produtos : get all the classProdutos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of classProdutos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/class-produtos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ClassProduto>> getAllClassProdutos(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ClassProdutos");
        Page<ClassProduto> page = classProdutoService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/class-produtos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /class-produtos/:id : get the "id" classProduto.
     *
     * @param id the id of the classProduto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the classProduto, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/class-produtos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ClassProduto> getClassProduto(@PathVariable Long id) {
        log.debug("REST request to get ClassProduto : {}", id);
        ClassProduto classProduto = classProdutoService.findOne(id);
        return Optional.ofNullable(classProduto)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /class-produtos/:id : delete the "id" classProduto.
     *
     * @param id the id of the classProduto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/class-produtos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteClassProduto(@PathVariable Long id) {
        log.debug("REST request to delete ClassProduto : {}", id);
        classProdutoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("classProduto", id.toString())).build();
    }

    /**
     * SEARCH  /_search/class-produtos?query=:query : search for the classProduto corresponding
     * to the query.
     *
     * @param query the query of the classProduto search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/class-produtos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ClassProduto>> searchClassProdutos(@RequestParam String query, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of ClassProdutos for query {}", query);
        Page<ClassProduto> page = classProdutoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/class-produtos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * SEARCH  /_search/class-produtos/select?query=:query : search for the classproduto corresponding
     * to the query.
     *
     * @param query the query of the classproduto search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/class-produtos/select",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Selection> searchClassProdutosNew(@RequestParam String query, Pageable pageable)
        throws URISyntaxException {
    	
    	try{
    		Page<ClassProduto> page;
    		
    		if(query.trim().equalsIgnoreCase("*")){
    			page = classProdutoService.findAll(pageable);
    		}else{
    			page = classProdutoService.findByDsClassProdutoStartingWithOrderByDsClassProdutoAsc(query, pageable);    			
    		}; 	    	
	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/class-produtos/select");
	    	
	        Selection sel = new Selection(page);	        
	        
	        return new ResponseEntity<Selection>(sel, headers, HttpStatus.OK);
	        
    	}catch(Exception e){
    		log.error(e.getMessage());
    		
    		return ResponseEntity.badRequest().header("Falha", e.getMessage()).body(null);
    	}
		
    }


}
