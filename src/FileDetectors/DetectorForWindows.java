package FileDetectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
public class DetectorForWindows {
    
    public static void DetectFilesOnWindows(){
        try{
            URL url = new URL("http://localhost:9222/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String input;
            StringBuilder content = new StringBuilder();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((input = in.readLine()) != null){
                content.append(input);
            }

            in.close();
            connection.disconnect();

            System.out.println("Open Chrome Tabs:");
            System.out.println(content.toString());
            
        }catch (Exception e){
            e.printStackTrace();
        }
        

    }

    public static void main (String[] args){
        DetectFilesOnWindows();
    } 
}
