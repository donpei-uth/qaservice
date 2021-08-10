package edu.uth.deidentifier.service;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import edu.uth.app.qac.QacApp;

@Service
public class QaService {

	StringBuffer reportText;

	public void getReport(String path) throws IOException {
		reportText = new StringBuffer();
		QacApp qacApp = new QacApp();
		String message = qacApp.validateThenToNIH(true, path, false);
		reportText.append(message + "\n");
		FileWriter reportFileWriter = new FileWriter(path + "/QA_REPORT.txt");
		reportFileWriter.write(reportText.toString());
		reportFileWriter.close();
	}

}
