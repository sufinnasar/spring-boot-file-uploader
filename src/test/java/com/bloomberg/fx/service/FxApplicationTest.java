package com.bloomberg.fx.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bloomberg.fx.FxApplication;
import com.bloomberg.fx.entity.FxFile;
import com.bloomberg.fx.entity.FxInvalidFileData;
import com.bloomberg.fx.repository.EntityManagerFile;
import com.bloomberg.fx.repository.FxFileRepository;
import com.bloomberg.fx.repository.FxInvalidFileDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FxApplication.class)
@Transactional
public class FxApplicationTest {
	

	@Autowired
	FxFileRepository rep;
	
	@Autowired
	FxInvalidFileDataRepository fxInvalidFileRepository;
	
	
	
    @Autowired
    private SaveFileService saveFileService;
    
    @Autowired
    private EntityManagerFile entityManagerFile;
    
    List<String> fileValidInvalidData;
    FxFile fxFile= null;
    FxInvalidFileData fxInvalidFile= null;
    List<FxFile> listFxFile = null;
    List<FxInvalidFileData> listInvalidFxFile = null;

    @Before
    public void init() {
    	listFxFile = new ArrayList<>();
    	fxFile = new FxFile();
		fxFile.setDealID("99957");
		fxFile.setFromCurrency("USD");
		fxFile.setToCurrency("IND");
		fxFile.setFileName("test");
		fxFile.setTimestampField(Timestamp.valueOf("2017-11-10 10:19:10"));
		fxFile.setDealAmount("90731");
		listFxFile.add(fxFile);
		
		listInvalidFxFile = new ArrayList<>();
		fxInvalidFile = new FxInvalidFileData();
		fxInvalidFile.setId((int) fxInvalidFileRepository.count()+1);
		fxInvalidFile.setDealID("99958");
		fxInvalidFile.setFromCurrency("USDa");
		fxInvalidFile.setToCurrency("IND");
		fxInvalidFile.setFileName("test");
		fxInvalidFile.setTimestampField(Timestamp.valueOf("2017-11-10 10:19:10"));
		fxInvalidFile.setDealAmount("90731");
		listInvalidFxFile.add(fxInvalidFile);
    	
    	
    	fileValidInvalidData = new ArrayList<String>();
    	fileValidInvalidData.add("99957,USD,IND,2017-11-21 10:19:10,90731");
    	fileValidInvalidData.add("99958,CAD,IND,2017-11-21 10:19:10,90732");
    	fileValidInvalidData.add("99959,IND,USD,2017-11-21 10:19:10,90733");
    	fileValidInvalidData.add("99960,USD,IND,2017-11-21 10:19:10,90734");
    	fileValidInvalidData.add("99961,USD,IND,2017-11-21 10:19:10,90734");
    	fileValidInvalidData.add("99962,USDa,IND,2017-11-21 10:19:10,90734");
    	fileValidInvalidData.add("99963,USD,IND,2017-11-21 10:19:10,90734");
    	fileValidInvalidData.add("99964,USD,IND,2017-11-21 10:19:10,90734a");
    	
    }
    
    @Test
    @Transactional
    public void assertThatDataInsertIntoDB() {
    	assertThat(true,is(entityManagerFile.bulksave(listFxFile)));
    	assertThat(true,is(saveFileService.checkFileExists("test")));
    	assertThat(false,is(saveFileService.checkFileExists("bad")));
    	assertThat(true,is(saveFileService.saveFileList(fileValidInvalidData, "test")));
    	
    }
    
    @Test
    @Transactional
    public void assertThatInvalidDataInsertIntoDB() {
    	assertThat(true,is(entityManagerFile.bulkSaveInvalid(listInvalidFxFile)));
   }


}
