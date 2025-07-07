package FileDetectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DetectorForMacs {
    
    public static void DetectFilesOnMacs(){
        try {
            String[] cmd = {
                "osascript",
                "-e",
                "tell application \"System Events\" to get name of (processes where background only is false)"
            };

            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Open Applications:");

            while ((line = reader.readLine()) != null){
                for (String app: line.split(", ")){
                    System.out.println("- " + app.trim());
                }
            }
            process.waitFor();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(){
        DetectFilesOnMacs();
    }
}
