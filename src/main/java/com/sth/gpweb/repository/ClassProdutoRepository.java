package com.sth.gpweb.repository;

import com.sth.gpweb.domain.ClassProduto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the ClassProduto entity.
 */
@SuppressWarnings("unused")
public interface ClassProdutoRepository extends JpaRepository<ClassProduto,Long> {
    
    //Verify if cdClassProduto already exits on database before insert	
 	@Query("SELECT c.cdClassProduto FROM ClassProduto c where c.cdClassProduto = :cdClassProduto") 
 	String findCdClassProdutoExists(@Param("cdClassProduto") String cdClassProduto);
    
    //Verify if dsClassProduto already exits on database before insert	
 	@Query("SELECT c.dsClassProduto FROM ClassProduto c where c.dsClassProduto = :dsClassProduto") 
 	String findDsClassProdutoExists(@Param("dsClassProduto") String dsClassProduto);

	//Find by name, used by select2 on product
	Page<ClassProduto> findByDsClassProdutoStartingWithOrderByDsClassProdutoAsc(String descricao, Pageable pageable);
		
}