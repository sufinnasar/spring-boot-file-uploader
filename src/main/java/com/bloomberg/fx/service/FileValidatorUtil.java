package com.bloomberg.fx.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloomberg.fx.entity.FxFile;
import com.bloomberg.fx.entity.FxInvalidFileData;
import com.bloomberg.fx.repository.EntityManagerFile;
import com.bloomberg.fx.repository.FxFileRepository;
import com.bloomberg.fx.repository.FxInvalidFileDataRepository;


/**
 * Utility class for validation and save of the file
 */
@Service
public class FileValidatorUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileValidatorUtil.class);

	@Autowired
	EntityManagerFile entityManagerFile;

	@Autowired
	FxFileRepository fxFileRepository;

	@Autowired
	FxInvalidFileDataRepository fxInvalidFileRepository;

	public boolean checkFileExists(final String fileName) {

		try {
			if (fxFileRepository.existsbyfileName(fileName)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	public void validateFile(List<String> list, String fileName) {

		String errordesc = "";
		boolean valid = true;
		List<FxFile> fxFiles = new ArrayList<>();
		List<FxInvalidFileData> invalidFileDatas = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDate = null;
		try {
			List<String> dealList = fxFileRepository.getDealIDs();
			FxFile fxFile = null;
			FxInvalidFileData fxInvalidFileData = null;

			int invalidId = (int) fxInvalidFileRepository.count();
			for (String data : list) {

				String[] details = data.split(",");
				if (!details[0].matches("[0-9]+")) {

					valid = false;
					errordesc += " Deal Id contains character.";
				}
				if (details[1].length() != 3 || details[2].length() != 3) {

					valid = false;
					errordesc += " Invalid Currency Length.";
				}
				if (details[1].matches(".*\\d+.*") || details[2].matches(".*\\d+.*")) {

					valid = false;
					errordesc += " Currency contains numeric values.";
				}
				if (!details[4].matches("[0-9]+")) {

					valid = false;
					errordesc += " Deal amount contains charaters.";
				}
				if (dealList.contains(details[0])) {
					valid = false;
					errordesc += " Duplicate Deal Id.";
				}

				try {
					parsedDate = dateFormat.parse(details[3]);

				} catch (Exception e) {
					details[3] = null;
					valid = false;
					errordesc += " Invalid TimeStamp";
				}

				if (valid) {
					fxFile = new FxFile();
					fxFile.setDealID(details[0]);
					fxFile.setFromCurrency(details[1]);
					fxFile.setToCurrency(details[2]);
					fxFile.setFileName(fileName);
					fxFile.setTimestampField(Timestamp.valueOf(details[3]));
					fxFile.setDealAmount(details[4]);
					fxFiles.add(fxFile);
				} else {

					fxInvalidFileData = new FxInvalidFileData();
					fxInvalidFileData.setId(invalidId++);
					fxInvalidFileData.setDealID(details[0]);
					fxInvalidFileData.setFromCurrency(details[1]);
					fxInvalidFileData.setToCurrency(details[2]);
					if (details[3] != null) {
						fxInvalidFileData.setTimestampField(Timestamp.valueOf(details[3]));
					} else {
						fxInvalidFileData.setTimestampField(null);
					}

					fxInvalidFileData.setFileName(fileName);
					fxInvalidFileData.setDealAmount(details[4]);
					fxInvalidFileData.setReason(errordesc);
					invalidFileDatas.add(fxInvalidFileData);
					errordesc = "";
					valid = true;
				}
			}
			if (invalidFileDatas != null) {
				entityManagerFile.bulkSaveInvalid(invalidFileDatas);
			}

			if (fxFiles != null) {
				entityManagerFile.bulksave(fxFiles);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		
		} finally {
			fxFiles = null;
			invalidFileDatas = null;
		}

	}
}
