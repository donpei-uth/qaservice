package edu.uth.deidentifier.api;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uth.deidentifier.dto.ReportReq;
import edu.uth.deidentifier.service.ArxService;
import edu.uth.deidentifier.service.QaService;

@RestController
// @CrossOrigin("http://localhost:4200")

public class CommonController {

	@Autowired
	ArxService arxService;
	@Autowired
	QaService qaService;

	@GetMapping("/api/test")
	public String info() throws ParseException {
		return "Test";
	}

	private void getReport(String path) throws ParseException, IOException {
		arxService.getReport(path);
	}

	@PostMapping("/api/report")
	public void report(@RequestBody ReportReq req) throws ParseException, IOException {
		getReport(req.getPath());
	}

	private void getReportQa(String path) throws ParseException, IOException {
		arxService.getReport(path);
	}

	@PostMapping("/api/reportqa")
	public void reportQa(@RequestBody ReportReq req) throws ParseException, IOException {
		getReportQa(req.getPath());
	}

}
