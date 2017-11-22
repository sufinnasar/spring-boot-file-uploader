package com.bloomberg.fx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bloomberg.fx.entity.FxFile;

/**
 * Spring Data JPA repository for the FxFile entity.
 */
@Transactional
public interface FxFileRepository extends CrudRepository<FxFile, Long> {

	public FxFile findBydealID(String dealID);

	@Query("select CASE WHEN COUNT(e) > 0 THEN true ELSE false END from FxFile e where e.fileName = :fileName")
	public boolean existsbyfileName(@Param("fileName") String fileName);

	@Query("select f.dealID from FxFile f")
	public List<String> getDealIDs();

	public List<FxFile> findByfileName(String fileName);
}
