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
                "-e", "set window_list to every window",
                "-e", "set tab_list to {}",
                "-e", "repeat with the_window in window_list",
                "-e", "repeat with the_tab in every tab of the_window",
                "-e", "set the_url to URL of the_tab",
                "-e", "set the_title to title of the_tab",
                "-e", "if the_url contains \"google.com\" then",
                "-e", "set end of tab_list to the_title",
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
                // for (String app: line.split(", ")){
                //     for (String program: app.split(", ")){
                //         System.out.println("- " + program.trim());
                //     }
                //     System.out.println("- " + app.trim());
                // }
                System.out.println("- " + line.trim());
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
