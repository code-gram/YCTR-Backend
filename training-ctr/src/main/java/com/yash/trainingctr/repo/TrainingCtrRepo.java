package com.yash.trainingctr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.trainingctr.models.TrainingCtr;

public interface TrainingCtrRepo extends JpaRepository<TrainingCtr, Long> {
	
}
