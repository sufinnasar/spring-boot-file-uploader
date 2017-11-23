package com.bloomberg.fx.web.rest;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bloomberg.fx.entity.FileStatus;
import com.bloomberg.fx.entity.FxFile;
import com.bloomberg.fx.entity.FxInvalidFileData;
import com.bloomberg.fx.repository.FxFileRepository;
import com.bloomberg.fx.repository.FxInvalidFileDataRepository;
import com.bloomberg.fx.service.SaveFileService;

@RestController
@RequestMapping("/api/file")
public class FileResource {

	private static final Logger logger = LoggerFactory.getLogger(FileResource.class);

	@Autowired
	SaveFileService saveFileService;

	@Autowired
	FxFileRepository fxFileRepository;

	@Autowired
	FxInvalidFileDataRepository fxInvalidFileDataRepository;
	
	  /**
     * POST  /upload : post the file data into the controller.
     *
     * @param file to get the MultipartFile data from the client
     * @return the FileStatus with status 200 (OK)
     */
	@PostMapping("/upload")
	private @ResponseBody FileStatus uploadFile(@RequestParam(value = "files[]", required = true) MultipartFile file) {
		String name = "";
		String fileString = null;
		StopWatch stopWatch = new StopWatch("My Stop Watch");
		stopWatch.start();

		if (!file.isEmpty()) {

			try {
				if(!file.getOriginalFilename().endsWith(".csv")){
					stopWatch.stop();
					return new FileStatus(stopWatch.getTotalTimeSeconds(), file.getOriginalFilename(),
							" File not imported.Please select a csv file");
				}
				name = FilenameUtils.removeExtension(file.getOriginalFilename());
				if (fxFileRepository.existsbyfileName(name)) {
					stopWatch.stop();
					return new FileStatus(stopWatch.getTotalTimeSeconds(), name,
							" Unsuccessfull Import .File already exists");
				} 
				
				else {

					byte[] bytes = file.getBytes();
					fileString = new String(bytes, StandardCharsets.UTF_8);
					List<String> fileDetailsList = Stream.of(fileString.split("\\r?\\n")).map(elem -> new String(elem))
							.collect(Collectors.toList());
					saveFileService.saveFileList(fileDetailsList, name);
				}

				stopWatch.stop();
			} catch (Exception e) {
				logger.error(e.getMessage());

			}
			return new FileStatus(stopWatch.getTotalTimeSeconds(), name, "File imported succesfully.");

		} else {
			return new FileStatus(stopWatch.getTotalTimeSeconds(), name, "File is Empty");
		}
	}

	 /**
     * GET  /search/valid-file-details : get a page of valid file details
     *
     * @param fileName Name of the file 
     * @return the FxFile with status 200 (OK) and the list of FxFile in body
     */
	@GetMapping("search/valid-file-details/{fileName}")
	private @ResponseBody List<FxFile> getValidFileDetails(@PathVariable String fileName) {
		return fxFileRepository.findByfileName(fileName);

	}

	/**
     * GET  /search/invalid-file-details : get a page of invalid file details
     *
     * @param fileName Name of the file 
     * @return the FxInvalidFileData with status 200 (OK) and the list of FxInvalidFileData in body
     */
	@GetMapping("search/invalid-file-details/{fileName}")
	private @ResponseBody List<FxInvalidFileData> getInValidFileDetails(@PathVariable String fileName) {

		return fxInvalidFileDataRepository.findByfileName(fileName);

	}
}
