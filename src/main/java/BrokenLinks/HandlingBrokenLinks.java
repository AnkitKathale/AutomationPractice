package BrokenLinks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class HandlingBrokenLinks {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://www.deadlinkcity.com/");
        driver.manage().window().maximize();

        //Capture all links on page
        List<WebElement> li = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links on the page " + li.size());

        //Loop through each link and check if it is broken
        int brokenlinks = 0;
        for (WebElement e : li)
        {
            String hrefurl = e.getAttribute("href");
            if(hrefurl == null || hrefurl.isEmpty()){
                System.out.println("URL IS EMPTY");
                continue;
            }
            try{

                // hit url to the server and get the response code
                URL linkURL = new URL(hrefurl);// Convert string to URL
                HttpURLConnection httpURLConnect = (HttpURLConnection) linkURL.openConnection();// open connection
                httpURLConnect.connect();// connect to the URL
                // get response code
                int responseCode = httpURLConnect.getResponseCode();
                if(responseCode >= 400) {
                    System.out.println(hrefurl + " - " + responseCode + " - Broken Link");
                    brokenlinks++;
                }
                else {
                    System.out.println(hrefurl + " - " + responseCode + " - Valid Link");
                }

            }
            catch (Exception ef){
                System.out.println("EXCEPTION IS CAUGHT " + ef.getMessage());
            }
            System.out.println("TOTAL NUMBER OF BROKEN LINKS " + brokenlinks);
        }
    }
}
