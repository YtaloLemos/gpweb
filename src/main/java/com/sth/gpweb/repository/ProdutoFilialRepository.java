package com.sth.gpweb.repository;

import com.sth.gpweb.domain.ProdutoFilial;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ProdutoFilial entity.
 */
@SuppressWarnings("unused")
public interface ProdutoFilialRepository extends JpaRepository<ProdutoFilial,Long> {

}
