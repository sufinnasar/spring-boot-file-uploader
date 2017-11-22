package com.bloomberg.fx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bloomberg.fx.entity.FxInvalidFileData;

/**
 * Spring Data JPA repository for the FxInvalidFileData entity.
 */
public interface FxInvalidFileDataRepository extends CrudRepository<FxInvalidFileData,Long>{

	public List<FxInvalidFileData> findByfileName(String fileName);
	
	@Query("select MAX(f.id) from FxInvalidFileData f")
	public Integer getMaxDealID();
}
