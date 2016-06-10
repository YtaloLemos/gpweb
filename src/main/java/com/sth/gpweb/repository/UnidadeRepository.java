package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Unidade;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Unidade entity.
 */
@SuppressWarnings("unused")
public interface UnidadeRepository extends JpaRepository<Unidade,Long> {

}
