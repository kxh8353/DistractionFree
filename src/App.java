import Trainer.ArffExporter;
import WebsiteDetectorForMac.DetectorForMacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static ArrayList<String> uniqueSites = new ArrayList<>();

    public static void SelectOperatingSystem() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("what operating system do you use?");
        String answer = scanner.nextLine(); 
        if (answer.equals("Windows")|| answer.equals("windows")){
            // TODO
        }else if (answer.equals("Macbook")|| answer.equals("macbook")){
            uniqueSites = DetectorForMacs.DetectFilesOnMacs();
            // System.out.println(uniqueSites);
            System.out.println("Sites detected: " + uniqueSites.size());
            ArffExporter.exportToArff(uniqueSites, "Data/uniquesites.arff");
        }else{
            System.out.println("invalid request");
        }
        scanner.close();  
    }
    
    public static void SelectWebsitesToBlock() throws IOException{
        SelectOperatingSystem();

    }

    public static void main(String[] args) throws IOException{
        SelectWebsitesToBlock();
    }
}
