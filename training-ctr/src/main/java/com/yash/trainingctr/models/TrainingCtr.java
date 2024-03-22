package com.yash.trainingctr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class TrainingCtr {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	private long emp_id;

	
	private String name;
	

	private String email_id;

	private String grade;

	
	private String resource_type;
	
	private String training_stack;

	private String training_name;

	private String purpose_to_attend_training;

	private long training_duration;

	private String training_start_date;

	private String training_end_date;

	private String current_skilss;

	private String upgraded_skills;

	private long pre_assessment_score;

	private long final_score;

	private String current_allocation;

	private String project;
	
	private String current_location;

	private String status;

	private String feedback;

}