package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Unidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;



/**
 * Spring Data JPA repository for the Unidade entity.
 */
@SuppressWarnings("unused")
public interface UnidadeRepository extends JpaRepository<Unidade,Long> {

	//Verify if nmUnidade already exits on database before insert	
	@Query("SELECT u.nmUnidade FROM Unidade u where u.nmUnidade = :nmUnidade") 
	String findNmUnidadeExists(@Param("nmUnidade") String nmUnidade);
    
    //Verify if sgUnidade already exits on database before insert	
	@Query("SELECT u.sgUnidade FROM Unidade u where u.sgUnidade = :sgUnidade") 
	String findSgUnidadeExists(@Param("sgUnidade") String sgUnidade);

	//Find by name, used by select2 on product
	Page<Unidade> findByNmUnidadeStartingWithOrderByNmUnidadeAsc(String descricao, Pageable pageable);
	
}
