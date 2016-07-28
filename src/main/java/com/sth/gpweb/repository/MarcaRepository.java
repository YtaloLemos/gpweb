package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Marca entity.
 */
@SuppressWarnings("unused")
public interface MarcaRepository extends JpaRepository<Marca,Long> {
    
    //Verify if nmFabricante already exits on database before insert	
 	@Query("SELECT f.nmFabricante FROM Marca f where f.nmFabricante = :nmFabricante") 
 	String findNmFabricanteExists(@Param("nmFabricante") String nmFabricante);

	//Find by name, used by select2 on product
	Page<Marca> findByNmFabricanteStartingWithOrderByNmFabricanteAsc(String descricao, Pageable pageable);
	
}
