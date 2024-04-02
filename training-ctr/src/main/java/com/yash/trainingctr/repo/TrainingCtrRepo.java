package com.yash.trainingctr.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yash.trainingctr.models.TrainingCtr;

public interface TrainingCtrRepo extends JpaRepository<TrainingCtr, Long> {
	
	
	
	@Query("SELECT t FROM TrainingCtr t WHERE SUBSTRING_INDEX(SUBSTRING_INDEX(t.training_start_date, ' ', -1), ' ', 1) = :year")
	List<TrainingCtr> findByTrainingStartDateContaining(@Param("year") String year);
	
}
