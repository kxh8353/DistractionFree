package FileDetectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DetectorForWindows {
    
    public static void DetectFilesOnWindows(){
        try{
            Process process = Runtime.getRuntime().exec("tasklisk");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main (String[] args){
        DetectFilesOnWindows();
    } 
}
