package com.bloomberg.fx.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloomberg.fx.repository.EntityManagerFile;

@Service

public class SaveFileService {

	private static final Logger logger = LoggerFactory.getLogger(SaveFileService.class);

	@Autowired
	FileValidatorUtil fileValidatorUtil;

	@Autowired
	EntityManagerFile entityManagerFile;

	public boolean checkFileExists(final String fileName) {

		try {
			return fileValidatorUtil.checkFileExists(fileName);
				
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	public boolean saveFileList(List<String> inputList, final String fileName) {

		try {
			fileValidatorUtil.validateFile(inputList, fileName);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

}
