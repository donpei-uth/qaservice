package edu.uth.deidentifier.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.ARXPopulationModel.Region;
import org.deidentifier.arx.risk.HIPAAIdentifierMatch;
import org.springframework.stereotype.Service;

@Service



public class ArxService {
	
	
	
	StringBuffer reportText;
	public void getReport(String path) throws IOException {
		reportText=new StringBuffer();
		reportText.append("ARX REPORT FOR "+path+" data files\n");
		try (Stream<Path> walk = Files.walk(Paths.get(path)); FileWriter reportFileWriter=new FileWriter(path+"/ARX_REPORT.txt");) {

			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(r -> {
				if (r.endsWith(".csv") && (r.indexOf("_DATA_")>-1 || r.indexOf("_data_")>-1)) {
					reportText.append("\n");
					try {
						Data data = Data.create(r, StandardCharsets.UTF_8, ',');
						DataHandle handle = data.getHandle();
						HIPAAIdentifierMatch[] warnings = handle.getRiskEstimator(ARXPopulationModel.create(Region.USA))
								.getHIPAAIdentifiers();
						printWarnings(warnings,r);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
			reportFileWriter.write(reportText.toString());

		}
	}

	private void printWarnings(HIPAAIdentifierMatch[] warnings, String filePath) {
		
		reportText.append("[ "+filePath+" ]\n");
		String leftAlignFormat = "| %-21s | %-32s | %-36s | %-16s | %19s |%n";
		String val = "";
		if (warnings.length == 0) {
			reportText.append("No warnings\n\n");
		} else {
			reportText.append("+-----------------------+----------------------------------+--------------------------------------+------------------+---------------------+\n");
			reportText.append("| COLUMN NAME           | IDENTIFIER                       | INSTANCE                             | MATCH TYPE       | VALUE / CONFIDENCE  |\n");
			reportText.append("+-----------------------+----------------------------------+--------------------------------------+------------------+---------------------+\n");

			for (HIPAAIdentifierMatch w : warnings) {

				if (w.getConfidence() != null) {
					val = String.valueOf(w.getConfidence() * 100) + "%";
				} else {
					val = w.getValue();
				}
				reportText.append(String.format(leftAlignFormat, w.getColumn(), w.getIdentifier(), w.getInstance(), w.getMatchType(),
						val));
				//System.out.format("+-----------------------+------------------------+------------------+------------------+---------------------+%n");
				// System.out.println(w.toString());
				// System.out.println(w.getConfidence());
			}
			reportText.append("+-----------------------+----------------------------------+--------------------------------------+------------------+---------------------+\n\n");
		}
		
	}

}
