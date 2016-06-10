package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Produto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Produto entity.
 */
@SuppressWarnings("unused")
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

}
