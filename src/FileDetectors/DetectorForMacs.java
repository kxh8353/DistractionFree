package FileDetectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class DetectorForMacs {
    
    public static void DetectFilesOnMacs(){
        try {
            String[] cmd = {
                "osascript",
                "-e", "tell application \"Safari\"",
                "-e", "set tab_list to {}",
                "-e", "repeat with w in windows",
                "-e", "repeat with t in tabs of w",
                "-e", "set the_url to URL of t",
                "-e", "set the_title to name of t",
                "-e", "if the_url contains \".com\" then",
                "-e", "copy the_title to end of tab_list",
                "-e", "end if",
                "-e", "end repeat",
                "-e", "end repeat",
                "-e", "return tab_list",
                "-e", "end tell"
            };

            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Open Tabs:");

            while ((line = reader.readLine()) != null){
                String[] websites = line.split(",\\s*");
                for (String website: websites){
                    System.out.println("- " + website.trim());
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
