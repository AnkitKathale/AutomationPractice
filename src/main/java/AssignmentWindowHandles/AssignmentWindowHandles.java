package AssignmentWindowHandles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AssignmentWindowHandles {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        //2. enter search team
        driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("Selenium");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //3. wait for search results to be located
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='wikipedia-search-result-link']")));
        //4. Get list of elements

        List<WebElement> links = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']"));

        System.out.println("Number of search result links + " + links.size());

        //5. Loop through each link
        for(int i =0;i<links.size();i++){
            String mainWindow = driver.getWindowHandle();
            // Refetch the links due to DOM refresh possibility
            List<WebElement> currentLinks = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']/a"));
            currentLinks.get(i).click();

            //6.wait until a new window opens
            wait.until(driver1 -> driver1.getWindowHandles().size() > 1);

            //7. get all window handles and switch

            Set<String> st = driver.getWindowHandles();

            for (String s:st){
                if(!st.equals(mainWindow)){
                    driver.switchTo().window(s);

                    //8. wait for title to load

                    wait.until(ExpectedConditions.titleIs(driver.getTitle()));
                    String title = driver.getTitle();
                    System.out.println("TITLE +"+title);
                    System.out.println("WINDOW ID " + s);
                    // 9. Close the specific window if title matches
                    if (title.equals("Selenium (software) - Wikipedia")) {
                        System.out.println("Closing window with title: " + title);
                        driver.close();
                    } else {
                        //Optional: keep other windows open
                        driver.switchTo().window(mainWindow);
                    }


                }
            }
            // 10. Switch back to main window
            driver.switchTo().window(mainWindow);
        }








    }
}
