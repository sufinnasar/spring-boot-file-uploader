package com.bloomberg.fx.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bloomberg.fx.entity.FxFile;
import com.bloomberg.fx.entity.FxInvalidFileData;

@Transactional
@Repository
public class EntityManagerFile {

	private static final Logger logger = LoggerFactory.getLogger(EntityManagerFile.class);

	@PersistenceContext()
	private EntityManager entityManager;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	private int i = 0;
	private int j = 0;

	public boolean bulkSaveInvalid(List<FxInvalidFileData> fxFiles) {
		try {
			for (FxInvalidFileData t : fxFiles) {
				entityManager.persist(t);
				i++;
				if (i % batchSize == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		} finally {
			i = 0;
			entityManager.flush();
			entityManager.clear();

		}

	}

	public boolean bulksave(List<FxFile> fxFiles) {

		try {
			for (FxFile t : fxFiles) {
				entityManager.persist(t);
				j++;
				if (j % batchSize == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		} finally {
			j = 0;
			entityManager.flush();
			entityManager.clear();
		}

	}

}
