package Trainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArffExporter {
    
    public static void exportToArff(List <String> uniqueSites, String outputPath) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            writer.write("@relation website_classification\n\n");
            writer.write("@attribute hasEdu {0,1}\n");
            writer.write("@attribute hasGov {0,1}\n");
            writer.write("@attribute class {Academic}\n\n");
            writer.write("@data\n");
    
            for (String uniqueSite : uniqueSites) {
                int hasEdu = uniqueSite.contains(".edu") ? 1 : 0;
                int hasGov = uniqueSite.contains(".gov") ? 1 : 0;
                writer.write(String.format("%d,%d,Academic\n", hasEdu, hasGov));
            }
    
            System.out.println("ARFF file written to " + outputPath);
    
        } catch (IOException e) {
            System.err.println("Failed to write ARFF file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
