import FileDetectors.DetectorForMacs;
import FileDetectors.DetectorForWindows;
import java.util.Scanner;

public class App {

    public static void SelectOperatingSystem(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("what operating system do you use?");
        String answer = scanner.nextLine();
        if (answer.equals("Windows")|| answer.equals("windows")){
            DetectorForWindows.DetectFilesOnWindows();
        }else if (answer.equals("Macbook")|| answer.equals("macbook")){
            DetectorForMacs.DetectFilesOnMacs();
        }else{
            System.out.println("invalid request");
        }
        scanner.close();
    }
    
    // public static void SelectWebsites(String[] args){
    //     System.out.println("Select Website(s) to Block:");
    // }

    public static void main(String[] args){
        SelectOperatingSystem();
    }
}
