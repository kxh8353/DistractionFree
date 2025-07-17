package WebsiteDetectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class DetectorForMacs {

    public static ArrayList<String> WebsiteList = new ArrayList<>();
    
    public static ArrayList<String> DetectFilesOnMacs(){ 
        try {
            String[] SafariCMD = {
                "osascript",
                "-e", "set tab_list to {}",
                "-e", "tell application \"Safari\"",
                "-e", "repeat with w in windows",
                "-e", "repeat with t in tabs of w",
                "-e", "set the_url to URL of t",
                "-e", "set the_title to name of t",
                "-e", "if the_url contains \".com\" then",
                "-e", "copy the_title & \" | \" & the_url to end of tab_list",
                "-e", "end if",
                "-e", "end repeat",
                "-e", "end repeat",
                "-e", "end tell",
                "-e", "return tab_list"
            };

            Process process = Runtime.getRuntime().exec(SafariCMD);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // System.out.println("Open Tabs on Safari:");

            while ((line = reader.readLine()) != null){
                String[] SafariTabs = line.split(",\\s*");
                for (String SafariTab: SafariTabs){
                    String[] parts = SafariTab.split("\\s\\|\\s");
                    if (parts.length == 2){
                        String url = parts[1].trim();
                        String domain = extractDomainForSafari(url);
                        WebsiteList.add(domain);
                    }
                }
            }
            process.waitFor();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            String[] ChromeCMD = {
                "osascript",
                "-e", "set tab_list to {}",
                "-e", "tell application \"Google Chrome\"",
                "-e", "repeat with w in windows",
                "-e", "repeat with t in tabs of w",
                "-e", "set the_url to URL of t",
                "-e", "set the_title to name of t",
                "-e", "if the_url contains \".com\" then",
                "-e", "copy the_title & \" | \" & the_url to end of tab_list",
                "-e", "end if",
                "-e", "end repeat",
                "-e", "end repeat",
                "-e", "end tell",
                "-e", "return tab_list"
            };

            Process process = Runtime.getRuntime().exec(ChromeCMD);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // System.out.println("Open Tabs on Chrome:");

            while ((line = reader.readLine()) != null){
                String[] ChromeTabs = line.split(",\\s*");
                for (String ChromeTab: ChromeTabs){
                    String[] parts = ChromeTab.split("\\s\\|\\s");
                      if (parts.length == 2){
                         String url = parts[1].trim();
                         String domain = extractDomainForChrome(url);
                         WebsiteList.add(domain);
                      }
                }
            }
            process.waitFor();

        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<String>uniqueWebsites = removeDuplicateTabs(WebsiteList);
        return uniqueWebsites;
    }

    private static String extractDomainForSafari(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if (host == null) return "(unknown)";
            if (host.startsWith("www.")) host = host.substring(4);
    
            String[] parts = host.split("\\.");
            int len = parts.length;
            if (len >= 2) {
                return parts[len - 2] + "." + parts[len - 1];
            } else {
                return host;
            }
        } catch (Exception e) {
            return "(invalid URL)";
        }
    }

    private static String extractDomainForChrome(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if (host == null) return "(unknown)";
            if (host.startsWith("www.")) host = host.substring(4);
    
            String[] parts = host.split("\\.");
            int len = parts.length;
            if (len >= 2) {
                return parts[len - 2] + "." + parts[len - 1];
            } else {
                return host;
            } 
        } catch (Exception e) {
            return "(invalid URL)";
        }
    }

    public static ArrayList<String> removeDuplicateTabs(ArrayList<String> WebsiteList){
        return new ArrayList<>(new LinkedHashSet<>(WebsiteList));
    }

    public static void main(){
        DetectFilesOnMacs();
    }
}
