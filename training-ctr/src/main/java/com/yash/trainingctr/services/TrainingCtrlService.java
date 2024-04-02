package com.yash.trainingctr.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.trainingctr.helper.ExcelHelper;
import com.yash.trainingctr.models.TrainingCtr;
import com.yash.trainingctr.repo.TrainingCtrRepo;

@Service
public class TrainingCtrlService implements TrainingCtrI {

	@Autowired
	TrainingCtrRepo trainingRepository;

	public void save(MultipartFile file) {
		try {
			List<TrainingCtr> tutorial = ExcelHelper.excelToTutorials(file.getInputStream());
			trainingRepository.saveAll(tutorial);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<TrainingCtr> getAllTrainingCtr() {
		return trainingRepository.findAll();
	}

	@Override
	public List<TrainingCtr> getDataByYear(String year) {
		// TODO Auto-generated method stub
		return trainingRepository.findByTrainingStartDateContaining(year);
	}

}
