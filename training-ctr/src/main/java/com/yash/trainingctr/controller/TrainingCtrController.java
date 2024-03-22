package com.yash.trainingctr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.trainingctr.helper.ExcelHelper;
import com.yash.trainingctr.messages.ResponseMessage;
import com.yash.trainingctr.models.TrainingCtr;
import com.yash.trainingctr.services.TrainingCtrI;

@CrossOrigin("*")
@RestController
@RequestMapping("/training-ctr")
public class TrainingCtrController {

	@Autowired
	TrainingCtrI tarinngCtr;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (file.isEmpty()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessage("Please select a file to upload"));
		}

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				tarinngCtr.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("")
	public ResponseEntity<List<TrainingCtr>> getAllTutorials() {
		try {
			List<TrainingCtr> ctrdatas = tarinngCtr.getAllTrainingCtr();

			if (ctrdatas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(ctrdatas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
