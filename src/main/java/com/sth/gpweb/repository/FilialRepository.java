package com.sth.gpweb.repository;

import com.sth.gpweb.domain.Filial;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Filial entity.
 */
@SuppressWarnings("unused")
public interface FilialRepository extends JpaRepository<Filial,Long> {
    
    //Verify if nmFilial already exits on database before insert	
	@Query("SELECT f.nmFilial FROM Filial f where f.nmFilial = :nmFilial") 
	String findNmFilialExists(@Param("nmFilial") String nmFilial);

}
