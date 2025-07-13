import FileDetectors.DetectorForMacs;
import FileDetectors.DetectorForWindows;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static ArrayList<String> uniqueSites = new ArrayList<>();

    public static void SelectOperatingSystem(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("what operating system do you use?");
        String answer = scanner.nextLine(); 
        if (answer.equals("Windows")|| answer.equals("windows")){
            DetectorForWindows.DetectFilesOnWindows();
        }else if (answer.equals("Macbook")|| answer.equals("macbook")){
            uniqueSites = DetectorForMacs.DetectFilesOnMacs();
        }else{
            System.out.println("invalid request");
        }
        scanner.close();  
    }
    
    public static void SelectWebsitesToBlock(){
        SelectOperatingSystem();
        
    }

    public static void main(String[] args){
        SelectWebsitesToBlock();
    }
}
