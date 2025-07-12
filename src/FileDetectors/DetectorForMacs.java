package FileDetectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class DetectorForMacs {
    
    public static void DetectFilesOnMacs(){
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
            System.out.println("Open Tabs on Safari:");

            while ((line = reader.readLine()) != null){
                String[] tabs = line.split(",\\s*");
                for (String tab: tabs){
                    // System.out.println("- " + tab.trim());
                    String[] parts = tab.split("\\s\\|\\s");
                    if (parts.length == 2){
                        String title = parts[0].trim();
                        String url = parts[1].trim();
                        String domain = extractDomain(url);
                        System.out.println("- " + title + " | " + domain);
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
                "-e", "tell application \"Google Chrome\"",
                "-e", "set tab_list to {}",
                "-e", "repeat with w in windows",
                "-e", "repeat with t in tabs of w",
                "-e", "set the_url to URL of t",
                "-e", "set the_title to title of t",
                "-e", "if the_url contains \".com\" then",
                "-e", "try",
                "-e", "set domain_part to do shell script \"echo \" & quoted form of the_url & \" | sed -E 's|https?://([^/]+).*|\\\\1|'\"",
                "-e", "copy the_title & \" | \" & domain_part to end of tab_list",
                "-e", "end try",
                "-e", "end if",
                "-e", "end repeat",
                "-e", "end repeat",
                "-e", "return tab_list",
                "-e", "end tell"
            };

            Process process = Runtime.getRuntime().exec(ChromeCMD);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Open Tabs on Chrome:");

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

    private static String extractDomain(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if (host == null) return "(unknown)";
            if (host.startsWith("www.")) host = host.substring(4);
    
            // Only keep domain and TLD (e.g. facebook.com)
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

    public static void main(){
        DetectFilesOnMacs();
    }
}
