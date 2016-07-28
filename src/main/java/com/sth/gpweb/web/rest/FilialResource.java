package com.sth.gpweb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sth.gpweb.domain.Filial;
import com.sth.gpweb.service.FilialService;
import com.sth.gpweb.web.rest.util.HeaderUtil;
import com.sth.gpweb.web.rest.util.PaginationUtil;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Filial.
 */
@RestController
@RequestMapping("/api")
public class FilialResource {

    private final Logger log = LoggerFactory.getLogger(FilialResource.class);
        
    @Inject
    private FilialService filialService;
    
    /**
     * POST  /filials : Create a new filial.
     *
     * @param filial the filial to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filial, or with status 400 (Bad Request) if the filial has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/filials",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Filial> createFilial(@Valid @RequestBody Filial filial) throws URISyntaxException {
        log.debug("REST request to save Filial : {}", filial);
        
        if (filial.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("filial", "idexists", "A new filial cannot already have an ID")).body(null);
        }
        
        if (filialService.findNmFilialExists(filial.getNmFilial()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("filial", "nmexists", "A new filial cannot already have an ID")).body(null);
        }
        
        filial.setDtOperacao(LocalDate.now()); //Always use the operation date from server
        
        Filial result = filialService.save(filial);
        return ResponseEntity.created(new URI("/api/filials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("filial", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /filials : Updates an existing filial.
     *
     * @param filial the filial to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filial,
     * or with status 400 (Bad Request) if the filial is not valid,
     * or with status 500 (Internal Server Error) if the filial couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/filials",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Filial> updateFilial(@Valid @RequestBody Filial filial) throws URISyntaxException {
        log.debug("REST request to update Filial : {}", filial);
        if (filial.getId() == null) {
            return createFilial(filial);
        }
        
        filial.setDtOperacao(LocalDate.now()); //Always use the operation date from server
        
        Filial result = filialService.save(filial);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("filial", filial.getId().toString()))
            .body(result);
    }

    /**
     * GET  /filials : get all the filials.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of filials in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/filials",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Filial>> getAllFilials(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Filials");
        Page<Filial> page = filialService.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filials");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /filials/:id : get the "id" filial.
     *
     * @param id the id of the filial to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filial, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/filials/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Filial> getFilial(@PathVariable Long id) {
        log.debug("REST request to get Filial : {}", id);
        Filial filial = filialService.findOne(id);
        return Optional.ofNullable(filial)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /filials/:id : delete the "id" filial.
     *
     * @param id the id of the filial to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/filials/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFilial(@PathVariable Long id) {
        log.debug("REST request to delete Filial : {}", id);
        filialService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("filial", id.toString())).build();
    }

    /**
     * SEARCH  /_search/filials?query=:query : search for the filial corresponding
     * to the query.
     *
     * @param query the query of the filial search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/filials",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Filial>> searchFilials(@RequestParam String query, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of Filials for query {}", query);
        Page<Filial> page = filialService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/filials");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
