package com.yash.trainingctr.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.yash.trainingctr.models.TrainingCtr;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	static String[] HEADERs = { "S.No", "Emp ID", "Name", "EmailID", "Grade", "Resource Type", "Training Stack",
			"Training Name", "Purpose to attend training", "Training Duration(Days)", "Training Start Date",
			"Training End Date", "Current Skills", "Upgraded Skills", "Pre Assessment score", "Final Score",
			"Current Allocation", "Project","Current location", "Status", "Feedback /Remarks" };
	static String SHEET = "Consolidated_24";
	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<TrainingCtr> excelToTutorials(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<TrainingCtr> traningCtrs = new ArrayList<TrainingCtr>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				TrainingCtr ctrdata = new TrainingCtr();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						//ctrdata.setId(Math.round(currentCell.getNumericCellValue()));
						break;

					case 1:
						ctrdata.setEmp_id(Math.round(currentCell.getNumericCellValue()));
						break;

					case 2:
						ctrdata.setName(currentCell.getStringCellValue());
						break;

					case 3:
						ctrdata.setEmail_id(currentCell.getStringCellValue());
						break;
						
					case 4:
						ctrdata.setGrade(currentCell.getStringCellValue());
						break;

					case 5:
						ctrdata.setResource_type(currentCell.getStringCellValue());
						break;

					case 6:
						ctrdata.setTraining_stack(currentCell.getStringCellValue());
						break;

					case 7:
						ctrdata.setTraining_name(currentCell.getStringCellValue());
						break;

					case 8:
						ctrdata.setPurpose_to_attend_training(currentCell.getStringCellValue());
						break;

					case 9:
						ctrdata.setTraining_duration(Math.round(currentCell.getNumericCellValue()));
						break;

					case 10:
						ctrdata.setTraining_start_date(currentCell.getDateCellValue().toString());
						break;

					case 11:
						ctrdata.setTraining_end_date(currentCell.getDateCellValue().toString());
						break;

					case 12:
						ctrdata.setCurrent_skilss(currentCell.getStringCellValue());
						break;

					case 13:
						ctrdata.setUpgraded_skills(currentCell.getStringCellValue());
						break;

					case 14:
						ctrdata.setPre_assessment_score(Math.round(currentCell.getNumericCellValue()));
						break;

					case 15:
						ctrdata.setFinal_score(Math.round(currentCell.getNumericCellValue()));
						break;
						
					case 16:
						ctrdata.setCurrent_allocation(currentCell.getStringCellValue());
						break;
					case 17:
						ctrdata.setProject(currentCell.getStringCellValue());
						break;
					case 18:
						ctrdata.setCurrent_location(currentCell.getStringCellValue());
						break;
					case 19:
						ctrdata.setStatus(currentCell.getStringCellValue());
						break;
					case 20:
						ctrdata.setFeedback(currentCell.getStringCellValue());
						break;
	
					 default:
				            break;

				
					}

					cellIdx++;
				}

				traningCtrs.add(ctrdata);
			}

			workbook.close();

			return traningCtrs;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	


}
