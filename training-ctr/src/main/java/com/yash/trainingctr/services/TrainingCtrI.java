package com.yash.trainingctr.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.trainingctr.models.TrainingCtr;

public interface TrainingCtrI {
	
	public void save(MultipartFile file);
	List<TrainingCtr> getAllTrainingCtr();
	

}
