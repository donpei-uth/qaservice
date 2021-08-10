package edu.uth.deidentifier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.ARXPopulationModel.Region;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.risk.HIPAAIdentifierMatch;


public class Example35 {
    
    /**
     * Entry point
     * @param args
     * @throws IOException
     */
    public static void main_(String[] args) throws IOException {
    	 Data data1 = Data.create("/Users/ftiryaki/Downloads/Firat.csv", StandardCharsets.UTF_8, ',');
    	
    	Data.DefaultData data = createData();
        
        DataHandle handle = data1.getHandle();
        
        HIPAAIdentifierMatch[] warnings = handle.getRiskEstimator(ARXPopulationModel.create(Region.USA))
                                                .getHIPAAIdentifiers();
        
        printWarnings(warnings);
    }
    
    /**
     * Creates the data used in the example.
     * @return
     */
    private static Data.DefaultData createData() {
        Data.DefaultData data = Data.create();
        data.add("first name", "age", "gender", "code", "birth", "email-address", "SSN", "Bank", "Vehicle", "URL", "IP", "phone");
        data.add("Max", "34", "male", "81667", "2008-09-02", "", "123-45-6789", "GR16 0110 1250 0000 0001 2300 695", "", "http://demodomain.com", "8.8.8.8", "+49 1234566");
        data.add("Max", "45", "female", "81675", "2008-09-02", "user@arx.org", "", "", "WDD 169 007-1J-236589", "", "2001:db8::1428:57ab", "");
        data.add("Max", "66", "male", "89375", "2008-09-02", "demo@email.com", "", "", "", "", "", "");
        data.add("Max", "70", "female", "81931", "2008-09-02", "", "", "", "", "", "", "");
        data.add("Max", "34", "female", "81931", "2008-09-02", "", "", "", "", "", "", "");
        data.add("Max", "90", "male", "81931", "2008-09-02", "", "", "", "", "", "", "");
        data.add("Max", "45", "male", "81931", "2008-09-02", "", "", "", "", "", "", "");
        return data;
    }
    
    /**
     * Displays the found warnings.
     * @param warnings
     */
    private static void printWarnings(HIPAAIdentifierMatch[] warnings) {
    	
    	String leftAlignFormat = "| %-21s | %-22s | %-16s | %-16s | %19s |%n";
    	String val="";
    	if (warnings.length == 0) {
            System.out.println("No warnings");
        } else {
        	System.out.format("+-----------------------+------------------------+------------------+------------------+---------------------+%n");
        	System.out.format("| COLUMN NAME           | IDENTIFIER             | INSTANCE         | MATCH TYPE       | VALUE / CONFIDENCE  |%n");
        	System.out.format("+-----------------------+------------------------+------------------+------------------+---------------------+%n");
            
            for (HIPAAIdentifierMatch w : warnings) {
            	
            	if (w.getConfidence() != null) {
                    val=String.valueOf(w.getConfidence()*100)+"%";
                } else {
                	val=w.getValue();
                }
            	System.out.format(leftAlignFormat, w.getColumn(),w.getIdentifier(),w.getInstance(),w.getMatchType(),val);
            	System.out.format("+-----------------------+------------------------+------------------+------------------+---------------------+%n");
            	//System.out.println(w.toString());
                //System.out.println(w.getConfidence());
            }
            //System.out.format("+-----------------------+------------------------+------------------+------------------+---------------------+%n");
        }
    }
}
